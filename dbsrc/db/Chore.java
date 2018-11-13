package db;

import java.util.Vector;

public class Chore {
    private String choreDescription;
    private long rotationTime;
    private boolean completed;
    private String roomID;
    private String choreID;
    private String currentUser;
    private Vector<User> previousUsers;
    private long rotationPeriod;
    private boolean shame;

    public Chore() {}

    public Chore(String choreDescription, long rotationPeriod, long rotationTime, String userID, String roomID, String choreID) {
        this.choreDescription = choreDescription;
        this.rotationPeriod = rotationPeriod;
        this.rotationTime = rotationTime;
        this.currentUser = userID;
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

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public Vector<User> getPreviousUsers() {
        return previousUsers;
    }

    public void setPreviousUsers(Vector<User> previousUsers) {
        this.previousUsers = previousUsers;
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
}
