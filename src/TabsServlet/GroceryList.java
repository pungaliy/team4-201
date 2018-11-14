package TabsServlet;

import Methods.Magic;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import db.GroceryItem;

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

		System.out.println(jsonObj.get("add").getAsString()
				+ " " + jsonObj.get("itemName").getAsString()
				+ " " + jsonObj.get("roomID").getAsString());

		if(add.equals("Y")){
			magic.addGrocery(jsonObj.get("itemName").getAsString(), jsonObj.get("roomID").getAsString());
			doGet(request, response);
		} else {

		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		response.setContentType("application/json;charset=UTF-8");

		Magic magic = new Magic();

		//For testing
		request.setAttribute("room", "5566");
		//magic.addGrocery("Beef", "5566");
		//TODO: remove this

		String roomID = (String) request.getAttribute("room");

		ArrayList<db.GroceryItem> groceryList = magic.getGroceryList("5566");
		Gson gson = new Gson();
		String output = gson.toJson(groceryList);
		PrintWriter out = response.getWriter();
		out.print(output);
	}

}
