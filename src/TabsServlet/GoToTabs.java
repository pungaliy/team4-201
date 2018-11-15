package TabsServlet;

import Methods.Magic;
import com.google.gson.Gson;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet(name= "GoToTabs", urlPatterns = {"/Tabs"})
public class GoToTabs extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("application/json");
		Magic magic = new Magic();
		Gson gson = new Gson();

		User current = (User)request.getSession().getAttribute("user");

		String output = gson.toJson(current);
		System.out.println("Returning user: " + output);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(output);

	}
}