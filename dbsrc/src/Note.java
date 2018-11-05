package src;
//for now, change all doubles/ints to strings while i figure out the issues with mongoDB
public class Note {
    private String text;
    private String xPos;
    private String yPos;
    private String IDname;

    public Note() {}

    public Note(String content, String x, String y, String noteID) {
        this.setText(content);
        this.setxPos(x);
        this.setyPos(y);
        this.setIDname(noteID);
    }

    public void print() {
        System.out.println(getIDname() + ": " + getxPos() +", "+ getyPos() +"; "+ getText());
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public String getxPos() {
        return xPos;
    }

    public void setxPos(final String xPos) {
        this.xPos = xPos;
    }

    public String getyPos() {
        return yPos;
    }

    public void setyPos(final String yPos) {
        this.yPos = yPos;
    }

    public String getIDname() {
        return IDname;
    }

    public void setIDname(final String IDname) {
        this.IDname = IDname;
    }
}
