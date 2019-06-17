package nvduy1997.com.easytoeic.model;

public class Score {
    private int id;
    private String name;
    private String part;
    private String date;
    private int score;

    public Score() {

    }

    public Score(String name, String part, String date, int score) {
        this.name = name;
        this.part = part;
        this.date = date;
        this.score = score;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
