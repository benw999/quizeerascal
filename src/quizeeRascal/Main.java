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
            FXMLLoader loaderOne = new FXMLLoader(getClass().getResource("scenes\\main.fxml"));
            GridPane rootOne = loaderOne.load();
            Scene sceneOne = new Scene(rootOne);
            //[STYLESHEET]

            //Set up Scene 2 (Difficulty)
            FXMLLoader loaderTwo = new FXMLLoader(getClass().getResource("scenes\\difficulty.fxml"));
            GridPane rootTwo = loaderTwo.load();
            Scene sceneTwo = new Scene(rootTwo);
            //[STYLESHEET]

            //Set up Scene 3 (Quiz)
            FXMLLoader loaderThree = new FXMLLoader(getClass().getResource("scenes\\quiz.fxml"));
            GridPane rootThree = loaderThree.load();
            Scene sceneThree = new Scene(rootThree);

            //Set up Scene 4 (Finish)
            FXMLLoader loaderFour = new FXMLLoader(getClass().getResource("scenes\\finish.fxml"));
            GridPane rootFour = loaderFour.load();
            Scene sceneFour = new Scene(rootFour);

            //Set up Scene 5 (Scoreboard)
            FXMLLoader loaderFive = new FXMLLoader(getClass().getResource("scenes\\scoreboard.fxml"));
            GridPane rootFive = loaderFive.load();
            Scene sceneFive = new Scene(rootFive);

            //Set up Scene 6 (Admin Login)
            FXMLLoader loaderSix = new FXMLLoader(getClass().getResource("scenes\\admin_login.fxml"));
            GridPane rootSix = loaderSix.load();
            Scene sceneSix = new Scene(rootSix);

            //Set up Scene 7 (Admin Panel)
            FXMLLoader loaderSeven = new FXMLLoader(getClass().getResource("scenes\\admin.fxml"));
            GridPane rootSeven = loaderSeven.load();
            Scene sceneSeven = new Scene(rootSeven);

            Controller soc = loaderOne.getController();
            difficultyController stc = loaderTwo.getController();
            quizController s3c = loaderThree.getController();
            finController s4c = loaderFour.getController();
            scoreboardController s5c = loaderFive.getController();
            loginController s6c = loaderSix.getController();
            adminController s7c = loaderSeven.getController();

            soc.setNextScene(sceneTwo);
            soc.setStc(stc);
            soc.setLoginScene(sceneSix);

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