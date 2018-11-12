package PostItServlets;

import db.NoteBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SH5DeletePostIt", urlPatterns = {"/delete_postit"})
public class SH5DeletePostIt extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idname = request.getParameter("idname");
        System.out.println("Deleting: " + idname);
        NoteBase db = (NoteBase) request.getServletContext().getAttribute("notebase");
        db.deleteNote(idname);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
