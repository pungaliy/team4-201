package db;

public class GroceryItem {
    private String roomID;
    private String itemName;

    public GroceryItem() {}

    public GroceryItem(String roomID, String itemName) {
        this.roomID = roomID;
        this.itemName = itemName;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
