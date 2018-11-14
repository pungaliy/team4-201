package sock.calendar;

import com.google.gson.Gson;
import sock.database.DataBase;
import sock.database.EventBase;
import sock.database.User;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint(value = "/CalendarSocket")
public class CalendarSocket {
    enum CalendarMessageType {
        INIT(0), ADD_EVENT(1), UPDATE(2);

        private int i;

        CalendarMessageType(int i) {
            this.i = i;
        }

        public int getI() {
            return i;
        }
    }

    class CalendarData {
        private CalendarMessageType messageType;
        private String userID;
        private String roomID;
        private String jsonData;

        public CalendarData(CalendarMessageType messageType, String userID, String roomID, String jsonData) {
            this.messageType = messageType;
            this.userID = userID;
            this.roomID = roomID;
            this.jsonData = jsonData;
        }

        public CalendarMessageType getMessageType() {
            return messageType;
        }

        public String getUserID() {
            return userID;
        }

        public String getRoomID() {
            return roomID;
        }

        public String getJsonData() {
            return jsonData;
        }
    }

    class CalendarSession {
        private String userID;
        private String roomID;
        private Session session;

        public CalendarSession(String userID, String roomID, Session session) {
            this.userID = userID;
            this.roomID = roomID;
            this.session = session;
        }

        public String getUserID() {
            return userID;
        }

        public String getRoomID() {
            return roomID;
        }

        public Session getSession() {
            return session;
        }
    }

    class PrimitiveUser {
        private String userID;
        private String username;
        private ArrayList<Event> events;

        public PrimitiveUser(String userID, String username, ArrayList<Event> events) {
            this.userID = userID;
            this.username = username;
            this.events = events;
        }
    }

    private static Gson gson = new Gson();
    private static Vector<CalendarSession> calendarSessions = new Vector<>();

    @OnOpen
    public void open(Session session) {
        System.out.println("Connection made.");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        CalendarData calendarData = gson.fromJson(message, CalendarData.class);

        System.out.println("Message received of type: " + calendarData.getMessageType());

        switch (calendarData.getMessageType()) {
            case INIT:
                //store the session's data as a CalendarSession
                CalendarSession calendarSession = new CalendarSession(calendarData.getUserID(), calendarData.getRoomID(), session);
                calendarSessions.add(calendarSession);

                //TODO: Make sure this block works.
                ArrayList<PrimitiveUser> primitiveUsers = new ArrayList<>();
//                ArrayList<User> users = new DataBase().retrieveUsers(calendarSession.getRoomID());
//                for (User foo : users) {
//                    String userID = foo.getUserID();
//                    String name = foo.getFullName();
//                    ArrayList<Event> userEvents = new EventBase().retrieveEvents(userID);
//                    primitiveUsers.add(new PrimitiveUser(userID, name, userEvents));
//                }

                //TODO: Remove dummy data.
                var std1 = new GregorianCalendar(2018, Calendar.NOVEMBER, 10, 12, 0);
                std1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
                var etd1 = new GregorianCalendar(2018, Calendar.NOVEMBER, 10, 15, 0);
                etd1.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
                Event event1 = new Event("strawsnowrries@gmail.com", "Cool Event #1", std1, etd1);

                var std2 = new GregorianCalendar(2018, Calendar.NOVEMBER, 12, 9, 0);
                std2.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
                var etd2 = new GregorianCalendar(2018, Calendar.NOVEMBER, 12, 10, 30);
                etd2.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
                Event event2 = new Event("strawsnowrries@gmail.com", "Cool Event #2", std2, etd2);

                ArrayList<Event> eventArrayList1 = new ArrayList<>();
                eventArrayList1.add(event1);
                eventArrayList1.add(event2);
                primitiveUsers.add(new PrimitiveUser("strawsnowrries@gmail.com", "Allan Zhang", eventArrayList1));

                var std3 = new GregorianCalendar(2018, Calendar.NOVEMBER, 9, 11, 0);
                std3.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
                var etd3 = new GregorianCalendar(2018, Calendar.NOVEMBER, 9, 17, 0);
                etd3.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
                Event event3 = new Event("allanzha@usc.edu", "Cool Event #1", std3, etd3);

                var std4 = new GregorianCalendar(2018, Calendar.NOVEMBER, 12, 9, 30);
                std4.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
                var etd4 = new GregorianCalendar(2018, Calendar.NOVEMBER, 12, 11, 30);
                etd4.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
                Event event4 = new Event("allanzha@usc.edu", "Cool Event #2", std4, etd4);

                ArrayList<Event> eventArrayList2 = new ArrayList<>();
                eventArrayList2.add(event3);
                eventArrayList2.add(event4);
                primitiveUsers.add(new PrimitiveUser("allanzha@usc.edu", "Ballin' Zhang", eventArrayList2));
                //END DUMMY DATA...

                String jsonMessage = gson.toJson(primitiveUsers);

                //create a new CalendarData message to encapsulate the data we're sending back
                calendarData = new CalendarData(CalendarMessageType.INIT, calendarSession.getUserID(), calendarSession.getRoomID(), jsonMessage);

                //send the json'd INIT response data back
                try {
                    session.getBasicRemote().sendText(gson.toJson(calendarData));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            case ADD_EVENT:
                Event event = gson.fromJson(calendarData.getJsonData(), Event.class);

                //TODO: Make sure this block works.
                new EventBase().addEvent(event);

                for (var foo : calendarSessions) {
                    //determines if this message be sent to this user's session
                    boolean needsUpdating = foo.getUserID().equals(calendarData.getUserID());

                    //TODO: Make sure this block works.
//                    ArrayList<User> bar = new DataBase().retrieveUsers(foo.getRoomID());
//                    for (User baz : bar) {
//                        if (baz.getUserID().equals(calendarData.getUserID())) {
//                            needsUpdating = true;
//                        }
//                    }

                    if (needsUpdating) {
                        try {
                            foo.getSession().getBasicRemote().sendText(gson.toJson(new CalendarData(CalendarMessageType.UPDATE, foo.getUserID(), foo.getRoomID(), gson.toJson(event))));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                break;
            case UPDATE:
                //SHOULD NEVER BE REACHED....?????
                break;
        }
    }

    @OnClose
    public void close(Session session) {
        calendarSessions.removeIf(foo -> (foo.getSession().equals(session)));

        System.out.println("Closed a session.");
    }

    @OnError
    public void error(Throwable throwable) {
        throwable.printStackTrace();
    }

}
