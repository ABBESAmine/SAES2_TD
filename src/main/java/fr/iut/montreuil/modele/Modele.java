package fr.iut.montreuil.modele;

import fr.iut.montreuil.modele.acteur.Tour;
import fr.iut.montreuil.Appli;
import fr.iut.montreuil.ListObsTour;
import fr.iut.montreuil.modele.acteur.Soldat;
import fr.iut.montreuil.ListObsEnnemis;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Modele {

    //Lists
    private ObservableList<Soldat> ListActeurs;
    private ObservableList<Tour> ListTours;
    private ArrayList<Circle> listCercle;

    private IntegerProperty difficulte;

    private Joueur j;

    private int[] tab;



    public Modele(BorderPane bord, Joueur j){
        ListActeurs = FXCollections.observableArrayList();
        ListTours = FXCollections.observableArrayList();
        listCercle = new ArrayList<>();
        difficulte = new SimpleIntegerProperty(0);
        this.j = j;



        this.tab = new int[]{1, 1, 9, 1, 1, 1, 9, 1, 1, 1, 1, 9, 1, 1, 9, 1, 1, 1, 1, 1,
                             1, 1, 1, 1, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 9, 1, 1,
                             1, 1, 1, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 9, 1, 1, 1, 1,
                             1, 1, 8, 5, 1, 1, 1, 1, 1, 1, 9, 1, 1, 1, 3, 4, 1, 9, 1, 1,
                             1, 8, 5, 1, 1, 1, 8, 2, 2, 4, 1, 1, 1, 1, 1, 2, 9, 1, 1, 1,
                             1, 2, 1, 1, 1, 8, 5, 1, 1, 3, 2, 4, 1, 9, 1, 3, 4, 1, 1, 9,
                             1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1, 3, 2, 4, 1, 1, 2, 1, 1, 1,
                             1, 2, 9, 1, 1, 2, 1, 8, 2, 4, 1, 1, 1, 2, 1, 9, 2, 9, 1, 1,
                             1, 2, 1, 1, 1, 2, 1, 2, 1, 2, 20, 21, 22, 2, 1, 1, 3, 4, 1, 1,
                             2, 5, 1, 1, 9, 2, 1, 2, 9, 2, 11, 10, 12, 2, 9, 1, 1, 2, 1, 9,
                             1, 9, 1, 9, 1, 2, 1, 2, 1, 3, 2, 5, 1, 2, 1, 9, 1, 2, 1, 1,
                             1, 1, 9, 1, 1, 2, 1, 2, 1, 9, 1, 1, 1, 2, 1, 1, 8, 5, 9, 1,
                             9, 1, 1, 1, 8, 5, 1, 2, 1, 1, 1, 1, 8, 5, 9, 1, 2, 1, 1, 1,
                             1, 9, 1, 8, 5, 1, 1, 2, 1, 9, 1, 8, 5, 1, 1, 1, 2, 1, 1, 9,
                             1, 1, 1, 2, 1, 1, 1, 3, 2, 2, 2, 5, 9, 1, 1, 1, 2, 9, 1, 9,
                             9, 9, 1, 3, 4, 1, 1, 9, 1, 1, 1, 9, 1, 1, 8, 2, 5, 1, 1, 1,
                             1, 1, 1, 1, 3, 4, 1, 1, 1, 9, 1, 1, 1, 8, 5, 1, 1, 1, 1, 9,
                             9, 1, 1, 9, 1, 3, 2, 2, 2, 2, 2, 2, 2, 5, 1, 1, 1, 9, 1, 1,
                             1, 1, 9, 1, 1, 1, 1, 1, 1, 9, 1, 1, 9, 1, 1, 1, 1, 1, 9, 1,
                             1, 1, 1, 1, 1, 9, 1, 1, 1, 1, 9, 1, 1, 1, 9, 1, 1, 1, 1, 1};



    }



    public int[] genererTableauMap(){
        return this.tab;
    }

    public void ajouterActeur(Soldat a){
        ListActeurs.add(a);
    }

    public void ajouterTour(Tour t){
        ListTours.add(t);
    }


    public void supprimeTour(Tour t){
        ListTours.remove(t);
        j.setArgent(j.getArgent()+(50*t.getType()));
    }



    public void unTour(int tmp) {
        Soldat act;
        Tour t;

        if (ListActeurs != null){
            for(int i = 0;i<ListActeurs.size();i++){
                //System.out.println(i);
                act = ListActeurs.get(i);
                if (tmp%act.getVitesse() == 0){
                    act.seDeplace();
                }
                if (ListTours != null && ListActeurs != null) {
                    for (int c = 0; c < ListTours.size(); c++) {
                        t = ListTours.get(c);
                        System.out.println(t.getId());

                        for (int y = 0; y < ListActeurs.size(); y++) {
                            act = ListActeurs.get(y);

                            if (t.ennemiPortee(act) && tmp % 4 == 0) {
                                System.out.println(t.getId() + " attaque " + act.getId());
                                act.setPointsDeVieValue(act.getPointsDeVieValue() - t.getDegat());

                                if (act.getPointsDeVieValue() <= 0) {
                                    j.gagneArgent(act.getValeur());
                                    ListActeurs.remove(act);
                                    y--; // Décrémente l'index pour compenser la suppression de l'élément
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }


    public void verifSoldat(){
        Soldat act;

        if (ListActeurs != null){
            for(int i = 0;i<ListActeurs.size();i++){
                //System.out.println(i);
                act = ListActeurs.get(i);
                if (act.getTileValue() == 10){
                    j.subitDegat(ListActeurs.get(i).getValeur());
                    System.out.println("PV fin : "+ListActeurs.get(i).getPointsDeVieValue());
                    ListActeurs.remove(ListActeurs.get(i));
                }
            }
        }
    }


    public boolean ennemiEnVie(){
        return ListActeurs.isEmpty();
    }


    public void vague(int temps){
        if (difficulte.getValue() == 1){
            if (temps % 5 == 0 && temps <= 25){
                Soldat sld = new Soldat(0, 370,  this, 1);
                this.ajouterActeur(sld);
            }
        } else if (difficulte.getValue() == 2){
            if (temps % 5 == 0 && temps <= 25){
                Soldat sld = new Soldat(0, 370,   this, 2);
                this.ajouterActeur(sld);
            }
        }else if (difficulte.getValue() >= 3){
            if (temps % 5 == 0 && temps <= 25*(difficulte.getValue()/2)){
                Soldat sld = new Soldat(0, 370, this, (int)(Math.random()*5+1));
                this.ajouterActeur(sld);
            }
        }

    }

    public void listenAct(ListObsEnnemis l){
        this.ListActeurs.addListener(l);
    }
    public void listenTour(ListObsTour l){
        this.ListTours.addListener(l);
    }



    public  int getTileValue(double x, double y) {
        int xTile = (int)x/40;
        int yTile = (int)y/40;

        if(x >= 800 || y >= 800){
            return 0;
        }

        return this.tab[xTile+(yTile*20)];
    }

    public int getDifficulte() {
        return difficulte.getValue();
    }

    public IntegerProperty getDifficulteProperty() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte.setValue(difficulte);
    }


    public Tour dejaTour(double x, double y){
        Tour t;

        if (ListTours != null){
            for(int i = 0;i<ListTours.size();i++){
                t = ListTours.get(i);
                if ((x > t.getX()-25 && x < t.getX()+25) && (y > t.getY()-25 && y < t.getY()+25)){
                    return t;
                }
                System.out.println("X:"+t.getX()+" Y:"+t.getY());
            }
        }else {
            return null;
        }

        return null;
    }




    public void victoire(Button button){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Appli.class.getResource("fxml/victoire.fxml"));


        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 752, 423);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage1 = new Stage();
        stage1.setScene(scene);
        //stage.setResizable(false);
        stage1.show();
    }

    public void defaite(Button button){
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(Appli.class.getResource("fxml/defaite.fxml"));


        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 752, 423);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Stage stage1 = new Stage();
        stage1.setScene(scene);
        //stage.setResizable(false);
        stage1.show();
    }




}
