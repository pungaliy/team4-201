package db;

public class Event {
    private String userID;
    private String eventSummary;
    private String startDateTime; //stored as JSON of GregorianCalendar
    private String endDateTime; //stored as JSON of GregorianCalendar

    //TODO: MAKE LESS HACKY. STORE AS GregorianCalendar IF POSSIBLE.

    public Event() {

    }

    /**
     * Constructor for a new Event. All parameters must be not null.
     *
     * @param eventID       The ID of this event.
     * @param eventSummary  Short title that describes the event.
     * @param startDateTime JSON String of a GregorianCalendar that represents the starting date and time.
     * @param endDateTime   JSON String of a GregorianCalendar the represents the ending date and time.
     */
    public Event(String eventID, String eventSummary, String startDateTime, String endDateTime) {
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

}

