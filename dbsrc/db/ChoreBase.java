package db;

import com.mongodb.client.MongoCollection;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class ChoreBase extends DataBase {
    public ArrayList<Chore> retrieveChores(String roomID) {
        MongoCollection<Chore> collection = database.getCollection("chores", Chore.class);

        ArrayList<Chore> tmp = new ArrayList<>();
        for(Chore chore : collection.find(eq("roomID", roomID))) {
            tmp.add(chore);
        }
        return tmp;
    }

    public void updateChore(Chore chore) {
        MongoCollection<Chore> collection = database.getCollection("chores", Chore.class);
        collection.replaceOne(eq("choreID", chore.getChoreID()), chore);
    }

    public void insertChore(Chore chore) {
        MongoCollection<Chore> collection = database.getCollection("chores", Chore.class);
        collection.insertOne(chore);
    }

    public void deleteChore(Chore chore) {
        MongoCollection<Chore> collection = database.getCollection("chores", Chore.class);
        collection.deleteOne(eq("choreID", chore.getChoreID()));
    }

//    public static void main(String [] args) {
//        ChoreBase cb = new ChoreBase();
//        Chore chore = new Chore("THis is a chore",100, 100, "user1","room1","chore1");
//        cb.insertChore(chore);
//        chore.setCompleted(true);
//        cb.updateChore(chore);
//
//    }
}
