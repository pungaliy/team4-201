package db;

import java.util.ArrayList;

public class Room {
    private String roomID;
    private String roomStatus;
    private ArrayList<User> residents;
    private String roomImgURL;


    //empty constructor, for codec purposes
    public Room() {}

    public Room(String roomID, ArrayList<User> residents, String roomImgURL, String roomStatus) {
        this.roomID = roomID;
        this.residents = residents;
        this.roomImgURL = roomImgURL;
        this.roomStatus = roomStatus;
    }



    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public ArrayList<User> getResidents() {
        return residents;
    }

    public void setResidents(ArrayList<User> residents) {
        this.residents = residents;
    }

    public String getRoomImgURL() {
        return roomImgURL;
    }

    public void setRoomImgURL(String roomImgURL) {
        this.roomImgURL = roomImgURL;
    }
}
