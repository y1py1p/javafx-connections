import java.io.FileNotFoundException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import javax.swing.plaf.TreeUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.fxml.FXML;

public class connectionscontroller {
    @FXML
    Label title;

    @FXML
    GridPane grid;

    // @FXML
    // ToggleButton tb0;

    @FXML
    Button submitButton;

    String[][] easies = {
        {"FANCY", "LOVE", "RELISH", "SAVOR", "ENJOY"}, // 0
        {"BAR" ,"CLUB" ,"DISCO" ,"LOUNGE"},
        {"BUTTON" ,"FLY" ,"POCKET" ,"RIVET"},
        {"ACE" ,"CRACKERJACK" ,"EXPERT" ,"HOTSHOT"}
    };

    String[][] mediums = {
        {"EGG" ,"EVERYTHING" ,"PLAIN" ,"POPPY"},
        {"LIKE" ,"LITERALLY" ,"UM" ,"WELL"},
        {"DITCH" ,"DROP" ,"LOSE" ,"SHED"},
        {"CHOCK" ,"JACK" ,"TIRE" ,"WRENCH"}
    };

    String[][] hards = {
        {"ACT" ,"DIRECT" ,"PRODUCE" ,"WRITE"},
        {"CYLINDER" ,"PIN" ,"SPRING" ,"TUMBLER"},
        {"FATHOM" ,"FOOT" ,"LEAGUE" ,"YARD"},
        {"GLADIATOR" ,"HER" ,"JOKER" ,"SIGNS"}
    };

    String[][] experts = {
        {"POPCORN" ,"RAPTURE" ,"ROCKETRY" ,"SOULMATE"},
        {"ANIMAL" ,"DOOR" ,"KINK" ,"SUPREME"},
        {"BAKE" ,"CLEARANCE" ,"GARAGE" ,"SAMPLE"},
        {"CARROT" ,"HURTS" ,"JEWEL" ,"OM"}
    };

    int easyIndex = 0;
    int mediumIndex = 0;
    int hardIndex = 0;
    int expertsIndex = 0;


    // Pick a random set of 4 words for each category
    
    // Map<String,String[]> wordMap = new HashMap<>();
    ArrayList<String> wordsList;

    ArrayList<Integer> clickedIndexes = new ArrayList<>();

    int maxPress = 3;
    int numPress = 0;

    @FXML
    public void initialize() throws FileNotFoundException{
        System.out.println("INITIALIZE");
        Random random = new Random();
        easyIndex = random.nextInt(easies.length);
        mediumIndex = random.nextInt(mediums.length);
        hardIndex = random.nextInt(hards.length);
        expertsIndex = random.nextInt(experts.length);
        wordsList = new ArrayList<>();
        wordsList.addAll(Arrays.asList(easies[easyIndex]));
        wordsList.addAll(Arrays.asList(mediums[mediumIndex]));
        wordsList.addAll(Arrays.asList(hards[hardIndex]));
        wordsList.addAll(Arrays.asList(experts[expertsIndex]));
        Collections.shuffle(wordsList);

        for (int i = 0; i < 16; i++) {
            String word = wordsList.get(i);
            ToggleButton button = new ToggleButton(word);
            button.setId(Integer.toString(i));
            int row = i % 4;
            int col = i / 4;
            
            GridPane.setRowIndex(button, row);
            GridPane.setColumnIndex(button, col);
            button.setOnAction(this::buttonClick);
            grid.getChildren().add(button);
        }
        
    }

    public void submitButtonClicked() {
        if (numPress != 4){
            System.out.println("Too few options selected");
            return;
        }
        
        System.out.println(clickedIndexes);
        String[] selectedWords = {
            wordsList.get(clickedIndexes.get(0)),
            wordsList.get(clickedIndexes.get(1)),
            wordsList.get(clickedIndexes.get(2)),
            wordsList.get(clickedIndexes.get(3)),
        };
        System.out.println(Arrays.toString(selectedWords));

        boolean isEasy = true;
        List<String> easieArrayList = Arrays.asList(easies[easyIndex]);
        System.out.println("Easies: " + easieArrayList.toString());
        for (int i = 0; i < 4; i++) {
            String word = selectedWords[i];
            if (!easieArrayList.contains(word)) {
                isEasy = false;
            }
        }

        if (isEasy == true) {
            System.out.println("Congrats, you got the easies!");
        }

        boolean isMedium = true;
        List<String> mediumArrayList = Arrays.asList(mediums[mediumIndex]);
        System.out.println("Medium: " + mediumArrayList.toString());
        for (int i = 0; i < 4; i++) {
            String word = selectedWords[i];
            if (!mediumArrayList.contains(word)) {
                isMedium = false;
            }
        }

        if (isMedium == true) {
            System.out.println("Congrats, you got the mediums!");
        }
        
        boolean isHard = true;
        List<String> hardArrayList = Arrays.asList(hards[hardIndex]);
        System.out.println("Hard: " + hardArrayList.toString());
        for (int i = 0; i < 4; i++) {
            String word = selectedWords[i];
            if (!hardArrayList.contains(word)) {
                isHard = false;
            }
        }

        if (isHard == true) {
            System.out.println("Congrats, you got the Hards!");
        }

        boolean isExpert = true;
        List<String> expertArrayList = Arrays.asList(experts[expertsIndex]);
        System.out.println("Expert: " + expertArrayList.toString());
        for (int i = 0; i < 4; i++) {
            String word = selectedWords[i];
            if (!expertArrayList.contains(word)) {
                isExpert = false;
            }
        }

        if (isExpert == true) {
            System.out.println("Congrats, you got the Experts!");
        }


    }
    
    private void buttonClick(ActionEvent event) {
        ToggleButton clickedButton = (ToggleButton)event.getSource();
        String idString = clickedButton.getId();
        Integer index = Integer.parseInt(idString);
        //System.out.println(idString);
        boolean isButtonBeingDisabled = !clickedButton.isSelected();
        
        System.out.println(numPress);
        System.out.println(isButtonBeingDisabled);
    
        // If one of the 4 clicked buttons is being turned off, decrement the number of clicked buttons
        if (isButtonBeingDisabled == true){
            numPress -= 1;
            clickedIndexes.remove(index);
        }
        // Otherwise, increment the number of clicked buttons
        else if (numPress <= 3){
            numPress += 1;
            clickedIndexes.add(index);
        } 
        // If we already have 4 buttons clicked, don't allow any others to click
        else if (numPress > maxPress) {
            clickedButton.setSelected(false);
        }

        //oggleButton.setText(fortunes[randNum.nextInt(fortunes.length)]);
    }

   

}
