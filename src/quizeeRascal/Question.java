package quizeeRascal;

public class Question {

    private String question;
    private String A;
    private String B;
    private String C;
    private String correct;

    public String getQuestion(){
        return this.question;
    }

    public String getA(){
        return this.A;
    }

    public String getB(){
        return this.B;
    }

    public String getC(){
        return this.C;
    }

    public String getCorrect(){
        return this.correct;
    }

    public Question(String question, String A, String B, String C, String correct) {
        this.question = question;
        this.A = A;
        this.B = B;
        this.C = C;
        this.correct = correct;
    }
}
