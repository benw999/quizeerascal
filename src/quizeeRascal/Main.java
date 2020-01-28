package quizeeRascal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setOnCloseRequest(event -> controlledShutdown(primaryStage));

            //Set up Scene 1 (Main)
            FXMLLoader loaderOne = new FXMLLoader(getClass().getResource("main.fxml"));
            GridPane rootOne = loaderOne.load();
            Scene sceneOne = new Scene(rootOne);
            //[STYLESHEET]

            //Set up Scene 2 (Difficulty)
            FXMLLoader loaderTwo = new FXMLLoader(getClass().getResource("difficulty.fxml"));
            GridPane rootTwo = loaderTwo.load();
            Scene sceneTwo = new Scene(rootTwo);
            //[STYLESHEET]

            //Set up Scene 3 (Quiz)
            FXMLLoader loaderThree = new FXMLLoader(getClass().getResource("quiz.fxml"));
            GridPane rootThree = loaderThree.load();
            Scene sceneThree = new Scene(rootThree);

            //Set up Scene 4 (Finish)
            FXMLLoader loaderFour = new FXMLLoader(getClass().getResource("finish.fxml"));
            GridPane rootFour = loaderFour.load();
            Scene sceneFour = new Scene(rootFour);

            //Set up Scene 5 (Scoreboard)
            FXMLLoader loaderFive = new FXMLLoader(getClass().getResource("scoreboard.fxml"));
            GridPane rootFive = loaderFive.load();
            Scene sceneFive = new Scene(rootFive);

            Controller soc = loaderOne.getController();
            difficultyController stc = loaderTwo.getController();
            quizController s3c = loaderThree.getController();
            finController s4c = loaderFour.getController();
            scoreboardController s5c = loaderFive.getController();

            soc.setNextScene(sceneTwo);
            soc.setStc(stc);
            stc.setNextScene(sceneThree);
            stc.setS3c(s3c);
            s3c.setNextScene(sceneFour);
            s3c.setS4c(s4c);
            s4c.setNextScene(sceneFive);
            s4c.setS5c(s5c);
            s4c.setFirstScene(sceneOne);


            primaryStage.setScene(sceneOne);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void controlledShutdown(Stage primaryStage){
        primaryStage.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

//primaryStage.setScene(new Scene(root, 472, 160));