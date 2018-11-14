package Chores;

import db.DataBase;
import db.User;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ServerEndpoint(value="/sockets/chore")
public class ChoreSocket {
    public ExecutorService executor = Executors.newCachedThreadPool();

    @OnOpen
    public void open(Session session) {
        DataBase db = new DataBase();
        ArrayList<User> users = db.retrieveUsers("coolroom");
        executor.execute(new ChoreThread(session, users.get(0)));
        System.out.println("Connection!");
    }

    @OnMessage
    public void message(String message, Session session) {
        System.out.println(message);
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
