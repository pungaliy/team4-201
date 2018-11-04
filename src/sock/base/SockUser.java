package sock.base;

import sock.calendar.SockCalendar;

public class SockUser {
    private int userID;

    private SockCalendar userCalendar;

    public SockUser() {
        this.userCalendar = new SockCalendar();
    }

    public int getUserID() {
        return userID;
    }

    public SockCalendar getUserCalendar() {
        return userCalendar;
    }
}
