package com.punisher.play;
import java.io.File;
import javafx.scene.media.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class AudioFile {
	
	private String fileName;
	
	//To do store more info such as artist, song title, album
	
	public AudioFile()
	{
		fileName = "Content/Audio/Rolling Stones- Satisfaction (Lyrics).mp3";
	}
	
	public AudioFile(String fName)
	{
		fileName = fName;
	}
	
	public String getFileName()
	{
		return fileName;
	}
	
	public void PlayAudioFile()
	{
		Media sound = new Media(new File(fileName).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}
	
}
