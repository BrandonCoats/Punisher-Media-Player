package com.punisher.play;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class PlaylistCreator extends JPanel {
	
	ArrayList<String> mplaylist = new ArrayList<String>();
	VBox content = new VBox();
	String p = "default";
	Label label;
	JList list;
	DefaultListModel model;
    int counter = 15;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void CreatePlaylist() {
		
		JFrame p = new JFrame();
		
		    model = new DefaultListModel();
		    list = new JList(model);
		  	JScrollPane pane = new JScrollPane(list);
		    JButton addButton = new JButton("Add Song");
		    JButton removeButton = new JButton("Remove Song");
		    for (int i = 0; i < 15; i++)
		      model.addElement("Element " + i);

		    addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent arg0) {
				// TODO Auto-generated method stub
				 model.addElement("Element " + counter);
			        counter++;
			}
		    });
		    removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				// TODO Auto-generated method stub
				 if (model.getSize() > 0)
			          model.removeElementAt(0);
			}
		    });

		
		    p.add(pane,BorderLayout.NORTH);
		    p.add(addButton,BorderLayout.WEST);
		    p.add(removeButton,BorderLayout.EAST);
		    p.setSize(300, 300);
		    p.setVisible(true);
		  
	}
		 
		  
	
public void MakePlaylist() { 
	
	Stage Playlist = new Stage();
	
	Button makeplaylistbtn = new Button("Create Playlist");
	makeplaylistbtn.setOnAction(new EventHandler<ActionEvent>(){

		@Override
		public void handle(ActionEvent event)
		{
			CreatePlaylist();
		}
	});
	
	//Code that adds the contents of the folder to the vbox inside of the scrollpane
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
	 //-----------------------------------------------------------------
	    
	ScrollPane playlist = new ScrollPane();
	playlist.setPrefSize(100, 200);
	
	playlist.setContent(content);
	//add content to the playlist
	content.getChildren().add(label);
	FlowPane flow = new FlowPane();
	flow.setPadding(new Insets(10, 10, 10, 10));
	flow.setStyle("-fx-background-color: black;");
	flow.setHgap(5);
	flow.getChildren().addAll(playlist,makeplaylistbtn);

	Scene scene = new Scene(flow);
	Playlist.setScene(scene);
	Playlist.show();
	
	
		
	}
}
