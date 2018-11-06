package db;

//for now, change all doubles/ints to strings while i figure out the issues with mongoDB
public final class Note {
    private String text;
    private double xpos;
    private double ypos;
    private String noteid;
    private String roomid;

    public Note() {
    }

    public Note(final String content, final double x, final double y, final String noteid, String roomid) {
        this.text = content;
        this.xpos = x;
        this.ypos = y;
        this.noteid = noteid;
        this.roomid = roomid;
    }
    public String getText() {
        return this.text;
    }
    public String getNoteid() {
        return this.noteid;
    }
    public double getXpos() {
        return this.xpos;
    }
    public double getYpos() {
        return this.ypos;
    }
    public String getRoomid() {
        return this.roomid;
    }
    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setNoteid(String IDname) {
        this.noteid= IDname;
    }
    public void setXpos(double xPos) {
        this.xpos = xPos;
    }
    public void setYpos(double yPos) {
        this.ypos = yPos;
    }

    public void print() {
        System.out.println(noteid + ": " + xpos + ", " + ypos + "; " + text);
    }



//    @Override
//    public String toString() {
//        return IDname + ": " + xPos + ", " + yPos + "; " + text;
//    }
}
