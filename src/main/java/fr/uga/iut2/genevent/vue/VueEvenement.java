package fr.uga.iut2.genevent.vue;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VueEvenement extends IHM {

    VueEvenement() {
        super();
    }

    public void changerFenetre(Stage stage) {
        FXMLLoader mainViewLoader = new FXMLLoader(getClass().getResource("evenement.fxml"));
        mainViewLoader.setController(this);
        try {
            Scene mainScene = new Scene(mainViewLoader.load());
            stage.setScene(mainScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void informerUtilisateur(String message, boolean succes) {
        System.out.println(message);
    }
}
