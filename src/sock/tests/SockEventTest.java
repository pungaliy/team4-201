package sock.tests;

import org.junit.jupiter.api.Test;
import sock.calendar.SockEvent;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SockEventTest {
    @Test
    void test0_getEventSummary() {
        Calendar startDateTime = new GregorianCalendar(2018, 11, 3, 11, 0, 0);
        startDateTime.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        Calendar endDateTime = new GregorianCalendar(2018, 11, 3, 13, 0 , 0);
        endDateTime.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        SockEvent sockEvent = new SockEvent("Test Event", startDateTime, endDateTime);

        assertEquals("Test Event", sockEvent.getEventSummary());
    }

    @Test
    void test0_getStartDateTime() {
        Calendar startDateTime = new GregorianCalendar(2018, 11, 3, 11, 0, 0);
        startDateTime.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        Calendar endDateTime = new GregorianCalendar(2018, 11, 3, 13, 0 , 0);
        endDateTime.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        SockEvent sockEvent = new SockEvent("Test Event", startDateTime, endDateTime);

        Calendar calendar = sockEvent.getStartDateTime();

        assertEquals(2018, calendar.get(Calendar.YEAR));
        assertEquals(11, calendar.get(Calendar.MONTH));
        assertEquals(3, calendar.get(Calendar.DAY_OF_MONTH));
        assertEquals(11, calendar.get(Calendar.HOUR_OF_DAY) );
        assertEquals(0, calendar.get(Calendar.MINUTE));
        assertEquals(0, calendar.get(Calendar.SECOND));
    }

    @Test
    void test0_getEndDateTime() {
        Calendar startDateTime = new GregorianCalendar(2018, 11, 3, 11, 0, 0);
        startDateTime.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        Calendar endDateTime = new GregorianCalendar(2018, 11, 3, 13, 0 , 0);
        endDateTime.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        SockEvent sockEvent = new SockEvent("Test Event", startDateTime, endDateTime);

        Calendar calendar = sockEvent.getEndDateTime();

        assertEquals(2018, calendar.get(Calendar.YEAR));
        assertEquals(11, calendar.get(Calendar.MONTH));
        assertEquals(3, calendar.get(Calendar.DAY_OF_MONTH));
        assertEquals(13, calendar.get(Calendar.HOUR_OF_DAY)); //HOUR is 0-12, HOUR_OF_DAY is 0-24
        assertEquals(0, calendar.get(Calendar.MINUTE));
        assertEquals(0, calendar.get(Calendar.SECOND));
    }

    @Test
    void test0_compareTo() {
        Calendar startDateTime1 = new GregorianCalendar(2018, 10, 3, 11, 0, 0);
        startDateTime1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        Calendar endDateTime1 = new GregorianCalendar(2018, 11, 3, 13, 0 , 0);
        endDateTime1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        SockEvent sockEvent1 = new SockEvent("Test Event", startDateTime1, endDateTime1);

        Calendar startDateTime2 = new GregorianCalendar(2018, 11, 3, 11, 0, 0);
        startDateTime2.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        Calendar endDateTime2 = new GregorianCalendar(2018, 11, 3, 13, 0 , 0);
        endDateTime2.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        SockEvent sockEvent2 = new SockEvent("Test Event", startDateTime2, endDateTime2);

        assertEquals(-1, sockEvent1.compareTo(sockEvent2));
    }

    @Test
    void test0_equals() {
        Calendar startDateTime1 = new GregorianCalendar(2018, 11, 3, 11, 0, 0);
        startDateTime1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        Calendar endDateTime1 = new GregorianCalendar(2018, 11, 3, 13, 0 , 0);
        endDateTime1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        SockEvent sockEvent1 = new SockEvent("Test Event", startDateTime1, endDateTime1);

        Calendar startDateTime2 = new GregorianCalendar(2018, 11, 3, 11, 0, 0);
        startDateTime2.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        Calendar endDateTime2 = new GregorianCalendar(2018, 11, 3, 13, 0 , 0);
        endDateTime2.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        SockEvent sockEvent2 = new SockEvent("Test Event", startDateTime2, endDateTime2);

        assertEquals(sockEvent1, sockEvent2);
    }

    @Test
    void test0_hashCode() {
        Calendar startDateTime1 = new GregorianCalendar(2018, 11, 3, 11, 0, 0);
        startDateTime1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        Calendar endDateTime1 = new GregorianCalendar(2018, 11, 3, 13, 0 , 0);
        endDateTime1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        SockEvent sockEvent1 = new SockEvent("Test Event", startDateTime1, endDateTime1);

        Calendar startDateTime2 = new GregorianCalendar(2018, 11, 3, 11, 0, 0);
        startDateTime2.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        Calendar endDateTime2 = new GregorianCalendar(2018, 11, 3, 13, 0 , 0);
        endDateTime2.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        SockEvent sockEvent2 = new SockEvent("Test Event", startDateTime2, endDateTime2);

        assertEquals(sockEvent1.hashCode(), sockEvent2.hashCode());
    }
}