package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Fonctionnalite;
import fr.uga.iut2.genevent.modele.TypeEvenement;
import fr.uga.iut2.genevent.util.Vues;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class VueCreation extends IHM {

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

    @Override
    public void informerUtilisateur(String message, boolean succes) {
        System.out.println(message);
    }

    @FXML
    private void onAnnuler(ActionEvent event) {
        Stage stage = (Stage) typeMarche.getScene().getWindow();
        stage.close();
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


    @FXML
    private void onCreer(ActionEvent event) {
        Evenement evenement = controleur.getControleurEvenement().creerEvenement(nom.getText(), getTypeEvenement(), getFonctionnalitesSelected(), this);
        controleur.setEvenementCourant(evenement);
        ((Stage) typeMarche.getScene().getWindow()).close();
        Vues.loadViewIntoStage(previousStage, "tab-event.fxml", new VueEvenement(new VueOnglets()));
    }
}
