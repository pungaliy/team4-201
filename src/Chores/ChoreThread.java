package Chores;

import com.google.gson.Gson;
import db.Chore;
import db.User;

import javax.websocket.Session;
import java.util.ArrayList;

public class ChoreThread extends Thread {
    private Session s;
    private ChoreManager cm;

    public ChoreThread(Session s, User u) {
        System.out.println("Starting thread");
        this.s = s;
        this.cm = new ChoreManager(u);
    }

    public Session getSession() { return this.s; }

    public ChoreManager getChoreManager() { return this.cm; }

    public void run() {
        System.out.println("Running");
        while(this.s.isOpen()) {
            ArrayList<Chore> chores = this.cm.getChores();
            this.s.getAsyncRemote().sendText(this.cm.getJSONPackage(chores));
            long sleepTime = this.cm.minExpirationTime(chores)+5;
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted!");
            }
        }
    }
}
