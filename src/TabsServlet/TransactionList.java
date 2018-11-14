package TabsServlet;

import Methods.Magic;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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

			//For testing
				request.setAttribute("room", "5566");
				request.setAttribute("userID", "id1");

			//TODO: remove this

			String roomID = (String) request.getAttribute("room");

			ArrayList<db.Transaction> allTransaction = magic.getAllRelatedTransaction("id1");

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

		System.out.println("Try to add transaction with this JSON: " + reqJson);

		JsonObject jsonObj = gson.fromJson(reqJson, JsonObject.class);

		String roomID = jsonObj.get("roomID").getAsString();
		String itemName = jsonObj.get("itemName").getAsString();
		int quantity = jsonObj.get("quantity").getAsInt();
		float pricePerItem = jsonObj.get("pricePerItem").getAsFloat();
		String purchaser = jsonObj.get("purchaser").getAsString();
		JsonArray splittersJson = jsonObj.get("splitters").getAsJsonArray();
		Vector<String> splitters = new Vector<String>();
		for(JsonElement s : splittersJson) {
			splitters.add(s.getAsString());
		}
		magic.addTransactionToAllSplitters(purchaser, quantity, pricePerItem, roomID, splitters, itemName);
		doGet(request, response);
	}

}
