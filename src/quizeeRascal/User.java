package quizeeRascal;

public class User {
    private String name;
    private int score = 0;

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName(){
        return this.name;
    }

    public int getScore(){
        return this.score;
    }

    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
