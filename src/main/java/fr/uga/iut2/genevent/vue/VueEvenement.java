package fr.uga.iut2.genevent.vue;

import javafx.fxml.FXML;
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

    @FXML
    HBox container;

    VueEvenement() {
        super();
    }

    // Implémentations et redéfinitions

    // @FXML
    // private void initialize() {
    //     try {
    //         Parent ongletsRoot = Vues.loadView("tabs.fxml", vueOnglets);
    //         container.getChildren().add(0, ongletsRoot);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    public void informerUtilisateur(String message, boolean succes) {
        System.out.println(message);
    }

    @Override
    public void changerFenetre(Stage stage) {
        // TODO Auto-generated method stub
    }
}
