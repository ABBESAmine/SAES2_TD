package fr.iut.montreuil.modele.vue;

import fr.iut.montreuil.modele.Joueur;
import fr.iut.montreuil.modele.acteur.Tour;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;

public class TourVue {
    @FXML
    private TilePane tilemap;

    private ArrayList<Circle> listCercle;

    private BorderPane bord;

    private Pane pane;

    private static int compteur = 0;
    public TourVue(BorderPane bord, Pane p){
        this.bord = bord;
        this.pane = p;
        listCercle = new ArrayList<>();
    }


    public void preAffichageSpriteTour(ImageView imageShip){
        bord.getChildren().add(imageShip);
    }

    public void ajouterSpriteTour(int type, Joueur j, ImageView imageShip){
        if (type ==1){
            j.setArgent(j.getArgent()-50);
        }else if (type ==2){
            j.setArgent(j.getArgent()-100);
        }else if (type ==3){
            j.setArgent(j.getArgent()-160);
        }
        imageShip.xProperty().unbind();
        imageShip.yProperty().unbind();

    }

    public void supprimerSpriteTour(Tour i) {
        System.out.println("test");
        this.bord.getChildren().remove(this.bord.lookup("#C"+ i.getId()));
        this.bord.getChildren().remove(this.bord.lookup("#"+ i.getImgId()));
    }

    public void afficherRayonPortee(Tour t) {
        Circle rayonPortee = new Circle();
        double centerX = t.getX()+25;
        double centerY = t.getY()+25;

        rayonPortee.setCenterX(centerX);
        rayonPortee.setCenterY(centerY);
        rayonPortee.setRadius(t.getPortee());
        rayonPortee.setFill(null); // Aucun remplissage
        rayonPortee.setStroke(Color.rgb(255, 177, 6));
        rayonPortee.setStrokeWidth(2.0);
        rayonPortee.setId(t.getId());

        DropShadow dropShadow = new DropShadow(10, Color.rgb(0, 0, 0, 1));
        rayonPortee.setEffect(dropShadow);

        listCercle.add(rayonPortee);
        rayonPortee.setId("C"+t.getId());
        bord.getChildren().add(rayonPortee);
        System.out.println("Cercle numéro "+rayonPortee.getId());
    }

    public void supprimerSprite(ImageView imageShip) {
        bord.getChildren().remove(imageShip);
    }
}
