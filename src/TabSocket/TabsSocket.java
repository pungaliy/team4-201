package TabSocket;
import db.DataBase;
import db.User;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ServerEndpoint(value="/TabsSocket")
public class TabsSocket {
	private static Vector<Session> sessionVector = new Vector<Session>();
	@OnOpen
	public void open(Session session) {
		System.out.println("Server Endpt: have a new connection!");
		sessionVector.add(session);
	}

	@OnMessage
	public void message(String message, Session session) {
		System.out.println(message);
		for(Session s : sessionVector) {
			try {
				s.getBasicRemote().sendText(message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("IO exception in Server Endpt: " + e.getMessage());
			}
		}
	}

	@OnClose
	public void close(Session session) {
		System.out.println("Server Endpt: someone disconnected");
		sessionVector.remove(session);
	}

	@OnError
	public void error(Throwable error) {
		System.out.println("Server Endpt: ERROR!");
	}
}

