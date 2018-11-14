package sock.database;

//db:
//retrieve user object from id

public class User {
    private String fullName;
    private String userID;
    private String roomID;
    private String imgURL;

    //empty for codec purposes
    public User() {}

    public User(String fullName, String userID, String roomID, String imgURL) {
        this.setFullName(fullName);
        this.setUserID(userID);
        this.setRoomID(roomID);
        this.setImgURL(imgURL);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }
}
