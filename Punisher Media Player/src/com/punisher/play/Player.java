package com.punisher.play;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Player extends Application{
	public void start(Stage primaryStage) throws Exception
	{
		Button button = new Button("Play Music");
		button.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent event)
		{
			VideoFile video = new VideoFile();
			video.PlayVideoFile(primaryStage);
			//AudioFile audio = new AudioFile();
			//audio.PlayAudioFile();
		}
		});
		StackPane root = new StackPane();
		root.getChildren().add(button);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void Launcher()
	{
		launch();
	}
	public void SearchFile(Stage primaryStage)
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose a file");
		//primaryStage.addEventHandler(eventType, eventHandler);
	}
}
