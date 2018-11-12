package LoginRegistration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SH5Registration", urlPatterns = {})
public class SH5Registration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        String new_room = request.getParameter("is_new_room");
        String roomid = request.getParameter("room_id");

        String uid = (String) request.getServletContext().getAttribute("uid");

        switch (new_room) {
            case "true": {
                String ret_string = roomIsValid(roomid);
                if (ret_string.equals("VALID")) {
                    createNewRoom(roomid, uid);
                    pw.print("[\"ACC\", \"Room created\"]");
                    request.getServletContext().setAttribute("room", roomid);
                } else {
                    pw.print(ret_string);
                }
                break;
            }
            case "false": {
                String ret_string = roomIsValid(roomid);
                if (ret_string.equals("VALID")) {
                    joinRoom(roomid, uid);
                    pw.print("[\"ACC\", \"Room joined\"]");
                    request.getServletContext().setAttribute("room", roomid);
                } else {
                    pw.print(ret_string);
                }
                break;
            }
            default:
                pw.print("[\"REJ\", \"Your submission is not valid\"]");
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

    private String roomIsValid(String roomid) {
        if (roomid.equals("undefined")) {
            return ("[\"REJ\", \"Your room id is not valid\"]");
        }
        else if (roomid.length() != 8) {
            return ("[\"REJ\", \"Your room id must be exactly 8 characters long\"]");
        }
        else if (!roomid.matches("[a-zA-Z0-9]+")) {
            return ("[\"REJ\", \"Your room id must contain only alphanumeric characters\"]");
        }
        return "VALID";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("html/_registration.html");
        view.forward(request, response);
    }
}
