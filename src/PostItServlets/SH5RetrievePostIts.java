package PostItServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name= "SH5RetrievePostIts", urlPatterns = {"/retrieve_postits"})
public class SH5RetrievePostIts extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //TODO: CHANGE THIS vvv
        request.getServletContext().setAttribute("room", "123456");
        //END
        String res = "[{\"text\": \"abc123\",\"xpos\": 20, \"ypos\": 20, \"idname\": \"idname1\"},{\"text\": \"abc123\",\"xpos\": 20, \"ypos\": 20, \"idname\": \"idname2\"}]";
        PrintWriter pw = response.getWriter();
        pw.print(res);
        System.out.println("retrieval successful");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String res = "{\"notes\": [{\"text\": \"abc123\",\"xpos\": 20, \"ypos\": 20, \"idname\": \"idname1\"},{\"text\": \"abc123\",\"xpos\": 20, \"ypos\": 20, \"idname\": \"idname2\"}]}";
//        PrintWriter pw = response.getWriter();
//        pw.print(res);
    }
}
