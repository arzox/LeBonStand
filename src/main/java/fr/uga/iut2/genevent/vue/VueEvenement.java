package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.util.Vues;
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

    VueEvenement(VueOnglets vueOnglets) {
        super();
        this.vueOnglets = vueOnglets;
    }

    // Implémentations et redéfinitions
    
    /**
     * Modifie l'état de la fenêtre en argument pour lui appliquer l'onglet par défaut pour la vue principale.
     * 
     * @param stage - Le stage dont la vue doit être changée
     */
    @Override
    public void changerFenetre(Stage stage) {
        // tab-X.fxml = onglet de la vue principale
        // tabs.fxml = panneau de navigation sur le côté
        changerFenetre(stage, "tab-event.fxml");
    }

    // Signature alternative de changerFenetre

    /**
     * Modifie l'état de la fenêtre en argument pour lui appliquer l'onglet spécifié par l'argument {@code fxmlName}, puis ajoute le panneau de navigation vertical pour compléter.
     * 
     * @param stage - Le stage dont la vue doit être changée
     * @param fxmlName - Vue à appliquer au stage
     */
    public void changerFenetre(Stage stage, String fxmlName) {
        vueOnglets.load();
        Parent parent = Vues.loadViewIntoStage(stage, fxmlName, this);
        
        ((HBox) parent).getChildren().add(0, vueOnglets.getOngletsRoot());
    }

    // Getters et setters

    public VueOnglets getVueOnglets() {
        return vueOnglets;
    }
}
