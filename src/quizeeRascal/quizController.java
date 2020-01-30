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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class quizController {

    private int qNumber;
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

    public void setQNumber(int qNumber) {
        this.qNumber = qNumber;
    }

    //Defines objects from the FXML file, labels to display user/score/difficulty/question and the answer buttons.
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

    //Method initialized upon launch of the main quiz scene.
    public void startQuiz(User currUser, String difficulty){
        this.currUser = currUser; //Sets current user object.
        this.difficulty = difficulty; //Sets difficulty level.
        this.qNumber = 0; //Sets question number to 0 (1).
        userText.setText("User: " + this.currUser.getName()); //Displays username.
        scoreText.setText("Score: " + this.currUser.getScore()); //Displays user score.
        diffText.setText(this.difficulty); //Displays difficulty level.
        this.questions = loadQuestions(this.difficulty); //Calls method to load questions for the correct level.
        this.question = this.questions.get(0); //Sets first question.
        qText.setText((this.qNumber + 1) + ") " + question[0]); //Displays question number (1).
        //Sets text of answer buttons to respective answers for the question.
        A.setText(question[1]);
        B.setText(question[2]);
        C.setText(question[3]);
    }

    public void nextQuestion(ActionEvent event) { //Called when "next" button is pressed.
        if (selection != null) { //If the user has selected an answer...
            if (selection.getId().equals(this.questions.get(this.qNumber)[4])){ //If correct answer selected...
                this.currScore = currUser.getScore();
                this.currUser.setScore(this.currScore + 1); //Increase score by one.
                this.scoreText.setText("Score: " + this.currUser.getScore()); //Display new score.
            }
            if (this.qNumber <= 8){ //If it is not the last question...
                this.qNumber++; //Increase question number.
                newQuestion(); //Load next question.
            }
            else { //If it is the last question... display final results screen.
                Node node = (Node) event.getSource();
                Stage primaryStage = (Stage) node.getScene().getWindow();
                primaryStage.hide();
                primaryStage.setOnShown(e -> s4c.finQuiz(currUser, difficulty)); //Passes user object and difficulty.
                primaryStage.setScene(nextScene);
                primaryStage.show();
            }
        } else { //If the user does not select an answer... won't continue to next question and alerts them.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Come on, try harder than that!");
            alert.setContentText("Please select A, B or C.");
            alert.showAndWait();
        }
    }

    //Method to display the next question in the quiz.
    public void newQuestion(){
        this.selection = null; //Resets user answer from last question to null.
        this.question = this.questions.get(this.qNumber); //Get question that is the for the correct number.
        qText.setText((this.qNumber + 1) + ") " + question[0]); //Displays new question numbers.
        //Sets answer button to new answers.
        A.setText(question[1]);
        B.setText(question[2]);
        C.setText(question[3]);
    }

    //Sets answer based on the ID of the chosen answer button (A, B or C).
    public void setAnswer(ActionEvent event) {
        this.selection = (Button) event.getSource();
    }


    //Loads questions from selected csv based on difficulty level.
    public ArrayList<String[]> loadQuestions(String difficulty){
        String csvPath = "C:\\Users\\Admin\\IdeaProjects\\test6\\src\\quizeeRascal\\data\\" + difficulty + ".csv"; //Chooses right file for difficulty chosen.
        ArrayList<String[]> questions = new ArrayList(); //Instantiates list of questions.
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
            String row;
            while ((row = csvReader.readLine()) != null) { //Reads CSV line by line...
                String[] data = row.split(","); //Splits row by delimiter (,) into question and answers etc.
                questions.add(data); //Add new question to the list of questions.
            }
            csvReader.close(); //Closes the CSV reader object.
        }
        catch(IOException e){
            e.printStackTrace();
        }
        Collections.shuffle(questions); //Shuffle the list to ensure the order of questions isn't the same each play-through.
        return questions; //Returns list of questions.
    }
}
