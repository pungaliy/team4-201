package Chores;

import com.google.gson.Gson;
import db.Chore;
import db.ChoreBase;
import db.DataBase;
import db.User;

import java.util.*;

public class ChoreManager {
    private static Gson gson = new Gson();
    private static ChoreBase cb = new ChoreBase();
    private User user;

    public ChoreManager(User user) {
        this.user = user;
    }

    public User getUser() { return this.user; }

    public void addChore(String choreDescription, long rotationPeriod) {
        ArrayList<Chore> chores = getChores();
        Chore chore = new Chore(choreDescription, rotationPeriod, System.currentTimeMillis(), this.user.getRoomID(),
                this.user.getUserID()+System.currentTimeMillis());
        assignChore(chore, chores, this.cb.retrieveUsers(user.getRoomID()));
        this.cb.insertChore(chore);
    }

    public ArrayList<Chore> getChores() {
        ArrayList<Chore> chores = this.cb.retrieveChores(user.getRoomID());
        if(chores.size() >= 1) update(chores);
        return chores;
    }

    public boolean update() {
        ArrayList<Chore> chores = this.cb.retrieveChores(user.getRoomID());
        if(chores.size() >= 1) return update(chores);
        return false;
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
                System.out.println("reassigned");
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
        ArrayList<User> validUsers = chore.getValidUsers(users);
        if(validUsers.size() >= 1) {
            User toAssign = validUsers.get(0);
            int minChores = Integer.MAX_VALUE;
            for (User user : validUsers) {
                int numChores = getUserChores(user, chores).size();
                if (numChores < minChores) {
                    minChores = numChores;
                    toAssign = user;
                }
            }
            chore.assignTo(toAssign, users.size());
        }
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

    public ArrayList<Chore> getShamedChores(ArrayList<Chore> chores) {
        ArrayList<Chore> wos = new ArrayList<>();
        for(Chore chore : chores) {
            if(chore.isShame()) {
                wos.add(chore);
            }
        }
        return wos;
    }

    public String getJSONPackage(ArrayList<Chore> allchores) {
        ArrayList<Chore> mychores = getMyChores(allchores);
        ArrayList<Chore> shamedchores = getShamedChores(allchores);
        return gson.toJson(new ChorePackage(mychores, allchores, shamedchores));
    }

    public long minExpirationTime(List<Chore> chores) {
        long minT = Long.MAX_VALUE - 6;
        for(Chore chore : chores) {
            long t = chore.expirationTime();
            if(t < minT) minT = t;
        }
        return minT;
    }

    public void print() {
        System.out.println(this.user.getFullName());
        ArrayList<Chore> chores = this.getMyChores(this.getChores());
        for(Chore c : chores) {
            System.out.println(c.getChoreDescription());
        }
    }

    public static void main(String[] args) {
        DataBase db = new DataBase();
//        db.addUser(new User("Paul", "paul@usc.edu", "room6666", "picture"));
//        db.addUser(new User("John", "john@usc.edu", "room6666", "picture"));
//        db.addUser(new User("Mary", "mary@usc.edu", "room6666", "picture"));
//        db.addUser(new User("James", "james@usc.edu", "room6666", "picture"));

        ArrayList<User> users = db.retrieveUsers("room6666");
        System.out.println(users.size());
        System.out.println(users);

        ChoreManager cm0 = new ChoreManager(users.get(0));
        ChoreManager cm1 = new ChoreManager(users.get(1));
        ChoreManager cm2 = new ChoreManager(users.get(2));
        ChoreManager cm3 = new ChoreManager(users.get(3));
//        cm0.addChore("Take out the trash!", 10000);
//        cm0.addChore("Scrub the floor...", 15000);
//        cm0.addChore("Do the dishes", 30000);
//        cm0.addChore("Clean the bathroom", 35000);
//        cm0.addChore("Sweep the floors", 45000);
//        cm0.addChore("Clean stuff", 60000);

        cm0.print();
        cm1.print();
        cm2.print();
        cm3.print();

//        while(true) {
//            System.out.println(cm0.minExpirationTime(cm0.getChores()));
//        }
//        chores.get(0).setShame(false);
//
//        System.out.println(cm0.getShamedChores(chores));
    }
}
