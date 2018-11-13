package db;

import java.util.ArrayList;
import java.util.Vector;


public class Chore {
    private String choreDescription;
    private long rotationTime;
    private boolean completed;
    private String roomID;
    private String choreID;
    private User currentUser;
    private Vector<User> previousUsers;
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
        this.previousUsers = new Vector<>();
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
            if(this.shame && previousUsers.size() >= 1) {
                User shamedUser = this.previousUsers.get(this.previousUsers.size()-1);
                this.previousUsers = new Vector<>();
                this.previousUsers.add(shamedUser);
            }
            else {
                this.previousUsers.clear();
                this.shame = false;
            }
        }
        else this.previousUsers.add(this.currentUser);
        this.currentUser = user;
    }

    public Vector<User> getPreviousUsers() { return previousUsers; }

    public void setPreviousUsers(Vector<User> previousUsers) { this.previousUsers = previousUsers; }

    public boolean isPreviousUser(User user) {
        for(User u : this.previousUsers) {
            if(u.getUserID().equals(user.getUserID())) return true;
        }
        return false;
    }

    public ArrayList<User> getValidUsers(ArrayList<User> users) {
        ArrayList<User> validUsers = new ArrayList<>();
        for(User u : users) {
            if(!isPreviousUser(u) && !u.getUserID().equals(this.currentUser.getUserID())) {
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

    public boolean isExpired() { return System.currentTimeMillis()-this.rotationTime >= this.rotationPeriod; }

    public User getLastUser() {
        if(this.previousUsers.size() >= 1) {
            return this.previousUsers.get(this.previousUsers.size()-1);
        }
        return null;
    }
}
