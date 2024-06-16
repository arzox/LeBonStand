package fr.uga.iut2.genevent.vue;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import fr.uga.iut2.genevent.util.Vues;

public class VueAccueil extends IHM {

//-----  Éléments de la page d'accueil  -------------------------------------------------
    @FXML Button supprimerEvenementOui;
    @FXML Button supprimerEvenementNon;

    @FXML
    protected void nouvelEvenementCliquer() throws Exception {
        try {
            Vues.loadViewIntoStage(new Stage(), "new-event.fxml", this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void supprimerEvenement() throws Exception {
        try {
            Vues.loadViewIntoStage(new Stage(), "event-delete.fxml", this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//-----  Implémentation des méthodes abstraites  -------------------------------

    @Override
    public void changerFenetre(Stage stage) {
        try {
            FXMLLoader mainViewLoader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
            mainViewLoader.setController(this);
            Scene mainScene = new Scene(mainViewLoader.load());
            stage.setScene(mainScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
