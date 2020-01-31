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

    //Selection of methods to set the difficulty to easy, medium or hard depending on which button is pressed.
    public void setEasy(){
        this.difficulty = "EASY";
    }

    public void setMedium(){
        this.difficulty = "MEDIUM";
    }

    public void setHard(){
        this.difficulty = "HARD";
    }

    //Called upon switching to this scene... sets user name based on user input on the last scene.
    public void setUser(User currUser) {
        this.currUser = currUser;
        diffText.setText("Hello " + currUser.getName() + "! Choose your difficulty.");
        userText.setText("User: " + currUser.getName());
    }

    //Method to switch to the main quiz.
    public void goQuiz(ActionEvent event){
        if (this.difficulty != null) { //If the user has chosen a difficulty level... go to quiz.
            Node node = (Node) event.getSource();
            Stage primaryStage = (Stage) node.getScene().getWindow();
            primaryStage.hide();
            primaryStage.setOnShown(e -> s3c.startQuiz(currUser, difficulty)); //Passes user object and difficulty level to quiz controller.
            primaryStage.setScene(nextScene);
            primaryStage.show();
        } else { //If the user has not chosen a difficulty level... do not allow them to continue and display alert.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Don't be scared!");
            alert.setContentText("Please select a difficulty level to continue.");
            alert.showAndWait();
        }
    }
}
