package TabsStuff;

import temp.tempUser;

public class Transaction {
	private tempUser purchaser;
	private tempUser splitter;
	private float amount;
	private int roomID;
	private TabsItem itemBought;
	private boolean isConfirmed;

	public Transaction(tempUser purchaser, tempUser splitter, float amount, int roomID, TabsItem item){
		this.isConfirmed = false;
		this.purchaser = purchaser;
		this.splitter = splitter;
		this.amount = amount;
		this.roomID = roomID;
		this.itemBought = item;
	}

	public tempUser getPurchaser() {
		return purchaser;
	}

	public float getAmount() {
		return amount;
	}

	public int getRoomID() {
		return roomID;
	}

	public TabsItem getItemBought() {
		return itemBought;
	}

	public tempUser getSplitter() {
		return splitter;
	}

	public boolean isConfirmed(){
		return isConfirmed;
	}
}
