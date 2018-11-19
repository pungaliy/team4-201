package TabsServlet;

public class TabsTotal {
	private String user1;
	private String user2;
	private float amount;

	public TabsTotal(String user1, String user2, float amount){
		this.user1 = user1;
		this.user2 = user2;
		this.amount = amount;
	}

	public String getUser1() {
		return user1;
	}

	public String getUser2(){
		return user2;
	}

	public float getAmount(){
		return amount;
	}
}
