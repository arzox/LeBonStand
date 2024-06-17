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
     * Modifie l'état de la fenêtre en argument pour lui appliquer la vue
     * correspondant à la classe courante. Pour chaque classe héritant de IHM,
     * {@link fr.uga.iut2.genevent.vue.IHM#changerFenetre(Stage)} devrait appliquer
     * une vue différente dont le contrôleur JavaFX est cette classe.
     * 
     * @param stage - Le stage dont la vue doit être changée
     */
    public abstract void changerFenetre(Stage stage);

    /**
     * Produit une notification à l'utilisateur (pour l'instant inutilisé)
     * 
     * @param message - Le message à afficher
     * @param succes  - true si le message correspond à un succès, false sinon.
     */
    public void informerUtilisateur(String message, boolean succes) {
        System.out.println(message);
    }
}
