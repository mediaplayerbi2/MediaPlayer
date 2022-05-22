package com.javafx.mediaplayer;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.control.ProgressBar;


public class FXMLController implements Initializable {
    @FXML
    private Button addButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Button nextButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;

    @FXML
    private Button playlistButton;

    @FXML
    private ListView<?> playlistsListView;

    @FXML
    private Button shareButton;

    @FXML
    private Button songButton;

    @FXML
    private Label songLabel;
    @FXML
    private Label timeLabel;

    @FXML
    private ProgressBar songProgressBar;

    @FXML
    private ListView<?> songsListView;

    @FXML
    private Slider volumeSlider;
    private Media media;
    private MediaPlayer mediaPlayer;
    private File[] files;
    private File directory;
    private ArrayList<File> songs;
    private int songNumber;
    private Timer timer;
    private TimerTask task;
    private boolean running;

    public void playMedia(ActionEvent actionEvent) {
        mediaPlayer.play();
        beginTimer();
    }

    public void nextMedia(ActionEvent actionEvent) {
        if (songNumber < songs.size() - 1) {
            songNumber++;
            mediaPlayer.stop();
            if(running){
                cancelTimer();
            }


            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
        } else {
            songNumber = 0;
            mediaPlayer.stop();
            if(running){
                cancelTimer();
            }

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());



        }
        mediaPlayer.play();
    }


    public void pauseMedia(ActionEvent actionEvent) {
        cancelTimer();
        mediaPlayer.pause();
    }

    public void addMediaToPlaylist(ActionEvent actionEvent) {
    }

    public void shareMedia(ActionEvent actionEvent) {
    }

    public void chooseSong(ActionEvent actionEvent) {
    }

    public void choosePlaylist(ActionEvent actionEvent) {
    }
    /*public void beginTimer(){
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                System.out.println(current/end);
                songProgressBar.setProgress(current/end);
                if (current/end == 1){
                    cancelTimer();
                }

            }
        };
        timer.scheduleAtFixedRate(task, 0,100);
    }

    public void cancelTimer(){
        running = false;
        timer.cancel();

    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        songs = new ArrayList<File>();
        directory = new File("C:\\Users\\MargoRitta\\IdeaProjects\\MediaPlayer\\src\\music");
        files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                songs.add(file);
                System.out.println(file);
            }

        }
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songs.get(songNumber).getName());
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>(){
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });
        songProgressBar.setStyle("-fx-accent: #332c54;");
    }

    public void previousMedia(ActionEvent actionEvent) {
        if (songNumber > 0 ) {
            songNumber--;
            mediaPlayer.stop();
            if(running){
                cancelTimer();
            }


            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
        } else {
            songNumber = songs.size() - 1;
            mediaPlayer.stop();
            if(running){
                cancelTimer();
            }

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());


        }
        mediaPlayer.play();
    }
    public void beginTimer(){
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                System.out.println(current/end);
                songProgressBar.setProgress(current/end);
                if (current/end == 1){
                    cancelTimer();
                }

            }
        };
        timer.scheduleAtFixedRate(task, 0,100);
    }

    public void cancelTimer(){
        running = false;
        timer.cancel();

    }
}





/*

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<?> ListView;

    @FXML
    private Button nextButton;

    @FXML
    private Button pauseButton;

    @FXML
    private Button playButton;

    @FXML
    private Button previousButton;

    @FXML
    private ProgressBar songProgressBar;

    @FXML
    private ComboBox<String> speedBox;

    @FXML
    private Slider volumeSlider;
    @FXML
    private Label songLabel;

    private Media media;
    private MediaPlayer mediaPlayer;
    private File[] files;
    private File directory;
    private ArrayList<File> songs;
    private int songNumber;
    private Timer timer;
    private TimerTask task;
    private boolean running;

    @FXML
    void initialize() {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        songs = new ArrayList<File>();
        directory = new File("C:\\Users\\MargoRitta\\IdeaProjects\\MediaPlayer\\src\\music");
        files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                songs.add(file);
                System.out.println(file);
            }
        }
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songs.get(songNumber).getName());
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });

    }

    public void previosMedia(ActionEvent actionEvent) {
        if (songNumber > 0 ) {
            songNumber--;
            mediaPlayer.stop();
            if(running){
                cancelTimer();
            }

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
        } else {
            songNumber = songs.size() - 1;
            mediaPlayer.stop();
            if(running){
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());


        }
        mediaPlayer.play();

    }

    public void playMedia(ActionEvent actionEvent) {
        beginTimer();
        mediaPlayer.play();
    }

    public void nextMedia(ActionEvent actionEvent) {
        if (songNumber < songs.size() - 1) {
            songNumber++;
            mediaPlayer.stop();
            if(running){
                cancelTimer();
            }

            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
        } else {
            songNumber = 0;
            mediaPlayer.stop();
            if(running){
                cancelTimer();
            }
            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());


        }
        mediaPlayer.play();
    }



    public void pauseMedia(ActionEvent actionEvent) {
        cancelTimer();
        mediaPlayer.pause();
    }
    public void beginTimer() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                running = true;
                double current = mediaPlayer.getCurrentTime().toSeconds();
                double end = media.getDuration().toSeconds();
                System.out.println(current / end);
                songProgressBar.setProgress(current / end);
                if (current / end == 1) {
                    cancelTimer();
                }
                timer.scheduleAtFixedRate(task, 1000, 1000);
            }


        };
    }

        public void cancelTimer() {
            running = false;
            timer.cancel();
        }


    public void shareMedia(ActionEvent actionEvent) {
    }

    public void addMediaToPlaylist(ActionEvent actionEvent) {
    }
}
*/

