package Chores;

import db.Chore;
import db.ChoreBase;
import db.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChoreManager {
    private ChoreBase cb = new ChoreBase();
    private User user;

    public ChoreManager(User user) {
        this.user = user;
    }

    public void addChore(String choreDescription, long rotationPeriod) {
        ArrayList<Chore> chores = getChores();
        Chore chore = new Chore(choreDescription, rotationPeriod, System.currentTimeMillis(), this.user,
                this.user.getRoomID(), this.user.getUserID()+System.currentTimeMillis());
        assignChore(chore, chores, this.cb.retrieveUsers(user.getRoomID()));
        this.cb.insertChore(chore);
    }

    public ArrayList<Chore> getChores() {
        ArrayList<Chore> chores = this.cb.retrieveChores(user.getRoomID());
        update(chores);
        return chores;
    }

    public boolean update() {
        return update(this.cb.retrieveChores(user.getRoomID()));
    }

    public boolean update(ArrayList<Chore> chores) {
        boolean updated = false;
        boolean reassigned;
        ArrayList<User> users = this.cb.retrieveUsers(user.getRoomID());
        for(Chore chore : chores) {
            reassigned = false;
            while(chore.isExpired()) {
                reassignChore(chore, chores, users);
                reassigned = true;
            }
            if(reassigned) {
                this.cb.updateChore(chore);
                updated = true;
            }
            System.out.println(chore.getChoreID()+" "+chore.getChoreDescription()+" "+chore.getCurrentUser());
        }
        return updated;
    }

    public void reassignChore(Chore chore, ArrayList<Chore> chores, ArrayList<User> users) {
        if(chore.isCompleted()) chore.setShame(false);
        else chore.setShame(true);
        chore.setCompleted(false);
        chore.setRotationTime(chore.getRotationTime()+chore.getRotationPeriod());
        assignChore(chore, chores, users);
    }

    public void assignChore(Chore chore, ArrayList<Chore> chores, ArrayList<User> users) {
        /*ArrayList<User> users = this.cb.retrieveUsers(roomID);
        HashMap<String, Integer> choreCount = new HashMap<>();
        for(User user : users) {
            if(!chore.isPreviousUser(user.getUserID())) {
                choreCount.put(user.getUserID(), 0);
            }
        }
        for(Chore c : chores) {
            String user = c.getCurrentUser();
            choreCount.put(user, choreCount.get(user) + 1);
        }
        String toAssign = users.get((int)(Math.random()*users.size())).getUserID();
        int minChores = Integer.MAX_VALUE;
        for(Map.Entry<String, Integer> entry : choreCount.entrySet()) {
            int numChores = entry.getValue();
            if(numChores < minChores) {
                minChores = numChores;
                toAssign = entry.getKey();
            }
        }
        chore.replaceCurrentUser(toAssign, users.size());
        */
        ArrayList<User> validUsers = chore.getValidUsers(users);
        User toAssign = validUsers.get((int)(Math.random()*users.size()));
        int minChores = Integer.MAX_VALUE;
        for(User user : validUsers) {
            int numChores = getUserChores(user, chores).size();
            if(numChores < minChores) {
                minChores = numChores;
                toAssign = user;
            }
        }
        chore.assignTo(toAssign, users.size());
    }

    public ArrayList<Chore> getMyChores(ArrayList<Chore> chores) { return getUserChores(this.user, chores); }

    public ArrayList<Chore> getUserChores(User user, ArrayList<Chore> chores) {
        ArrayList<Chore> userChores = new ArrayList<>();
        for(Chore chore : chores) {
            if(chore.getCurrentUser().getUserID().equals(user.getUserID())) {
                userChores.add(chore);
            }
        }
        return userChores;
    }

    public ArrayList<User> getWallOfShame(ArrayList<Chore> chores) {
        ArrayList<User> wos = new ArrayList<User>();
        for(Chore chore : chores) {
            if(chore.isShame()) {
                wos.add(chore.getLastUser());
            }
        }
        return wos;
    }

//    public static void main(String[] args) {
//        ChoreManager cm = new ChoreManager("room1", "Micah");
//        cm.getChores("room1");
//    }
}
