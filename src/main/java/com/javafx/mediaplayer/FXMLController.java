package com.javafx.mediaplayer;


import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;


import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

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
    private Slider progressBar;

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
        mediaPlayer.stop();
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songs.get(songNumber).getName());
        mediaPlayer.play();
        progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
            }
        });

        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<javafx.util.Duration>() {
            @Override
            public void changed(ObservableValue<? extends javafx.util.Duration> observable,
                                javafx.util.Duration oldValue, javafx.util.Duration newValue) {
                progressBar.setValue(newValue.toSeconds());
            }
        });
        progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
            }
        });

        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                javafx.util.Duration total = media.getDuration();
                progressBar.setMax(total.toSeconds());
            }
        });

        progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
            }
        });

        progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
            }
        });

    }

    public void nextMedia(ActionEvent actionEvent) {
        if (songNumber < songs.size() - 1) {
            songNumber++;
            mediaPlayer.stop();


            media = new Media(songs.get(songNumber).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            songLabel.setText(songs.get(songNumber).getName());
        } else {
            songNumber = 0;
            mediaPlayer.stop();
        }
        media = new Media(songs.get(songNumber).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        songLabel.setText(songs.get(songNumber).getName());

        mediaPlayer.play();
        progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
            }
        });

        progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
            }
        });

        mediaPlayer.setOnReady(new Runnable() {
            @Override
            public void run() {
                javafx.util.Duration total = media.getDuration();
                progressBar.setMax(total.toSeconds());
            }
        });
        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<javafx.util.Duration>() {
            @Override
            public void changed(ObservableValue<? extends javafx.util.Duration> observable,
                                javafx.util.Duration oldValue, javafx.util.Duration newValue) {
                progressBar.setValue(newValue.toSeconds());
            }
        });

        progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
            }
        });

        progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
            }
        });
    }


    public void pauseMedia(ActionEvent actionEvent) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        songs = new ArrayList<File>();
        directory = new File("src/music");
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
        songsListView.getItems().addAll();
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number,
                                Number t1) {
                mediaPlayer.setVolume(volumeSlider.getValue() * 0.01);
            }
        });
    }

    public void previousMedia(ActionEvent actionEvent) {
        if (progressBar.getValue() < 5) {
            if (songNumber > 0) {
                songNumber--;
                mediaPlayer.stop();

                media = new Media(songs.get(songNumber).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                songLabel.setText(songs.get(songNumber).getName());
            }
            mediaPlayer.play();
            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
                }
            });

            progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
                }
            });

            mediaPlayer.setOnReady(new Runnable() {
                @Override
                public void run() {
                    javafx.util.Duration total = media.getDuration();
                    progressBar.setMax(total.toSeconds());
                }
            });

            mediaPlayer.currentTimeProperty().addListener(new ChangeListener<javafx.util.Duration>() {
                @Override
                public void changed(ObservableValue<? extends javafx.util.Duration> observable,
                                    javafx.util.Duration oldValue, javafx.util.Duration newValue) {
                    progressBar.setValue(newValue.toSeconds());
                }
            });

            progressBar.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
                }
            });

            progressBar.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    mediaPlayer.seek(javafx.util.Duration.seconds(progressBar.getValue()));
                }
            });
        }
        else {
            progressBar.setValue(0);
            mediaPlayer.seek(Duration.seconds(0));
        }
    }
}




