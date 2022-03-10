package mines;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
public int h,w,m;
private MinesFX g;
public Controller()
{
	h=10;
	w=10;
	m=10;
}
    @FXML
    private TextField mines;

    @FXML
    private TextField width;

    @FXML
    private Button reset;

    @FXML
    private TextField height;

    @FXML
    void startGame(ActionEvent event) {
    	h=Integer.parseInt(height.getText());
    	w=Integer.parseInt(width.getText());
    	m=Integer.parseInt(mines.getText());
    	g.new Game().handle(event);
    }
    public void setMinesFX(MinesFX g)
    {
    	this.g=g;
    }

}
