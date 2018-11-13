package TabsServlet;

import Methods.Magic;
import TabsStuff.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

			//TODO: remove this

			String room = (String) request.getAttribute("room");
			int roomID = Integer.parseInt(room);

			//Get a user here
			//Vector<Transaction> allTransaction = magic.getAllRelatedTransaction(user);

		}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

	}

}
