package db;

import com.mongodb.client.MongoCollection;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;

public class TabBase extends DataBase {

    public ArrayList<GroceryItem> retrieveGroceryItems(String roomID) {
        MongoCollection<GroceryItem> collection = database.getCollection("groceryitems", GroceryItem.class);

        ArrayList<GroceryItem> tmp = new ArrayList<>();
        for(GroceryItem item : collection.find(eq("roomID", roomID))) {
            tmp.add(item);
        }
        return tmp;
    }

    public void addGroceryItem(GroceryItem item) {
        MongoCollection<GroceryItem> collection = database.getCollection("groceryitems", GroceryItem.class);
        collection.insertOne(item);

    }

    public void deleteGroceryItem(GroceryItem item) {
        MongoCollection<GroceryItem> collection = database.getCollection("groceryitems", GroceryItem.class);
        collection.deleteOne(eq("itemName", item.getItemName()));
    }

    ArrayList<Transaction> retrieveTransactionsByRoom(String roomID) {
        MongoCollection<Transaction> collection = database.getCollection("transactions", Transaction.class);

        ArrayList<Transaction> tmp = new ArrayList<>();
        for(Transaction transaction : collection.find(eq("roomID", roomID))) {
            tmp.add(transaction);
        }
        return tmp;
    }

    ArrayList<Transaction> retrieveTransactionsByUser1(String userID) {
        MongoCollection<Transaction> collection = database.getCollection("transactions", Transaction.class);

        ArrayList<Transaction> tmp = new ArrayList<>();
        for(Transaction transaction : collection.find(eq("user1" , userID))) {
            tmp.add(transaction);
        }
        return tmp;
    }

    ArrayList<Transaction> retrieveTransactionsByUser2(String userID) {
        MongoCollection<Transaction> collection = database.getCollection("transactions", Transaction.class);

        ArrayList<Transaction> tmp = new ArrayList<>();
        for(Transaction transaction : collection.find(eq("user2" , userID))) {
            tmp.add(transaction);
        }
        return tmp;
    }

    ArrayList<Transaction> retrieveTransactionsByUserInvolved(String userID) {
        MongoCollection<Transaction> collection = database.getCollection("transactions", Transaction.class);

        ArrayList<Transaction> tmp = new ArrayList<>();
        for(Transaction transaction : collection.find(or(eq("user1" , userID), eq("user2", userID)))) {
            tmp.add(transaction);
        }
        return tmp;

    }

    void addTransaction(Transaction transaction) {
        MongoCollection<Transaction> collection = database.getCollection("transactions", Transaction.class);
        collection.insertOne(transaction);

    }

    void deleteTransaction(Transaction transaction) {
        MongoCollection<Transaction> collection = database.getCollection("transactions", Transaction.class);
        collection.deleteOne(eq("transactionID", transaction.getTransactionID()));

    }
}
