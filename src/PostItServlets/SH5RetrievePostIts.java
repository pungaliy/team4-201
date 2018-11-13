package PostItServlets;

import db.NoteBase;

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

        //TODO: CHANGE THIS vvv
        request.getServletContext().setAttribute("room", "123455");
        //END

        String room = (String) request.getServletContext().getAttribute("room");
//        NoteBase db = (NoteBase) request.getServletContext().getAttribute("notebase");
        NoteBase db = new NoteBase();
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
