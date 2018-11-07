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

	public void getGroceryList(int roomID){
		Vector<GroceryItem> gList = fakeDB.getGroceryList(roomID);
		for(GroceryItem g : gList){
			System.out.println(g.getItemName());
		}
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

	public void printAllTransactions(){
		Vector<Transaction> all = fakeDB.getAllTransactions();
		for(Transaction t : all){
			System.out.println("Payer: " + t.getPurchaser()
					+ " Owner: " + t.getSplitter()
					+ " Item: " + t.getItemBought().getItemName()
					+ " Amount: " + t.getAmount());
		}
	}

}
