package TabsServlet;

import Methods.Magic;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import db.GroceryItem;
import db.Room;
import db.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

@WebServlet (name= "GroceryList", urlPatterns = {"/GroceryList"})
public class GroceryList extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		Magic magic = new Magic();
		Gson gson = new Gson();
		BufferedReader buffer = new BufferedReader(request.getReader());
		String reqJson = buffer.readLine();
		JsonObject jsonObj = gson.fromJson(reqJson, JsonObject.class);
		String add = jsonObj.get("add").getAsString();
		String itemName = jsonObj.get("itemName").getAsString();

		//TODO: remove
		User user1 = magic.searchByUserIDandRoomID("id1", "5566");
		request.getServletContext().setAttribute("user", user1);
		request.getServletContext().setAttribute("room", user1.getRoomID());

		User current = (User) request.getServletContext().getAttribute("user");
		String roomID = current.getRoomID();

		System.out.println("Try to add grocery: " + itemName + " to room " + roomID);
		if(add.equals("Y")){
			magic.addGrocery(itemName, roomID);
		} else {
			magic.removeGrocery(itemName, roomID);

		}
		doGet(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		response.setContentType("application/json;charset=UTF-8");

		Magic magic = new Magic();

		//TODO: for testing
		/*Room room = new Room("5566");
		User user1 = new User("name1", "id1", "5566", "url1");
		User user2 = new User("name2", "id2", "5566", "url2");
		User user3 = new User("name3", "id3", "5566", "url3");
		magic.addRoom(room);
		magic.addUser(user1);
		magic.addUser(user2);
		magic.addUser(user3);*/
		//TODO: remove
		User user1 = magic.searchByUserIDandRoomID("id1", "5566");
		request.getServletContext().setAttribute("user", user1);
		request.getServletContext().setAttribute("room", user1.getRoomID());

		User current = (User) request.getServletContext().getAttribute("user");
		String roomID = current.getRoomID();
		System.out.println("RoomID in GroceryList Get: " + roomID);

		ArrayList<db.GroceryItem> groceryList = magic.getGroceryList(roomID);
		Gson gson = new Gson();
		String output = gson.toJson(groceryList);
		PrintWriter out = response.getWriter();
		out.print(output);
	}

}
