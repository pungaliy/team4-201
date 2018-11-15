package LoginRegistration;

import com.google.gson.Gson;
import db.DataBase;
import db.Room;
import db.User;
//import org.omg.CORBA.Request;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "SH5Registration", urlPatterns = {"/registration"})
public class SH5Registration extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        PrintWriter pw = response.getWriter();
        String join = request.getParameter("join");
        String create = request.getParameter("create");

        String room_code = request.getParameter("room_code");

        System.out.println(String.format("Doing something with %s %s %s",join, create, room_code));

        if (create != null) {
            String ret_string = roomIsValid(room_code, true);
            if (ret_string.equals("VALID")) {
                createNewRoom(room_code, request);
                write(pw, "[\"ACC\", \"Room created\"]");
                request.getServletContext().setAttribute("room", room_code);
            } else {
                write(pw, ret_string);
            }
        } else if (join != null) {
            String ret_string = roomIsValid(room_code, false);
            if (ret_string.equals("VALID")) {
                joinRoom(room_code, request);
                write(pw, "[\"ACC\", \"Room joined\"]");
                request.getServletContext().setAttribute("room", room_code);
            } else {
                write(pw, ret_string);
            }
        } else {
            write(pw, "[\"REJ\", \"Your submission is not valid\"]");
        }
    }

    private void write(PrintWriter pw, String s) {
        String x = String.format("{\"values\":%s}", s);
        pw.print(x);
    }

    private void createNewRoom(String roomid, HttpServletRequest req) {
        DataBase db = new DataBase();
        db.addRoom(new Room(roomid));

        String username = (String) req.getServletContext().getAttribute("name");
        String image = (String) req.getServletContext().getAttribute("image");
        String email = (String) req.getServletContext().getAttribute("email");
        User u = new User(username, email, roomid, image);
        req.getServletContext().setAttribute("user", u);
        db.addUser(u);
    }

    private void joinRoom(String roomid, HttpServletRequest req) {
        DataBase db = new DataBase();

        String username = (String) req.getServletContext().getAttribute("name");
        String image = (String) req.getServletContext().getAttribute("image");
        String email = (String) req.getServletContext().getAttribute("email");
        User u = new User(username, email, roomid, image);
        req.getServletContext().setAttribute("user", u);
        db.addUser(u);
    }

    private String roomIsValid(String roomid, boolean create) {
        if (roomid.equals("undefined")) {
            return ("[\"REJ\", \"Your room id is not valid\"]");
        }
        else if (roomid.length() != 8) {
            return ("[\"REJ\", \"Your room id must be exactly 8 characters long\"]");
        }
        else if (!roomid.matches("[a-zA-Z0-9]+")) {
            return ("[\"REJ\", \"Your room id must contain only alphanumeric characters\"]");
        } else if (create && roomExists(roomid)) {
            return ("[\"REJ\", \"That room id already exists\"]");
        } else if (!create && !roomExists(roomid)) {
            return ("[\"REJ\", \"That room id doesn't exist\"]");
        }
        return "VALID";
    }

    private boolean roomExists(String roomid) {
        DataBase db = new DataBase();
        return db.roomExists(roomid);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("html/registration.html");
        view.forward(request, response);
    }
}
