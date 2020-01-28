package quizeeRascal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class quizController {

    private int qNumber = 0;
    private User currUser;
    private int currScore;
    private String difficulty;
    private Button selection;
    private ArrayList<String[]> questions;
    private String[] question;
    private finController s4c;
    private Scene nextScene;

    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }

    public void setS4c(finController s4c) {
        this.s4c = s4c;
    }

    @FXML
    private Text userText;
    @FXML
    private Text scoreText;
    @FXML
    private Text diffText;
    @FXML
    private Text qText;
    @FXML
    private Button A;
    @FXML
    private Button B;
    @FXML
    private Button C;

    public void startQuiz(User currUser, String difficulty){
        this.currUser = currUser;
        this.difficulty = difficulty;
        userText.setText("User: " + this.currUser.getName());
        scoreText.setText("Score: " + this.currUser.getScore());
        diffText.setText(this.difficulty);
        this.questions = loadQuestions(this.difficulty);
        this.question = this.questions.get(0);
        qText.setText((this.qNumber + 1) + ") " + question[0]);
        A.setText(question[1]);
        B.setText(question[2]);
        C.setText(question[3]);
    }

    public void nextQuestion(ActionEvent event) {
        if (selection != null) {
            if (selection.getId().equals(this.questions.get(this.qNumber)[4])){
                this.currScore = currUser.getScore();
                this.currUser.setScore(this.currScore + 1);
                this.scoreText.setText("Score: " + this.currUser.getScore());
            }
            else {
                System.out.println("Wrong");
            }
            if (this.qNumber <= 8){
                this.qNumber++;
                newQuestion();
            }
            else {
                Node node = (Node) event.getSource();
                Stage primaryStage = (Stage) node.getScene().getWindow();
                primaryStage.hide();
                primaryStage.setOnShown(e -> s4c.finQuiz(currUser, difficulty));
                primaryStage.setScene(nextScene);
                primaryStage.show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Come on, try harder than that!");
            alert.setContentText("Please select A, B or C.");
            alert.showAndWait();
        }
    }

    public void newQuestion(){
        this.selection = null;
        this.question = this.questions.get(this.qNumber);
        qText.setText((this.qNumber + 1) + ") " + question[0]);
        A.setText(question[1]);
        B.setText(question[2]);
        C.setText(question[3]);
    }

    public void setAnswer(ActionEvent event) {
        this.selection = (Button) event.getSource();
    }


    public ArrayList<String[]> loadQuestions(String difficulty){
        String csvPath = "C:\\Users\\Admin\\IdeaProjects\\test6\\src\\quizeeRascal\\data\\" + difficulty + ".csv";
        ArrayList<String[]> questions = new ArrayList();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                questions.add(data);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return questions;
    }
}
