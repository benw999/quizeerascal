package quizeeRascal;

import com.sun.source.tree.TryTree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

public class loginController {
    private ArrayList<String[]> credentials;
    private String[] credential;
    private adminController s7c;
    private Scene nextScene;

    @FXML
    private TextField user;

    @FXML
    private PasswordField password;

    public void setS7c(adminController s7c) {
        this.s7c = s7c;
    }

    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }

    public ArrayList<String[]> loadCredentials() {
        String csvPath = "C:\\Users\\Admin\\IdeaProjects\\test6\\src\\quizeeRascal\\data\\admin.csv";
        ArrayList<String[]> credentials = new ArrayList();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                credentials.add(data);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return credentials;
    }

    private boolean validate() {
        this.credentials = loadCredentials();
        Iterator<String[]> adminIter = this.credentials.iterator();
        String[] currAcc;
        while(adminIter.hasNext()) {
            currAcc = adminIter.next();
            if(this.user.getText().equals(currAcc[0])){
                if(hasher(password.getText()).equals(currAcc[1])){
                    return true;
                }
                else{
                    return false;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    public void login(ActionEvent event) {
        if(validate()){
            goAdmin(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login failed.");
            alert.setContentText("Username or Password incorrect.");
            alert.showAndWait();
        }
    }

    private void goAdmin(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.hide();
        //primaryStage.setOnShown(e -> s7c.setUser(currUser));
        primaryStage.setScene(nextScene);
        primaryStage.show();
    }

    private String hasher (String unhashed){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(unhashed.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashstring = no.toString(16);
            while (hashstring.length() < 32) {
                hashstring = "0" + hashstring;
            }
            return hashstring;
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

}
