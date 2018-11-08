/**
 * Gets a Promise for the Room object.
 * @returns {*}
 */
function getRoomPromise() {
    return $.get("/GetRoomServlet");
}

/**
 * Test function. Delete later.
 *
 * @private
 */
function _print() {
    var roomPromise = getRoomPromise();

    roomPromise.success(function (room) {
        console.log("This room's id is " + room.roomID);

        for (i = 0; i < room.roomUsers.length; ++i) {
            console.log("\t" + "User " + room.roomUsers[i].userID);
            for (j = 0; j < room.roomUsers[i].userCalendar.sockEvents.length; ++j) {
                var event = room.roomUsers[i].userCalendar.sockEvents[j];
                console.log("\t\t" + "Event " + (j + 1));
                console.log("\t\t\t" + "Summary: " + event.eventSummary);
                console.log("\t\t\t" + "Start Date Time: " + event.startDateTime);
                console.log("\t\t\t" + "End Date Time: " + event.endDateTime);
            }
        }

        console.log();
    });
}

/**
 * Test function. Delete later.
 * @private
 */
function _ajax() {
    //JS Date objects have a month from 0-11, 10 would be November.
    var startDateTime = new Date(2018, 10, 6, 12, 0, 0, 0);
    var endDateTime = new Date(2018, 10, 6, 15, 0, 0, 0);
    addEvent("Event #1", startDateTime, endDateTime);
}

/**
 * Posts an event to the current user's calendar on the backend.
 *
 * @param eventSummary String that describes the event.
 * @param startDateTime JS Date object that holds the starting date and time.
 * @param endDateTime JS Date object that holds the ending date and time.
 */
function addEvent(eventSummary, startDateTime, endDateTime) {
    var event = {
        eventSummary: eventSummary,
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

    $.post("/AddEventServlet", JSON.stringify(event));
}

var showUser1Calendar = false;
var showUser2Calendar = false;

var eventsSourceUser1 = [{
    title: "Hello World!",
    start: moment(new Date(2018, 10, 6, 12, 0, 0, 0)),
    end: moment(new Date(2018, 10, 6, 15, 0, 0, 0))
}, {
    title: "Hello World!",
    start: moment(new Date(2018, 10, 8, 12, 0, 0, 0)),
    end: moment(new Date(2018, 10, 8, 18, 0, 0, 0))
}];
var eventsSourceUser2 = [{
    title: "User 2!",
    start: moment(new Date(2018, 10, 5, 9, 0, 0, 0)),
    end: moment(new Date(2018, 10, 5, 13, 0, 0, 0))
}, {
    title: "User 2!",
    start: moment(new Date(2018, 10, 8, 15, 0, 0, 0)),
    end: moment(new Date(2018, 10, 8, 16, 0, 0, 0))
}];

function toggleEvents() {
    var calendar = $('#calendar').fullCalendar('getCalendar');

    if ($('#user1').prop('checked') != showUser1Calendar) {
        if (!showUser1Calendar) {
            calendar.addEventSource(eventsSourceUser1);
        } else {
            calendar.removeEventSource(eventsSourceUser1);
        }
        showUser1Calendar = $('#user1').prop('checked');
    }
    if($('#user2').prop('checked') != showUser2Calendar) {
        if (!showUser2Calendar) {
            calendar.addEventSource(eventsSourceUser2);
        } else {
            calendar.removeEventSource(eventsSourceUser2);
        }
        showUser2Calendar = $('#user2').prop('checked');
    }
}



var showUser1Calendar = false;
var showUser2Calendar = false;

function onloadFunction() {
    //get the promise for the room data
    var roomPromise = getRoomPromise();

    //execute when the promise is fulfilled (the async request finished)
    roomPromise.then(function (room) {
        $(function() {
            $('#calendar').fullCalendar({
                defaultView: 'agendaWeek',

                header: {
                    center: 'addEventButton',
                },

                customButtons: {
                    addEventButton: {
                        text: 'Add Events',
                        click: function() {
                            //render the event on the calendar
                            //$('#calendar').fullCalendar('addEventSource', eventsSourceUser1);

                            //send the event info to the backend and add it to the SockUser
                            //addEvent(eventSummary, startDateTime, endDateTime);

                            alert('Success!');

                        }
                    }
                }
            });

        });
    });
}
