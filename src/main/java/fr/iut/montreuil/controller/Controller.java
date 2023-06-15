package fr.iut.montreuil.controller;


import fr.iut.montreuil.Appli;
import fr.iut.montreuil.modele.Joueur;
import fr.iut.montreuil.modele.acteur.Tour;
import fr.iut.montreuil.ListObsEnnemis;
import fr.iut.montreuil.ListObsTour;
import fr.iut.montreuil.modele.Modele;
import fr.iut.montreuil.modele.vue.MonstreVue;
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


    //Autre variables :
    private DoubleProperty mouseX;
    private DoubleProperty mouseY;
    private int tourDejaPose = 0;
    private Timeline gameLoop;
    private MonstreVue monstreVue;
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
        labelInstruction.setText("Cliquer sur un allié  pour le supprimer !");
        bord.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(envi.dejaTour(mouseX.getValue(), mouseY.getValue()) != null){
                    envi.supprimeTour(envi.dejaTour(mouseX.getValue(), mouseY.getValue()));
                } else {
                    System.out.println("Pas de tour ici");
                    System.out.println("X:"+mouseX.getValue()+" Y:"+mouseY.getValue());
                }

            }
        });
    }
    @FXML
    void startClique(ActionEvent event) {
        initAnimation();
        envi.setDifficulte(envi.getDifficulte()+1);
        gameLoop.play();
        labelInstruction.setText("Défendez le royaume d'Hyrule !!");
    }
    @FXML
    void pauseClique(ActionEvent event) {
        gameLoop.pause();
        startButton.setDisable(false);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        j = new Joueur();


        buttonAddDefense3.setDisable(true);

        monstreVue = new MonstreVue(bord, centerPane);
        this.envi = new Modele(bord, j, centerPane);
        listen1 = new ListObsEnnemis(monstreVue);
        listen2 = new ListObsTour(monstreVue);
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


                    if(envi.getDifficulte() >= 5){
                        buttonAddDefense3.setDisable(false);
                    }
                        envi.vague(temps);
                        startButton.setDisable(true);
                        suppTour.setDisable(true);
                        envi.verifSoldat();
                        envi.unTour(temps);

                        if(envi.ennemiEnVie() && temps > 10){
                            System.out.println("plus d'ennemi");
                            labelInstruction.setText("Préparez-vous pour la prochaine vague !");
                            if(envi.getDifficulte() == 10){
                                envi.victoire(startButton);
                            }

                            if(j.getPv() <=0){
                                envi.defaite(startButton);
                            }

                            temps=0;
                            gameLoop.stop();
                            startButton.setDisable(false);
                            suppTour.setDisable(false);
                        }
                })
        );
        gameLoop.getKeyFrames().add(kf);
    }



    public void ajoutDefense1(ActionEvent actionEvent) {
        if(j.getArgent() >= 50){
            valButtonDef = 1;
            this.ajoutDefense();
        }
    }


    public void ajoutDefense2(ActionEvent actionEvent) {
        if(j.getArgent() >= 100){
            valButtonDef = 2;
            this.ajoutDefense();
        }
    }

    public void ajoutDefense3(ActionEvent actionEvent) {
        if(j.getArgent() >= 150){
            valButtonDef = 3;
            this.ajoutDefense();
        }
    }




    public void ajoutDefense() {
        System.out.println("Bouton cliqué !");
        labelInstruction.setText("Cliquer à un endroit de la map pour poser un allié !!!\nAstuce : les allié ne " +
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

        double x = mouseX.getValue();
        double y = mouseY.getValue();

        // Ajout de la défense dans la pane
        bord.getChildren().add(imageShip);



        // Lorsque qu'on clique sur la map on laisse la position au clique
        bord.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(envi.getTileValue(imageShip.getX()+25, imageShip.getY()+25) == 1
                        && envi.dejaTour(imageShip.getX(), imageShip.getY()) == null){
                    if (type ==1){
                        j.setArgent(j.getArgent()-50);
                    }else if (type ==2){
                        j.setArgent(j.getArgent()-100);
                    }else if (type ==3){
                        j.setArgent(j.getArgent()-160);
                    }


                    imageShip.xProperty().unbind();
                    imageShip.yProperty().unbind();

                    Tour t1= new Tour((int)imageShip.getX(), (int)imageShip.getY(), bord, envi, type, imageShip);
                    System.out.println(mouseX.getValue()+" "+mouseY.getValue());

                    envi.ajouterTour(t1);
                    envi.afficherRayonPortee(t1);

                    System.out.println(envi.getTileValue(t1.getX(), t1.getY()));
                    tourDejaPose =1;
                }
                else if(tourDejaPose ==0){
                    bord.getChildren().remove(imageShip);
                }
            }
        });
    }


}