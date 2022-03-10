package mines;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MinesFX extends Application {
private Button buttons[];
private GridPane grid;
private HBox hbox;
private Mines game;
private int height,width,numMines;
private Controller controller;

public static void main(String[] args)
{
	launch(args);
}
	@Override
	public void start(Stage stage) throws Exception { //launching javafx 
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(getClass().getResource("minesweeper.fxml")); //the loader's source
		hbox=loader.load();
		controller.setMinesFX(this); //setting controller 
		//setting dimensions
		height=10;
		width=10;
		numMines=10;
		grid=makeGrid(10,10); //setting grid dimensions
		hbox.getChildren().add(grid); 
		game=new Mines(height,width,numMines);
		Scene scene=new Scene(hbox);//scenes of the game
		stage.setScene(scene);
		stage.setTitle("The Amazing Mines Sweeper");
		stage.show();
	}
class Game implements EventHandler<ActionEvent>
{
	@Override
	public void handle(ActionEvent event) {//giving action to pressed buttons
		height=controller.h;
		width=controller.w;
		numMines=controller.m;
		hbox.getChildren().remove(1);
		grid=makeGrid(height,width);
		hbox.getChildren().add(grid);
		game=new Mines(height,width,numMines);
	}
}
class play implements EventHandler<ActionEvent>
{
	private int i,j;
	public play(int i,int j)
	{
		this.i=i;
		this.j=j;
	}
	@Override
	public void handle(ActionEvent event) {
		if(game.open(i,j)==false)
			game.setShowAll(true);
		displayGame();
		if(game.isDone())//finished game 
		{
			VBox root=new VBox();
			root.setPadding(new Insets(20));
			root.getChildren().add(new Label("You Won!"));
			Scene scene=new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
		}
	}
}
private void displayGame()
{
	for(int i=0;i<height;i++)
	{
		for(int j=0;j<width;j++)
		{
			buttons[i*width+j].setText(game.get(i, j));
			if(buttons[i*width+j].getText()!=".")
				buttons[i*width+j].setDisable(true);
		}
	}
}
private GridPane makeGrid(int height,int width)
{
	GridPane grid=new GridPane();
	grid.setPadding(new Insets(3));
	grid.setHgap(1);
	grid.setVgap(1);
	grid.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
	ColumnConstraints col=new ColumnConstraints();
	col.setHalignment(HPos.RIGHT);
	grid.getColumnConstraints().add(col);
	buttons=new Button[height*width];
	for(int i=0;i<height;i++)
	{
		for(int j=0;j<width;j++)
		{
			buttons[i*width+j]=new Button(".");
			buttons[i*width+j].setFont(new Font("Arial",19));
			grid.add(buttons[i*width+j], j, i);
			buttons[i*width+j].setOnAction(new play(i,j));
		}
	}
	return grid;
}
}
