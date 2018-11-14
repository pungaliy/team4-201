/**
 * Enum that represents the possible message types that the WebSocket can send/receive.
 *
 * @type {Readonly<{INIT: string, ADD_EVENT: string, UPDATE: string}>}
 */
var CalendarMessageType = Object.freeze({"INIT":"INIT", "ADD_EVENT":"ADD_EVENT", "UPDATE":"UPDATE"});

/**
 * A JavaScript Array of Java PrimitiveUser.
 * The room itself and its room-wide calendar is also a PrimitiveUser.
 * Each PrimitiveUser's ArrayList<Event> events will be deleted after initialization.
 * ArrayList<Event> events will be replaced by a FullCalendar EventSource eventSource.
 * Each PrimitiveUser will then have two variables, userID and eventSource.
 */
var room;

/**
 * A JavaScript Map.
 * The Key is a PrimitiveUser's userID.
 * The Value is a boolean that tracks if the PrimitiveUser's EventSource is added or removed from the FullCalendar.
 * By default, the current user's calendar will be the only one added to the FullCalendar.
 */
var toggleEventMap;

/**
 * A JavaScript WebSocket that connects to "/CalendarSocket".
 */
var calendarSocket;

//TODO: Get actual userID and roomID of the current user on this page.
var userID = "strawsnowrries@gmail.com";
var roomID = "691337";

/**
 * calendarSocket's onopen function.
 *
 * Send an INIT request to the server to get the Java CalendarData necessary for this page.
 */
function onopen(event) {
    console.log("CalendarSocket opened.");

    //structure the message in the form of a Java CalendarData object
    var calendarData = {
        messageType: CalendarMessageType.INIT,
        userID: userID,
        roomID: roomID,
        jsonData: null
    };

    calendarSocket.send(JSON.stringify(calendarData));
}

/**
 * calendarSocket's onmessage function.
 * Handles receiving messages from the server of type INIT and UPDATE.
 *
 * INIT will initialize all the data for this page.
 * First, it will parse the Java CalendarData object and use it to initialize all the data for this page.
 * The Java CalendarData's jsonData will be a Java ArrayList<PrimitiveUser> for initializing the FullCalendar with.
 * Second, it will render the FullCalendar on #calendar.
 * Third, it will create and track checkboxes for toggling on and off each user's calendars.
 *
 * UPDATE will parse the Java CalendarData to handle an real-time update from the server.
 * The Java CalendarData's jsonData will be a Java Event, which will then be parsed into a FullCalendar Event and added to the corresponding user's EventSource.
 * The FullCalendar will then update itself after the new event has been added.
 *
 * @param event JSON.parse(event.data) will be a Java CalendarData object.
 */
function onmessage(event) {
    var calendarData = JSON.parse(event.data);
    switch (calendarData.messageType) {
        case CalendarMessageType.INIT:
            console.log("INIT Message received.");

            //initialize room to an array of PrimitiveUser, the actual room itself and its roomwide calendar will also be a PrimitiveUser
            room = JSON.parse(calendarData.jsonData);

            //convert every PrimitiveUser's ArrayList<Event> into a FullCalendar compliant EventSource object
            for (i = 0; i < room.length; ++i) {
                let primitiveUser = room[i];
                let events = primitiveUser.events;

                //the EventSource object that will hold all the FullCalendar Event
                let eventSource = {
                    events: []
                };

                //convert each Java Event object into a FullCalendar Event
                for (j = 0; j < events.length; ++j) {
                    let moment = {
                        title: events[j].eventSummary,
                        start: new Date(events[j].startDateTime.year,
                            events[j].startDateTime.month,
                            events[j].startDateTime.dayOfMonth,
                            events[j].startDateTime.hourOfDay,
                            events[j].startDateTime.minute, 0, 0),
                        end:   new Date(events[j].endDateTime.year,
                            events[j].endDateTime.month,
                            events[j].endDateTime.dayOfMonth,
                            events[j].endDateTime.hourOfDay,
                            events[j].endDateTime.minute, 0, 0)
                    };
                    eventSource.events.push(moment);
                }

                //create a new variable eventSource in each PrimitiveUser, then delete the ArrayList<Event> events since its no longer used
                primitiveUser.eventSource = eventSource;
                delete primitiveUser.events;
            }

            //append checkboxes for toggling each calendar to the HTML
            toggleEventMap = new Map();
            for (i = 0; i < room.length; ++i) {
                let primitiveUser = room[i];

                //make the id of each checkbox be "toggle___Calendar", where ___ is the PrimitiveUser's userID, but remove @ since its an illegal character
                let checkboxID = (String("toggle" + primitiveUser.userID + "Calendar")).replace('@', '');

                //TODO: Remove this when the actual HTML/CSS is set up.
                $("#checkboxes").append("<p>" + primitiveUser.userID + "</p>" + "<br>");
                //END

                //create the line of HTML that will be appended to #checkboxes; by default, show only the user's calendar initially
                let checkboxHTML = null;
                if (primitiveUser.userID === userID) {
                    checkboxHTML = "<input type='checkbox' id='" + checkboxID + "' onChange='toggleCalendars()' checked>";

                    //mark this user's calendar as showing since it is the actual user on the page
                    toggleEventMap.set(primitiveUser.userID, true);
                } else {
                    checkboxHTML = "<input type='checkbox' id='" + checkboxID + "' onChange='toggleCalendars()'>";

                    //initialize the PrimitiveUser's calendar to not show initially
                    toggleEventMap.set(primitiveUser.userID, false);
                }

                //add the checkbox to #checkboxes
                $("#checkboxes").append(checkboxHTML);
            }

            //load up the FullCalendar into the div with id="calendar"
            //WARNING: THIS BELOW METHOD IS ASYNCHRONOUS, BUT THE CONTENTS WILL BE EXECUTED IN SERIES.
            $(function() {
                $('#calendar').fullCalendar({
                    defaultView: 'agendaWeek',
                    //TODO: Remove these custom buttons, replace this initializer function with actual one.
                    header: {
                        center: 'addEventButton',
                    },
                    customButtons: {
                        addEventButton: {
                            text: 'Add Events',
                            click: addEvent
                        }
                    }
                });

                for (i = 0; i < room.length; ++i) {
                    let primitiveUser = room[i];
                    if (primitiveUser.userID === userID) {
                        $('#calendar').fullCalendar('addEventSource', primitiveUser.eventSource);
                    }
                }
            });
            break;
        case CalendarMessageType.ADD_EVENT:
            console.log("ADD_EVENT Message received...THIS SHOULD NEVER BE REACHED!!!");
            break;
        case CalendarMessageType.UPDATE: //payload will be a Java Event object
            console.log("UPDATE Message received");

            //convert the Java Event into a FullCalendar Event
            var javaEvent = JSON.parse(calendarData.jsonData);
            var javaEventUserID = javaEvent.userID;
            var fullCalendarEvent = {
                title: javaEvent.eventSummary,
                start: new Date(javaEvent.startDateTime.year,
                    javaEvent.startDateTime.month,
                    javaEvent.startDateTime.dayOfMonth,
                    javaEvent.startDateTime.hourOfDay,
                    javaEvent.startDateTime.minute, 0, 0),
                end:   new Date(javaEvent.endDateTime.year,
                    javaEvent.endDateTime.month,
                    javaEvent.endDateTime.dayOfMonth,
                    javaEvent.endDateTime.hourOfDay,
                    javaEvent.endDateTime.minute, 0, 0)
            };

            //find who this FullCalendar Event should belong to and add it to their events
            for (i = 0; i < room.length; ++i) {
                let primitiveUser = room[i];
                if (primitiveUser.userID === javaEventUserID) {
                    primitiveUser.eventSource.events.push(fullCalendarEvent);

                    if (toggleEventMap.get(primitiveUser.userID)) {
                        //reload the EventSource by removing it and re-adding it...
                        $('#calendar').fullCalendar("removeEventSource", primitiveUser.eventSource);
                        $('#calendar').fullCalendar("addEventSource", primitiveUser.eventSource);
                    }
                }
            }

            break;
    }
}

/**
 * calendarSocket's onclose function.
 */
function onclose(event) {
    console.log("Socket closed.");
}

/**
 * calendarSocket's onerror function.
 */
function onerror(event) {
    console.log("ERROR.");
}

function init() {
    //create a WebSocket to CalendarSocket and set its functions
    calendarSocket = new WebSocket("ws://localhost:8080/CalendarSocket");
    calendarSocket.onopen = onopen;
    calendarSocket.onmessage = onmessage;
    calendarSocket.onclose = onclose;
    calendarSocket.onerror = onerror;
}

/**
 * Handles adding an event to this user's calendar. This will take the event data that is submitted into the function,
 * then proceed to send it to the server. This will not actually update the FullCalendar. The server will process the input,
 * then proceed to send an UPDATE message to all appropriate FullCalendar. Updating will be handled in the UPDATE block in onmessage.
 */
function addEvent() {
    //TODO: Handle actually getting a date somehow when the add event form is implemented. Replace dummy Java Event object.
    let day = 11 + Math.floor(Math.random() * 7);
    let hour = Math.floor(Math.random() * 18);

    var event = {
        userID: userID,
        eventSummary: "Javascript Sent Event",
        startDateTime: {
            year: 2018,
            month: 10,
            dayOfMonth: day,
            hourOfDay: hour,
            minute: 0
        },
        endDateTime: {
            year: 2018,
            month: 10,
            dayOfMonth: day,
            hourOfDay: hour + Math.floor(Math.random() * 6),
            minute: 0
        }
    };

    //create and send the CalendarData object
    var calenderData = {
        messageType: CalendarMessageType.ADD_EVENT,
        userID: userID,
        roomID: roomID,
        jsonData: JSON.stringify(event)
    };
    calendarSocket.send(JSON.stringify(calenderData));
}

/**
 * Checks the toggled state for all checkboxes and renders/unrenders events on the calendar based on the event data.
 */
function toggleCalendars() {
    var fullCalendar = $('#calendar').fullCalendar('getCalendar');

    for (i = 0; i < room.length; ++i) {
        var primitiveUser = room[i];

        var checkboxID = (String("toggle" + primitiveUser.userID + "Calendar")).replace('@', '');
        var checked = document.getElementById(checkboxID).checked;

        if (checked !== toggleEventMap.get(primitiveUser.userID)) {
            if (checked) {
                fullCalendar.addEventSource(primitiveUser.eventSource);
            } else {
                fullCalendar.removeEventSource(primitiveUser.eventSource);
            }
            toggleEventMap.set(primitiveUser.userID, checked);
        }
    }
}

/**
 * Forces every calendar to display. Updates toggleEventMap appropriately, and checkboxes on the page.
 */
function setAllCalendars() {
    var fullCalendar = $('#calendar').fullCalendar('getCalendar');

    for (i = 0; i < room.length; ++i) {
        let primitiveUser = room[i];

        let checkboxID = (String("toggle" + room[i].userID + "Calendar")).replace('@', '');
        let checked = document.getElementById(checkboxID).checked;

        //turn on all the unchecked ones
        if (!checked) {
            fullCalendar.addEventSource(primitiveUser.eventSource);
            toggleEventMap.set(primitiveUser.userID, true);
            document.getElementById(checkboxID).checked = true;
        }
    }
}

/**
 * ArrayList<User> users = retrieveUsers(String roomid);
 for (User foo : users) {
	String userID = foo.getEmail();
	ArrayList<Event> userEvents = retrieveEvents(userID);

}
 Json object:
 userID
 Events[]

 JS:
 “INIT”:
 Create a connection, send a message “INIT” that contains USERID, ROOMID.

 “ADDEVENT”
 Send a message “ADDEVENT” that contains USERID, ROOMID, Event data.

 “UPDATE”
 Replace the old USERID+EVENTS object with the new one.

 Server:
 “INIT”
 Accept connection.
 Put the USERID, ROOMID, and SESSION into a Tuple.
 Send a message “INIT” that contains a list of USERID + EVENTS object. (Room + Users)

 “ADDEVENT”
 Receive a message “ADDEVENT”. Create an Event object. Add it to the database.
 Look through each Tuple. If ROOMID match, send an UPDATE message.

 “UPDATE”:
 Send a USERID + EVENTS object.

 method newUserAdded(roomID) {
	LOOK through TupleList for matching room IDs
	if roomID matches, then do an UPDATE and send the UserId + Events obj in.
}
 */
