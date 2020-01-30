package quizeeRascal;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.*;
import java.util.ArrayList;


public class finController {

    private scoreboardController s5c;
    private Scene nextScene;
    private Scene firstScene;
    private User currUser;
    private String difficulty;

    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }

    public void setS5c(scoreboardController s5c) {
        this.s5c = s5c;
    }

    public void setFirstScene(Scene firstScene){
        this.firstScene = firstScene;
    }

    @FXML
    private Text finText;

    @FXML
    private Text scoreText;

    //Method called when quiz is complete and switches this screen.
    public void finQuiz(User currUser, String difficulty){
        this.currUser = currUser;
        this.difficulty = difficulty;
        finText.setText("Well done " + this.currUser.getName() + "!"); //Congratulates user by name.
        scoreText.setText("You scored " + this.currUser.getScore() + "/10!"); //Displays final score out of 10.
        String csvPath = "C:\\Users\\Admin\\IdeaProjects\\test6\\src\\quizeeRascal\\data\\scores.csv"; //CSV file for scores.
        try {
            BufferedWriter csvWriter = new BufferedWriter(new FileWriter(csvPath, true));
            csvWriter.write(currUser.getName() + "," + currUser.getScore()); //Writes user and their score to the csv file.
            csvWriter.newLine();
            csvWriter.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    //Method to switch to scoreboard.
    public void goScores(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.hide();
        primaryStage.setOnShown(e -> s5c.loadScores()); //Calls loadScores method to populate scoreboard on next scene.
        primaryStage.setScene(nextScene);
        primaryStage.show();
    }

    //Method which resets the user and goes back to the first scene where the user can enter their name.
    public void startAgain(ActionEvent event) {
        this.currUser.setName(null);
        this.currUser.setScore(0);
        Node node = (Node) event.getSource();
        Stage primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(firstScene);
        primaryStage.show();
    }
}
