package fr.uga.iut2.genevent.vue;


import fr.uga.iut2.genevent.controleur.Controleur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class IHM {

    protected Controleur controleur;

    IHM() {
        this.controleur = Controleur.getInstance(null);
    }

    abstract void changerFenetre(Stage stage);
}
