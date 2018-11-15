package LoginRegistration;

import Chores.ChoreThread;
import com.google.gson.Gson;
import db.DataBase;
import db.User;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ServerEndpoint(value="/sockets/message")
public class MessageSocket {
    public static Map<Session, User> sessionToUser = Collections.synchronizedMap(new HashMap<>());
    public static Gson gson = new Gson();

    @OnOpen
    public void open(Session session) {
        System.out.println("Connection!");
    }

    @OnMessage
    public void message(String message, Session session) {
        System.out.println(message);
        System.out.println(message.equals("ping"));
        if(!message.equals("ping")) {
            sessionToUser.put(session, gson.fromJson(message, User.class));
        } else {
            System.out.println("Broadcasting...");
            User currUser = sessionToUser.get(session);
            for(Map.Entry<Session, User> e : sessionToUser.entrySet()) {
                System.out.print(e.getValue().getFullName());
                if(e.getValue().getRoomID().equals(currUser.getRoomID()) && !e.getKey().equals(session)) {
                    System.out.print(" - bull's eye!")
                    e.getKey().getAsyncRemote().sendText("pong");
                }
                System.out.println();
            }
        }
    }

    @OnClose
    public void close(Session session) {
        sessionToUser.remove(session);
        System.out.println("Disconnected!");
    }

    @OnError
    public void error(Throwable error) {
        System.out.println("Error!");
    }
}
