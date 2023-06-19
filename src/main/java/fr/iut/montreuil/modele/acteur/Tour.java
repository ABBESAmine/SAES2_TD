package fr.iut.montreuil.modele.acteur;


import fr.iut.montreuil.modele.Modele;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public  class Tour {

    private int x, y;
    protected BorderPane bord;
    private int prix;
    private int portee;
    private int degat;

    private String id;
    private static int compteur=0;

    private int type;

    private ImageView img;

    public Tour(int x, int y, BorderPane bord, int type, ImageView img) {
        this.type = type;
        if (type == 1){
            this.prix = 100;
            this.portee = 100;
            this.degat = 3;
        } else if (type == 2){
            this.prix = 150;
            this.portee = 100;
            this.degat = 7;
        }else{
            this.prix = 250;
            this.portee = 150;
            this.degat = 17;
        }

        this.x = x;
        this.y = y;
        this.bord = bord;



        this.id= "T"+compteur;
        this.img = img;
        img.setId("TT"+compteur);
        compteur++;
    }


    public final int getX() {
        return this.x;
    }

    public String getImgId() {

        return this.img.getId();
    }
    public final int getY() {
        return this.y;
    }

    public int getDegat() {
        return degat;
    }

    public int getPortee() {
        return portee;
    }

    public int getType() {
        return type;
    }
    public String getId() {
        return this.id;
    }

    public ImageView getImg() {
        return img;
    }

    public boolean ennemiPortee(Soldat ennemi) {
        double distance = Math.sqrt(Math.pow(ennemi.getX0Value() - getX(), 2) + Math.pow(ennemi.getY0Value() - getY(), 2));
        return distance <= portee;
    }

}
