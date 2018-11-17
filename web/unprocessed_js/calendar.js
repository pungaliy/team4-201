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

var userID;
var roomID;

/**
 * A ton of colors to use for displaying each user's events...
 */
var colors = [
    "#f205e6" ,"#1c0365" ,"#21ad5a" ,"#f98b11","#651be6","#d298e2" ,"#d0210a",
    "#63b598", "#ce7d78", "#ea9e70", "#a48a9e", "#c6e1e8", "#648177" ,"#0d5ac1" ,
    "#d2737d" ,"#c0a43c" ,"#f2510e" ,"#a4e43f","#79806e" ,"#61da5e" ,"#cd2f00" ,
    "#9348af" ,"#01ac53" ,"#c5a4fb" ,"#996635","#b11573" ,"#4bb473" ,"#75d89e" ,
    "#2f3f94" ,"#2f7b99" ,"#da967d" ,"#34891f" ,"#b0d87b" ,"#ca4751" ,"#7e50a8" ,
    "#c4d647" ,"#e0eeb8" ,"#11dec1" ,"#289812" ,"#566ca0" ,"#ffdbe1" ,"#2f1179" ,
    "#935b6d" ,"#916988" ,"#513d98" ,"#aead3a", "#9e6d71", "#4b5bdc", "#0cd36d",
    "#250662", "#cb5bea", "#228916", "#ac3e1b", "#df514a", "#539397", "#880977",
    "#f697c1", "#ba96ce", "#679c9d", "#c6c42c", "#5d2c52", "#48b41b", "#e1cf3b",
    "#5be4f0", "#57c4d8", "#a4d17a", "#225b8", "#be608b", "#96b00c", "#088baf",
    "#f158bf", "#e145ba", "#ee91e3", "#05d371", "#5426e0", "#4834d0", "#802234",
    "#6749e8", "#0971f0", "#8fb413", "#b2b4f0", "#c3c89d", "#c9a941", "#41d158",
    "#fb21a3", "#51aed9", "#5bb32d", "#807fb", "#21538e", "#89d534", "#d36647",
    "#7fb411", "#0023b8", "#3b8c2a", "#986b53", "#f50422", "#983f7a", "#ea24a3",
    "#79352c", "#521250", "#c79ed2", "#d6dd92", "#e33e52", "#b2be57", "#fa06ec",
    "#1bb699", "#6b2e5f", "#64820f", "#1c271", "#21538e", "#89d534", "#d36647",
    "#7fb411", "#0023b8", "#3b8c2a", "#986b53", "#f50422", "#983f7a", "#ea24a3",
    "#79352c", "#521250", "#c79ed2", "#d6dd92", "#e33e52", "#b2be57", "#fa06ec",
    "#1bb699", "#6b2e5f", "#64820f", "#1c271", "#9cb64a", "#996c48", "#9ab9b7",
    "#06e052", "#e3a481", "#0eb621", "#fc458e", "#b2db15", "#aa226d", "#792ed8",
    "#73872a", "#520d3a", "#cefcb8", "#a5b3d9", "#7d1d85", "#c4fd57", "#f1ae16",
    "#8fe22a", "#ef6e3c", "#243eeb", "#1dc18", "#dd93fd", "#3f8473", "#e7dbce",
    "#421f79", "#7a3d93", "#635f6d", "#93f2d7", "#9b5c2a", "#15b9ee", "#0f5997",
    "#409188", "#911e20", "#1350ce", "#10e5b1", "#fff4d7", "#cb2582", "#ce00be",
    "#32d5d6", "#17232", "#608572", "#c79bc2", "#00f87c", "#77772a", "#6995ba",
    "#fc6b57", "#f07815", "#8fd883", "#060e27", "#96e591", "#21d52e", "#d00043",
    "#b47162", "#1ec227", "#4f0f6f", "#1d1d58", "#947002", "#bde052", "#e08c56",
    "#28fcfd", "#bb09b", "#36486a", "#d02e29", "#1ae6db", "#3e464c", "#a84a8f",
    "#911e7e", "#3f16d9", "#0f525f", "#ac7c0a", "#b4c086", "#c9d730", "#30cc49",
    "#3d6751", "#fb4c03", "#640fc1", "#62c03e", "#d3493a", "#88aa0b", "#406df9",
    "#615af0", "#4be47", "#2a3434", "#4a543f", "#79bca0", "#a8b8d4", "#00efd4",
    "#7ad236", "#7260d8", "#1deaa7", "#06f43a", "#823c59", "#e3d94c", "#dc1c06",
    "#f53b2a", "#b46238", "#2dfff6", "#a82b89", "#1a8011", "#436a9f", "#1a806a",
    "#4cf09d", "#c188a2", "#67eb4b", "#b308d3", "#fc7e41", "#af3101", "#ff065",
    "#71b1f4", "#a2f8a5", "#e23dd0", "#d3486d", "#00f7f9", "#474893", "#3cec35",
    "#1c65cb", "#5d1d0c", "#2d7d2a", "#ff3420", "#5cdd87", "#a259a4", "#e4ac44",
    "#1bede6", "#8798a4", "#d7790f", "#b2c24f", "#de73c2", "#d70a9c", "#25b67",
    "#88e9b8", "#c2b0e2", "#86e98f", "#ae90e2", "#1a806b", "#436a9e", "#0ec0ff",
    "#f812b3", "#b17fc9", "#8d6c2f", "#d3277a", "#2ca1ae", "#9685eb", "#8a96c6",
    "#dba2e6", "#76fc1b", "#608fa4", "#20f6ba", "#07d7f6", "#dce77a", "#77ecca"];

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
                    events: [],
                    color: colors[i]
                };

                //convert each Java Event object into a FullCalendar Event
                for (j = 0; j < events.length; ++j) {
                    let moment = {
                        title: primitiveUser.username + ": " + events[j].eventSummary,
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
                let checkboxMessage = "Toggle " + primitiveUser.username + "'s Calendar";

                //create the line of HTML that will be appended to #checkboxes; by default, show only the user's calendar initially
                let checkboxHTML = null;
                if (primitiveUser.userID === userID) {
                    checkboxHTML = "<label class='mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect check' for='" + checkboxID + "'>";
                    checkboxHTML +=     "<input type='checkbox' id='" + checkboxID + "' class='mdl-checkbox__input' onChange='toggleCalendars()' checked>";
                    checkboxHTML +=     "<span class='mdl-checkbox__label'>" + checkboxMessage + "</span>";
                    checkboxHTML +="</label>";

                    //mark this user's calendar as showing since it is the actual user on the page
                    toggleEventMap.set(primitiveUser.userID, true);
                } else {
                    checkboxHTML = "<label class='mdl-checkbox mdl-js-checkbox mdl-js-ripple-effect check' for='" + checkboxID + "'>";
                    checkboxHTML +=     "<input type='checkbox' id='" + checkboxID + "' class='mdl-checkbox__input' onChange='toggleCalendars()'>";
                    checkboxHTML +=     "<span class='mdl-checkbox__label'>" + checkboxMessage + "</span>";
                    checkboxHTML +="</label>";

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
                    defaultView: 'listWeek',
                    header: {
                        left: 'agendaDay,agendaWeek,listWeek,month',
                        // center: 'title',
                        right: 'prev,next'
                    },
                    height: "parent",
                    nowIndicator: true,
                    allDaySlot: false,
                    scrollTime: '08:00:00',
                    themeSystem: "bootstrap4",
                    buttonText: {
                        today: "Today",
                        agendaDay: "Day",
                        agendaWeek: "Week",
                        listWeek: "List",
                        month: "Month"
                    }
                });
                //https://bootswatch.com/4/yeti/bootstrap.min.css

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

            //find who this FullCalendar Event should belong to and add it to their events
            for (i = 0; i < room.length; ++i) {
                let primitiveUser = room[i];
                if (primitiveUser.userID === javaEventUserID) {
                    var fullCalendarEvent = {
                        title: primitiveUser.username + ": " + javaEvent.eventSummary,
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
    $.ajax({
        type: "POST",
        url: "/get-user",
        contentType: "application/json",
        async: false,
        success: function(response) {
            let user  = JSON.parse(response);
            userID = user.userID;
            roomID = user.roomID;
        }
    });

    //TODO: Remove the following two lines to fully enable SH5GetUserServlet.
    userID = "strawsnowrries@gmail.com";
    roomID = "691337";

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
    let eventForm = document.getElementById("eventForm");
    let summary = eventForm.elements.namedItem("summary").value;
    let startDateTime = new Date(eventForm.elements.namedItem("startDateTime").value);
    let endDateTime = new Date(eventForm.elements.namedItem("endDateTime").value);
    let whichCalendar = eventForm.elements.namedItem("whichCalendar").value === "user" ? userID : roomID;
    eventForm.reset();

    let event = {
        userID: whichCalendar,
        eventSummary: summary,
        startDateTime: {
            year: startDateTime.getFullYear(),
            month: startDateTime.getMonth(),
            dayOfMonth: startDateTime.getDate(),
            hourOfDay: startDateTime.getHours(),
            minute: startDateTime.getMinutes()
        },
        endDateTime: {
            year: endDateTime.getFullYear(),
            month: endDateTime.getMonth(),
            dayOfMonth: endDateTime.getDate(),
            hourOfDay: endDateTime.getHours(),
            minute: endDateTime.getMinutes()
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
