package PostItServlets;

import Chores.ChoreThread;
import db.DataBase;
import db.User;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ServerEndpoint(value="/sockets/message")
public class MessageSocket {
    public ExecutorService executor = Executors.newCachedThreadPool();

    @OnOpen
    public void open(Session session) {
        System.out.println("Connected!");
    }

    @OnMessage
    public void message(String message, Session session) {
        System.out.println(message);
        System.out.println(message.equals(""));
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
