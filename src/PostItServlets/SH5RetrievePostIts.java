package PostItServlets;

import com.google.gson.Gson;
import db.NoteBase;
import db.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name= "SH5RetrievePostIts", urlPatterns = {"/message-board"})
public class SH5RetrievePostIts extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User u = (User) request.getServletContext().getAttribute("user");
        String json_str = new Gson().toJson(u);
        System.out.println("HI" + json_str);
        response.getWriter().print(json_str);
        String room = (String) request.getServletContext().getAttribute("room");
        NoteBase db = (NoteBase) request.getServletContext().getAttribute("notebase");
//        NoteBase db = new NoteBase();
        PrintWriter pw = response.getWriter();
        pw.print(db.retrieveNotes(room));
        System.out.println("Retrieving notes for room: " + room);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("html/message.html");
        view.forward(request, response);
        request.getServletContext().setAttribute("notebase", new NoteBase());
    }
}
