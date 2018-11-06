package PostItServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SH5UpdatePostIt", urlPatterns = {"/update_postit"})
public class SH5UpdatePostIt extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idname");
        String xpos = request.getParameter("xpos");
        String ypos = request.getParameter("ypos");
        String text = request.getParameter("text");
        System.out.print("Updating " + id + " with: ");
        System.out.println(xpos + " " + ypos + " " + text);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
