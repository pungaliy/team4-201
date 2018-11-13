package Methods;

import FakeDB.FakeDB;
import TabsStuff.GroceryItem;
import TabsStuff.TabsItem;
import TabsStuff.TabsLedger;
import TabsStuff.Transaction;
import db.TabBase;
import temp.tempUser;

import java.util.ArrayList;
import java.util.Vector;

public class Magic {
	/*
	* This is the place where magic happens
	* This class contains methods that handle logic.
	*/

	//private static FakeDB fakeDB = new FakeDB();
	private static TabBase db = new TabBase();

	public void addGrocery(String name, String roomID){
		db.GroceryItem grocery = new db.GroceryItem(roomID, name);
		db.addGroceryItem(grocery);
		System.out.println("Add grocery: " + name + " for room " + roomID);
	}

	public void removeGrocery(String name, String roomID){
		ArrayList<db.GroceryItem> roomGrocery = db.retrieveGroceryItems(roomID);
		for(db.GroceryItem i : roomGrocery){
			if(i.getItemName().equals(name)){
				db.deleteGroceryItem(i);
			}
		}
	}

	public ArrayList<db.GroceryItem> getGroceryList(String roomID){
		return db.retrieveGroceryItems(roomID);
	}

	/*public void addSingleTransction(db.User purchaser, db.User splitter, float amount, String roomID, TabsItem item){
		Transaction t = new Transaction(purchaser, splitter, amount, roomID, item);
		fakeDB.addSingleTransaction(t);
	}

	public void addTransactionToAllSplitters(TabsLedger ledger){
		db.User purchaser = ledger.getPurchaser();
		float amount = ledger.getItemBought().getPricePerItem()
				* ledger.getItemBought().getQuantity()
				/ ledger.getSplitters().size();
		String roomID = purchaser.getRoomID();
		TabsItem item = ledger.getItemBought();
		db.User splitter = null;
		for(db.User u : ledger.getSplitters()){
			splitter = u;
			addSingleTransction(purchaser, splitter, amount, roomID, item);
		}
	}

	public float getTabsTotal(db.User user1, db.User user2){
		Vector<Transaction> toMinus = fakeDB.searchTransaction(user1, user2);
		Vector<Transaction> toAdd = fakeDB.searchTransaction(user2, user1);
		float amount = 0;
		for(Transaction t : toAdd ){
			amount += t.getAmount();
		}
		for(Transaction t : toMinus){
			amount -= t.getAmount();
		}

		return amount;
	}

	public Vector<Transaction> getAllRelatedTransaction(db.User user){
		return fakeDB.searchTransactionSingle(user);
	}

	public void printAllTransactions(){
		Vector<Transaction> all = fakeDB.getAllTransactions();
		for(Transaction t : all){
			System.out.println("Payer: " + t.getPurchaser().getFullName()
					+ " Owner: " + t.getSplitter().getFullName()
					+ " Item: " + t.getItemBought().getItemName()
					+ " Amount: " + t.getAmount());
		}
	}
*/
}
