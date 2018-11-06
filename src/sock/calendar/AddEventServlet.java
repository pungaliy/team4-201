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

        //convert the JSON data into a SockEvent
        SockEvent sockEvent = new Gson().fromJson(name, SockEvent.class);

        //add the event to the user's calendar
        sockUser.getUserCalendar().addSockEvent(sockEvent);

        //TODO: Update the database and push updates to all clients.


        //make sure it worked. delete later.
        System.out.println(sockEvent);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
