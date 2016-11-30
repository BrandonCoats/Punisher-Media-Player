package com.punisher.play;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

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
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Player extends Application{
	public void start(Stage primaryStage) throws Exception
	{
		primaryStage.setWidth(550);
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

			}
		});
		Button pause = new Button("Pause");
		pause.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event)
			{

			}
		});

		Button add = new Button("Add Media");
		add.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				FileInputStream in = null;
				FileOutputStream out = null;
				try {
					FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Media Files", "*.mp4", "*.mp3");
					File file = searchFile(primaryStage, filter);
					String path = null;
					if(FilenameUtils.getExtension(file.getPath()).equals("mp4")){
						path = "Content/Video/";
					}else if(FilenameUtils.getExtension(file.getPath()).equals("mp3")){
						path = "Content/Audio/";
					}
					File copy = new File(path + file.getName());			

					in = new FileInputStream(file);
					out = new FileOutputStream(copy);
					
					byte[] buffer = new byte[1024];
					int length;
					
					while((length = in.read(buffer))> 0){
						out.write(buffer, 0, length);
					}
							
				} catch (IOException e) {
					e.printStackTrace();
				}
				finally {
					if(in != null){
						try {
							in.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if(out != null){
						try {
							out.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				}

			}

		});

		ScrollPane playlist = new ScrollPane();
		playlist.setPrefSize(100, 200);
		FlowPane flow = new FlowPane();
		flow.setPadding(new Insets(10, 10, 10, 10));
		flow.setStyle("-fx-background-color: DAE6F3;");
		flow.setHgap(5);
		flow.getChildren().addAll(playlist,add,Rewind,pause,music, video,FastForward);

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
