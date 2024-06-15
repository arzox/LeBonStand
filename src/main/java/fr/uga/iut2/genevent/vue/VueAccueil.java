package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.Controleur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VueAccueil extends IHM {


    VueAccueil() {
        super();
    }

    void changerFenetre(Stage stage) {
        FXMLLoader mainViewLoader = new FXMLLoader(getClass().getResource("accueil.fxml"));
        mainViewLoader.setController(this);
        try {
            Scene mainScene = new Scene(mainViewLoader.load());
            stage.setScene(mainScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
