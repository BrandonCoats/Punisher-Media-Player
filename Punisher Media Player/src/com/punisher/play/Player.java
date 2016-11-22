package com.punisher.play;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
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
			AudioFile audio = new AudioFile();
			audio.PlayAudioFile(primaryStage);
		}
		});
		
		Button video = new Button("Play video");
		video.setOnAction(new EventHandler<ActionEvent>(){
		
		@Override
		public void handle(ActionEvent event)
		{
			VideoFile video = new VideoFile();
			video.PlayVideoFile(primaryStage);
		}
		});
		
		 FlowPane flow = new FlowPane();
		 flow.setPadding(new Insets(10, 10, 10, 10));
		 flow.setStyle("-fx-background-color: DAE6F3;");
		 flow.setHgap(5);
		 flow.getChildren().addAll(button, video);
		
		//StackPane root = new StackPane();
		//root.getChildren().add(button);
		//root.getChildren().add(video);
		Scene scene = new Scene(flow);
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
