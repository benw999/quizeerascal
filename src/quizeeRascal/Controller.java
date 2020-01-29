package quizeeRascal;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Controller {

    private Scene nextScene;
    private Scene loginScene;
    private difficultyController stc;

    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }

    public void setStc(difficultyController stc) {
        this.stc = stc;
    }

    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    @FXML

    private TextField nameInput;
    @FXML
    private Text status;

    public void goDifficulty(ActionEvent event) {
        if (nameInput.getText().matches("[A-z0-9]+")) {
            User currUser = new User((nameInput.getText()), 0);
            System.out.println(currUser.getName());
            Node node = (Node) event.getSource();
            Stage primaryStage = (Stage) node.getScene().getWindow();
            primaryStage.hide();
            primaryStage.setOnShown(e -> stc.setUser(currUser));
            primaryStage.setScene(nextScene);
            primaryStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Who are you?");
            alert.setContentText("Please choose a username with only letters or numbers.");
            alert.showAndWait();
        }
    }

    public void goLogin(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}

