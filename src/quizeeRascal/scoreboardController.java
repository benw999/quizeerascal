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
        //Looks for properties in the user that match the columns of the table (name and score).
        scoreboard.getItems().clear(); //Clears table first.
        dataList.clear();
        userCol.setCellValueFactory(new PropertyValueFactory("name"));
        scoreCol.setCellValueFactory(new PropertyValueFactory("score"));
        String csvPath = "C:\\Users\\Admin\\IdeaProjects\\test6\\src\\quizeeRascal\\data\\scores.csv";
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
            String row;
            while ((row = csvReader.readLine()) != null) { //Reads scores file line by line.
                String[] data = row.split(","); //Splits csv row into respective properties.
                User user = new User(data[0], Integer.parseInt(data[1])); //Creates new temporary user instance to insert into table.
                dataList.add(user); //Add this user to a list of users.
            }
            csvReader.close(); //Closes the CSV reader.
        }
        catch(IOException e){
            e.printStackTrace();
        }
        scoreboard.getSortOrder().add(scoreCol); //Sorts scoreboard by score value.
        scoreboard.setItems(dataList); //Populates table with the data loaded from csv.
    }

    //Method to go back to the final screen.
    public void goBack(ActionEvent event) {
        scoreboard.getItems().clear();
        dataList.clear();
        Node node = (Node) event.getSource();
        Stage primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(prevScene);
        primaryStage.show();
    }
}
