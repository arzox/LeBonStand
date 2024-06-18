package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.util.Vues;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * La classe VueEvenement est responsable des interactions avec
 * l'utilisa·teur/trice en mode graphique pour la vue accueil (liste des
 * événements)
 * <p>
 * Contrôleur de tab-event.fxml (informations générales sur l'événement)
 */
public class VueEvenement extends IHM {

    private final VueOnglets vueOnglets;

    @FXML
    HBox container;

    VueEvenement(VueOnglets vueOnglets) {
        super();
        this.vueOnglets = vueOnglets;
    }

    // Implémentations et redéfinitions

    @FXML
    private void initialize() {
        try {
            Parent ongletsRoot = Vues.loadView("tabs.fxml", vueOnglets);
            container.getChildren().add(0,ongletsRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void informerUtilisateur(String message, boolean succes) {
        System.out.println(message);
    }

    // Getters et setters
    public VueOnglets getVueOnglets() {
        return vueOnglets;
    }
}
