package fr.uga.iut2.genevent.vue;

import java.net.URL;
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

    public void changerFenetre(Stage stage) {

    }

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

    public void setOngletsRoot(Parent ongletsRoot) {
        this.ongletsRoot = ongletsRoot;
    }

    public Parent getOngletsRoot() {
        return ongletsRoot;
    }

    public void load() {
        // Charger le panneau du côté (onglets)
        try {
            // Charger la scène dans le loader et lui affecter le controleur en argument
            URL temp = getClass().getResource("/fr/uga/iut2/genevent/vue/tabs.fxml");
            FXMLLoader loader = new FXMLLoader(temp);
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
}
