package db;


//RoomBase:
//add room, retrieve room from id, check if room exists, add user to room


import java.util.ArrayList;

public class Room {
    private String roomID;
    private String roomStatus;
//    private ArrayList<String> residents;


    //empty constructor, for codec purposes
    public Room() {}

    public Room(String roomID) {
        this.roomID = roomID;
        this.roomStatus = "DO NOT DISTURB";
//        this.residents = new ArrayList<String>();
    }

    public Room(String roomID, ArrayList<String> residents,  String roomStatus) {
        this.roomID = roomID;
//        this.residents = residents;
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

//    public ArrayList<String> getResidents() {
//        return residents;
//    }

//    public void setResidents(ArrayList<String> residents) {
//        this.residents = residents;
//    }

}
