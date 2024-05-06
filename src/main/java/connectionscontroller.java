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


    // Pick a random set of 4 words for each category
    Random random = new Random();
    int easyIndex = random.nextInt(easies.length);
    int mediumIndex = random.nextInt(mediums.length);
    int hardIndex = random.nextInt(hards.length);
    int expertsIndex = random.nextInt(experts.length);
    
    // Map<String,String[]> wordMap = new HashMap<>();
    ArrayList<String> wordsList;


    int maxPress = 3;
    int numPress = 0;


    @FXML
    public void initialize() throws FileNotFoundException{
        wordsList = new ArrayList<>();
        wordsList.addAll(Arrays.asList(easies[easyIndex]));
        wordsList.addAll(Arrays.asList(mediums[easyIndex]));
        wordsList.addAll(Arrays.asList(hards[easyIndex]));
        wordsList.addAll(Arrays.asList(experts[easyIndex]));
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
    
    private void buttonClick(ActionEvent event) {
        ToggleButton clickedButton = (ToggleButton)event.getSource();
        String idString = clickedButton.getId();
        //System.out.println(idString);
        
        boolean isButtonBeingDisabled = !clickedButton.isSelected();
        
        System.out.println(numPress);
        System.out.println(isButtonBeingDisabled);
    
        // If one of the 4 clicked buttons is being turned off, decrement the number of clicked buttons
        if (isButtonBeingDisabled == true){
            numPress -= 1;
        }
        // Otherwise, increment the number of clicked buttons
        else if (numPress <= 3){
            numPress += 1;
        } 
        // If we already have 4 buttons clicked, don't allow any others to click
        else if (numPress > maxPress) {
            clickedButton.setSelected(false);
        } 

        //oggleButton.setText(fortunes[randNum.nextInt(fortunes.length)]);
    }

   

}
