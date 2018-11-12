package TabsStuff;

public class GroceryItem extends Item{
	private int roomID;

	public GroceryItem(String name, int roomID){
		super(name);
		this.roomID = roomID;
	}

	public int getRoomID(){
		return this.roomID;
	}

}
