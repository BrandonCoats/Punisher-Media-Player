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
	
	public Media returnAudioFile()
	{
		Media sound = new Media(file.toURI().toString());
		return sound;
	}
	
}
