package fr.uga.iut2.genevent.vue;


import fr.uga.iut2.genevent.controleur.Controleur;
import javafx.stage.Stage;

public abstract class IHM {

    protected Controleur controleur;

    IHM() {
        this.controleur = Controleur.getInstance(null);
    }

    public abstract void changerFenetre(Stage stage);

    public abstract void informerUtilisateur(String message, boolean succes);
}
