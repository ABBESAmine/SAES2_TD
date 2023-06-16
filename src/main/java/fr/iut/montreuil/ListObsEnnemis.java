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
                    ennemisVue.creerSpriteAct(e);
                }
            }
            if (change.wasRemoved()) {
                for (Soldat e : change.getRemoved()) {
                    ennemisVue.supprimerSpriteAct(e);
                }
            }
        }
    }
}
