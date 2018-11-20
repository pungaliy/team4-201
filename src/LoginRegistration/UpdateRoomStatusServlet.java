package LoginRegistration;

import com.google.gson.Gson;
import db.DataBase;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateRoomStatusServlet", urlPatterns = {"/update-status"})
public class UpdateRoomStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        String roomID = u.getRoomID();
        String status = request.getParameter("status");

        new DataBase().setRoomStatus(roomID, status);
        System.out.println("Set room " + roomID + "'s status to: " + status);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
