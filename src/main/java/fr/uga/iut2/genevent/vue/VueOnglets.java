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
 * La classe VueOnglets est responsable des interactions avec
 * l'utilisa·teur/trice en mode graphique pour la vue des onglets.
 */
public class VueOnglets extends IHM {

    private Parent ongletsRoot;

    @FXML
    private Text nomEvenement;
    @FXML
    private VBox panel;

    public VueOnglets() {
        super();
    }

    @Override
    public void informerUtilisateur(String message, boolean succes) {
        System.out.println(message);
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
        // Définir le nom de l'événement dans le texte
        nomEvenement.setText(controleur.getControleurEvenement().getEvenement().getNom());

        // Obtenir toutes les fonctionnalités disponibles
        ArrayList<Fonctionnalite> allFonctionnalites = new ArrayList<>(EnumSet.allOf(Fonctionnalite.class));

        // Obtenir les fonctionnalités activées pour cet événement
        ArrayList<Fonctionnalite> fonctionnalitesEvenement = controleur.getControleurEvenement().getEvenement().getFonctionnalites();

        // Obtenir la liste des boutons dans le panneau après le cinquième élément
        List<Node> buttons = panel.getChildren().subList(5, panel.getChildren().size());

        // Supprimer les boutons pour les fonctionnalités non activées
        for (int i = buttons.size() - 1; i >= 0; i--) {
            Fonctionnalite fonctionnalite = allFonctionnalites.get(i);
            if (!fonctionnalitesEvenement.contains(fonctionnalite)) {
                buttons.remove(i);
            }
        }

        // Ajouter des écouteurs d'événements pour les clics sur les sections
        panel.getChildren().forEach(node -> node.setOnMouseClicked(event -> {
            int index = panel.getChildren().indexOf(node);
            if (index >= 0 && index < panel.getChildren().size()) {
                setCurrentOnglet(index);
            }
        }));
    }

    /**
     * Définit l'onglet courant et met à jour le style des boutons pour refléter la sélection.
     */
    public void setCurrentOnglet(int i) {
        if (i < 0 || i >= panel.getChildren().size()) {
            return;
        }
        panel.getChildren().forEach(node -> node.getStyleClass().remove("button-selected"));
        panel.getChildren().get(i).getStyleClass().add("button-selected");
    }

    @FXML
    private void onAccueil() {
        Stage stage = (Stage) panel.getScene().getWindow();
        Vues.loadViewIntoStage(stage, "accueil.fxml", new VueAccueil());
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
            newScene.getStylesheets().add(Vues.class.getResource("/fr/uga/iut2/genevent/style/style.css").toExternalForm());

            setOngletsRoot(parent);
        } catch (Exception e) {
            System.err.println("Erreur pendant le chargement de la vue :\n");
            e.printStackTrace();
        }
    }

    // Getters et setters

    public void setOngletsRoot(Parent ongletsRoot) {
        this.ongletsRoot = ongletsRoot;
    }

    public Parent getOngletsRoot() {
        return ongletsRoot;
    }
}
