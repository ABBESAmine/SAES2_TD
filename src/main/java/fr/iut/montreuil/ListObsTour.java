package fr.iut.montreuil;

import fr.iut.montreuil.modele.acteur.Tour;
import fr.iut.montreuil.modele.vue.MonstreVue;
import javafx.collections.ListChangeListener;


public class ListObsTour implements ListChangeListener<Tour> {


    private MonstreVue ennemisVue;

    public ListObsTour(MonstreVue mVue) {
        this.ennemisVue = mVue;
    }

    @Override
    public void onChanged(Change<? extends Tour> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (Tour e : change.getAddedSubList()) {
                    System.out.println("add list");
                }
            }
            if (change.wasRemoved()) {
                for (Tour e : change.getRemoved()) {
                    System.out.println("supp list 1");
                    ennemisVue.supprimerSpriteTour(e);
                }
            }
        }
    }
}
