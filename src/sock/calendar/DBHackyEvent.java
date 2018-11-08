package sock.calendar;

/**
 * Event represents an event that is stored in Calendar objects. Each event has an eventSu
 *
 *
 * For Calendar objects, make sure to use GregorianCalendar objects.
 * Each Calendar object should have their timezone set, by calling .setTimeZone(TimeZone.getTimeZone(s)) on it, where s is a String that represents the time zone.
 * For PST timezone, it is "America/Los_Angeles", so you would call .setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"))
 * GregorianCalendar objects have a constructor that initializes it with year, month, day, hour, second values.
 */
public class DBHackyEvent {
    private String userID;
    private String eventSummary;
    private String startDateTime; //stored as JSON of GregorianCalendar
    private String endDateTime; //stored as JSON of GregorianCalendar

    //TODO: MAKE LESS HACKY. STORE AS GregorianCalendar IF POSSIBLE.

    public DBHackyEvent() {

    }

    /**
     * Constructor for a new Event. All parameters must be not null.
     *
     * @param eventID The ID of this event.
     * @param eventSummary Short title that describes the event.
     * @param startDateTime JSON String of a GregorianCalendar that represents the starting date and time.
     * @param endDateTime JSON String of a GregorianCalendar the represents the ending date and time.
     */
    public DBHackyEvent(String eventID, String eventSummary, String startDateTime, String endDateTime) {
        this.userID = eventID;
        this.eventSummary = eventSummary;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEventSummary() {
        return eventSummary;
    }

    public void setEventSummary(String eventSummary) {
        this.eventSummary = eventSummary;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    //    /**
//     *  Compares if this Event starts chronologically before o.
//     *
//     * @param o A Event.
//     * @return -1 if this Event is before o, 1 if this Event is after o.
//     */
//    @Override
//    public int compareTo(Object o) {
//        if (this == o) return 0;
//        if (o == null || getClass() != o.getClass()) return 1;
//        Event sockEvent = (Event) o;
//
//        if (this.startDateTime.before(sockEvent.startDateTime)) {
//            return -1;
//        } else {
//            return 1;
//        }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Event sockEvent = (Event) o;
//        return Objects.equals(eventSummary, sockEvent.eventSummary) &&
//                Objects.equals(startDateTime, sockEvent.startDateTime) &&
//                Objects.equals(endDateTime, sockEvent.endDateTime);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(eventSummary, startDateTime, endDateTime);
//    }
//
//    @Override
//    public String toString() {
//        return ("eventSummary: " + eventSummary + ", " +
//                "startDateTime: " + startDateTime.getTime() + ", " +
//                "endDateTime: " +   endDateTime.getTime());
//    }


}
