import java.io.FileNotFoundException;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    @FXML
    ToggleButton tb0;

    @FXML
    Button button;

    String[] fortunes = {"miaau", "sd", "sdf"};

 

    
    @FXML
    public void initialize() throws FileNotFoundException{
        //title.setText("Fortune teller");
        //buttonClick();
    }
    @FXML
    private void buttonClicked(){
        // e.getButton();
        System.out.println("hi");
        // System.out.println(e.getButton());
        // Random randNum = new Random();
        // fortunetxt.setText(fortunes[randNum.nextInt(fortunes.length)]);
    }

}
