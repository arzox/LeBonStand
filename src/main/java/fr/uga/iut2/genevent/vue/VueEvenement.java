package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.ControleurEvenement;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Fonctionnalite;
import fr.uga.iut2.genevent.modele.Lieu;
import fr.uga.iut2.genevent.modele.TypeEvenement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class VueEvenement extends IHM {

    private ControleurEvenement controleurEvenement;

    @FXML
    private TextField nomEvenement;
    @FXML
    private ComboBox<TypeEvenement> typeEvenement;
    @FXML
    private CheckBox cbSecurite;
    @FXML
    private CheckBox cbEntretien;
    @FXML
    private CheckBox cbAnimations;
    @FXML
    private CheckBox cbParticipants;
    @FXML
    private TextField adresse;
    @FXML
    private TextField ville;
    @FXML
    private TextField codePostal;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private Text errorMessage;
    @FXML
    private VBox leftPanel;

    public VueEvenement() {
        // Constructeur sans argument nécessaire pour le FXMLLoader
    }

    public void setControleurEvenement(ControleurEvenement controleurEvenement) {
        this.controleurEvenement = controleurEvenement;
    }

    @FXML
    public void initialize() {
        typeEvenement.getItems().setAll(TypeEvenement.values());
        addDoubleClickHandlers();
        initializeLeftPanel();
    }

    public void changerFenetre(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("tab-event.fxml"));
            loader.setController(this);
            Parent parent = loader.load();
            BorderPane root = new BorderPane();
            root.setLeft(leftPanel);
            root.setCenter(parent);
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeLeftPanel() {
        updateLeftPanel();
    }

    private void updateLeftPanel() {
        leftPanel.getChildren().clear();
        leftPanel.getChildren().add(createTextNode("Événement"));
        leftPanel.getChildren().add(createTextNode("Commerçant"));
        if (cbSecurite.isSelected()) {
            leftPanel.getChildren().add(createTextNode("Sécurité"));
        }
        if (cbEntretien.isSelected()) {
            leftPanel.getChildren().add(createTextNode("Entretien"));
        }
        if (cbAnimations.isSelected()) {
            leftPanel.getChildren().add(createTextNode("Animations"));
        }
        if (cbParticipants.isSelected()) {
            leftPanel.getChildren().add(createTextNode("Participants"));
        }
    }

    private Text createTextNode(String text) {
        Text textNode = new Text(text);
        textNode.getStyleClass().add("label");
        return textNode;
    }

    @FXML
    private void enregistrerEvenement() {
        try {
            clearErrorStyles();

            String nom = nomEvenement.getText();
            if (nom.isEmpty()) {
                throw new IllegalArgumentException("Le nom de l'événement est obligatoire.");
            }
            setValidStyle(nomEvenement);

            TypeEvenement type = typeEvenement.getValue();
            if (type == null) {
                throw new IllegalArgumentException("Le type de l'événement est obligatoire.");
            }
            setValidStyle(typeEvenement);

            String adresseText = adresse.getText();
            if (adresseText.isEmpty()) {
                throw new IllegalArgumentException("L'adresse est obligatoire.");
            }
            setValidStyle(adresse);

            String villeText = ville.getText();
            if (villeText.isEmpty()) {
                throw new IllegalArgumentException("La ville est obligatoire.");
            }
            setValidStyle(ville);

            String codePostalText = codePostal.getText();
            if (codePostalText.isEmpty()) {
                throw new IllegalArgumentException("Le code postal est obligatoire.");
            }
            setValidStyle(codePostal);

            LocalDate debut = dateDebut.getValue();
            if (debut == null) {
                throw new IllegalArgumentException("La date de début est obligatoire.");
            }
            setValidStyle(dateDebut);

            LocalDate fin = dateFin.getValue();
            if (fin == null) {
                throw new IllegalArgumentException("La date de fin est obligatoire.");
            }
            if (fin.isBefore(debut)) {
                throw new IllegalArgumentException("La date de fin ne peut pas être antérieure à la date de début.");
            }
            setValidStyle(dateFin);

            List<Fonctionnalite> fonctionnalites = new ArrayList<>();
            if (cbSecurite.isSelected()) fonctionnalites.add(Fonctionnalite.AGENT_SECURITE);
            if (cbEntretien.isSelected()) fonctionnalites.add(Fonctionnalite.AGENT_ENTRETIEN);
            if (cbAnimations.isSelected()) fonctionnalites.add(Fonctionnalite.ANIMATION);
            if (cbParticipants.isSelected()) fonctionnalites.add(Fonctionnalite.PARTICIPANT);

            Evenement evenement = controleurEvenement.creerEvenement(nom, type, fonctionnalites, this);
            evenement.setLieu(new Lieu(adresseText, villeText, codePostalText));

            controleurEvenement.setDebutEvenement(evenement, debut.toString(), this);
            controleurEvenement.setFinEvenement(evenement, fin.toString(), this);

            errorMessage.setText("");
            rafraichirVue();
            setFieldsEditable(false);
            updateLeftPanel();
        } catch (IllegalArgumentException e) {
            errorMessage.setText(e.getMessage());
        } catch (DateTimeParseException e) {
            errorMessage.setText("Format de date invalide.");
        }
    }

    private void rafraichirVue() {
        nomEvenement.clear();
        typeEvenement.setValue(null);
        cbSecurite.setSelected(false);
        cbEntretien.setSelected(false);
        cbAnimations.setSelected(false);
        cbParticipants.setSelected(false);
        adresse.clear();
        ville.clear();
        codePostal.clear();
        dateDebut.setValue(null);
        dateFin.setValue(null);
    }

    private void setFieldsEditable(boolean editable) {
        nomEvenement.setEditable(editable);
        typeEvenement.setDisable(!editable);
        adresse.setEditable(editable);
        ville.setEditable(editable);
        codePostal.setEditable(editable);
        dateDebut.setDisable(!editable);
        dateFin.setDisable(!editable);
    }

    private void addDoubleClickHandlers() {
        setDoubleClickHandler(nomEvenement);
        setDoubleClickHandler(adresse);
        setDoubleClickHandler(ville);
        setDoubleClickHandler(codePostal);
    }

    private void setDoubleClickHandler(TextField textField) {
        textField.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                textField.setEditable(true);
            }
        });
    }

    private void clearErrorStyles() {
        nomEvenement.getStyleClass().remove("error");
        typeEvenement.getStyleClass().remove("error");
        adresse.getStyleClass().remove("error");
        ville.getStyleClass().remove("error");
        codePostal.getStyleClass().remove("error");
        dateDebut.getStyleClass().remove("error");
        dateFin.getStyleClass().remove("error");
    }

    private void setValidStyle(TextField textField) {
        textField.getStyleClass().add("valid");
    }

    private void setValidStyle(ComboBox<?> comboBox) {
        comboBox.getStyleClass().add("valid");
    }

    private void setValidStyle(DatePicker datePicker) {
        datePicker.getStyleClass().add("valid");
    }
}
