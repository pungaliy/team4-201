package TabsStuff;

import temp.tempUser;

public class TabsTotal {
	private tempUser user1;
	private tempUser user2;
	private float amount;

	public TabsTotal(tempUser user1, tempUser user2, float amount){
		this.user1 = user1;
		this.user2 = user2;
		this.amount = amount;
	}

	public tempUser getUser1() {
		return user1;
	}

	public tempUser getUser2(){
		return user2;
	}

	public float getAmount(){
		return amount;
	}
}
