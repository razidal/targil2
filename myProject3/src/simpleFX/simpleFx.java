package simpleFX;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class simpleFx extends Application{
private int i;
	public static void main(String[] args) { //a main to launch javafx show
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		Scene scene=new Scene(createRoot());//creating the whole show 
		stage.setScene(scene);
		stage.setTitle("Voting Machine");//the name of the javafx 
		stage.show();
	}
	private VBox createRoot() //method to create scenes on stage
	{
		Label label=new Label("0");//the initial voting counter 
		Button button1=new Button ("Ofra Haza");//the first button 
		
		class LabelIncreaser implements EventHandler<ActionEvent>{ //the action for the button to do when pressed 
			@Override
			public void handle(ActionEvent event) { //an event for the first button increases the votes when pressed
				i++;
				label.setText(""+i);
			}
		}
		Button button2=new Button("Yardena Arazi");//the second button
		class LabelDecreaser implements EventHandler<ActionEvent>{
			@Override
			public void handle(ActionEvent event) {//an event for the second button decreases the votes when pressed 
				i--;
				label.setText(""+i);
			}
		}
		button1.setOnAction(new LabelIncreaser());//make button to be pressed on
		button2.setOnAction(new LabelDecreaser());
		button1.setMaxWidth(Double.MAX_VALUE);//the maximum width of the button display 
		button2.setMaxWidth(Double.MAX_VALUE);
		Label space=new Label();//used to have a space between the displayed buttons on screen
		space.setPadding(new Insets(10));
		HBox hbox=new HBox(button1,space,button2);//used to have the two buttons on screen with a space between them
		VBox vbox=new VBox(hbox,label);// used to include the hbox and the number of votes underneath the buttons
		vbox.setMaxWidth(Double.MAX_VALUE);//the maximum width of everything in the vbox 
		vbox.setAlignment(Pos.CENTER);//aligning the vbox to be at the center of the displayed window
		vbox.setPadding(new Insets(10));//sets the borders of the vbox
		hbox.setPadding(new Insets(10));//sets the borders of the hbox
		BackgroundFill bgfill=new BackgroundFill(Color.RED,new CornerRadii(0),null);//sets a color for the background of the label
		Background bg=new Background(bgfill);
		label.setPadding(new Insets(10));//the label is amount of votes displayed
		label.setBackground(bg); //sets the background for the label
		label.setMaxWidth(Double.MAX_VALUE);
		label.setAlignment(Pos.CENTER);
		return vbox;
		
	}
}
