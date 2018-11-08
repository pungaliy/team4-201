package sock.tests;

import org.junit.jupiter.api.Test;
import sock.calendar.Event;
import sock.calendar.SockCalendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SockCalendarTest {
//    /**
//     * Basic test to make sure the SockCalendar can insert objects and Event are sorted chronologically.
//     */
//    @Test
//    void test0_getSockEvents() {
//        SockCalendar sockCalendar = new SockCalendar();
//
//        Calendar startDateTime1 = new GregorianCalendar(2018, 10, 3, 11, 0, 0);
//        startDateTime1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
//        Calendar endDateTime1 = new GregorianCalendar(2018, 11, 3, 13, 0 , 0);
//        endDateTime1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
//        Event event1 = new Event("Test Event 1", startDateTime1, endDateTime1);
//
//        Calendar startDateTime2 = new GregorianCalendar(2018, 11, 3, 11, 0, 0);
//        startDateTime2.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
//        Calendar endDateTime2 = new GregorianCalendar(2018, 11, 3, 13, 0 , 0);
//        endDateTime2.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
//        Event event2 = new Event("Test Event 2", startDateTime2, endDateTime2);
//
//        sockCalendar.addSockEvent(event1);
//        sockCalendar.addSockEvent(event2);
//
//        var iterator = sockCalendar.getEvents().iterator();
//        assertEquals(event1, iterator.next());
//        assertEquals(event2, iterator.next());
//    }
}