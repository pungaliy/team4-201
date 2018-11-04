package sock.base;

import sock.calendar.SockCalendar;

import java.util.ArrayList;

public class SockRoom {
    private int roomID;

    private ArrayList<SockUser> roomUsers;

    private SockCalendar roomCalendar;

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
}
