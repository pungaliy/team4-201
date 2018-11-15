package LoginRegistration;

import db.User;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SH5GetUserServlet", urlPatterns = {"/get-user"})
public class SH5GetUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Retrieving current user");
        User u = (User) request.getServletContext().getAttribute("user");
        System.out.println(u);
        while(u==null) {
            System.out.print("busy waiting ");
        };
        String json_str = new Gson().toJson(u);
        System.out.println("HI" + json_str);
        response.getWriter().print(json_str);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
