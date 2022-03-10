package SimpleFx2;


import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Voting2 extends Application {
	public static void main(String[] args)
	{
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception { //method to load the fxml file from scenebuilder
		VBox vbox;
		@SuppressWarnings("unused")
		Controller controller;
		try { //trying to load the scene from SceneBuilder
		FXMLLoader loader=new FXMLLoader(); //loading file
		loader.setLocation(getClass().getResource("simplefx2.fxml"));//getting the location for it
		vbox=loader.load();
		controller=loader.getController();//get the controller file for the scene
		}
		catch (IOException e)//exception caught of loader doesn't work
		{
			e.printStackTrace();
			return;
		}
		//the same here as the previous question
		Scene scene=new Scene(vbox);
		stage.setScene(scene);
		stage.setTitle("Voting Machine");
		stage.show();
	}
}
