package com.punisher.play;

import java.io.File;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class VideoFile {
	
	private File file;
	private String fileName;
	
	//To do store more info such as artist, song title, album
	
	public VideoFile()
	{
		file = new File("Content/Video/SouthParkSeason16Episode3.mp4");
	}
	
	public VideoFile(String fName)
	{
		fileName = fName;
	}
	
	public VideoFile(File file)
	{
		this.file = file;
	}
	
	public String getFileName()
	{
		return fileName;
	}
	
	public void PlayVideoFile(Stage stage)
	{
		//video works a little different
		Media sound = new Media(file.toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		MediaView viewer = new MediaView(mediaPlayer);
		
		DoubleProperty width = viewer.fitWidthProperty();
		DoubleProperty height = viewer.fitHeightProperty();
		width.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
		height.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));
		viewer.setPreserveRatio(true);


		StackPane root = new StackPane();
		root.getChildren().add(viewer);

		//set the Scene
		Scene scenes = new Scene(root, 500, 500, Color.BLACK);
		stage.setScene(scenes);
		stage.setTitle("Hard Coded video");
		stage.setFullScreen(true);
		stage.show();   
		 
		mediaPlayer.play();
	}
}
