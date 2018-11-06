package src;


import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.not;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static java.util.Arrays.asList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DataBase {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private Gson gson;


    public DataBase() {
        mongoClient = MongoClients.create();
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        database = mongoClient.getDatabase("SuiteHome").withCodecRegistry(pojoCodecRegistry);


    }


    public void insertNote(Note note) {
        MongoCollection<Note> collection = database.getCollection("notes", Note.class);
        collection.insertOne(note);
    }

    public String retrieveNotes(String roomID) {
        MongoCollection<Note> collection = database.getCollection("notes", Note.class);

        gson = new Gson();
        String json;
        ArrayList<Note> tmp = new ArrayList<>();
        for( Note note : collection.find(eq("roomid", roomID))) {
            tmp.add(note);
        }
        json = gson.toJson(tmp);
        return json;
    }

    public void deleteNote(String noteID) {
        MongoCollection<Note> collection = database.getCollection("notes", Note.class);
        collection.deleteOne(eq("noteid", noteID));
    }

    public void updateNote(Note note) {
        MongoCollection<Note> collection = database.getCollection("notes", Note.class);
        collection.replaceOne(eq("noteid", note.getNoteid()), note);

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