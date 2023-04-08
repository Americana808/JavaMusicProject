package com.example.tests;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class FinalProject extends Application{

    Stage window;
    Scene scene1;
    Button play, pause;
    Label currentSong, startLabel;
    ListView<String> songs;
    Clip clip;
    AudioInputStream audioInput;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Media Player");

        BorderPane bPane = new BorderPane();        //Scene layout
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20,20,20,20));
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(20, 20, 20, 20));

        //Play a song scene
        startLabel = new Label("Please select song");
        currentSong = new Label("Now Playing:");
        play = new Button("Play");
        pause = new Button("Stop");

        songs = new ListView<>();
        songs.getItems().addAll("12:51", "Reptilia", "Automatic Stop", "Under Control", "I Can't Win");
        songs.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //Play button function
        play.setOnAction(this::playSong);

        //Pause button function
        pause.setOnAction(this::pauseSong);

        vbox.getChildren().addAll(startLabel, currentSong, songs);      //Vertical Laid objects
        hbox.getChildren().addAll(play, pause);                         //Horizontal laid objects

        vbox.setSpacing(7);
        hbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        hbox.setAlignment(Pos.CENTER);
        bPane.setCenter(vbox);
        bPane.setBottom(hbox);
        scene1 = new Scene(bPane, 400, 250);
        window.setScene(scene1);
        window.show();
    }

    //Pause button function
    public void pauseSong(ActionEvent event) {
        clip.stop();
        currentSong.setText("Now Playing: ");
        //playMusic("stop","C:\\Users\\Marco\\Desktop\\college stuff\\Fall 2021\\CIS163AA\\Code\\tests\\src\\main\\java\\com\\example\\tests\\04 12-51.wav");
    }

    //Play button function
    public void playSong(ActionEvent event) {
        String song = new String(String.valueOf(songs.getSelectionModel().getSelectedItem()));
        currentSong.setText("Now Playing: ".concat(song));
        switch (song) {
            case "12:51":
                playMusic("play", "C:\\Users\\Marco\\Desktop\\college stuff\\Fall 2021\\CIS163AA\\Code\\tests\\src\\main\\java\\com\\example\\tests\\04 12-51.wav");
                break;
            case "Reptilia":
                playMusic("play", "C:\\Users\\Marco\\Desktop\\college stuff\\Fall 2021\\CIS163AA\\Code\\tests\\src\\main\\java\\com\\example\\tests\\02 Reptilia.wav");
                break;
            case "Automatic Stop":
                playMusic("play", "C:\\Users\\Marco\\Desktop\\college stuff\\Fall 2021\\CIS163AA\\Code\\tests\\src\\main\\java\\com\\example\\tests\\03 Automatic Stop.wav");
                break;
            case "Under Control":
                playMusic("play", "C:\\Users\\Marco\\Desktop\\college stuff\\Fall 2021\\CIS163AA\\Code\\tests\\src\\main\\java\\com\\example\\tests\\08 Under Control.wav");
                break;
            case "I Can't Win":
                playMusic("play", "C:\\Users\\Marco\\Desktop\\college stuff\\Fall 2021\\CIS163AA\\Code\\tests\\src\\main\\java\\com\\example\\tests\\11 I Can't Win.wav");
                break;
            default:
                System.out.println("Selection not found");
        }
    }

    //Playing music
    public void playMusic(String option, String musicLocation){
        try {
            File musicPath = new File(musicLocation);
            System.out.println(musicLocation);
            audioInput = AudioSystem.getAudioInputStream(musicPath);
            clip = AudioSystem.getClip();

            if (option.equals("play") || option.equals("stop")) {
                if (option.equals("play")) {
                    clip.open(audioInput);
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    System.out.println("Clip started");
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
