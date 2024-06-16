package fr.uga.iut2.genevent.vue;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import fr.uga.iut2.genevent.util.Vues;

public class VueAccueil extends IHM {

//-----  Éléments de la page d'accueil  -------------------------------------------------
    @FXML Button supprimerEvenementOui;
    @FXML Button supprimerEvenementNon;

    @FXML
    protected void nouvelEvenementCliquer() throws Exception {
        Vues.loadViewIntoStage(new Stage(), "new-event.fxml", this);

    }

    @FXML
    protected void supprimerEvenement() throws Exception {
        Vues.loadViewIntoStage(new Stage(), "delete-event.fxml", this);
    }

//-----  Implémentation des méthodes abstraites  -------------------------------

    @Override
    public void changerFenetre(Stage stage) {
        Vues.loadViewIntoStage(stage, "accueil.fxml", this);
    }
}
