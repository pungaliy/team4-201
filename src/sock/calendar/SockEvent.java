package sock.calendar;

import java.util.Calendar;
import java.util.Objects;

/**
 * SockEvent represents an event that is stored in Calendar objects. Each event has an eventSu
 *
 *
 * For Calendar objects, make sure to use GregorianCalendar objects.
 * Each Calendar object should have their timezone set, by calling .setTimeZone(TimeZone.getTimeZone(s)) on it, where s is a String that represents the time zone.
 * For PST timezone, it is "America/Los_Angeles", so you would call .setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"))
 * GregorianCalendar objects have a constructor that initializes it with year, month, day, hour, second values.
 */
public class SockEvent implements Comparable {
    private String eventSummary;
    private Calendar startDateTime;
    private Calendar endDateTime;

    /**
     * Constructor for a new SockEvent. All parameters must be not null.
     *
     * @param eventSummary Short title that describes the event.
     * @param startDateTime Calendar that represents the starting date and time.
     * @param endDateTime Calendar the represents the ending date and time.
     */
    public SockEvent(String eventSummary, Calendar startDateTime, Calendar endDateTime) {
        this.eventSummary = eventSummary;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String getEventSummary() {
        return eventSummary;
    }

    public Calendar getStartDateTime() {
        return startDateTime;
    }

    public Calendar getEndDateTime() {
        return endDateTime;
    }

    /**
     *  Compares if this SockEvent starts chronologically before o.
     *
     * @param o A SockEvent.
     * @return -1 if this SockEvent is before o, 1 if this SockEvent is after o.
     */
    @Override
    public int compareTo(Object o) {
        if (this == o) return 0;
        if (o == null || getClass() != o.getClass()) return 1;
        SockEvent sockEvent = (SockEvent) o;

        if (this.startDateTime.before(sockEvent.startDateTime)) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SockEvent sockEvent = (SockEvent) o;
        return Objects.equals(eventSummary, sockEvent.eventSummary) &&
                Objects.equals(startDateTime, sockEvent.startDateTime) &&
                Objects.equals(endDateTime, sockEvent.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventSummary, startDateTime, endDateTime);
    }

    @Override
    public String toString() {
        return ("eventSummary: " + eventSummary + ", " +
                "startDateTime: " + startDateTime.getTime() + ", " +
                "endDateTime: " +   endDateTime.getTime());
    }


}
