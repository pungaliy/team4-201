package PostItServlets;

import db.Note;
import db.NoteBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SH5UpdatePostIt", urlPatterns = {"/update_postit"})
public class SH5UpdatePostIt extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idname = request.getParameter("idname");
        String xpos = request.getParameter("xpos");
        String ypos = request.getParameter("ypos");
        String text = request.getParameter("text");
        String room  = (String) request.getServletContext().getAttribute("room");

        System.out.print("Updating " + idname + " with: ");
        System.out.println(xpos + " " + ypos + " " + text);

        NoteBase db = (NoteBase) request.getServletContext().getAttribute("notebase");
        updateNote(xpos, ypos, text, idname, room, db);
    }

    private void updateNote(String xpos, String ypos, String text, String idname, String room, NoteBase db) {
        System.out.println("Adding: " + xpos + " " +  ypos + " " + text+ " " + idname + " " + room);
        Note note = new Note(text, Double.parseDouble(xpos), Double.parseDouble(ypos), idname, room);
        db.updateNote(note);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
