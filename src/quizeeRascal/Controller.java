package quizeeRascal;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Controller {

    //Initialises properties for the class.
    private Scene nextScene;
    private Scene loginScene;
    private difficultyController stc;

    //Method to assign the property which holds the next scene.
    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }

    //Method to assign the property which holds the controller for the next scene.
    public void setStc(difficultyController stc) {
        this.stc = stc;
    }

    //Method to assign the property which holds the scene for the admin login (special users only).
    public void setLoginScene(Scene loginScene) {
        this.loginScene = loginScene;
    }

    @FXML //Indicates the object can be found in the FXMl file for the scene by its fx:id
    private TextField nameInput; //Represents the text box for the users name.

    //Moves to difficulty selection screen if called, but only if the user has entered a valid name.
    public void goDifficulty(ActionEvent event) {
        if (nameInput.getText().matches("[A-z0-9]+")) { //Checks name against regex.
            User currUser = new User((nameInput.getText()), 0);
            System.out.println(currUser.getName());
            Node node = (Node) event.getSource();
            Stage primaryStage = (Stage) node.getScene().getWindow();
            primaryStage.hide();
            primaryStage.setOnShown(e -> stc.setUser(currUser)); //Calls method in new controller to set name.
            primaryStage.setScene(nextScene);
            primaryStage.show();
        } else { //Displays a dialog box with a warning that the name chosen is invalid.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText("Who are you?");
            alert.setContentText("Please choose a username with only letters or numbers.");
            alert.showAndWait();
        }
    }

    //Moves to admin login screen when called.
    public void goLogin(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
}

