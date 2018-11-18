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


@WebServlet(name= "GetRoommates", urlPatterns = {"/GetRoommates"})
public class GetRoommates extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("application/json");
		Magic magic = new Magic();
		Gson gson = new Gson();


		User current = (User)request.getSession().getAttribute("user");
		String userID = current.getUserID();
		String roomID = current.getRoomID();

		ArrayList<User> roommates = magic.getRoommates(roomID);
		String output = gson.toJson(roommates);
		System.out.println("Getting roommates for room: " + roomID + " for user: " + userID);
		System.out.println("Roommates are: " + output);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(output);

	}
}