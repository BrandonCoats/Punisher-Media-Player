package com.punisher.play;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.JFrame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class PlaylistCreator {
	
	ArrayList<String> totalPlaylist = new ArrayList<String>();
	VBox content = new VBox();
	String p = "default";
	Label label;
	public void CreatePlaylist() {
		
		Stage Playlist = new Stage();
		
		Button addbtn = new Button("Add Song");
		addbtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event)
			{
				
			}
		});
		
		
		Button deletebtn = new Button("Remove Song");
		deletebtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event)
			{
				
			}
		});
		
		 File f = new File("Content/Audio/");// current directory
		
			

		    File[] files = f.listFiles();
		    for (File file : files) {
		       if (file.isDirectory()) {
		           System.out.print("directory:");
		        } else {
		            System.out.print("     file:");
		        }
		       try {
					System.out.println(file.getCanonicalPath());
					p = file.getCanonicalPath();
					 label = new Label(p);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    
		ScrollPane playlist = new ScrollPane();
		playlist.setPrefSize(100, 200);
		
		playlist.setContent(content);
		//add content to the playlist
		content.getChildren().add(label);
		FlowPane flow = new FlowPane();
		flow.setPadding(new Insets(10, 10, 10, 10));
		flow.setStyle("-fx-background-color: darkblue;");
		flow.setHgap(5);
		flow.getChildren().addAll(playlist,addbtn,deletebtn);

		Scene scene = new Scene(flow);
		Playlist.setScene(scene);
		Playlist.show();
		
	}

	public String addSong(String song) {
		
		return song;
	}
	
	public String deleteSong(String song) {
		
		return song;
	}
}
