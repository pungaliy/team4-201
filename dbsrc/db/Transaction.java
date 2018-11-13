package db;

public class Transaction {
    private String transactionID;
    private String user1;
    private String user2;
    private float amount;
    private String roomID;
    private GroceryItem item;

    public Transaction() {}

    public Transaction(String transactionID, String user1, String user2, float amount, String roomID, GroceryItem item) {
        this.transactionID = transactionID;
        this.user1 = user1;
        this.user2 = user2;
        this.amount = amount;
        this.roomID = roomID;
        this.item = item;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public GroceryItem getItem() {
        return item;
    }

    public void setItem(GroceryItem item) {
        this.item = item;
    }
}
