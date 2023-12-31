package fr.iut.montreuil.controller;


import fr.iut.montreuil.Appli;
import fr.iut.montreuil.modele.Joueur;
import fr.iut.montreuil.modele.acteur.Tour;
import fr.iut.montreuil.ListObsEnnemis;
import fr.iut.montreuil.ListObsTour;
import fr.iut.montreuil.modele.Modele;
import fr.iut.montreuil.modele.vue.MonstreVue;
import fr.iut.montreuil.modele.vue.TourVue;
import fr.iut.montreuil.modele.vue.Vue;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.util.Duration;
import javafx.beans.property.DoubleProperty;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;

public class Controller implements Initializable {


    //FXML variables :
    @FXML
    public Label labelInstruction;
    @FXML
    private TilePane tilemap;
    @FXML
    private Button startButton;

    @FXML
    private Button buttonAddDefense3;
    @FXML
    private Button buttonAddDefense1;
    @FXML
    private Button buttonAddDefense2;
    @FXML
    private Button suppTour;
    @FXML
    private BorderPane bord;
    @FXML
    private Label labelArgent;

    @FXML
    private Label labelPv;

    @FXML
    private Label labelDiff;

    @FXML
    private Pane centerPane;
    @FXML
    private Button pauseButton;


    //Autre variables :
    private DoubleProperty mouseX;
    private DoubleProperty mouseY;
    private int tourDejaPose = 0;
    private Timeline gameLoop;
    private MonstreVue monstreVue;
    private TourVue tourVue;
    private int temps = 0;
    private int valButtonDef;
    private Modele envi;

    private ListObsEnnemis listen1;
    private ListObsTour listen2;

    private int type;
    private Joueur j;





    //FXML methodes :
    @FXML
    public void suppTourButton(ActionEvent actionEvent) {
        suppTour.setDisable(true);
        buttonAddDefense1.setDisable(true);
        buttonAddDefense2.setDisable(true);
        if(envi.getDifficulte() >=5){
            buttonAddDefense3.setDisable(true);
        }
        labelInstruction.setText("Cliquez sur un allié pour le supprimer ! Cela vous permet de gagner 40, 90 ou 140 pièces d'or en fonction de quel allié vous supprimez");
        bord.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(envi.dejaTour(mouseX.getValue(), mouseY.getValue()) != null){
                    envi.supprimeTour(envi.dejaTour(mouseX.getValue(), mouseY.getValue()));
                }
                suppTour.setDisable(false);
                buttonAddDefense1.setDisable(false);
                buttonAddDefense2.setDisable(false);
                if(envi.getDifficulte() >=5){
                    buttonAddDefense3.setDisable(false);
                }
            }
        });
    }
    @FXML
    void startClique(ActionEvent event) {
        initAnimation();
        gameLoop.play();
        labelInstruction.setText("Défendez le royaume d'Hyrule !!");
    }
    @FXML
    void pauseClique(ActionEvent event) {
        gameLoop.pause();
        startButton.setDisable(false);
        pauseButton.setDisable(true);
    }
    @FXML
    public void descPerso(ActionEvent actionEvent) {

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        j = new Joueur();


        buttonAddDefense3.setDisable(true);
        pauseButton.setDisable(true);

        monstreVue = new MonstreVue(bord, centerPane);
        tourVue = new TourVue(bord, centerPane);

        this.envi = new Modele(bord, j, tourVue);
        listen1 = new ListObsEnnemis(monstreVue);
        listen2 = new ListObsTour(tourVue, j);
        envi.listenAct(listen1);
        envi.listenTour(listen2);


        labelArgent.textProperty().bind(j.getArgentProperty().asString());
        labelPv.textProperty().bind(j.getPvProperty().asString());
        labelDiff.textProperty().bind(envi.getDifficulteProperty().asString());






        Vue vue = new Vue(this.envi, tilemap, bord);




        // Mouse Property
        this.mouseY = new SimpleDoubleProperty(0);
        this.mouseX = new SimpleDoubleProperty(0);

        // Get mouse position
        bord.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouseX.setValue(mouseEvent.getX()-25);
                mouseY.setValue(mouseEvent.getY()-25);
            }
        });

    }


    private void initAnimation() {
        gameLoop = new Timeline();



        gameLoop.setCycleCount(Timeline.INDEFINITE);


        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.07),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    temps++;

                    pauseButton.setDisable(false);

                    if(envi.getDifficulte() >= 5){
                        buttonAddDefense3.setDisable(false);
                    }
                        envi.vague(temps);
                        startButton.setDisable(true);
                        //suppTour.setDisable(true);
                        envi.verifSoldat();
                        envi.unTour(temps);

                        if(envi.ennemiEnVie() && temps > 10){
                            labelInstruction.setText("Vous venez de gagner 100 pièces d'or !! Préparez-vous pour la prochaine vague.");
                            if(envi.getDifficulte() == 10){
                                envi.victoire(startButton);
                            }
                            envi.setDifficulte(envi.getDifficulte()+1);

                            if(j.getPv() <=0){
                                envi.defaite(startButton);
                            }

                            temps=0;
                            gameLoop.stop();
                            startButton.setDisable(false);
                            suppTour.setDisable(false);
                            pauseButton.setDisable(true);
                            j.setArgent(j.getArgent()+100);
                        }
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }



    public void ajoutDefense1(ActionEvent actionEvent) {
        if(j.getArgent() >= 50){
            buttonAddDefense1.setDisable(true);
            valButtonDef = 1;
            this.ajoutDefense();
        }
    }


    public void ajoutDefense2(ActionEvent actionEvent) {
        if(j.getArgent() >= 100){
            buttonAddDefense2.setDisable(true);
            valButtonDef = 2;
            this.ajoutDefense();
        }
    }

    public void ajoutDefense3(ActionEvent actionEvent) {
        if(j.getArgent() >= 150){
            buttonAddDefense3.setDisable(true);
            valButtonDef = 3;
            this.ajoutDefense();
        }
    }




    public void ajoutDefense() {
        suppTour.setDisable(true);
        labelInstruction.setText("Cliquez à un endroit de la map pour poser un allié !!!\nAstuce : les alliés ne " +
                "peuvent pas se poser sur le circuit ou les hautes herbes");

        // Récupère l'image de la défense
        URL urlImage = null;
        Image img = null;
        if(this.valButtonDef == 1){
            type = 1;
            urlImage = Appli.class.getResource("image/villageoisTour.png");
            img = new Image(String.valueOf(urlImage));
        } else if(this.valButtonDef == 2){
            type = 2;
            urlImage = Appli.class.getResource("image/Sidon.png");
            img = new Image(String.valueOf(urlImage));
        }
        else{
            type = 3;
            urlImage = Appli.class.getResource("image/Link.png");
            img = new Image(String.valueOf(urlImage));
        }
        final ImageView imageShip = new ImageView(img);

        // Bindings des positions de la défense avec celle de la souris
        imageShip.xProperty().bind(mouseX);
        imageShip.yProperty().bind(mouseY);


        // Ajout de la défense dans la pane
        tourVue.preAffichageSpriteTour(imageShip);



        // Lorsque qu'on clique sur la map on laisse la position au clique
        bord.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(envi.getTileValue(imageShip.getX()+25, imageShip.getY()+25) == 1 && envi.dejaTour(imageShip.getX(), imageShip.getY()) == null){
                    Tour t1= new Tour((int)imageShip.getX(), (int)imageShip.getY(), bord, type, imageShip);
                    envi.ajouterTour(t1);
                    tourDejaPose =1;
                    suppTour.setDisable(false);
                    buttonAddDefense1.setDisable(false);
                    buttonAddDefense2.setDisable(false);
                    if(envi.getDifficulte() >=5){
                        buttonAddDefense3.setDisable(false);
                    }
                }
                else if(tourDejaPose ==0){
                    tourVue.supprimerSprite(imageShip);
                    suppTour.setDisable(false);
                    buttonAddDefense1.setDisable(false);
                    buttonAddDefense2.setDisable(false);
                    if(envi.getDifficulte() >=5){
                        buttonAddDefense3.setDisable(false);
                    }
                }

            }
        });
    }


}