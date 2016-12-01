package com.punisher.play;



import java.io.File;

import com.sun.javafx.geom.Rectangle;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Player extends Application{
	MediaPlayer player;
	MediaView mediaView;
	HBox mediaBar;
	BorderPane bp = new BorderPane();
	private Duration duration;
    private Slider timeSlider;
    private Label playTime;
    private Slider volumeSlider;
	
	public void start(Stage primaryStage) throws Exception
	{
		
		ScrollPane playlist = new ScrollPane();
		playlist.setPrefSize(100, 200);
		VBox content = new VBox();
		playlist.setContent(content);
		//try to add content to the playlist
		Label label = new Label("hello");
		content.getChildren().add(label);
		initializeMediaView();
		//hello
		//force upload
		//work
		
		
		bp.setLeft(playlist);

		Scene scene = new Scene(bp);
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
	
	private void initializeMediaView()
	{
		mediaView = new MediaView(player);
		Pane mvPane = new Pane() {};
		mvPane.getChildren().add(mediaView);
        mvPane.setStyle("-fx-background-color: black;");
        DoubleProperty mvw = mediaView.fitWidthProperty();
        DoubleProperty mvh = mediaView.fitHeightProperty();
        mvw.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mvh.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
        bp.setCenter(mvPane);
        
        initializeMediaBar();
	}
	
	private void initializeMediaBar()
	{
		mediaBar = new HBox();
		mediaBar.setAlignment(Pos.CENTER);
        mediaBar.setPadding(new Insets(5, 10, 5, 10));
        BorderPane.setAlignment(mediaBar, Pos.CENTER);
        
        Image playimage = new Image("file:playimg.png");
		Button music = new Button("Load Music", new ImageView(playimage));
		music.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event)
			{

				FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Audio Files", "*.mp3");
				File file = searchFile((Stage) mediaBar.getScene().getWindow(), filter);

				if(file != null){
					if(player == null || player.getMedia() == null)
					{
						AudioFile audio = new AudioFile(file);

						//audio.PlayAudioFile(primaryStage);
						Media sound = audio.returnAudioFile();
						player = new MediaPlayer(sound);
						initializeMediaView();
						duration = player.getMedia().getDuration();
						//CheckMediaStatus();
					}
					else
					{
						//CheckMediaStatus();
					}
					//	audio.PlayAudioFile(primaryStage);
				}
				



				//				AudioFile audio = new AudioFile();
				//				audio.PlayAudioFile(primaryStage);
			}
		});
		if(player != null)
		{
			player.currentTimeProperty().addListener(new InvalidationListener() {
	            public void invalidated(Observable ov) {
	                updateValues();
	            }
	        });
		}
		

		Button video = new Button("Load Video");
		video.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event)
			{

				FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Video Files", "*.mp4");
				File file = searchFile((Stage) mediaBar.getScene().getWindow(), filter);

				if(file != null){
					if(player == null || player.getMedia() != null)
					{
						VideoFile video = new VideoFile(file);
						//video.PlayVideoFile(primaryStage);
						Media recording = video.returnVideoFile();
						player = new MediaPlayer(recording);
						initializeMediaView();
						duration = player.getMedia().getDuration();
						//CheckMediaStatus();
					}
					else
					{
						//CheckMediaStatus();
					}
				}
				

				//
				//				VideoFile video = new VideoFile();
				//				video.PlayVideoFile(primaryStage);
			}
		});
		Button pause = new Button("Pause");
		pause.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event)
			{
				player.pause();
			}
		});
		
		Button play = new Button("Play");
		play.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event)
			{
				player.play();
			}
		});
		
		
		Button playlistbtn = new Button("PlayList");
		playlistbtn.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event)
			{
				PlaylistCreator pc = new PlaylistCreator();
				pc.MakePlaylist();
			}
		});
		
		Label timeLabel = new Label("Time: ");
        mediaBar.getChildren().add(timeLabel);

        // Add time slider
        timeSlider = new Slider();
        HBox.setHgrow(timeSlider, Priority.ALWAYS);
        timeSlider.setMinWidth(50);
        timeSlider.setMaxWidth(Double.MAX_VALUE);
        timeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging()) {
                    // multiply duration by percentage calculated by slider position
                    player.seek(duration.multiply(timeSlider.getValue() / 100.0));
                }
            }
        });
        mediaBar.getChildren().add(timeSlider);

        // Add Play label
        playTime = new Label();
        playTime.setPrefWidth(130);
        playTime.setMinWidth(50);
        mediaBar.getChildren().add(playTime);

        // Add the volume label
        Label volumeLabel = new Label("Vol: ");
        mediaBar.getChildren().add(volumeLabel);

        // Add Volume slider
        volumeSlider = new Slider();
        volumeSlider.setPrefWidth(70);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(30);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (volumeSlider.isValueChanging()) {
                    player.setVolume(volumeSlider.getValue() / 100.0);
                }
            }
        });
        mediaBar.getChildren().add(volumeSlider);

		mediaBar.getChildren().addAll(play, pause,music, video, playlistbtn);
		bp.setBottom(mediaBar);
	}
	
	protected void updateValues() {
        if (playTime != null && timeSlider != null && volumeSlider != null) {
            Platform.runLater(new Runnable() {
                public void run() {
                    Duration currentTime = player.getCurrentTime();
                    playTime.setText(formatTime(currentTime, duration));
                    timeSlider.setDisable(duration.isUnknown());
                    if (!timeSlider.isDisabled()
                            && duration.greaterThan(Duration.ZERO)
                            && !timeSlider.isValueChanging()) {
                        timeSlider.setValue(currentTime.divide(duration).toMillis()
                                * 100.0);
                    }
                    if (!volumeSlider.isValueChanging()) {
                        volumeSlider.setValue((int) Math.round(player.getVolume()
                                * 100));
                    }
                }
            });
        }
	}
	
	/*protected boolean CheckMediaStatus()
	{
		boolean validStatus = false;
		Status playerStatus = player.getStatus();
		if(playerStatus != Status.HALTED || playerStatus != Status.UNKNOWN)
		{
			if(playerStatus == Status.PAUSED || playerStatus == Status.READY || playerStatus == Status.STOPPED)
			{
				if(CheckIfAtEnd())
				{
					player.seek(player.getStartTime());
				}
				player.play();
			}else
			{
				player.pause();
			}
		}
		return validStatus;
		
	}*/              
	
	public boolean CheckIfAtEnd()
	{
		boolean isAtEnd;
		isAtEnd = player.getCurrentTime() == player.getStopTime();
		return isAtEnd;
	}
	
	private static String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int) Math.floor(elapsed.toSeconds());
        int elapsedHours = intElapsed / (60 * 60);
        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * 60 * 60;
        }
        int elapsedMinutes = intElapsed / 60;
        int elapsedSeconds = intElapsed - elapsedHours * 60 * 60
                - elapsedMinutes * 60;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) Math.floor(duration.toSeconds());
            int durationHours = intDuration / (60 * 60);
            if (durationHours > 0) {
                intDuration -= durationHours * 60 * 60;
            }
            int durationMinutes = intDuration / 60;
            int durationSeconds = intDuration - durationHours * 60 * 60
                    - durationMinutes * 60;
            if (durationHours > 0) {
                return String.format("%d:%02d:%02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds, durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d", elapsedHours,
                        elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d", elapsedMinutes,
                        elapsedSeconds);
            }
        }
	}

}
