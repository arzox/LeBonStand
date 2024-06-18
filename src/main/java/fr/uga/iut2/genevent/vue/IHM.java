package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.Controleur;
import fr.uga.iut2.genevent.util.Vues;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * La classe abstraite IHM fournit un cadre aux classes faisant office de
 * Controller dans le cadre de la librairie JavaFX
 */
public abstract class IHM {
    private Parent parent;

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

    /**
     * Applique cette vue à l'objet Stage en argument
     * 
     * @param stage
     */
    public void changerFenetre(Stage stage) {
        Vues.loadViewIntoStage(stage, getFxmlName(), this);
    }

    /**
     * Charge la vue et crée l'objet Parent correspondant afin qu'il
     * puisse être utilisé par les classes utilisant les onglets sur le côté
     * (panneau de navigation)
     */
    public void load() {
        try {
            // Charger la scène dans le loader et lui affecter le controleur en argument
            setParent(Vues.loadViewAsParent(getFxmlName(), this));
        } catch (Exception e) {
            System.err.println("Erreur pendant le chargement de la vue :\n");
            e.printStackTrace();
        }
    }

    // Getters et setters

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public abstract String getFxmlName();
}
