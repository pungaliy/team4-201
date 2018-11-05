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
}