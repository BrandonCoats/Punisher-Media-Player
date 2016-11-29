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
	
	/*public void PlayVideoFile(Stage stage, MediaPlayer mediaPlayer)
	{
		//video works a little different
		Media sound = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		
		
		


		StackPane root = new StackPane();
		root.getChildren().add(viewer);

		//set the Scene
		Scene scenes = new Scene(root, 500, 500, Color.BLACK);
		stage.setScene(scenes);
		stage.setTitle("Hard Coded video");
		stage.setFullScreen(true);
		stage.show();   
		 
		mediaPlayer.play();
	}*/
	
	public Media returnVideoFile()
	{
		Media video = new Media(file.toURI().toString());
		return video;
	}
}
