package quizeeRascal;

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

    private ArrayList<String[]> credentials; //List of credentials to be populated by the admin.csv file.
    private String[] credential; //A single credential
    private adminController s7c;
    private Scene nextScene;
    private Scene prevScene;

    @FXML
    private TextField user;

    @FXML
    private PasswordField password; //Object for the password field.

    public void setS7c(adminController s7c) {
        this.s7c = s7c;
    }

    public void setNextScene(Scene nextScene) {
        this.nextScene = nextScene;
    }

    public void setPrevScene(Scene nextScene) {
        this.prevScene = prevScene;
    }

    //Method that reads the admin.csv and uses its data to populate the credentials list.
    public ArrayList<String[]> loadCredentials() {
        String csvPath = "C:\\Users\\Admin\\IdeaProjects\\test6\\src\\quizeeRascal\\data\\admin.csv"; //Path to file.
        ArrayList<String[]> credentials = new ArrayList();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
            String row;
            while ((row = csvReader.readLine()) != null) { //Reads file line by line until it reaches a blank.
                String[] data = row.split(","); //Splits CSV row into separate values.
                credentials.add(data); //Add this to the list of credentials/
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return credentials; //Return the list of credentials.
    }

    //Method to check the username is a genuine admin and the password matches.
    private boolean validate() {
        this.credentials = loadCredentials(); //Calls method to load credentials from csv.
        Iterator<String[]> adminIter = this.credentials.iterator(); //Iterator object to loop through credentials list.
        String[] currAcc;
        while(adminIter.hasNext()) { //Loops through credentials until the end.
            currAcc = adminIter.next();
            if(this.user.getText().equals(currAcc[0])){ //If user exists...
                if(hasher(password.getText()).equals(currAcc[1])){ //Check password hash (MD5) matches that in the csv...
                    return true; //Returns true if password is correct for user.
                }
                else{
                    return false; //Returns false if password is wrong.
                }
            } else {
                continue; //Continues to check all credentials for user match.
            }
        }
        return false; //Returns false if user is not found.
    }

    public void login(ActionEvent event) {
        if(validate()){ //If validate returns true...
            goAdmin(event); //Call goAdmin to launch admin panel.
        } else { //If validate returns false...
            Alert alert = new Alert(Alert.AlertType.ERROR); //Creates alert dialog object.
            alert.setTitle("Error");
            alert.setHeaderText("Login failed.");
            alert.setContentText("Username or Password incorrect.");
            alert.showAndWait(); //Shows alert dialog to the user to inform them their login has failed.
        }
    }

    //Method to switch to admin panel upon successful login.
    private void goAdmin(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.hide();
        primaryStage.setOnShown(e -> s7c.loadTable());
        primaryStage.setScene(nextScene);
        primaryStage.show();
    }

    //Method to switch back to main user screen.
    private void goBack(ActionEvent event) {
        Node node = (Node) event.getSource();
        Stage primaryStage = (Stage) node.getScene().getWindow();
        primaryStage.hide();
        primaryStage.setScene(prevScene);
        primaryStage.show();
    }

    //Method which converts user input for password into an MD5 hash to compare to hashes in admin.csv, for secure storage of passwords.
    private String hasher (String unhashed){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5"); //Selects MD5 as hashing algorithm.
            byte[] messageDigest = md.digest(unhashed.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashstring = no.toString(16); //Converts hash to something readable.
            while (hashstring.length() < 32) {
                hashstring = "0" + hashstring;
            }
            return hashstring; //Returns MD5 hash as a string value for comparison in validate method.
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

}
