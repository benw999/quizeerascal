package quizeeRascal;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


public class finController {

    private scoreboardController s5c;
    private Scene nextScene;
    private Scene firstScene;
    private User currUser;

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

    public void finQuiz(User currUser){
        this.currUser = currUser;
        finText.setText("Well done " + this.currUser.getName() + "!");
        scoreText.setText("You scored " + this.currUser.getScore() + "/10!");
    }

    public void goScores(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(nextScene);
        primaryStage.show();
    }

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
