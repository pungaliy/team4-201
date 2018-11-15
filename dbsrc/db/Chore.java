package db;

import java.util.ArrayList;
import java.util.ArrayList;


public class Chore {
    private String choreDescription;
    private long rotationTime;
    private boolean completed;
    private String roomID;
    private String choreID;
    private User currentUser;
    private ArrayList<User> previousUsers;
    private long rotationPeriod;
    private boolean shame;

    public Chore() {}

    public Chore(String choreDescription, long rotationPeriod, long rotationTime, User user, String roomID, String choreID) {
        this.choreDescription = choreDescription;
        this.rotationPeriod = rotationPeriod;
        this.rotationTime = rotationTime;
        this.currentUser = user;
        this.roomID = roomID;
        this.choreID = choreID;
        this.completed = false;
        this.shame = false;
        this.previousUsers = new ArrayList<>();
    }

    public Chore(String choreDescription, long rotationPeriod, long rotationTime, String roomID, String choreID) {
        this.choreDescription = choreDescription;
        this.rotationPeriod = rotationPeriod;
        this.rotationTime = rotationTime;
        this.currentUser = null;
        this.roomID = roomID;
        this.choreID = choreID;
        this.completed = false;
        this.shame = false;
        this.previousUsers = new ArrayList<>();
    }

    public String getChoreDescription() {
        return choreDescription;
    }

    public void setChoreDescription(String choreDescription) {
        this.choreDescription = choreDescription;
    }

    public long getRotationTime() {
        return rotationTime;
    }

    public void setRotationTime(long rotationTime) {
        this.rotationTime = rotationTime;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getChoreID() {
        return choreID;
    }

    public void setChoreID(String choreID) {
        this.choreID = choreID;
    }

    public User getCurrentUser() { return currentUser; }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void assignTo(User user, int userCount) {
        if(previousUsers.size() >= userCount-2) {
            this.previousUsers.clear();
        }
        if(this.currentUser != null) this.previousUsers.add(this.currentUser);
        this.currentUser = user;
    }

    public ArrayList<User> getPreviousUsers() { return previousUsers; }

    public void setPreviousUsers(ArrayList<User> previousUsers) { this.previousUsers = previousUsers; }

    public boolean isPreviousUser(User user) {
        for(User u : this.previousUsers) {
            if(u.getUserID().equals(user.getUserID())) return true;
        }
        return false;
    }

    public ArrayList<User> getValidUsers(ArrayList<User> users) {
        ArrayList<User> validUsers = new ArrayList<>();
        String userID = "";
        if(this.currentUser != null) userID = this.currentUser.getUserID();
        for(User u : users) {
            if(!isPreviousUser(u) && !u.getUserID().equals(userID)) {
                validUsers.add(u);
            }
        }
        return validUsers;
    }

    public long getRotationPeriod() {
        return rotationPeriod;
    }

    public void setRotationPeriod(long rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    public boolean isShame() {
        return shame;
    }

    public void setShame(boolean shame) {
        this.shame = shame;
    }

    public long expirationTime() {
        return this.rotationTime+this.rotationPeriod-System.currentTimeMillis();
    }

    public boolean isExpired() { return expirationTime() <= 0; }

    public User getLastUser() {
        if(this.previousUsers.size() >= 1) {
            return this.previousUsers.get(this.previousUsers.size()-1);
        }
        return null;
    }
}
