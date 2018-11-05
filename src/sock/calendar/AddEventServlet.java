package sock.calendar;

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

        //get the SockUser...?
        //SockUser sockUser = new SockUser(5);
        //delete above ^^^

        System.out.println("Reached here.");

        var temp = request.getParameterNames();
        while (temp.hasMoreElements()) {
            System.out.println(temp.nextElement());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
