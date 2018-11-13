package TabsServlet;

import Methods.Magic;
import TabsStuff.GroceryItem;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

@WebServlet (name= "RetriveGroceryList", urlPatterns = {"/tabs"})
public class RetriveGroceryList extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Magic magic = new Magic();

		//For testing
		request.setAttribute("room", "5566");
		magic.addGrocery("Beef", 5566);
		//TODO: remove this

		String room = (String) request.getAttribute("room");
		int roomID = Integer.parseInt(room);

		Vector<GroceryItem> groceryList = magic.getGroceryList(roomID);
		Gson gson = new Gson();
		String output = gson.toJson(groceryList);

		request.setAttribute("groceryList", output);
		/*response.setContentType("application/json;charset=UTF-8");
		ServletOutputStream out = response.getOutputStream();
		out.print(output);*/
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		doPost(request, response);
	}

}
