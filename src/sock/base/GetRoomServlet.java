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
        //dummy setup data...
        Calendar startDateTime1 = new GregorianCalendar(2018, 11, 5, 12, 0);
        startDateTime1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        Calendar endDateTime1 = new GregorianCalendar(2018, 11, 5, 15, 0);
        endDateTime1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        SockEvent event1 = new SockEvent("Event #1", startDateTime1, endDateTime1);

        SockUser user1 = new SockUser(1);
        user1.getUserCalendar().addSockEvent(event1);

        SockRoom sockRoom = new SockRoom(1);
        sockRoom.addSockUser(user1);
        //delete above eventually/replace...^^^

        String json = new Gson().toJson(sockRoom);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
