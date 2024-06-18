package fr.uga.iut2.genevent.vue;


import fr.uga.iut2.genevent.controleur.ControleurEvenement;
import fr.uga.iut2.genevent.exception.MauvaisChampsException;

import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Fonctionnalite;
import fr.uga.iut2.genevent.modele.Lieu;
import fr.uga.iut2.genevent.modele.TypeEvenement;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;


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

    private ControleurEvenement controleurEvenement;

    public VueEvenement() {
        super();
    }

    @FXML
    private void initialize() {
        typeEvenementComboBox.setItems(FXCollections.observableArrayList(controleurEvenement.getEvenement().getType().toString()));

        loadEventData();

        addFieldListeners();
    }

    private void loadEventData() {
        nomEvenementField.setText(controleurEvenement.getEvenement().getNom());
        typeEvenementComboBox.setValue(controleurEvenement.getEvenement().getType().toString());
        dateDebutPicker.setValue(controleurEvenement.getEvenement().getDateDebut());
        dateFinPicker.setValue(controleurEvenement.getEvenement().getDateFin());

        securiteCheckBox.setSelected(controleurEvenement.getEvenement().getFonctionnalites().contains(Fonctionnalite.AGENT_SECURITE));
        entretienCheckBox.setSelected(controleurEvenement.getEvenement().getFonctionnalites().contains(Fonctionnalite.AGENT_ENTRETIEN));
        animationsCheckBox.setSelected(controleurEvenement.getEvenement().getFonctionnalites().contains(Fonctionnalite.ANIMATION));
        participantsCheckBox.setSelected(controleurEvenement.getEvenement().getFonctionnalites().contains(Fonctionnalite.PARTICIPANT));

        if (controleurEvenement.getEvenement().getLieu() != null) {
            adresseField.setText(controleurEvenement.getEvenement().getLieu().getAdresse());
            villeField.setText(controleurEvenement.getEvenement().getLieu().getNom());
            codePostalField.setText(controleurEvenement.getEvenement().getLieu().getCodePostal());
        } else {
            adresseField.setText("");
            villeField.setText("");
            codePostalField.setText("");
        }
    }

    private void addFieldListeners() {
        nomEvenementField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                controleurEvenement.modifierNomEvenement(controleurEvenement.getEvenement(), newValue);
            } catch (MauvaisChampsException e) {
                informerUtilisateur(e.getMessage(), false);
            }
        });

        typeEvenementComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                controleurEvenement.modifierTypeEvenement(controleurEvenement.getEvenement(), TypeEvenement.fromString(newValue));
            } catch (MauvaisChampsException e) {
                informerUtilisateur(e.getMessage(), false);
            }
        });

        dateDebutPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                controleurEvenement.modifierDebutEvenement(controleurEvenement.getEvenement(), newValue);
            } catch (MauvaisChampsException e) {
                informerUtilisateur(e.getMessage(), false);
            }
        });

        dateFinPicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            try {
                controleurEvenement.modifierFinEvenement(controleurEvenement.getEvenement(), newValue);
            } catch (MauvaisChampsException e) {
                informerUtilisateur(e.getMessage(), false);
            }
        });

        adresseField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (controleurEvenement.getEvenement().getLieu() == null) {
                controleurEvenement.getEvenement().setLieu(new Lieu());
            }
            controleurEvenement.getEvenement().getLieu().setAdresse(newValue);
        });

        villeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (controleurEvenement.getEvenement().getLieu() == null) {
                controleurEvenement.getEvenement().setLieu(new Lieu());
            }
            controleurEvenement.getEvenement().getLieu().setNom(newValue);
        });

        codePostalField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (controleurEvenement.getEvenement().getLieu() == null) {
                controleurEvenement.getEvenement().setLieu(new Lieu());
            }
            controleurEvenement.getEvenement().getLieu().setCodePostal(newValue);
        });

        securiteCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> updateFonctionnalite(Fonctionnalite.AGENT_SECURITE, newValue));
        entretienCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> updateFonctionnalite(Fonctionnalite.AGENT_ENTRETIEN, newValue));
        animationsCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> updateFonctionnalite(Fonctionnalite.ANIMATION, newValue));
        participantsCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> updateFonctionnalite(Fonctionnalite.PARTICIPANT, newValue));
    }


    private void updateFonctionnalite(Fonctionnalite fonctionnalite, boolean add) {
        Evenement evenement = controleurEvenement.getEvenement();
        ArrayList<Fonctionnalite> fonctionnalites = new ArrayList<>(evenement.getFonctionnalites());

        if (add) {
            if (!fonctionnalites.contains(fonctionnalite)) {
                fonctionnalites.add(fonctionnalite);
            }
        } else {
            fonctionnalites.remove(fonctionnalite);
        }
        controleurEvenement.modifierFonctionnalitesEvenement(evenement, fonctionnalites);
    }

    @Override
    public void informerUtilisateur(String message, boolean succes) {
        Alert alert = new Alert(succes ? Alert.AlertType.INFORMATION : Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
