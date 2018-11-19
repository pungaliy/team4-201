package db;

public class Transaction {
    private String transactionID;
    private String user1; //purchaser
    private String user2; //splitter
    private float amount;
    private String roomID;
    private String item;

    public Transaction() {}

    public Transaction(String transactionID, String user1, String user2, float amount, String roomID, String item) {
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
