package Methods;

import FakeDB.FakeDB;
import TabsStuff.GroceryItem;
import TabsStuff.TabsItem;
import TabsStuff.TabsLedger;
import db.TabBase;
import db.Transaction;
import org.junit.platform.commons.util.StringUtils;
import temp.tempUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

public class Magic {
	/*
	* This is the place where magic happens
	* This class contains methods that handle logic.
	*/

	//private static FakeDB fakeDB = new FakeDB();
	private static TabBase db = new TabBase();

	public void addUser(db.User user){
		db.addUser(user);
	}

	public db.User searchByUserIDandRoomID(String userID, String roomID){
		ArrayList<db.User> allUsersInRoom = db.retrieveUsers(roomID);
		for(db.User u : allUsersInRoom){
			System.out.println(u.getUserID());
			if(userID.equals(u.getUserID())){
				System.out.println("FOUND");
				return u;
			}
		}
		return null;
	}

	public void addGrocery(String name, String roomID){
		db.GroceryItem grocery = new db.GroceryItem(roomID, name);
		db.addGroceryItem(grocery);
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

	public void addSingleTransction(String purchaser, String splitter, float amount, String roomID, String item){
		String ID = Long.toString(System.currentTimeMillis());
		Transaction t = new Transaction(ID, purchaser, splitter, amount, roomID, item);
		db.addTransaction(t);
	}

	public void addTransactionToAllSplitters(TabsLedger ledger){
		db.User purchaser = ledger.getPurchaser();
		float amount = ledger.getItemBought().getPricePerItem()
				* ledger.getItemBought().getQuantity()
				/ ledger.getSplitters().size();
		String roomID = purchaser.getRoomID();
		String item = ledger.getItemBought().getItemName();
		db.User splitter = null;
		for(db.User u : ledger.getSplitters()){
			splitter = u;
			if(!purchaser.getUserID().equals(splitter.getUserID())){
				addSingleTransction(purchaser.getUserID(), splitter.getUserID(), amount, roomID, item);
			}
		}
	}

	/*public float getTabsTotal(db.User user1, db.User user2){
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
	}*/

	public ArrayList<db.Transaction> getAllRelatedTransaction(db.User user){
		return db.retrieveTransactionsByUserInvolved(user.getUserID());
	}


}