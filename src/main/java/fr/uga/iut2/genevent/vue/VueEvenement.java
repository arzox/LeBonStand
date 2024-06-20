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
import javafx.stage.Stage;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;

public class VueEvenement extends IHM {

    public static final String FXML_NAME = "tab-event.fxml";

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
    @FXML
    private Button changeImageButton;
    @FXML
    private ImageView changeImageView;

    private ControleurEvenement controleurEvenement = controleur.getControleurEvenement();

    public VueEvenement() {
        super();
    }

    @FXML
    private void initialize() {
        typeEvenementComboBox.setItems(FXCollections.observableList(TypeEvenement.getTypesEvenement()));

        loadEventData();

        addFieldListeners();
    }

    @FXML
    private void updatePanel() {
        getPanelController().isLoaded = false;
        getPanelController().changerFenetre((Stage) nomEvenementField.getScene().getWindow());
    }

    private void loadEventData() {
        Evenement evenement = controleurEvenement.getEvenement();
        nomEvenementField.setText(evenement.getNom());
        typeEvenementComboBox.setValue(evenement.getType().getDisplayName());
        dateDebutPicker.setValue(evenement.getDateDebut());
        dateFinPicker.setValue(evenement.getDateFin());

        securiteCheckBox.setSelected(
                controleurEvenement.getEvenement().getFonctionnalites().contains(Fonctionnalite.AGENT_SECURITE));
        entretienCheckBox.setSelected(
                controleurEvenement.getEvenement().getFonctionnalites().contains(Fonctionnalite.AGENT_ENTRETIEN));
        animationsCheckBox.setSelected(
                controleurEvenement.getEvenement().getFonctionnalites().contains(Fonctionnalite.ANIMATION));
        participantsCheckBox.setSelected(
                controleurEvenement.getEvenement().getFonctionnalites().contains(Fonctionnalite.PARTICIPANT));

        if (evenement.getLieu() != null) {
            adresseField.setText(evenement.getLieu().getAdresse());
            villeField.setText(evenement.getLieu().getVille());
            codePostalField.setText(String.valueOf(evenement.getLieu().getCodePostal()));
        } else {
            adresseField.setText("");
            villeField.setText("");
            codePostalField.setText("");
        }

        // Charger l'image de l'événement ou une image par défaut
        String imagePath = evenement.getImagePath();
        Image image_defaut = new Image(getClass().getResourceAsStream("/fr/uga/iut2/genevent/images/marche_noel.jpg"));
        if (imagePath != null && !imagePath.isEmpty()) {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                Image image = new Image(imageFile.toURI().toString());
                changeImageView.setImage(image);
                changeImageView.setFitHeight(120);
                changeImageView.setFitWidth(120);
            } else {
                // Charger une image par défaut si l'image n'existe pas
                changeImageView.setImage(image_defaut);
                changeImageView.setFitHeight(120);
                changeImageView.setFitWidth(120);
            }
        } else {
            // Charger une image par défaut si aucun chemin d'image n'est défini
            changeImageView.setImage(image_defaut);
            changeImageView.setFitHeight(120);
            changeImageView.setFitWidth(120);
        }
    }

    @FXML
    private void changerImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            Image newImage = new Image(file.toURI().toString());
            changeImageView.setImage(newImage);
            controleurEvenement.setImagePath(controleurEvenement.getEvenement(), file.getAbsolutePath());
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
                controleurEvenement.modifierTypeEvenement(controleurEvenement.getEvenement(),
                        TypeEvenement.fromString(newValue));
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
                controleurEvenement.getEvenement().setLieu(new Lieu("", "", "", 0));
            }
            // Depuis la branche de Rahim
            // if (!newValue.trim().isEmpty()) {
            // Lieu lieu = controleurEvenement.getEvenement().getLieu();
            // if (lieu == null) {
            // lieu = controleurEvenement.creerLieu("", newValue, "", 0);
            // } else {
            // controleurEvenement.modifierAdresseLieu(lieu, newValue);
            // }
            // }
        });

        villeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (controleurEvenement.getEvenement().getLieu() == null) {
                controleurEvenement.getEvenement().setLieu(controleurEvenement.creerLieu("", "", "", 0));
            }
            // Depuis la branche de Rahim
            // if (!newValue.trim().isEmpty()) {
            // Lieu lieu = controleurEvenement.getEvenement().getLieu();
            // if (lieu == null) {
            // lieu = controleurEvenement.creerLieu("", "", newValue, 0);
            // } else {
            // controleurEvenement.modifierVilleLieu(lieu, newValue);
            // }
            // }
        });

        codePostalField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (controleurEvenement.getEvenement().getLieu() == null) {
                controleurEvenement.getEvenement().setLieu(controleurEvenement.creerLieu("", "", "", 0));
            }
            controleurEvenement.modifierCodePostalLieu(controleurEvenement.getEvenement().getLieu(),
                    controleurEvenement.getEvenement().getLieu().getCodePostal());

            // Depuis la branche de Rahim
            // if (!newValue.trim().isEmpty()) {
            // try {
            // int codePostal = Integer.parseInt(newValue);
            // Lieu lieu = controleurEvenement.getEvenement().getLieu();
            // if (lieu == null) {
            // lieu = controleurEvenement.creerLieu("", "", "", codePostal);
            // } else {
            // controleurEvenement.modifierCodePostalLieu(lieu, codePostal);
            // }
            // } catch (NumberFormatException e) {
            // informerUtilisateur("Code postal invalide", false);
            // }
            // }
        });

        securiteCheckBox.selectedProperty().addListener(
                (observable, oldValue, newValue) -> updateFonctionnalite(Fonctionnalite.AGENT_SECURITE, newValue));
        entretienCheckBox.selectedProperty().addListener(
                (observable, oldValue, newValue) -> updateFonctionnalite(Fonctionnalite.AGENT_ENTRETIEN, newValue));
        animationsCheckBox.selectedProperty().addListener(
                (observable, oldValue, newValue) -> updateFonctionnalite(Fonctionnalite.ANIMATION, newValue));
        participantsCheckBox.selectedProperty().addListener(
                (observable, oldValue, newValue) -> updateFonctionnalite(Fonctionnalite.PARTICIPANT, newValue));
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
    public String getFxmlName() {
        return FXML_NAME;
    }
}
