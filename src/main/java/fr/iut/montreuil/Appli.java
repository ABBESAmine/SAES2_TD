package fr.iut.montreuil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Appli extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Appli.class.getResource("fxml/map2.fxml"));


        Scene scene = new Scene(fxmlLoader.load(), 752, 500);

        stage.setTitle("Tower Defense");
        stage.setScene(scene);

        stage.show();
    }
 
    public static void main(String[] args) {
        launch();
    }
}