package TabsStuff;

public class GroceryItem extends Item{
	private int roomID;
	private int groceryID;

	public GroceryItem(String name, int roomID, int groceryID){
		super(name);
		this.roomID = roomID;
		this.groceryID = groceryID;
	}

	public int getRoomID(){
		return this.roomID;
	}

	public int getGroceryID(){
		return this.groceryID;
	}
}
