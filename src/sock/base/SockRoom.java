package sock.base;

import com.google.gson.Gson;
import sock.calendar.SockCalendar;
import sock.calendar.Event;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class SockRoom {
    private int roomID;

    private ArrayList<SockUser> roomUsers;

    private SockCalendar roomCalendar;

    public SockRoom(int roomID) {
        this.roomID = roomID;
        this.roomUsers = new ArrayList<>();
        this.roomCalendar = new SockCalendar();
    }

    public int getRoomID() {
        return roomID;
    }

    /**
     * Adds a SockUser to this SockRoom. Will not add duplicate SockUser.
     *
     * @param sockUser SockUser to be added to the SockRoom.
     * @return If adding the SockUser was successful.
     */
    public boolean addSockUser(SockUser sockUser) {
        if (!roomUsers.contains(sockUser)) {
            return roomUsers.add(sockUser);
        } else {
            return false;
        }
    }

    /**
     * Finds and returns the SockUser in this SockRoom with the specified userID.
     *
     * @param userID The userID of the SockUser. All userIDs should be unique.
     * @return The SockUser with the specified userID if it exists, else null.
     */
    public SockUser getSockUser(int userID) {
        for (SockUser sockUser : roomUsers) {
            if (sockUser.getUserID() == userID) {
                return sockUser;
            }
        }

        return null;
    }

    /**
     * DELETE THIS LATER.
     *
     * @param args
     */
    public static void main (String[] args) {
//        Calendar startDateTime1 = new GregorianCalendar(2018, 11, 5, 12, 0);
//        startDateTime1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
//        Calendar endDateTime1 = new GregorianCalendar(2018, 11, 5, 15, 0);
//        endDateTime1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
//        Event event1 = new Event("Event #1", startDateTime1, endDateTime1);
//
//        SockUser user1 = new SockUser(1);
//        user1.getUserCalendar().addSockEvent(event1);
//
//        SockRoom sockRoom = new SockRoom(1);
//        sockRoom.addSockUser(user1);
//
//        String json = new Gson().toJson(sockRoom);
//        PrintWriter printWriter = null;
//        try {
//            printWriter = new PrintWriter("A:\\Bub\\Desktop\\Calendar.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        printWriter.write(json);
//        printWriter.flush();
//        printWriter.close();
    }
}
