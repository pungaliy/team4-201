package TabsServlet;

import Methods.Magic;
import TabsStuff.TabsTotal;
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

@WebServlet(name= "TabsTotalList", urlPatterns = {"/TabsTotalList"})
public class TabsTotalList extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException{
		Magic magic = new Magic();
		Gson gson = new Gson();

		/*//TODO:remove
		User user1 = magic.searchByUserIDandRoomID("id1", "5566");
		request.getServletContext().setAttribute("user", user1);
		request.getServletContext().setAttribute("room", user1.getRoomID());*/

		User current = (User)request.getServletContext().getAttribute("user");
		String userID = current.getUserID();
		String roomID = current.getRoomID();

		ArrayList<TabsTotal> tabs = magic.getAllTabs(userID, roomID);
		ArrayList<TabsTotal> outputTabs = new ArrayList<TabsTotal>();
		for(TabsTotal t : tabs){
			String user1name = magic.searchByUserIDandRoomID(t.getUser1(), roomID).getFullName();
			String user2name = magic.searchByUserIDandRoomID(t.getUser2(), roomID).getFullName();
			TabsTotal n = new TabsTotal(user1name, user2name, t.getAmount());
			outputTabs.add(n);
			System.out.println("Adding tabs with roommate: " + user2name + " with amount " + t.getAmount());
		}

		String output = gson.toJson(outputTabs);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(output);
	}
}
