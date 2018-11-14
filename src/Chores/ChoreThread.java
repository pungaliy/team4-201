package Chores;

import com.google.gson.Gson;
import db.User;

import javax.websocket.Session;

public class ChoreThread extends Thread {
    private Session s;
    private ChoreManager cm;

    public ChoreThread(Session s, User u) {
        System.out.println("Starting thread");
        this.s = s;
        this.cm = new ChoreManager(u);
    }

    public void run() {
        this.cm.update();
        this.s.getAsyncRemote().sendText(this.cm.getJSONPackage());
        while(this.s.isOpen()) {
            try {
                Thread.sleep(30000);
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted!");
                return;
            }
            if(this.cm.update()) {
                this.s.getAsyncRemote().sendText(this.cm.getJSONPackage());
            }
        }
    }
}
