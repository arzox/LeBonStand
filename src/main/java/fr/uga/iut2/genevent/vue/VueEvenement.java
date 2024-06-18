package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.Controleur;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Fonctionnalite;
import fr.uga.iut2.genevent.modele.Lieu;
import fr.uga.iut2.genevent.modele.TypeEvenement;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class VueEvenement extends IHM {

    @FXML
    private TextField nomEvenementField;
    @FXML
    private ComboBox<String> typeEvenementComboBox;
    @FXML
    private CheckBox securiteCheckBox;
    @FXML
    private CheckBox entretienCheckBox;
    @FXML
    private CheckBox animationsCheckBox;
    @FXML
    private CheckBox participantsCheckBox;
    @FXML
    private TextField adresseField;
    @FXML
    private TextField villeField;
    @FXML
    private TextField codePostalField;
    @FXML
    private DatePicker dateDebutPicker;
    @FXML
    private DatePicker dateFinPicker;

    private final Evenement evenement;

    public VueEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    @FXML
    private void initialize() {
        // Initialize the typeEvenementComboBox with the list of event types
        typeEvenementComboBox.setItems(FXCollections.observableArrayList(TypeEvenement.getTypesEvenement()));

        // Load event data into the fields
        loadEventData();

        // Add listeners to update event data when fields are modified
        addFieldListeners();
    }

    private void loadEventData() {
        nomEvenementField.setText(evenement.getNom());
        typeEvenementComboBox.setValue(evenement.getType().toString());
        dateDebutPicker.setValue(evenement.getDateDebut());
        dateFinPicker.setValue(evenement.getDateFin());

        securiteCheckBox.setSelected(evenement.getFonctionnalites().contains(Fonctionnalite.AGENT_SECURITE));
        entretienCheckBox.setSelected(evenement.getFonctionnalites().contains(Fonctionnalite.AGENT_ENTRETIEN));
        animationsCheckBox.setSelected(evenement.getFonctionnalites().contains(Fonctionnalite.ANIMATION));
        participantsCheckBox.setSelected(evenement.getFonctionnalites().contains(Fonctionnalite.PARTICIPANT));

        if (evenement.getLieu() != null) {
            adresseField.setText(evenement.getLieu().getAdresse());
            villeField.setText(evenement.getLieu().getNom());
            codePostalField.setText(evenement.getLieu().getCodePostal());
        } else {
            adresseField.setText("");
            villeField.setText("");
            codePostalField.setText("");
        }
    }

    private void addFieldListeners() {
        nomEvenementField.textProperty().addListener((observable, oldValue, newValue) -> evenement.setNom(newValue));
        typeEvenementComboBox.valueProperty().addListener((observable, oldValue, newValue) -> evenement.setType(TypeEvenement.fromString(newValue)));
        dateDebutPicker.valueProperty().addListener((observable, oldValue, newValue) -> evenement.setDateDebut(newValue));
        dateFinPicker.valueProperty().addListener((observable, oldValue, newValue) -> evenement.setDateFin(newValue));

        adresseField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (evenement.getLieu() == null) {
                evenement.setLieu(new Lieu());
            }
            evenement.getLieu().setAdresse(newValue);
        });

        villeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (evenement.getLieu() == null) {
                evenement.setLieu(new Lieu());
            }
            evenement.getLieu().setNom(newValue);
        });

        codePostalField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (evenement.getLieu() == null) {
                evenement.setLieu(new Lieu());
            }
            evenement.getLieu().setCodePostal(newValue);
        });

        securiteCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> updateFonctionnalite(Fonctionnalite.AGENT_SECURITE, newValue));
        entretienCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> updateFonctionnalite(Fonctionnalite.AGENT_ENTRETIEN, newValue));
        animationsCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> updateFonctionnalite(Fonctionnalite.ANIMATION, newValue));
        participantsCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> updateFonctionnalite(Fonctionnalite.PARTICIPANT, newValue));
    }

    private void updateFonctionnalite(Fonctionnalite fonctionnalite, boolean add) {
        if (add) {
            evenement.addFonctionnalite(fonctionnalite);
        } else {
            evenement.removeFonctionnalite(fonctionnalite);
        }
    }


    @Override
    public void informerUtilisateur(String message, boolean succes) {
        Alert alert = new Alert(succes ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
