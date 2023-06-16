package fr.iut.montreuil.modele.acteur;



import fr.iut.montreuil.modele.Modele;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Soldat {


    private IntegerProperty X0, Y0; // position de l'axe de rotation
    private IntegerProperty pointsDeVie;
    private int pointsDeVieInit;
    private  int vitesse;

    private String id;

    private Modele env;
    public static int compteur=0;
    private int[] ram_map;

    private int previous = 0;


    private int valeur; // valeur du soldat, égal a 1, 2 ou 3 en fonction du type d'ennemi

    public Soldat(int x0, int y0, Modele envi, int valeur) {
        this.X0 = new SimpleIntegerProperty(x0);
        this.Y0 = new SimpleIntegerProperty(y0);

        this.env = envi;


        this.id= "A"+compteur;
        compteur++;
        this.valeur = valeur;

        if(valeur == 1){
            this.vitesse = 3;
            this.pointsDeVie = new SimpleIntegerProperty(100);
            pointsDeVieInit=100;
        } else if(valeur == 2){
            this.vitesse = 2;
            this.pointsDeVie = new SimpleIntegerProperty(150);
            pointsDeVieInit=150;
        } else {
            this.valeur=3;
            this.vitesse = 1;
            this.pointsDeVie = new SimpleIntegerProperty(200);
            pointsDeVieInit=200;
        }
    }


    public int getPointsDeVieInit() {
        return pointsDeVieInit;
    }

    public void seDeplace(){

        ram_map = env.genererTableauMap();
        int xSprite = this.getX0Value();
        int ySprite = this.getY0Value();
        int xTile = xSprite/40;
        int yTile = ySprite/40;

        if (ram_map[xTile+1+(yTile*20)] != 1 && ram_map[xTile+1+(yTile*20)] != 9 && ram_map[xTile+1+(yTile*20)] != 11
                && ram_map[xTile+1+(yTile*20)] != 12 && ram_map[xTile+1+(yTile*20)] != 20 && ram_map[xTile+1+(yTile*20)] != 21 && ram_map[xTile+1+(yTile*20)] != 22  && previous != 4){
            this.setX0(this.getX0Value()+40);
            previous = 1;
        }
        else if (ram_map[xTile+((yTile-1)*20)] != 1 && ram_map[xTile+((yTile-1)*20)] != 9 && ram_map[xTile+((yTile-1)*20)] != 11
                && ram_map[xTile+((yTile-1)*20)] != 12 && ram_map[xTile+((yTile-1)*20)] != 20 && ram_map[xTile+((yTile-1)*20)] != 21 && ram_map[xTile+((yTile-1)*20)] != 22 && previous != 3){
            this.setY0(this.getY0Value()-40);

            previous = 2;
        }
        else if (ram_map[xTile+((yTile+1)*20)] != 1 && ram_map[xTile+((yTile+1)*20)] != 9 && ram_map[xTile+((yTile+1)*20)] != 11
                && ram_map[xTile+((yTile+1)*20)] != 12 && ram_map[xTile+((yTile+1)*20)] != 20 && ram_map[xTile+((yTile+1)*20)] != 21 && ram_map[xTile+((yTile+1)*20)] != 22 && previous != 2){
            this.setY0(this.getY0Value()+40);
            previous = 3;
        }
        else if (ram_map[xTile-1+(yTile*20)] != 1 && ram_map[xTile-1+(yTile*20)] != 9 && ram_map[xTile-1+(yTile*20)] != 11
                && ram_map[xTile-1+(yTile*20)] != 12 && ram_map[xTile-1+(yTile*20)] != 20 && ram_map[xTile-1+(yTile*20)] != 21 && ram_map[xTile-1+(yTile*20)] != 22 && previous != 1){
            this.setX0(this.getX0Value()-40);
            previous = 4;
        }
    }



    // Get et set
    public int getX0Value() { return this.X0.getValue(); }      // Valeur x0
    public IntegerProperty getX0Property() { return this.X0; }  // Property x0

    // y0
    public int getY0Value() { return this.Y0.getValue(); }      // Valeur y0
    public IntegerProperty getY0Property() { return this.Y0; }  // Property y0

    public void setX0(int val) {
        this.X0.setValue(val);

    }
    public void setY0(double val) { this.Y0.setValue(val); }
    public int getValeur() {
        return valeur;
    }
    public int getPointsDeVieValue() { return this.pointsDeVie.getValue(); }
    public IntegerProperty getPointsDeVieProperty() { return this.pointsDeVie; }
    public int getVitesse() { return this.vitesse; }
    public void setPointsDeVieValue(int val) { this.pointsDeVie.setValue(val); }
    public  int getTileValue() {
        int xTile = this.getX0Value()/40;
        int yTile = this.getY0Value()/40;
        ram_map = env.genererTableauMap();

        return ram_map[xTile+(yTile*20)];
    }
    public String getId() {
        return this.id;
    }
}