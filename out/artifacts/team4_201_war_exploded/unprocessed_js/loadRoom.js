//stores a Java SockRoom object as a JS Object
var room = null;

function init() {
    //load the room
    $.post("/GetRoomServlet", function(response) {
        room = response;
    });
}

function print() {
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