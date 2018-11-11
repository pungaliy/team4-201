package sock.database;


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
    protected MongoClient mongoClient;
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
        for( User user : collection.find(eq("roomid", roomID))) {
            tmp.add(user);
        }
        return tmp;
    }





//    public static void main(String[] args) {
//        DataBase db = new DataBase();
//
//        Note note = new Note("note content main", 3.22, 2.33, "note1","room2");
//        db.insertNote(note);
//        String json = db.retrieveNotes("room2");
//        System.out.println(json);
//    }
}