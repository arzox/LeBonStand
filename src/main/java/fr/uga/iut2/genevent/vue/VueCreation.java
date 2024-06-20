package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.exception.MauvaisChampsException;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Fonctionnalite;
import fr.uga.iut2.genevent.modele.TypeEvenement;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * La classe VueCreation est responsable des interactions avec
 * l'utilisa·teur/trice en mode graphique pour la vue création d'événement
 * <p>
 * Contrôleur de : new-event.fxml
 */
public class VueCreation extends IHM {

    public static final String FXML_NAME = "new-event.fxml";

    @FXML
    private TextField nom;

    @FXML
    private VBox checkBox;

    @FXML
    private ComboBox<String> typeMarche;

    private Stage previousStage;

    public VueCreation(Stage previousStage) {
        super();
        this.previousStage = previousStage;
    }

    @FXML
    private void initialize() {
        typeMarche.setPromptText("Type de marché");
        typeMarche.setItems(FXCollections.observableList(TypeEvenement.getTypesEvenement()));
    }

    @FXML
    private void onAnnuler(ActionEvent event) {
        Stage stage = (Stage) typeMarche.getScene().getWindow();
        stage.fireEvent(
                new javafx.stage.WindowEvent(
                        stage,
                        javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    private void onCreer(ActionEvent event) {
        try {
            Evenement evenement = controleur.getControleurEvenement().creerEvenement(nom.getText(), getTypeEvenement(),
                    getFonctionnalitesSelected());
            controleur.setEvenementCourant(evenement);
            ((Stage) typeMarche.getScene().getWindow()).close();
            VueOnglets vueOnglets = new VueOnglets(new VueEvenement());
            vueOnglets.changerFenetre(previousStage);
        } catch (MauvaisChampsException e) {
            informerUtilisateur(e.getMessage(), false);
        }

    }

    private ArrayList<Fonctionnalite> getFonctionnalitesSelected() {
        ArrayList<Fonctionnalite> fonctionnalites = new ArrayList<>();
        for (int i = 0; i < checkBox.getChildren().size(); i++) {
            if (checkBox.getChildren().get(i) instanceof CheckBox) {
                CheckBox cb = (CheckBox) checkBox.getChildren().get(i);
                if (cb.isSelected()) {
                    fonctionnalites.add(Fonctionnalite.values()[i]);
                }
            }
        }
        return fonctionnalites;
    }

    private TypeEvenement getTypeEvenement() {
        return TypeEvenement.fromString(typeMarche.getValue());
    }

    // Implémentations et redéfinitions

    @Override
    public String getFxmlName() {
        return FXML_NAME;
    }
}
