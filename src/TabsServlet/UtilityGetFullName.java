package TabsServlet;

import Methods.Magic;
import TabsStuff.TabsItem;
import TabsStuff.TabsLedger;
import com.google.gson.Gson;
import db.Transaction;
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

@WebServlet(name= "UtilityGetFullName", urlPatterns = {"/UtilityGetFullName"})
public class UtilityGetFullName  extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

	}
}
