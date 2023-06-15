package fr.iut.montreuil;

import fr.iut.montreuil.modele.vue.MonstreVue;
import fr.iut.montreuil.modele.acteur.Soldat;
import javafx.collections.ListChangeListener;


public class ListObsEnnemis implements ListChangeListener<Soldat> {



    private MonstreVue ennemisVue;

    public ListObsEnnemis( MonstreVue mVue) {
        ennemisVue = mVue;
    }

    @Override
    public void onChanged(Change<? extends Soldat> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (Soldat e : change.getAddedSubList()) {
                    System.out.println("add");
                    ennemisVue.creerSpriteAct(e);
                }
            }
            if (change.wasRemoved()) {
                for (Soldat e : change.getRemoved()) {
                    System.out.println("supp");
                    ennemisVue.supprimerSpriteAct(e);
                }
            }
        }
    }
}
