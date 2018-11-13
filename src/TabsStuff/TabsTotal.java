package TabsStuff;

import temp.tempUser;

public class TabsTotal {
	private db.User user1;
	private db.User user2;
	private float amount;

	public TabsTotal(db.User user1, db.User user2, float amount){
		this.user1 = user1;
		this.user2 = user2;
		this.amount = amount;
	}

	public db.User getUser1() {
		return user1;
	}

	public db.User getUser2(){
		return user2;
	}

	public float getAmount(){
		return amount;
	}
}
