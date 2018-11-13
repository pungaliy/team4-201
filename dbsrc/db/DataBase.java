package db;


import com.google.gson.Gson;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DataBase {
    protected  MongoClient mongoClient;
    protected MongoDatabase database;
    protected Gson gson;


    public DataBase() {
        mongoClient = MongoClients.create();
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        database = mongoClient.getDatabase("SuiteHome").withCodecRegistry(pojoCodecRegistry);
    }

    public ArrayList<User> retrieveUsers(String roomID) {
        MongoCollection<User> collection = database.getCollection("users", User.class);
        ArrayList<User> tmp = new ArrayList<>();
        for( User user : collection.find(eq("roomID", roomID))) {
            tmp.add(user);
        }
        return tmp;
    }

    public User retrieveUser(String userID) {
        MongoCollection<User> collection = database.getCollection("users", User.class);
        return collection.find(eq("userID", userID)).first();
    }

    public void addUser(User user) {
        MongoCollection<User> collection = database.getCollection("users", User.class);
        collection.insertOne(user);
    }

    public void addRoom(Room room) {
        MongoCollection<Room> collection = database.getCollection("rooms", Room.class);
        collection.insertOne(room);
    }

    public boolean roomExists(String roomID) {
        MongoCollection<Room> collection = database.getCollection("rooms", Room.class);
        if(collection.find(eq("roomID", roomID)).first() != null) {
            return true;
        }
        return false;
    }




//

//    public static void main(String[] args) {
//        DataBase db = new DataBase();
//        Room room = new Room("roomIDOne");
//        User user = new User("full name", "123", "roomID", "imgurl");
//        user.setImgURL("img");
//        db.addUser(user);
//        db.addRoom(room);
////        Note note = new Note("note content main", 3.22, 2.33, "note1","room2");
//        System.out.println(db.roomExists("roomIDOne"));
//        User newUser = db.retrieveUser("123");
//        System.out.println(newUser.getUserID());
//        System.out.println(db.retrieveUsers("roomID"));
////        String json = db.retrieveNotes("room2");
////        System.out.println(json);
//    }
}