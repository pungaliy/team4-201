//stores a Java SockRoom object as a JS Object
var room = null;

function init() {
    //load the room
    $.get("/GetRoomServlet", function(response) {
        room = response;
    });
}

function _print() {
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
}

function _ajax() {
    var startDateTime = new Date(2018, 10, 6, 12, 0, 0, 0);
    var endDateTime = new Date(2018, 10, 6, 15, 0, 0, 0);
    addEvent("Event #1", startDateTime, endDateTime);
}

/**
 * Adds an event to the current user's calendar.
 *
 * @param eventSummary String that describes the event.
 * @param startDateTime JS Date object that holds the starting date and time.
 * @param endDateTime JS Date object that holds the ending date and time.
 */
function addEvent(eventSummary, startDateTime, endDateTime) {
    $.post("/AddEventServlet", {
        eventSummary: eventSummary,
        startDateTime: {
            year: startDateTime.getFullYear(),
            month: startDateTime.getMonth() + 1,
            dayOfMonth: startDateTime.getDate(),
            hourOfDay: startDateTime.getHours(),
            minute: startDateTime.getMinutes()
        },
        endDateTime: {
            year: endDateTime.getFullYear(),
            month: endDateTime.getMonth() + 1,
            dayOfMonth: endDateTime.getDate(),
            hourOfDay: endDateTime.getHours(),
            minute: endDateTime.getMinutes()
        }
    });
}