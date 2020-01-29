package quizeeRascal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class adminController {

    private final ObservableList<Question> dataList = FXCollections.observableArrayList();
    private loginController s6c;
    private Scene lastScene;

    public void setS6c(loginController s6c) {
        this.s6c = s6c;
    }

    public void setLastScene(Scene lastScene) {
        this.lastScene = lastScene;
    }

    @FXML
    private TableColumn questionCol;
    @FXML
    private TableColumn aCol;
    @FXML
    private TableColumn bCol;
    @FXML
    private TableColumn cCol;
    @FXML
    private TableColumn correctCol;
    @FXML
    private TableView<Question> adminTable;
    @FXML
    private MenuButton dropdown;

    public void loadTable(){
        dataList.clear();
        adminTable.getItems().clear();
        questionCol.setCellValueFactory(new PropertyValueFactory("question"));
        aCol.setCellValueFactory(new PropertyValueFactory("A"));
        bCol.setCellValueFactory(new PropertyValueFactory("B"));
        cCol.setCellValueFactory(new PropertyValueFactory("C"));
        correctCol.setCellValueFactory(new PropertyValueFactory("correct"));

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Please note:");
        alert.setContentText("This section only offers the ability to view questions & answers. Editing capabilities are not complete.");
        alert.showAndWait();
    }

    public void goBack(ActionEvent event) {
        dropdown.setText("Difficulty");
        Node node = (Node) event.getSource();
        Stage primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(lastScene);
        primaryStage.show();
    }

    public void setEasy() {
        dropdown.setText("Easy");
        popTable("EASY");
    }

    public void setMedium() {
        dropdown.setText("Medium");
        popTable("MEDIUM");
    }

    public void setHard() {
        dropdown.setText("Hard");
        popTable("HARD");
    }

    private void popTable(String difficulty) {
        dataList.clear();
        adminTable.getItems().clear();
        String csvPath = "C:\\Users\\Admin\\IdeaProjects\\test6\\src\\quizeeRascal\\data\\" + difficulty + ".csv";
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                Question question = new Question(data[0], data[1], data[2], data[3], data[4]);
                dataList.add(question);
            }
            csvReader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        adminTable.setItems(dataList);
    }

}
