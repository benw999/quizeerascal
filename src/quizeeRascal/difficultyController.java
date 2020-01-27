package quizeeRascal;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class difficultyController {

    private Scene nextScene;
    private User currUser;
    private quizController s3c;
    private String difficulty;

    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }

    public void setS3c(quizController s3c) {
        this.s3c = s3c;
    }


    @FXML
    private Text userText;
    @FXML
    private Text diffText;

    public void setEasy(){
        System.out.println("Set difficulty to easy.");
        this.difficulty = "EASY";
    }

    public void setMedium(){
        System.out.println("Set difficulty to medium.");
        this.difficulty = "MEDIUM";
    }

    public void setHard(){
        System.out.println("Set difficulty to hard.");
        this.difficulty = "HARD";
    }

    public void setUser(User currUser) {
        this.currUser = currUser;
        diffText.setText("Hello " + currUser.getName() + "! Choose your difficulty.");
        userText.setText("User: " + currUser.getName());
    }

    public void goQuiz(ActionEvent event){
        if (this.difficulty != null) {
            Node node = (Node) event.getSource();
            Stage primaryStage = (Stage) node.getScene().getWindow();
            primaryStage.hide();
            primaryStage.setOnShown(e -> s3c.startQuiz(currUser, difficulty));
            primaryStage.setScene(nextScene);
            primaryStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Don't be scared!");
            alert.setContentText("Please select a difficulty level to continue.");
            alert.showAndWait();
        }
    }
}
