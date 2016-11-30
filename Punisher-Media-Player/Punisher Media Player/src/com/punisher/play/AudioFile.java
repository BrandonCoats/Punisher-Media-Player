package com.punisher.play;
import java.io.File;


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

import java.io.File;

public class AudioFile {
	
	private File file;
	private String fileName;
	
	//To do store more info such as artist, song title, album
	
	public AudioFile()
	{
		fileName = "Content/Audio/Elgar - Nimrod (from Enigma Variations).mp3";
	}
	
	public AudioFile(String fName)
	{
		fileName = fName;
	}
	
	public AudioFile(File file)
	{
		this.file = file;;
	}
	
	public String getFileName()
	{
		return fileName;
	}
	
	public void PlayAudioFile(Stage stage)
	{
		Media sound = new Media(file.toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		
		
		MediaView viewer = new MediaView(mediaPlayer);
	
		viewer.setPreserveRatio(true);


		StackPane root = new StackPane();
		root.getChildren().add(viewer);

		//set the Scene
		Scene scenes = new Scene(root, 500, 500, Color.BLACK);
		stage.setScene(scenes);
		stage.setTitle("Hard Coded audio");
		    
		stage.show();   
		
		mediaPlayer.play();
	}
	
}
