package com.punisher.play;



import java.io.File;

import com.sun.javafx.geom.Rectangle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.media.Media;


public class Player extends Application{
	MediaPlayer mediaPlayer;
	MediaView viewer;
	public void start(Stage primaryStage) throws Exception
	{
		Image playimage = new Image("file:playimg.png");
		Button music = new Button("Play", new ImageView(playimage));
		music.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event)
			{
				if(mediaPlayer == null)
				{
				FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Audio Files", "*.mp3");
				File file = searchFile(primaryStage, filter);

					if(file != null){
						AudioFile audio = new AudioFile(file);
						Media sound = audio.returnAudioFile();
						mediaPlayer = new MediaPlayer(sound);
						viewer = new MediaView(mediaPlayer);
					}
				}
				else
				{
				mediaPlayer.play();
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
				if(mediaPlayer == null)
				{
					FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Video Files", "*.mp4");
					File file = searchFile(primaryStage, filter);

					if(file != null){
						VideoFile video = new VideoFile(file);
						Media recording = video.returnVideoFile();
						mediaPlayer = new MediaPlayer(recording);
						
						DoubleProperty width = viewer.fitWidthProperty();
						DoubleProperty height = viewer.fitHeightProperty();
						width.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
						height.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));
						viewer.setPreserveRatio(true);
					}
				}
				else
				{
					mediaPlayer.play();
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
				
			}
		});
		Button pause = new Button("Pause");
		pause.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event)
			{
				mediaPlayer.pause();
			}
		});
		ScrollPane playlist = new ScrollPane();
		playlist.setPrefSize(100, 200);
		FlowPane flow = new FlowPane();
		flow.setPadding(new Insets(10, 10, 10, 10));
		flow.setStyle("-fx-background-color: DAE6F3;");
		flow.setHgap(5);
		flow.getChildren().addAll(playlist,Rewind,pause,music, video, FastForward);
		//flow.getChildren().add(viewer);
		
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
