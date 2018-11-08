package sock.calendar;

import java.util.SortedSet;
import java.util.TreeSet;

public class SockCalendar {
    /**
     * SortedSet that contains all the Event for this SockCalendar, sorted by starting date from earliest to latest.
     */
    private SortedSet<Event> events;

    public SockCalendar() {
        events = new TreeSet<>();
    }

    public SortedSet<Event> getEvents() {
        return events;
    }

    public boolean addSockEvent(Event event) {
        return events.add(event);
    }
}
