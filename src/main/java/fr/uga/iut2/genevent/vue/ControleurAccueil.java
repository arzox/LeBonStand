package fr.uga.iut2.genevent.vue;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Set;

public class ControleurAccueil extends IHM {


//-----  Éléments de la page d'accueil  -------------------------------------------------

    @FXML
    protected void nouvelEvenementCliquer() throws Exception {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("new-event-view.fxml"));

            fxmlLoader.setController(this);

            Parent sceneDialog = fxmlLoader.load();
            Stage dialog = new Stage();
            dialog.setScene(new Scene(sceneDialog));
            dialog.show();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void supprimerEvenement() throws Exception {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("event-delete-view.fxml"));

            fxmlLoader.setController(this);

            Parent sceneDialog = fxmlLoader.load();
            Stage dialog = new Stage();
            dialog.setScene(new Scene(sceneDialog));
            dialog.show();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//-----  Implémentation des méthodes abstraites  -------------------------------

    @Override
    public void demarrerInteraction() {

    }

    @Override
    public void informerUtilisateur(String msg, boolean succes) {

    }

    @Override
    public void saisirUtilisateur() {

    }

    @Override
    public void saisirNouvelEvenement(Set<String> nomsExistants) {

    }
}
