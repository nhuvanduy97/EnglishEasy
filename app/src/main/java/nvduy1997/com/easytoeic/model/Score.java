package nvduy1997.com.easytoeic.model;

public class Score {
    private int id;
    private String name;
    private String part;
    private String date;
    private int score;
    private int numCorrect;
    private int numFail;
    private int numNotAns;

    public Score() {

    }

    public Score(String name, String part, String date, int score, int numCorrect, int numFail, int numNotAns) {
        this.name = name;
        this.part = part;
        this.date = date;
        this.score = score;
        this.numCorrect = numCorrect;
        this.numFail = numFail;
        this.numNotAns = numNotAns;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumCorrect() {
        return numCorrect;
    }

    public void setNumCorrect(int numCorrect) {
        this.numCorrect = numCorrect;
    }

    public int getNumFail() {
        return numFail;
    }

    public void setNumFail(int numFail) {
        this.numFail = numFail;
    }

    public int getNumNotAns() {
        return numNotAns;
    }

    public void setNumNotAns(int numNotAns) {
        this.numNotAns = numNotAns;
    }
}
