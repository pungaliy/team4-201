package sock.base;

import sock.calendar.SockCalendar;

public class SockUser {
    private int userID;
    private SockCalendar userCalendar;

    public SockUser(int userID) {
        this.userID = userID;
        this.userCalendar = new SockCalendar();
    }

    public int getUserID() {
        return userID;
    }

    public SockCalendar getUserCalendar() {
        return userCalendar;
    }
}
