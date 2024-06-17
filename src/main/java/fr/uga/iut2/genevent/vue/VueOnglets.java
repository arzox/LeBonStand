package fr.uga.iut2.genevent.vue;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import fr.uga.iut2.genevent.modele.Fonctionnalite;
import fr.uga.iut2.genevent.util.Vues;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * La classe VueEvenement est responsable des interactions avec
 * l'utilisa·teur/trice en mode graphique pour la vue accueil (liste des
 * événements)
 * <p>
 * Contrôleur de tab-event.fxml (informations générales sur l'événement)
 */
public class VueOnglets extends IHM {

    private Parent ongletsRoot;

    @FXML
    private Text nomEvenement;
    @FXML
    private VBox panel;

    VueOnglets() {
        super();
    }

    @FXML
    public void initialize() {
        setupButton();
    }

    /**
     * Initialise le panneau de navigation :
     * <p>
     * - Applique le nom de l'événement pour le bouton "Événement"
     * <p>
     * - Met à jour les boutons selon les fonctionnalités activées pour cet événement
     */
    private void setupButton() {
        nomEvenement.setText(controleur.getControleurEvenement().getEvenement().getNom());
        ArrayList<Fonctionnalite> allFonctionnalites = new ArrayList<>(EnumSet.allOf(Fonctionnalite.class));
        ArrayList<Fonctionnalite> fonctionnalitesEvenement = controleur.getControleurEvenement().getEvenement()
                .getFonctionnalites();

        List<Node> buttons = panel.getChildren().subList(4, panel.getChildren().size());

        for (int i = allFonctionnalites.size() - 1; i >= 0; i--) {
            Fonctionnalite fonctionnalite = allFonctionnalites.get(i);
            if (!fonctionnalitesEvenement.contains(fonctionnalite)) {
                buttons.remove(i);
            }
        }
    }

    /**
     * Charge la vue tabs.fxml et crée l'objet Parent correspondant afin qu'il
     * puisse être utilisé par les classes utilisant les onglets sur le côté
     * (panneau de navigation)
     */
    public void load() {
        try {
            // Charger la scène dans le loader et lui affecter le controleur en argument
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/uga/iut2/genevent/vue/tabs.fxml"));
            loader.setController(this);
            Parent parent = loader.load();
            Scene newScene = new Scene(parent);

            // Appliquer le css global
            newScene.getStylesheets().clear();
            newScene.getStylesheets()
                    .add(Vues.class.getResource("/fr/uga/iut2/genevent/style/style.css").toExternalForm());

            setOngletsRoot(parent);

        } catch (Exception e) {
            System.err.println("Erreur pendant le chargement de la vue :\n");
            e.printStackTrace();
        }
    }

    // Implémentations et redéfinitions

    public void changerFenetre(Stage stage) {
    }

    // Getters et setters

    public void setOngletsRoot(Parent ongletsRoot) {
        this.ongletsRoot = ongletsRoot;
    }

    public Parent getOngletsRoot() {
        return ongletsRoot;
    }
}
