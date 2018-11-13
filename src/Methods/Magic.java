package Methods;

import FakeDB.FakeDB;
import TabsStuff.GroceryItem;
import TabsStuff.TabsItem;
import TabsStuff.TabsLedger;
import TabsStuff.Transaction;
import temp.tempUser;

import java.util.Vector;

public class Magic {
	/*
	* This is the place where magic happens
	* This class contains methods that handle logic.
	*/

	private static FakeDB fakeDB = new FakeDB();

	public void addGrocery(String name, int roomID){
		GroceryItem grocery = new GroceryItem(name, roomID);
		fakeDB.addGrocery(grocery);
	}

	public void removeGrocery(String name, int roomID){
		fakeDB.removeGrocery(name, roomID);
	}

	public Vector<GroceryItem> getGroceryList(int roomID){
		return fakeDB.getGroceryList(roomID);
	}

	public void addSingleTransction(tempUser purchaser, tempUser splitter, float amount, int roomID, TabsItem item){
		Transaction t = new Transaction(purchaser, splitter, amount, roomID, item);
		fakeDB.addSingleTransaction(t);
	}

	public void addTransactionToAllSplitters(TabsLedger ledger){
		tempUser purchaser = ledger.getPurchaser();
		float amount = ledger.getItemBought().getPricePerItem()
				* ledger.getItemBought().getQuantity()
				/ ledger.getSplitters().size();
		int roomID = purchaser.getRoomID();
		TabsItem item = ledger.getItemBought();
		tempUser splitter = null;
		for(tempUser u : ledger.getSplitters()){
			splitter = u;
			addSingleTransction(purchaser, splitter, amount, roomID, item);
		}
	}

	public float getTabsTotal(tempUser user1, tempUser user2){
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

	public void printAllTransactions(){
		Vector<Transaction> all = fakeDB.getAllTransactions();
		for(Transaction t : all){
			System.out.println("Payer: " + t.getPurchaser().getName()
					+ " Owner: " + t.getSplitter().getName()
					+ " Item: " + t.getItemBought().getItemName()
					+ " Amount: " + t.getAmount());
		}
	}

}
