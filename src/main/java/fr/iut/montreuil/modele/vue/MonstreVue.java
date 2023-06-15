package fr.iut.montreuil.modele.vue;

import fr.iut.montreuil.modele.Modele;
import fr.iut.montreuil.Appli;
import fr.iut.montreuil.modele.acteur.Soldat;
import fr.iut.montreuil.modele.acteur.Tour;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.ProgressBar;
import java.net.URL;
import java.util.ArrayList;

public class MonstreVue {
    @FXML
    private TilePane tilemap;

    private Image imageMonstre1;
    private Image imageMonstre2;
    private Image imageMonstre3 ;
    private  ImageView iv2;

    private ProgressBar pb;
    private ArrayList<ImageView> ListImageView;
    private ArrayList<ProgressBar> ListBarre;





    private BorderPane bord;

    private Pane pane;
    private int[] tab;

    private Modele envi;




    public MonstreVue(BorderPane bord, Pane p) {
        this.ListImageView = new ArrayList<>();
        this.ListBarre = new ArrayList<>();
        this.bord = bord;
        this.pane = p;
        URL urlImage = Appli.class.getResource("image/monstre1.png");
        imageMonstre1 = new Image(String.valueOf(urlImage));
        urlImage = Appli.class.getResource("image/monstre2.png");
        imageMonstre2 = new Image(String.valueOf(urlImage));
        urlImage = Appli.class.getResource("image/monstre3.png");
        imageMonstre3 = new Image(String.valueOf(urlImage));
    }


    public void creerSpriteAct(Soldat ennemi) {
        if (ennemi.getValeur() == 1){
            iv2 = new ImageView(imageMonstre1);
        } else if (ennemi.getValeur() == 2){
            iv2 = new ImageView(imageMonstre2);
        } else {
            iv2 = new ImageView(imageMonstre3);
        }

        iv2.translateXProperty().bind(ennemi.getX0Property());
        iv2.translateYProperty().bind(ennemi.getY0Property());
        this.bord.getChildren().add(iv2);
        iv2.setId(ennemi.getId());
        ListImageView.add(iv2);

        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(ennemi.getPointsDeVieInit()/ennemi.getPointsDeVieValue());
        progressBar.setPrefWidth(50);
        progressBar.setPrefHeight(20);
        progressBar.setStyle("-fx-accent: green;");


        progressBar.translateXProperty().bind(ennemi.getX0Property());
        progressBar.translateYProperty().bind(ennemi.getY0Property().add(20*ennemi.getValeur()));
        this.pane.getChildren().add(progressBar);
        progressBar.setId("B"+ennemi.getId());
        ListBarre.add(progressBar);

        ennemi.getPointsDeVieProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double progress = newValue.doubleValue() / ennemi.getPointsDeVieInit();
                if(newValue.doubleValue() / ennemi.getPointsDeVieInit() < 0.6 && newValue.doubleValue() / ennemi.getPointsDeVieInit() > 0.3)
                    progressBar.setStyle("-fx-accent: orange;");
                else if(newValue.doubleValue() / ennemi.getPointsDeVieInit() < 0.3)
                    progressBar.setStyle("-fx-accent: red;");
                progressBar.setProgress(progress);
            }
        });


    }

    public void supprimerSpriteAct(Soldat i) {
        System.out.println("test");
        this.bord.getChildren().remove(this.bord.lookup("#"+ i.getId()));
        this.pane.getChildren().remove(this.pane.lookup("#B"+ i.getId()));
        System.out.println("#"+ i.getId());
    }

    public void supprimerSpriteTour(Tour i) {
        System.out.println("test");
        this.bord.getChildren().remove(this.bord.lookup("#C"+ i.getId()));
        this.bord.getChildren().remove(this.bord.lookup("#"+ i.getImgId()));
    }






}
