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
		Magic magic = new Magic();
		Gson gson = new Gson();
		BufferedReader buffer = new BufferedReader(request.getReader());
		String reqJson = buffer.readLine();
		JsonObject jsonObj = gson.fromJson(reqJson, JsonObject.class);
		String add = jsonObj.get("add").getAsString();
		String itemName = jsonObj.get("itemName").getAsString();

		User current = (User) request.getServletContext().getAttribute("user");
		String roomID = current.getRoomID();

		System.out.println("Try to add grocery: " + itemName + " to room " + roomID);
		if(add.equals("Y")){
			magic.addGrocery(itemName, roomID);
		} else {
			magic.removeGrocery(itemName, roomID);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		response.setContentType("application/json");

		Magic magic = new Magic();

		User current = (User) request.getServletContext().getAttribute("user");
		String roomID = current.getRoomID();
		String userID = current.getUserID();
		System.out.println("RoomID in GroceryList Get: " + roomID + " retrieved by " + userID);

		ArrayList<db.GroceryItem> groceryList = magic.getGroceryList(roomID);
		Gson gson = new Gson();
		String output = gson.toJson(groceryList);
		PrintWriter out = response.getWriter();
		out.print(output);
	}

}
