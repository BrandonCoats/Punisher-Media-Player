package com.punisher.play;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class VideoFile {
	

	private String fileName;
	
	//To do store more info such as artist, song title, album
	
	public VideoFile()
	{
		fileName = "Content/Video/Wildlife.wmv";
	}
	
	public VideoFile(String fName)
	{
		fileName = fName;
	}
	
	public String getFileName()
	{
		return fileName;
	}
	
	public void PlayVideoFile()
	{
		//video works a little different
		//Media sound = new Media(new File(fileName).toURI().toString());
		//MediaPlayer mediaPlayer = new MediaPlayer(sound);
		//mediaPlayer.play();
	}
}
