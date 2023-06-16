package fr.iut.montreuil;

import fr.iut.montreuil.modele.Joueur;
import fr.iut.montreuil.modele.acteur.Tour;
import fr.iut.montreuil.modele.vue.MonstreVue;
import fr.iut.montreuil.modele.vue.TourVue;
import javafx.collections.ListChangeListener;


public class ListObsTour implements ListChangeListener<Tour> {


    private Joueur j;
    private TourVue tourVue;

    public ListObsTour(TourVue mVue, Joueur joueur) {
        this.j = joueur;
        this.tourVue = mVue;
    }

    @Override
    public void onChanged(Change<? extends Tour> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (Tour e : change.getAddedSubList()) {
                    System.out.println("add list");
                    tourVue.ajouterSpriteTour(e.getType(), j, e.getImg());
                    tourVue.afficherRayonPortee(e);
                }
            }
            if (change.wasRemoved()) {
                for (Tour e : change.getRemoved()) {
                    System.out.println("supp list 1");
                    tourVue.supprimerSpriteTour(e);
                }
            }
        }
    }
}
