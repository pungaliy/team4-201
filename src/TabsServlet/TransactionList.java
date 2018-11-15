package TabsServlet;

import Methods.Magic;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import db.Room;
import db.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

@WebServlet(name= "TransactionList", urlPatterns = {"/TransactionList"})
public class TransactionList extends HttpServlet{
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
				IOException {
			response.setContentType("application/json;charset=UTF-8");
			Magic magic = new Magic();

			//TODO: For testing
			/*Room room = new Room("5566");
			User user1 = new User("name1", "id1", "5566", "url1");
			User user2 = new User("name2", "id2", "5566", "url2");
			User user3 = new User("name3", "id3", "5566", "url3");
			magic.addRoom(room);
			magic.addUser(user1);
			magic.addUser(user2);
			magic.addUser(user3);
			request.getServletContext().setAttribute("user", user1);
			request.getServletContext().setAttribute("room", user1.getRoomID());*/

			//TODO: remove
			User user1 = magic.searchByUserIDandRoomID("id1", "5566");
			request.getServletContext().setAttribute("user", user1);
			request.getServletContext().setAttribute("room", user1.getRoomID());

			User current = (User)request.getServletContext().getAttribute("user");
			String userID = current.getUserID();
			String roomID = current.getRoomID();

			System.out.println("Transaction get, roomID: " + roomID + " userID: " + userID);

			ArrayList<db.Transaction> allTransaction = magic.getAllRelatedTransaction(userID);

			try {
				ArrayList<db.Transaction> outputTransaction = new ArrayList<db.Transaction>();
				//Substituting userID with fullname of user
				for (db.Transaction t : allTransaction) {
					String user1Name = magic.searchByUserIDandRoomID(t.getUser1(), roomID).getFullName();
					String user2Name = magic.searchByUserIDandRoomID(t.getUser2(), roomID).getFullName();
					db.Transaction newT = new db.Transaction(t.getTransactionID(), user1Name, user2Name, t.getAmount(), t.getRoomID(), t.getItem());
					outputTransaction.add(newT);
				}
				Gson gson = new Gson();
				String output = gson.toJson(outputTransaction);
				PrintWriter out = response.getWriter();
				out.print(output);
			} catch (Exception e){
				e.printStackTrace();
			}
		}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		Magic magic = new Magic();
		Gson gson = new Gson();

		BufferedReader buffer = new BufferedReader(request.getReader());
		String reqJson = buffer.readLine();

		JsonObject jsonObj = gson.fromJson(reqJson, JsonObject.class);

		//TODO: For testing
		/*Room room = new Room("5566");
		User user1 = new User("name1", "id1", "5566", "url1");
		User user2 = new User("name2", "id2", "5566", "url2");
		User user3 = new User("name3", "id3", "5566", "url3");
		magic.addRoom(room);
		magic.addUser(user1);
		magic.addUser(user2);
		magic.addUser(user3);
		request.getServletContext().setAttribute("user", user1);
		request.getServletContext().setAttribute("room", user1.getRoomID());*/
		//TODO: remove
		User user1 = magic.searchByUserIDandRoomID("id1", "5566");
		request.getServletContext().setAttribute("user", user1);
		request.getServletContext().setAttribute("room", user1.getRoomID());

		User current = (User) request.getServletContext().getAttribute("user");
		String userID = current.getUserID();
		String roomID = current.getRoomID();

		System.out.println("Transaction post with values from attribute roomID: " + current.getRoomID() + " userID: " + current.getUserID());

		String itemName = jsonObj.get("itemName").getAsString();
		int quantity = jsonObj.get("quantity").getAsInt();
		float pricePerItem = jsonObj.get("pricePerItem").getAsFloat();
		String purchaser = jsonObj.get("purchaser").getAsString();
		roomID = jsonObj.get("roomID").getAsString();
		JsonArray splittersJson = jsonObj.get("splitters").getAsJsonArray();
		Vector<String> splitters = new Vector<String>();
		for(JsonElement s : splittersJson) {
			splitters.add(s.getAsString());
		}
		System.out.println("Transaction post with value from json roomID: " + roomID);
		magic.addTransactionToAllSplitters(purchaser, quantity, pricePerItem, roomID, splitters, itemName);
		doGet(request, response);
	}

}
