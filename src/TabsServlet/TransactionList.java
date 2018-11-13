package TabsServlet;

import Methods.Magic;
import TabsStuff.TabsItem;
import TabsStuff.TabsLedger;
import TabsStuff.Transaction;
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
			db.User user1 = new User("user1", "id1","5566");
			db.User user2 = new User("user2", "id2","5566");
			db.User user3 = new User("user3", "id3","5566");
			TabsItem fish = new TabsItem("Fish", 3, 30.0f);
			Vector<User> split = new Vector<User>();
			split.add(user1);
			split.add(user2);
			split.add(user3);
			TabsLedger fishLedger = new TabsLedger(fish,user1, split);
			//TODO: remove this

			String roomID = (String) request.getAttribute("room");

			ArrayList<Transaction> allTransaction = magic.getAllRelatedTransaction(user1);
			Gson gson = new Gson();
			String output = gson.toJson(allTransaction);
			PrintWriter out = response.getWriter();
			out.print(output);*/

		}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

	}

}
