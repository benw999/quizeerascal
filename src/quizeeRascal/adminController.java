package quizeeRascal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class adminController {

    private final ObservableList<Question> dataList = FXCollections.observableArrayList();

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

    public void loadTable(){
        questionCol.setCellValueFactory(new PropertyValueFactory("question"));
        aCol.setCellValueFactory(new PropertyValueFactory("A"));
        bCol.setCellValueFactory(new PropertyValueFactory("B"));
        cCol.setCellValueFactory(new PropertyValueFactory("C"));
        correctCol.setCellValueFactory(new PropertyValueFactory("correct"));

        String csvPath = "C:\\Users\\Admin\\IdeaProjects\\test6\\src\\quizeeRascal\\data\\EASY.csv";
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

    public void delete(){
        System.out.println("Delete");
    }

    public void save(){
        System.out.println("Save");
    }

    public void add(){
        System.out.println("Add");
    }
}
