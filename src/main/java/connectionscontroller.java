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
import javafx.scene.Node;

import javafx.fxml.FXML;

public class connectionscontroller {
    @FXML
    Label title;

    @FXML
    Label lives;

    @FXML
    GridPane grid;

    // @FXML
    // ToggleButton tb0;

    @FXML
    Button submitButton;

    @FXML
    Button shuffleButton;

    @FXML
    Button replayButton;

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

    String[] expertCategories =  {
        "HORROR",
        "ASD",
        ""
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
    int numLives = 4;

    @FXML
    public void initialize() {
        System.out.println("INITIALIZE");
        numPress = 0;
        numLives = 4;
        lives.setText("Lives: " + Integer.toString(numLives));
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

    private boolean areWordsIn(String[] selectedWords, List<String> checkWords) {
        for (int i = 0; i < 4; i ++) {
            String word = selectedWords[i];
            if (!checkWords.contains(word)) {
                return false;
            }
        }

        return true;

    }

    public void shuffleButtonClicked() {
        List<Node> buttons = new ArrayList<>();
        List<Node> notButtons = new ArrayList<>();
        for(Node node: grid.getChildren()) {
            if (node.getId() != null) {
                buttons.add(node);
            } else {
                notButtons.add(node);
            }
        }

        Collections.shuffle(buttons);
        grid.getChildren().clear();
        grid.getChildren().addAll(notButtons);
        int gridSize = 4;
        for (int i = 0; i < buttons.size(); i++) {
            grid.add(buttons.get(i), i / gridSize, i % gridSize);
        }
    }

    public void replayButtonClicked() {
        List<Node> notButtons = new ArrayList<>();
        for(Node node: grid.getChildren()) {
            if (node.getId() == null) {
                notButtons.add(node);
            }
        }
        grid.getChildren().clear();
        grid.getChildren().addAll(notButtons);
        initialize();
    }

    public void submitButtonClicked() {
        if (numPress != 4){
            System.out.println("Too few options selected");
            return;
        }

        if (numLives <= 0) {
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

        String category = null;
        if (this.areWordsIn(selectedWords, Arrays.asList(easies[easyIndex]))) {
            System.out.println("Congrats, you got the Easies!");
            
        } else if (this.areWordsIn(selectedWords, Arrays.asList(mediums[mediumIndex]))) {
            System.out.println("Congrats, you got the Mediums!");
        } else if (this.areWordsIn(selectedWords, Arrays.asList(hards[hardIndex]))) {
            System.out.println("Congrats, you got the Hards!");
           // category = hardCategories[hardIndex];
        } else if (this.areWordsIn(selectedWords, Arrays.asList(experts[expertsIndex]))) {
            System.out.println("Congrats, you got the Expers!");
           // category = expertCategories[expertsIndex];
        }

        if (
            this.areWordsIn(selectedWords, Arrays.asList(easies[easyIndex])) || 
            this.areWordsIn(selectedWords, Arrays.asList(mediums[mediumIndex])) ||
            this.areWordsIn(selectedWords, Arrays.asList(hards[hardIndex])) ||
            this.areWordsIn(selectedWords, Arrays.asList(experts[expertsIndex]))
        ) {
            // Disable the buttons that are selected
            for(Node button: grid.getChildren()) {
                String idStr = button.getId();
                if (idStr != null) {
                    Integer id = Integer.parseInt(button.getId());
                    if (clickedIndexes.contains(id)) {
                        button.setDisable(true);
                    }
                }
            } 

            numPress = 0;
           // categoryLabel.setValue(category);

        } else {
            numLives -= 1;
            lives.setText("Lives: " + Integer.toString(numLives));
            if (numLives <= 0) {
                System.out.println("GAME OVER");
                for(Node button: grid.getChildren()) {
                    String idStr = button.getId();
                    if (idStr != null) {
                        button.setDisable(true);
                    }
                } 
            }
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
