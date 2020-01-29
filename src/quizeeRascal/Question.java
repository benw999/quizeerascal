package quizeeRascal;

public class Question {

    private String question;
    private String A;
    private String B;
    private String C;
    private String correct;

    public Question(String question, String A, String B, String C, String correct) {
        this.question = question;
        this.A = A;
        this.B = B;
        this.C = C;
        this.correct = correct;
    }
}
