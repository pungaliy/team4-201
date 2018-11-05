package FakeDB;

import TabsStuff.GroceryItem;
import TabsStuff.TabsItem;
import TabsStuff.TabsTotal;
import TabsStuff.Transaction;
import temp.tempRoom;
import temp.tempUser;

import java.util.Vector;

public class FakeDB {
	private Vector<tempUser> users;
	private Vector<tempRoom> rooms;
	private Vector<GroceryItem> groceryItems;
	private Vector<TabsItem> tabsItems;
	private Vector<Transaction> transactions;
	private Vector<TabsTotal> tabsTotals;

	public FakeDB(){
		users = new Vector<tempUser>();
		rooms = new Vector<tempRoom>();
		groceryItems = new Vector<GroceryItem>();
		tabsItems = new Vector<TabsItem>();
		transactions = new Vector<Transaction>();
		tabsTotals = new Vector<TabsTotal>();

	}
}
