package TabsStuff;

import temp.tempUser;

public class Transaction {
	private db.User purchaser;
	private db.User splitter;
	private float amount;
	private String roomID;
	private TabsItem itemBought;
	private int transactionID;

	public Transaction(db.User purchaser, db.User splitter, float amount, String roomID, TabsItem item){
		this.purchaser = purchaser;
		this.splitter = splitter;
		this.amount = amount;
		this.roomID = roomID;
		this.itemBought = item;
	}

	public db.User getPurchaser() {
		return purchaser;
	}

	public float getAmount() {
		return amount;
	}

	public String getRoomID() {
		return roomID;
	}

	public TabsItem getItemBought() {
		return itemBought;
	}

	public db.User getSplitter() {
		return splitter;
	}

}
