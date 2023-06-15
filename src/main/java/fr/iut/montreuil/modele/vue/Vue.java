package fr.iut.montreuil.modele.vue;

import fr.iut.montreuil.Appli;
import fr.iut.montreuil.modele.Modele;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;

public class Vue {

    @FXML
    private TilePane tilemap;


    @FXML
    private BorderPane bord;
    private int[] tab;

    private Modele envi;


    public Vue(Modele env, TilePane tilemap1, BorderPane bp){
        this.envi = env;
        this.tilemap = tilemap1;
        this.bord = bp;

        tab = envi.genererTableauMap();
        this.genTerrain(tab);





    }

    public void genTerrain(int[] tab){
        for (int i = 0; i < 400; i++) {
            ImageView imageView = null;
            Image img = null;

            switch (tab[i]) {
                case 1:

                    try {
                        img = new Image(Appli.class.getResource("image/herbe.png").openStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                case 2:
                    try {
                        img = new Image(Appli.class.getResource("image/terre.png").openStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    try {
                        img = new Image(Appli.class.getResource("image/Coinbg.png").openStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    try {
                        img = new Image(Appli.class.getResource("image/Coinhd.png").openStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 5:
                    try {
                        img = new Image(Appli.class.getResource("image/Coinbd.png").openStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 8:
                    try {
                        img = new Image(Appli.class.getResource("image/Coinhg.png").openStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 9:
                    try {
                        img = new Image(Appli.class.getResource("image/herbe2.png").openStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 10:
                    try {
                        img = new Image(Appli.class.getResource("image/grotte.png").openStream());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                default:
                    imageView = null;
                    break;
            }

            imageView = new ImageView(img);
            tilemap.getChildren().add(imageView);
        }
    }

}
