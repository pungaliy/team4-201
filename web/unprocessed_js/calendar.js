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
            case CalendarMessageType.INIT:
                console.log("INIT Message received.");

                room = JSON.parse(message.jsonData);

                //convert every user's ArrayList<Event> into a FullCalendar compliant EventSource object
                for (i = 0; i < room.length; ++i) {
                    var user = room[i];
                    var events = user.events;

                    //the EventSource object that will hold all the FullCalendar Event
                    var eventSource = {
                        events: [],
                        color: 'blue'
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
                        // header: {
                        //     center: 'addEventButton',
                        // },
                        //
                        // customButtons: {
                        //     addEventButton: {
                        //         text: 'Add Events',
                        //         click: function() {
                        //
                        //         }
                        //     }
                        // }
                    });
                });

                //append checkboxes for toggling each calendar
                toggleEventMap = new Map();
                for (i = 0; i < room.length; ++i) {
                    var checkboxID = (String("toggle" + room[i].userID + "Calendar")).replace('@', '');

                    var checkboxHTML = "<input type='checkbox' id='" + checkboxID + "' onChange='toggleCalendars()'>";
                    $("#checkboxes").append(checkboxHTML);
                    toggleEventMap.set(room[i].userID, false);
                }
                break;
            case CalendarMessageType.ADD_EVENT:
                console.log("ADD_EVENT Message received");
                break;
            case CalendarMessageType.UPDATE:
                console.log("UPDATE Message received");
                break;
        }
    };

    calendarSocket.onclose = function (event) {
        console.log("Socket closed.");
    };
}

/**
 * Checks the toggled state for all checkboxes.
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
