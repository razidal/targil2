package SimpleFx2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {//controller to initialize given elements 
private int i=0;
    @FXML
    private Button button1;//the first button

    @FXML
    private Label label;//the label that holds the amount of votes

    @FXML
    private Button button2;//the second button

    @FXML
    void LabelIncreaser(ActionEvent event) {//the method for the first button that increases the number of votes
    	i++;
    	label.setText(""+i);
    }

    @FXML
    void LabelDecreaser(ActionEvent event) {//the method for the second button that decreases the number of votes
    	i--;
    	label.setText(""+i);
    }


}
