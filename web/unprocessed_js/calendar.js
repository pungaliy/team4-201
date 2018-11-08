/**
 * Enum that represents the possible message types that the WebSocket can send/receive.
 *
 * @type {Readonly<{INIT: string, ADD_EVENT: string, UPDATE: string}>}
 */
var CalendarMessageType = Object.freeze({"INIT":"INIT", "ADD_EVENT":"ADD_EVENT", "UPDATE":"UPDATE"});

var calendarSocket;
var room;
var userID = "strawsnowrries@gmail.com";
var roomID = "691337";
var toggleEventMap;

function init() {
    //create a WebSocket to CalendarSocket
    calendarSocket = new WebSocket("ws://localhost:8080/CalendarSocket");

    /**
     * Send a request with type INIT. CalendarSocket should respond with a payload of all people in this user's room
     * from the database for initializing the FullCalendar with.
     *
     * @param event
     */
    calendarSocket.onopen = function (event) {
        console.log("CalendarSocket opened.");

        //structure the message in the form of a CalendarData object
        var message = {
            messageType: CalendarMessageType.INIT,
            userID: userID,
            roomID: roomID,
            jsonData: null
        };

        calendarSocket.send(JSON.stringify(message));
    };

    calendarSocket.onmessage = function (event) {
        var message = JSON.parse(event.data);
        switch (message.messageType) {
            case CalendarMessageType.INIT: //payload will be an ArrayList<PrimitiveUser>
                console.log("INIT Message received.");
                room = JSON.parse(message.jsonData);

                //convert every user's ArrayList<Event> into a FullCalendar compliant EventSource object
                for (i = 0; i < room.length; ++i) {
                    var user = room[i];
                    var events = user.events;

                    //the EventSource object that will hold all the FullCalendar Event
                    var eventSource = {
                        events: []//,
                        //color: 'blue'
                    };

                    //convert each Java Event object into a FullCalendar Event
                    for (j = 0; j < events.length; ++j) {
                        var moment = {
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

                    user.events = eventSource;
                }

                //load up the FullCalendar into #calendar
                $(function() {
                    $('#calendar').fullCalendar({
                        defaultView: 'agendaWeek',
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
                });

                //append checkboxes for toggling each calendar
                toggleEventMap = new Map();
                for (i = 0; i < room.length; ++i) {
                    var checkboxID = (String("toggle" + room[i].userID + "Calendar")).replace('@', '');

                    //TODO: Remove this when the actual HTML/CSS is set up.
                    $("#checkboxes").append("<p>" + room[i].userID + "</p>" + "<br>");
                    //END

                    var checkboxHTML = "<input type='checkbox' id='" + checkboxID + "' onChange='toggleCalendars()'>";
                    $("#checkboxes").append(checkboxHTML);
                    toggleEventMap.set(room[i].userID, false);
                }
                break;
            case CalendarMessageType.ADD_EVENT:
                console.log("ADD_EVENT Message received");
                console.log("THIS SHOULDN'T EVER BE REACHED...SOMETHING BAD HAPPENED");
                break;
            case CalendarMessageType.UPDATE: //payload will be a Java Event object
                console.log("UPDATE Message received");
                //TODO: HANDLE DEALING WITH THE JAVA EVENT OBJECT PAYLOAD...
                break;
        }
    };

    calendarSocket.onclose = function (event) {
        console.log("Socket closed.");
    };
}

function addEvent() {
    //TODO: Handle actually getting a date somehow when the add event form is implemented. Replace dummy Java Event object
    var event = {
        userID: userID,
        eventSummary: "Javascript Sent Event",
        startDateTime: {
            year: 2018,
            month: 10,
            dayOfMonth: 6,
            hourOfDay: 16,
            minute: 0
        },
        endDateTime: {
            year: 2018,
            month: 10,
            dayOfMonth: 6,
            hourOfDay: 17,
            minute: 0
        }
    };

    //create the CalendarMessage object to send
    var message = {
        messageType: CalendarMessageType.ADD_EVENT,
        userID: userID,
        roomID: roomID,
        jsonData: JSON.stringify(event)
    };

    //send the message
    calendarSocket.send(JSON.stringify(message));
}

/**
 * Checks the toggled state for all checkboxes and renders/unrenders events on the calendar based on the event data.
 */
function toggleCalendars() {
    var fullCalendar = $('#calendar').fullCalendar('getCalendar');

    for (i = 0; i < room.length; ++i) {
        var user = room[i];

        var checkboxID = (String("toggle" + room[i].userID + "Calendar")).replace('@', '');

        var checked = document.getElementById(checkboxID).checked;

        if (checked !== toggleEventMap.get(user.userID)) {
            if (checked) {
                fullCalendar.addEventSource(user.events);
            } else {
                fullCalendar.removeEventSource(user.events);
            }
            toggleEventMap.set(user.userID, checked);
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
