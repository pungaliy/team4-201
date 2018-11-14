package TabsStuff;

public class GroceryItem{
	private String roomID;
	private String itemName;
	private String add;

	public GroceryItem(String name, String roomID, String add){
		this.itemName = name;
		this.roomID = roomID;
	}

	public String getItemName(){
		return this.itemName;
	}
	public String getRoomID(){
		return this.roomID;
	}
	public String getAdd(){
		return add;
	}

}
