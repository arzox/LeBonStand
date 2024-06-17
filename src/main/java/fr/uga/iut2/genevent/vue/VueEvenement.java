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

    public VueOnglets getVueOnglets() {
        return vueOnglets;
    }

    @Override
    public void changerFenetre(Stage stage) {
        vueOnglets.load();
        Parent parent = Vues.loadViewIntoStage(stage, "tab-event.fxml", this);

        ((HBox) parent).getChildren().add(0, vueOnglets.getOngletsRoot());
    }
}
