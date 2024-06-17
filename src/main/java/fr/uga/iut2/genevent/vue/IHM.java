package fr.uga.iut2.genevent.vue;


import fr.uga.iut2.genevent.controleur.Controleur;
import javafx.stage.Stage;

/**
 * La classe abstraite IHM fournit un cadre aux classes faisant office de
 * Controller dans le cadre de la librairie JavaFX
 */
public abstract class IHM {

    /**
     * Référence au contrôleur principal de l'application
     */
    protected Controleur controleur;

    IHM() {
        this.controleur = Controleur.getInstance(null);
    }

    /**
     * Produit une notification à l'utilisateur (pour l'instant inutilisé)
     *
     * @param message - Le message à afficher
     * @param succes  - true si le message correspond à un succès, false sinon.
     */
    public abstract void informerUtilisateur(String message, boolean succes);
}
