package db;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;


public class NoteBase extends DataBase {

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

    public static void main(String [] args) {
        NoteBase db = new NoteBase();
        Note note  = new Note("contento", 1, 1, "noteid1", "roomid1");
        db.insertNote(note);
        System.out.println(db.retrieveNotes("roomid1"));
        note.setText("new note text");
        db.updateNote(note);
        System.out.println(db.retrieveNotes("roomid1"));
        db.deleteNote("noteid1");
    }
}
