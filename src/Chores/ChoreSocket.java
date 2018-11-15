package Chores;

import com.google.gson.Gson;
import db.Chore;
import db.ChoreBase;
import db.DataBase;
import db.User;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ServerEndpoint(value="/sockets/chore")
public class ChoreSocket {
    public static Map<Session, ChoreThread> sessionToThread = Collections.synchronizedMap(new HashMap<>());
    public static Gson gson = new Gson();
    public static ChoreBase cb = new ChoreBase();

    @OnOpen
    public void open(Session session) {
        System.out.println("Connection!");
    }

    @OnMessage
    public void message(String message, Session session) {
        System.out.println(message);
        System.out.println(message.equals("ping"));
        if(message.substring(0,4).equals("init")) { //Initialize by starting thread
            User user = gson.fromJson(message.substring(4), User.class);
            ChoreThread ct = new ChoreThread(session, user);
            sessionToThread.put(session, ct);
            ct.start();
        } else if(message.substring(0,4).equals("comp")) { //Mark chore as completed
            boolean checked = message.substring(4,5).equals("t");
            ChoreBase cb = new ChoreBase();
            Chore c = cb.retrieveChore(message.substring(5));
            c.setCompleted(checked);
            cb.updateChore(c);
        } else { //Broadcast chores to other users in room
            System.out.println("Broadcasting...");
            User currUser  = sessionToThread.get(session).getChoreManager().getUser();
            for(Map.Entry<Session, ChoreThread> e : sessionToThread.entrySet()) {
                User u = e.getValue().getChoreManager().getUser();
                System.out.print(u.getFullName());
                if(u.getRoomID().equals(currUser.getRoomID())) {
                    System.out.print(" - bull's eye!");
                    e.getValue().interrupt();
//                    ChoreThread ct = new ChoreThread(session, u);
//                    e.setValue(ct);
//                    ct.start();
                }
                System.out.println();
            }
        }
    }

    @OnClose
    public void close(Session session) {
        System.out.println("Disconnected!");
    }

    @OnError
    public void error(Throwable error) {
        System.out.println("Error!");
    }
}
