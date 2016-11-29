package com.punisher.play;



import java.io.File;

import com.sun.javafx.geom.Rectangle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Player extends Application{
	public void start(Stage primaryStage) throws Exception
	{
		Image playimage = new Image("file:playimg.png");
		Button music = new Button("Play", new ImageView(playimage));
		music.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event)
			{

				FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Audio Files", "*.mp3");
				File file = searchFile(primaryStage, filter);

				if(file != null){
					AudioFile audio = new AudioFile(file);
					audio.PlayAudioFile(primaryStage);
				}



				//				AudioFile audio = new AudioFile();
				//				audio.PlayAudioFile(primaryStage);
			}
		});

		Button video = new Button("Play Video");
		video.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event)
			{

				FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Video Files", "*.mp4");
				File file = searchFile(primaryStage, filter);

				if(file != null){
					VideoFile video = new VideoFile(file);
					video.PlayVideoFile(primaryStage);
				}

				//
				//				VideoFile video = new VideoFile();
				//				video.PlayVideoFile(primaryStage);
			}
		});
		Button FastForward = new Button(">>");
		FastForward.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event)
			{

			}
		});
		Button Rewind = new Button("<<");
		Rewind.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event)
			{
//this is where you rewind
			}
		});
		Button pause = new Button("Pause");
		pause.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event)
			{

			}
		});
		ScrollPane playlist = new ScrollPane();
		playlist.setPrefSize(100, 200);
		VBox content = new VBox();
		playlist.setContent(content);
//try to add content to the playlist
		Label label = new Label("hello");
		content.getChildren().add(label);
		//hello
		//force upload
		//work
		FlowPane flow = new FlowPane();
		flow.setPadding(new Insets(10, 10, 10, 10));
		flow.setStyle("-fx-background-color: DAE6F3;");
		flow.setHgap(5);
		flow.getChildren().addAll(playlist,Rewind,pause,music, video,FastForward);

		Scene scene = new Scene(flow);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public void Launcher()
	{
		launch();
	}
	public File searchFile(Stage primaryStage, FileChooser.ExtensionFilter filter)
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open file");
		fileChooser.getExtensionFilters().add(filter);
		File file = fileChooser.showOpenDialog(primaryStage);
		return file;

		//primaryStage.addEventHandler(eventType, eventHandler);
	}
}
