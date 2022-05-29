package com.javafx.mediaplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;
import java.io.File;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("scene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 757, 480);
        stage.setTitle("Music Player");
        stage.setScene(scene);

        /*String path = "src/music/gorillaz-shes-my-collar-feat-kali-uchis.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);*/


        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}