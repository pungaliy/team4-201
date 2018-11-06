package sock.base;

import com.google.gson.Gson;
import sock.calendar.SockEvent;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@javax.servlet.annotation.WebServlet(name = "GetRoomServlet", urlPatterns = {"/GetRoomServlet"})
public class GetRoomServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //TODO: Replace with actual room data gotten from the database and using the HttpSession
        Calendar startDateTime1 = new GregorianCalendar(2018, 11, 5, 12, 0);
        startDateTime1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        Calendar endDateTime1 = new GregorianCalendar(2018, 11, 5, 15, 0);
        endDateTime1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        SockEvent event1 = new SockEvent("Event #1", startDateTime1, endDateTime1);

        SockUser user1 = new SockUser(1);
        user1.getUserCalendar().addSockEvent(event1);

        Calendar startDateTime2 = new GregorianCalendar(2018, 11, 7, 9, 0);
        startDateTime2.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        Calendar endDateTime2 = new GregorianCalendar(2018, 11, 7, 18, 0);
        endDateTime2.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        SockEvent event2 = new SockEvent("Event #2", startDateTime1, endDateTime1);

        SockUser user2 = new SockUser(2);
        user2.getUserCalendar().addSockEvent(event2);

        SockRoom sockRoom = new SockRoom(1);
        sockRoom.addSockUser(user1);
        sockRoom.addSockUser(user2);
        //END TO DO

        String json = new Gson().toJson(sockRoom);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        response.getWriter().flush();
    }
}
