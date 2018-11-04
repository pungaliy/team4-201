package sock.calendar;

import java.util.SortedSet;
import java.util.TreeSet;

public class SockCalendar {
    /**
     * SortedSet that contains all the SockEvent for this SockCalendar, sorted by starting date from earliest to latest.
     */
    private SortedSet<SockEvent> sockEvents;

    public SockCalendar() {
        sockEvents = new TreeSet<>();
    }

    public SortedSet<SockEvent> getSockEvents() {
        return sockEvents;
    }
}
