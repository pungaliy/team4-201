package TabsStuff;

import temp.tempUser;

import java.util.Vector;

public class TabsLedger {
	private TabsItem itemBought;
	private db.User purchaser;
	private Vector<db.User> splitters;

	public TabsLedger(TabsItem item, db.User buy, Vector<db.User> split){
		this.itemBought = item;
		this.purchaser = buy;
		this.splitters = split;
	}

	public TabsItem getItemBought() {
		return itemBought;
	}

	public db.User getPurchaser() {
		return purchaser;
	}

	public Vector<db.User> getSplitters() {
		return splitters;
	}
}
