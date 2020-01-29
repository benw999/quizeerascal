package quizeeRascal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class scoreboardController {

    private finController s4c;
    private Scene prevScene;
    private final ObservableList<User> dataList = FXCollections.observableArrayList();

    @FXML
    private TableColumn userCol;
    @FXML
    private TableColumn scoreCol;
    @FXML
    private TableView<User> scoreboard;

    public void setS4c(finController s4c) {
        this.s4c = s4c;
    }

    public void setPrevScene(Scene prevScene) {
        this.prevScene = prevScene;
    }

    public void loadScores(){
        userCol.setCellValueFactory(new PropertyValueFactory("name"));
        scoreCol.setCellValueFactory(new PropertyValueFactory("score"));
        String csvPath = "C:\\Users\\Admin\\IdeaProjects\\test6\\src\\quizeeRascal\\data\\scores.csv";
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                System.out.println(data[0] + " " + data[1]);
                User user = new User(data[0], Integer.parseInt(data[1]));
                dataList.add(user);
            }
            csvReader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        scoreboard.getSortOrder().add(scoreCol);
        scoreboard.setItems(dataList);
    }

    public void goBack(ActionEvent event) {
        scoreboard.getItems().clear();
        dataList.clear();
        System.out.println(dataList.size());
        Node node = (Node) event.getSource();
        Stage primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(prevScene);
        primaryStage.show();
    }
}
