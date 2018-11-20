package Search;

import db.DataBase;
import db.Room;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name= "SearchServlet", urlPatterns = {"/search"})
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Searching");
        String q = request.getParameter("query");
        PrintWriter out = response.getWriter();
//        if(q.length() != 8) {
//            System.out.println("!=8!=8!=8");
//            out.print("!=8");
//            return;
//        }
        DataBase db = new DataBase();
        Room r = db.retrieveRoom(q);
        if(r == null) {
            System.out.println("DNEDNEDNE");
            out.print("dne");
            return;
        }
        System.out.println(r);
        System.out.println(r.getRoomStatus());
        out.print(r.getRoomStatus());
    }
}
