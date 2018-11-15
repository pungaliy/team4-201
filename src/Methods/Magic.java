package Methods;

import TabsStuff.TabsTotal;
import db.TabBase;
import db.Transaction;
import db.User;

import java.util.ArrayList;
import java.util.Vector;

public class Magic {
	/*
	* This is the place where magic happens
	* This class contains methods that handle logic.
	*/

	private static TabBase db = new TabBase();

	public void addUser(db.User user){
		if(db.retrieveUser(user.getUserID()) != null){

		} else {
			db.addUser(user);
			System.out.println("Try to add user " + user.getUserID());
		}
	}

	public void addRoom(db.Room room){
		if(!db.roomExists(room.getRoomID())){
			db.addRoom(room);
			System.out.println("Try to add room " + room.getRoomID());
		}
	}

	public db.User searchByUserIDandRoomID(String userID, String roomID){
		ArrayList<db.User> allUsersInRoom = db.retrieveUsers(roomID);
		for(db.User u : allUsersInRoom){
			if(userID.equals(u.getUserID())){
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
		db.Transaction t = new db.Transaction(ID, purchaser, splitter, amount, roomID, item);
		db.addTransaction(t);
		System.out.println("Adding transaction: puchaser=" + purchaser + " splitter=" + splitter + " amount=" + amount + " item=" +item +" for room=" + roomID);
	}

	public void addTransactionToAllSplitters(String purchaser, int quantity, float pricePerItem, String roomID,
											 Vector<String> splitters, String itemName){
		float amount = pricePerItem * quantity / splitters.size();
		for(String u: splitters){
			if(!purchaser.equals(u)){
				addSingleTransction(purchaser,u, amount, roomID, itemName);
			}
		}
	}

	public ArrayList<TabsTotal> getAllTabs(String userID, String roomID){
		ArrayList<db.User> roommates = new ArrayList<User>();
		User current = db.retrieveUser(userID);
		ArrayList<TabsTotal> output = new ArrayList<TabsTotal>();
		for(User u : db.retrieveUsers(roomID)){
			if(!u.getUserID().equals(userID)){
				roommates.add(u);
			}
		}
		for(User u : roommates){
			TabsTotal t = new TabsTotal(userID, u.getUserID(), getTabsTotal(userID, u.getUserID()));
			output.add(t);
		}
		return output;
	}

	public float getTabsTotal(String user1, String user2){
		ArrayList<db.Transaction> roomTransaction = db.retrieveTransactionsByRoom(db.retrieveUser(user1).getRoomID());
		ArrayList<db.Transaction> toMinus = new ArrayList<Transaction>(); // 1, 2
		ArrayList<db.Transaction> toAdd = new ArrayList<Transaction>(); //2, 1
		for(Transaction t : roomTransaction){
			if(t.getUser1().equals(user1) && t.getUser2().equals(user2)){
				toMinus.add(t);
			} else if (t.getUser1().equals(user2) && t.getUser2().equals(user1)){
				toAdd.add(t);
			}
		}
		float amount = 0;
		for(Transaction t : toAdd ){
			amount += t.getAmount();
		}
		for(Transaction t : toMinus){
			amount -= t.getAmount();
		}
		return amount;
	}

	public ArrayList<db.Transaction> getAllRelatedTransaction(String user){
		return db.retrieveTransactionsByUserInvolved(user);
	}


}
