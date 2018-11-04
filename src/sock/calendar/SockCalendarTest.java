package sock.calendar;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SockCalendarTest {
    /**
     * Basic test to make sure the SockCalendar can insert objects and SockEvent are sorted chronologically.
     */
    @Test
    void test0_getSockEvents() {
        SockCalendar sockCalendar = new SockCalendar();

        Calendar startDateTime1 = new GregorianCalendar(2018, 10, 3, 11, 0, 0);
        startDateTime1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        Calendar endDateTime1 = new GregorianCalendar(2018, 11, 3, 13, 0 , 0);
        endDateTime1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        SockEvent sockEvent1 = new SockEvent("Test Event 1", startDateTime1, endDateTime1);

        Calendar startDateTime2 = new GregorianCalendar(2018, 11, 3, 11, 0, 0);
        startDateTime2.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        Calendar endDateTime2 = new GregorianCalendar(2018, 11, 3, 13, 0 , 0);
        endDateTime2.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        SockEvent sockEvent2 = new SockEvent("Test Event 2", startDateTime2, endDateTime2);

        sockCalendar.getSockEvents().add(sockEvent1);
        sockCalendar.getSockEvents().add(sockEvent2);

        var iterator = sockCalendar.getSockEvents().iterator();
        assertEquals(sockEvent1, iterator.next());
        assertEquals(sockEvent2, iterator.next());
    }
}