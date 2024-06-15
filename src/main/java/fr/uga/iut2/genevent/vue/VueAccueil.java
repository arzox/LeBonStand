package fr.uga.iut2.genevent.vue;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.util.Set;

import fr.uga.iut2.genevent.util.Vues;

public class VueAccueil extends IHM {


//-----  Éléments de la page d'accueil  -------------------------------------------------

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
