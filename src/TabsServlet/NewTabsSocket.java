package TabsServlet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Vector;

@ServerEndpoint(value="/sockets/tabs")
public class NewTabsSocket {
	private static Vector<Session> sessions = new Vector<>();

	@OnOpen
	public void open(Session session) {
		sessions.add(session);
		System.out.println("TabsSocket Connection!");
	}

	@OnMessage
	public void message(String message, Session session) {
		for(Session s : sessions){
			try {
				s.getBasicRemote().sendText("");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@OnClose
	public void close(Session session) {
		sessions.remove(session);
		System.out.println("TabsSocket: Disconnected!");
	}

	@OnError
	public void error(Throwable error) {
		System.out.println("TabsSocket: Error!");
		error.printStackTrace();
	}
}
