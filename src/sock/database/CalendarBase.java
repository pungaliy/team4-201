package sock.database;

import com.mongodb.client.MongoCollection;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class CalendarBase extends DataBase {


    ArrayList<Event> retrieveEvents(String id) {
        MongoCollection<Event> collection = database.getCollection("event", Event.class);
        ArrayList<Event> tmp = new ArrayList<>();
        for( Event event : collection.find(eq("userID", id))) {
            tmp.add(event);
        }
        return tmp;
    }

    void addEvent(Event event) {
        MongoCollection<Event> collection = database.getCollection("events", Event.class);
        collection.insertOne(event);
    }
    void removeEvent(Event event) {
        MongoCollection<Event> collection = database.getCollection("events", Event.class);
        collection.deleteOne(eq("userID", event.getUserID()));
    }
}
