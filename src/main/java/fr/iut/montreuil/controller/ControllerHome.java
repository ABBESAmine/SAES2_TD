package fr.iut.montreuil.controller;

import fr.iut.montreuil.Appli;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerHome implements Initializable {

    @FXML
    private Pane pane2;
    @FXML
    private Button startButton;

    @FXML
    private VBox botVbox;


    private String musicFile;
    private Media sound;
    private MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        botVbox.getStyleClass().add("gradient-color");

        musicFile = "src/main/resources/fr/iut/montreuil/musique/homeMusique.mp3";
        sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();


        Image img;
        try {
            img = new Image(Appli.class.getResource("image/backZelda.jpg").openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Création de l'objet BackgroundImage
        BackgroundImage background = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        // Création de l'objet Background
        Background backgroundObj = new Background(background);

        // Appliquer le Background au Pane
        pane2.setBackground(backgroundObj);

        startButton.setOnAction(event -> {
            mediaPlayer.stop();


            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(Appli.class.getResource("fxml/map1.fxml"));


            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 1050, 800);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.setTitle("Tower defense");
            //stage.setResizable(false);
            stage1.show();
        });


    }
}
