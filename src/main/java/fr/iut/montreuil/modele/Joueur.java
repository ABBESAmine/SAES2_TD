package fr.iut.montreuil.modele;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Joueur {

    private IntegerProperty argent;

    private IntegerProperty pv;





    public IntegerProperty getPvProperty(){
        return pv;
    }

    public void setArgent(int valeur) {
        argent.set(valeur);
    }

    public int getArgent() {
        return argent.get();
    }

    public void setPv(int valeur) {
        pv.set(valeur);
    }

    public int getPv() {
        return pv.get();
    }


    public Joueur(){
        argent = new SimpleIntegerProperty(100);
        pv = new SimpleIntegerProperty(100);


    }

    public void subitDegat(int val){
        if(val == 1){
            setPv(getPv()-10);
        } else if(val == 2){
            setPv(getPv()-15);
        } else{
            setPv(getPv()-20);
        }

    }

    public void gagneArgent(int val){
        if(val == 1){
            setArgent(getArgent()+25);
        } else if(val == 2){
            setArgent(getArgent()+50);
        } else{
            setArgent(getArgent()+75);
        }

    }


    public IntegerProperty getArgentProperty(){
        return argent;
    }

}
