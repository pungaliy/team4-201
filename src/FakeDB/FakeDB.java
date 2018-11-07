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

	public tempUser SearchUserByEmail(String email){
		for (tempUser u : users) {
			if (u.getEmail().equals(email)){
				return u;
			}
		}
		return null;
	}

	public tempRoom SearchRoomByID(int ID){
		for (tempRoom r : rooms){
			if(r.getRoomID() == ID){
				return r;
			}
		}
		return null;
	}

	public void addGrocery(GroceryItem g){
		groceryItems.add(g);
		System.out.println("Grocery added!");
	}

	public void removeGrocery(String name, int roomID){
		GroceryItem toRemove = null;
		for(GroceryItem g : groceryItems){
			if(g.getItemName().equals(name) && g.getRoomID() == roomID){
				toRemove = g;
			}
		}
		groceryItems.remove(toRemove);
	}

	public Vector<GroceryItem> getGroceryList(int roomID){
		Vector<GroceryItem> gList = new Vector<GroceryItem>();
		for(GroceryItem g : groceryItems){
			if(g.getRoomID() == roomID){
				gList.add(g);
			}
		}
		return gList;
	}

	public void addSingleTransaction(Transaction t){
		transactions.add(t);
	}

	public Vector<Transaction> getAllTransactions(){
		return transactions;
	}

}
