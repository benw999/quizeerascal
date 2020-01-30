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

    private final ObservableList<Question> dataList = FXCollections.observableArrayList(); //List of question objects for table.
    private loginController s6c;
    private Scene lastScene;

    public void setS6c(loginController s6c) {
        this.s6c = s6c;
    }

    public void setLastScene(Scene lastScene) {
        this.lastScene = lastScene;
    }

    @FXML
    private TableColumn questionCol; //Question column.
    @FXML
    private TableColumn aCol; //Answer A column.
    @FXML
    private TableColumn bCol; //Answer B column.
    @FXML
    private TableColumn cCol; //Answer C column.
    @FXML
    private TableColumn correctCol; //Correct answer column.
    @FXML
    private TableView<Question> adminTable; //Entire table object to display questions and answers.
    @FXML
    private MenuButton dropdown; //Drop down to select difficulty level.

    //Method which loads an (empty) table - called on launch of scene.
    public void loadTable(){
        dataList.clear(); //Clears list of data.
        adminTable.getItems().clear(); //Clears table if already populated.
        //Looks for properties in the question object which match the columns.
        questionCol.setCellValueFactory(new PropertyValueFactory("question"));
        aCol.setCellValueFactory(new PropertyValueFactory("A"));
        bCol.setCellValueFactory(new PropertyValueFactory("B"));
        cCol.setCellValueFactory(new PropertyValueFactory("C"));
        correctCol.setCellValueFactory(new PropertyValueFactory("correct"));

        //Alert dialog to inform the user the admin feature is not complete.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Please note:");
        alert.setContentText("This section only offers the ability to view questions & answers. Editing capabilities are not complete.");
        alert.showAndWait();
    }

    //Method which goes back to admin login screen.
    public void goBack(ActionEvent event) {
        dropdown.setText("Difficulty");
        Node node = (Node) event.getSource();
        Stage primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(lastScene);
        primaryStage.show();
    }

    //Collection of methods to populate table with a difficulty selected by the user...
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

    //Method which populates the table with question data from the CSV.
    private void popTable(String difficulty) {
        dataList.clear(); //Clears list of data from previous loading attempts.
        adminTable.getItems().clear(); //Clears table of previous data.
        String csvPath = "C:\\Users\\Admin\\IdeaProjects\\test6\\src\\quizeeRascal\\data\\" + difficulty + ".csv"; //Path to question data.
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                Question question = new Question(data[0], data[1], data[2], data[3], data[4]);
                dataList.add(question); //Adds question data to list of questions.
            }
            csvReader.close(); //Closes reader.
        }
        catch(IOException e){
            e.printStackTrace();
        }
        adminTable.setItems(dataList); //Sets rows of table to each question and their answers.
    }

}
