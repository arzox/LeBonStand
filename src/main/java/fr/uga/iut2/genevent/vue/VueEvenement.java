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

    // Signature alternative de changerFenetre

    /**
     * Modifie l'état de la fenêtre en argument pour lui appliquer l'onglet spécifié par l'argument {@code fxmlName}, puis ajoute le panneau de navigation vertical pour compléter.
     * 
     * @param stage - Le stage dont la vue doit être changée
     * @param fxmlName - Vue à appliquer au stage
     */
    public void changerFenetre(Stage stage, String fxmlName) {
        // tab-X.fxml = onglet de la vue principale
        // tabs.fxml = panneau de navigation sur le côté
        vueOnglets.load();
        Parent parent = Vues.loadViewIntoStage(stage, fxmlName, this);
        
        ((HBox) parent).getChildren().add(0, vueOnglets.getOngletsRoot());
    }

    public void informerUtilisateur(String message, boolean succes) {
        System.out.println(message);
    }

    // Getters et setters
    public VueOnglets getVueOnglets() {
        return vueOnglets;
    }
}
