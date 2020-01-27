package quizeeRascal;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.text.Text;

public class finController {

    public void setNextScene(Scene nextScene) {
    }

    public void setS4c(finController s4c) {
    }

    @FXML
    private Text finText;

    @FXML
    private Text scoreText;

    public void finQuiz(User currUser){
        finText.setText("Well done " + currUser.getName() + "!");
        scoreText.setText("You scored " + currUser.getScore() + "/10!");
    }
}
