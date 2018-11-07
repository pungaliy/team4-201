package LoginRegistration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SH5Registration")
public class SH5Registration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        String new_room = request.getParameter("new_room");
        String uid = (String) request.getServletContext().getAttribute("user_id");
        switch (new_room) {
            case "true": {
                String roomid = request.getParameter("roomid");
                if (roomid.equals("undefined")) {
                    pw.print("[\"reject\", \"Your room id is not valid\"]");
                }
                createNewRoom(roomid, uid);
                break;
            }
            case "false": {
                String roomid = request.getParameter("roomid");
                if (roomid.equals("undefined")) {
                    pw.print("[\"reject\", \"Your room id is not valid\"]");
                }
                joinRoom(roomid, uid);
                break;
            }
            default:
                pw.print("[\"reject\", \"Your submission is not valid\"]");
                break;
        }
    }

    private void createNewRoom(String roomid, String uid) {
        // Create the room object
        // Add the user to the room
    }

    private void joinRoom(String roomid, String uid) {
        // Join the room

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("html/_registration.html");
        view.forward(request, response);
    }
}
