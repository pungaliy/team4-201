package LoginRegistration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SH5Login", urlPatterns = {"/login"})
public class SH5Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String access_token = request.getParameter("access_token");
        String uid = request.getParameter("user_id");
        String image = request.getParameter("image");
        String user_name = request.getParameter("first_name");
        String email = request.getParameter("email");

        request.getServletContext().setAttribute("access_token", access_token);
        request.getServletContext().setAttribute("user_id", uid);
        request.getServletContext().setAttribute("image", image);
        request.getServletContext().setAttribute("first_name", user_name);
        request.getServletContext().setAttribute("email", email);

        System.out.println(email);
        System.out.println(access_token);
        System.out.println(image);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("html/login.html");
        view.forward(request, response);
    }
}
