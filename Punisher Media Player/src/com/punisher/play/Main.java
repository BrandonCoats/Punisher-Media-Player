package com.punisher.play;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main  extends Application{

	public static void main(String[] args) {
		launch();
	}
public void start(Stage primaryStage) throws Exception
{
	Button button = new Button("Play Music");
	StackPane root = new StackPane();
	root.getChildren().add(button);
	Scene scene = new Scene(root);
	primaryStage.setScene(scene);
	primaryStage.show();
}
}
