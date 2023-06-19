import fr.iut.montreuil.modele.Modele;
import fr.iut.montreuil.modele.acteur.Soldat;
import fr.iut.montreuil.modele.acteur.Tour;
import javafx.scene.layout.BorderPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

class ModeleTest {

    private Modele modele;
    private Tour tour;
    private Soldat soldat;

    @BeforeEach
    public void setUp() {
        modele = new Modele(new BorderPane(), null, null);
        tour = new Tour(500, 500, new BorderPane(), 1, null);
        soldat = new Soldat(0, 370, modele, 1);
    }
    @Test
    void testGenererTableauMap() {
        int[] tableauMap = modele.genererTableauMap();

        assertNotNull(tableauMap);
        assertEquals(400, tableauMap.length);

        // Vérifier les valeurs du tableau selon votre logique
        // ...
    }

    @Test
    void testAjouterActeur() {
        modele.ajouterActeur(soldat);

        assertEquals(1, modele.getListActeurs().size());
        assertTrue(modele.getListActeurs().contains(soldat));
    }

    @Test
    void testAjouterTour() {

        modele.ajouterTour(tour);

        assertEquals(1, modele.getListTours().size());
        assertTrue(modele.getListTours().contains(tour));
    }

    @Test
    public void testSupprimeTour() {

        modele.ajouterTour(tour);
        assertTrue(modele.getListTours().contains(tour));
        modele.supprimeTour(tour);
        assertFalse(modele.getListTours().contains(tour));
    }

    @Test
    public void testverifSoldat(){
        Soldat sld = new Soldat(460, 380, modele, 1);
        modele.verifSoldat();
        assertTrue(modele.getListActeurs().isEmpty());
    }

    @Test
    public void testgetTileValue(){
        assertEquals(modele.getTileValue(460, 380), 10);
    }

    // Ajoutez d'autres tests pour couvrir les autres fonctionnalités de la classe Modele
    // ...
}
