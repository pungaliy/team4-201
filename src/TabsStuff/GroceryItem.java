package TabsStuff;

public class GroceryItem{
	private int roomID;
	private String itemName;

	public GroceryItem(String name, int roomID){
		this.itemName = name;
		this.roomID = roomID;
	}

	public String getItemName(){
		return this.itemName;
	}
	public int getRoomID(){
		return this.roomID;
	}

}
