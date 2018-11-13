package FakeDB;

import TabsStuff.GroceryItem;
import TabsStuff.TabsItem;
import TabsStuff.TabsTotal;
import TabsStuff.Transaction;
import temp.tempRoom;
import temp.tempUser;

import java.util.Vector;

public class FakeDB {
	private Vector<db.User> users;
	private Vector<db.Room> rooms;
	private Vector<GroceryItem> groceryItems;
	private Vector<TabsItem> tabsItems;
	private Vector<Transaction> transactions;
	private Vector<TabsTotal> tabsTotals;

	public FakeDB(){
		users = new Vector<db.User>();
		rooms = new Vector<db.Room>();
		groceryItems = new Vector<GroceryItem>();
		tabsItems = new Vector<TabsItem>();
		transactions = new Vector<Transaction>();
		tabsTotals = new Vector<TabsTotal>();

	}

	public db.User SearchUserByEmail() {
		return SearchUserByEmail();
	}

	public db.User SearchUserByEmail(String email){
		for (db.User u : users) {
			if (u.getUserID().equals(email)){
				return u;
			}
		}
		return null;
	}

	public db.Room SearchRoomByID(String ID){
		for (db.Room r : rooms){
			if(r.getRoomID().equals(ID)){
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

	public Vector<Transaction> searchTransaction(db.User purchaser, db.User splitter){
		Vector<Transaction> toReturn = new Vector<Transaction>();
		for(Transaction t : transactions){
			if(t.getPurchaser().equals(purchaser) && t.getSplitter().equals(splitter)){
				toReturn.add(t);
			}
		}
		return toReturn;
	}

	public Vector<Transaction> searchTransactionSingle(db.User user){
		Vector<Transaction> toReturn = new Vector<Transaction>();
		for(Transaction t : transactions){
			if(t.getPurchaser().equals(user) || t.getSplitter().equals(user)){
				toReturn.add(t);
			}
		}
		return toReturn;
	}

}
