package quizeeRascal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class loginController {
    private ArrayList<String[]> credentials;
    private String[] credential;

    public void loadCredentials() {
        String csvPath = "C:\\Users\\Admin\\IdeaProjects\\test6\\src\\quizeeRascal\\data\\admin.csv";
        ArrayList<String[]> credentials = new ArrayList();
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                this.credentials.add(data);
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
