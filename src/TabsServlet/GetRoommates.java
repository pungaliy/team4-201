package TabsServlet;

import Methods.Magic;
import TabsStuff.TabsTotal;
import com.google.gson.Gson;
import db.Room;
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
		response.setContentType("application/json;charset=UTF-8");
		Magic magic = new Magic();
		Gson gson = new Gson();
		//TODO: for testing
		/*Room room = new Room("5566");
		User user1 = new User("name1", "id1", "5566", "url1");
		User user2 = new User("name2", "id2", "5566", "url2");
		User user3 = new User("name3", "id3", "5566", "url3");
		magic.addRoom(room);
		magic.addUser(user1);
		magic.addUser(user2);
		magic.addUser(user3);*.
		request.getServletContext().setAttribute("user", user1);
		request.getServletContext().setAttribute("room", user1.getRoomID());*/

		//TODO:remove
		User user1 = magic.searchByUserIDandRoomID("id1", "5566");
		request.getServletContext().setAttribute("user", user1);
		request.getServletContext().setAttribute("room", user1.getRoomID());

		User current = (User)request.getServletContext().getAttribute("user");
		String userID = current.getUserID();
		String roomID = current.getRoomID();

		ArrayList<User> roommates = magic.getRoommates(roomID);
		String output = gson.toJson(roommates);
		PrintWriter out = response.getWriter();
		out.print(output);

	}
}