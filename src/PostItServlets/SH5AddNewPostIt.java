package PostItServlets;

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
        String room  = (String) request.getServletContext().getAttribute("room");
        addNote(xpos, ypos, text, idname, room);
    }

    private void addNote(String xpos, String ypos, String text, String idname, String room) {
        System.out.println("Adding: " + xpos + " " +  ypos + " " + text+ " " + idname + " " + room);
//        Note note = new Note(text, xpos, ypos, idname, room);
//        DataBase db = new DataBase();
//        db.insertNote(note);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        pw.print("hey");
        System.out.println("hey");
    }
}
