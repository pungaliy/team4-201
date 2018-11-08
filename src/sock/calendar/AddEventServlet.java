package sock.calendar;

import com.google.gson.Gson;
import sock.base.SockUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddEventServlet", urlPatterns = {"/AddEventServlet"})
public class AddEventServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String email = (String) request.getSession().getAttribute("email");

        //TODO: Retrieve the SockUser from the database instead of using dummy SockUser.
        SockUser sockUser = new SockUser(5);

        var temp = request.getParameterNames();
        var name = temp.nextElement();
        while (temp.hasMoreElements()) {
            name = temp.nextElement();
        }

        //convert the JSON data into a Event
        Event event = new Gson().fromJson(name, Event.class);

        //add the event to the user's calendar
        sockUser.getUserCalendar().addSockEvent(event);

        //TODO: Update the database and push updates to all clients.


        //make sure it worked. delete later.
        System.out.println(event);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
