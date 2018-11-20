package PostItServlets;

import db.DataBase;
import db.Note;
import db.NoteBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name= "SH5AddNewPostIt", urlPatterns = {"/add_postit"})
public class SH5AddNewPostIt extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String xpos = request.getParameter("xpos");
        String ypos = request.getParameter("ypos");
        String text = request.getParameter("text");
        String idname = request.getParameter("idname");
        String room = (String) request.getSession().getAttribute("room");

        NoteBase db =  (NoteBase) request.getSession().getAttribute("notebase");
//        NoteBase db = new NoteBase();
        addNote(xpos, ypos, text, idname, room, db);
    }

    private void addNote(String xpos, String ypos, String text, String idname, String room, NoteBase db) {
        System.out.println("Adding: " + xpos + " " +  ypos + " " + text+ " " + idname + " " + room);
        Note note = new Note(text, Double.parseDouble(xpos), Double.parseDouble(ypos), idname, room);
        db.insertNote(note);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter pw = response.getWriter();
//        pw.print("hey");
//        System.out.println("hey");
    }
}
