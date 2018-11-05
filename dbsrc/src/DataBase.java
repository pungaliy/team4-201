package src;


//import com.google.gson.Gson;
//import com.mongodb.*;
//import com.mongodb.client.MongoClients;
////import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoDatabase;
//
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.MongoCollection;
//
//import org.bson.Document;
//
//import java.time.Clock;
//import java.util.Arrays;
//
//import com.mongodb.client.MongoCursor;
//import static com.mongodb.client.model.Filters.*;
//import com.mongodb.client.result.DeleteResult;
//import static com.mongodb.client.model.Updates.*;
//import com.mongodb.client.result.UpdateResult;
//import java.util.ArrayList;
//import java.util.List;
//
//
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.client.result.DeleteResult;
//import com.mongodb.client.result.UpdateResult;
//import org.bson.codecs.configuration.CodecRegistry;
//import org.bson.codecs.pojo.PojoCodecProvider;
//
//import java.util.List;
//
//import static com.mongodb.client.model.Filters.*;
//import static com.mongodb.client.model.Updates.*;
//import static java.util.Arrays.asList;
//import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
//import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
//
import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoClient;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import org.bson.codecs.configuration.CodecRegistry;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;
import static java.util.Arrays.asList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DataBase {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    Gson gson;

    public DataBase() {

//        mongoClient = MongoClients.create();
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClient mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .codecRegistry(pojoCodecRegistry)
//                .build();


//        MongoClient mongoClient = MongoClients.create(settings);
//        mongoClient = MongoClients.create();
        database = mongoClient.getDatabase("SuiteHome");//.withCodecRegistry(pojoCodecRegistry);
//        database = mongoClient.getDatabase("SuiteHome");
        gson = new Gson();
    }


    public void insertNote(Note note) {
        MongoCollection<Note> collection = database.getCollection("notes", Note.class);
        collection.insertOne(note);
    }

    public String retrieveNotes(String roomID) {
        String results = "";
        //navigate to notes collection
        MongoCollection<Note> collection = database.getCollection("notes", Note.class);
        //only get notes for a specific room, store results in cursor obj
        MongoCursor<Note> cursor = collection.find(eq("iDname", "room2")).iterator();

//        cursor.ToArray().ToJson();

        try {

            while (cursor.hasNext()) {
//                String tmp = cursor.next().toJson();
//                System.out.println(tmp);
//                results += tmp;
                cursor.next().print();
            }
        } finally {
            cursor.close();
        }
        return results;

    }

    public static void main(String [] args) {
        DataBase db  = new DataBase();

        Note note  = new Note("note content 2", "t", "s", "room2");
        db.insertNote(note);
        db.retrieveNotes("room2");
    }
}