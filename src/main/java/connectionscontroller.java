import java.io.FileNotFoundException;
import java.util.Random;

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
    Button button;



    String[] fortunes = {"miaau", "sd", "sdf"};

    @FXML
    public void initialize() throws FileNotFoundException{
        for (int i = 0; i < 16; i++) {
            ToggleButton button = new ToggleButton("Button " + (i + 1));
            int row = i % 4;
            int col = i / 4;
            
            GridPane.setRowIndex(button, row);
            GridPane.setColumnIndex(button, col);
            grid.getChildren().add(button);
        }
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
