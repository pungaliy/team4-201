package src;

public class Note {
    private String text;
    private double xPos;
    private double yPos;
    private String IDname;

    public Note() {}

    public Note(String content, double x, double y, String noteID) {
        this.setText(content);
        this.setxPos(x);
        this.setyPos(y);
        this.setIDname(noteID);
    }

    public void print() {
        System.out.println(IDname + ": " + xPos+", "+yPos+"; "+text);
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getxPos() {
        return xPos;
    }

    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public void setyPos(double yPos) {
        this.yPos = yPos;
    }

    public String getIDname() {
        return IDname;
    }

    public void setIDname(String IDname) {
        this.IDname = IDname;
    }
}
