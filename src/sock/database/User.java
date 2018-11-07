package sock.database;

public class User {
    private String name;
    private String email;
    private Room room;

    public User(String name, String email, Room room) {
        this.room = room;
        this.email = email;
        this.name = name;
    }

    public Room getRoom() {
        return room;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
}
