package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.ControleurEvenement;
import fr.uga.iut2.genevent.modele.Fonctionnalite;
import fr.uga.iut2.genevent.modele.TypeEvenement;
import fr.uga.iut2.genevent.util.Vues;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * La classe VueEvenement est responsable des interactions avec
 * l'utilisa·teur/trice en mode graphique pour la vue d'un événement
 * <p>
 * Contrôleur de : tab-event.fxml
 */
public class VueEvenement extends IHM {

    @FXML
    private Text nomEvenement; // Affiche le nom de l'événement

    @FXML
    private TextField nomEvenementField; // Champ de saisie pour le nom de l'événement

    @FXML
    private ComboBox<TypeEvenement> typeEvenementComboBox; // Sélecteur du type d'événement

    @FXML
    private VBox panel; // Panneau pour les fonctionnalités

    @FXML
    private TextField adresseField; // Champ de saisie pour l'adresse

    @FXML
    private TextField villeField; // Champ de saisie pour la ville

    @FXML
    private TextField codePostalField; // Champ de saisie pour le code postal

    @FXML
    private DatePicker dateDebutPicker; // Sélecteur de date pour la date de début

    @FXML
    private DatePicker dateFinPicker; // Sélecteur de date pour la date de fin

    @FXML
    private CheckBox securiteCheckBox;
    @FXML
    private CheckBox entretienCheckBox;
    @FXML
    private CheckBox animationsCheckBox;
    @FXML
    private CheckBox participantsCheckBox;

    private ControleurEvenement controleurEvenement;

    public VueEvenement() {
        super();
    }

    /**
     * Méthode d'initialisation appelée automatiquement après le chargement de la vue.
     */
    @FXML
    public void initialize() {
        // Charger les éléments de la ComboBox avec les valeurs de l'enum TypeEvenement
        typeEvenementComboBox.getItems().setAll(TypeEvenement.values());

        // Charger les détails de l'événement seulement si le contrôleur est défini
        if (controleurEvenement != null) {
            loadEventDetails();
        }

        // Ajouter des listeners pour les CheckBoxes
        securiteCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> handleFonctionnaliteChange(Fonctionnalite.AGENT_SECURITE, newValue));
        entretienCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> handleFonctionnaliteChange(Fonctionnalite.AGENT_ENTRETIEN, newValue));
        animationsCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> handleFonctionnaliteChange(Fonctionnalite.ANIMATION, newValue));
        participantsCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> handleFonctionnaliteChange(Fonctionnalite.PARTICIPANT, newValue));
    }

    /**
     * Charge les détails de l'événement.
     */
    private void loadEventDetails() {
        if (controleurEvenement.getEvenement() != null) {
            // Initialiser les champs avec les détails de l'événement
            nomEvenement.setText(controleurEvenement.getEvenement().getNom());
            nomEvenementField.setText(controleurEvenement.getEvenement().getNom());
            typeEvenementComboBox.setValue(controleurEvenement.getEvenement().getType());
            adresseField.setText(controleurEvenement.getEvenement().getLieu().getAdresse());
            villeField.setText(controleurEvenement.getEvenement().getLieu().getNom());
            codePostalField.setText(controleurEvenement.getEvenement().getLieu().getCodePostal());

            // Convertir les dates en LocalDate pour les DatePickers
            dateDebutPicker.setValue(LocalDate.parse(controleurEvenement.getEvenement().getDebut(), DateTimeFormatter.ISO_DATE));
            dateFinPicker.setValue(LocalDate.parse(controleurEvenement.getEvenement().getFin(), DateTimeFormatter.ISO_DATE));

            // Charger les fonctionnalités
            loadFunctionalities();
        } else {
            System.err.println("Aucun événement trouvé dans le contrôleur.");
        }
    }

    /**
     * Charge les fonctionnalités de l'événement.
     */
    private void loadFunctionalities() {
        List<Fonctionnalite> fonctionnalitesEvenement = controleurEvenement.getEvenement().getFonctionnalites();

        securiteCheckBox.setSelected(fonctionnalitesEvenement.contains(Fonctionnalite.AGENT_SECURITE));
        entretienCheckBox.setSelected(fonctionnalitesEvenement.contains(Fonctionnalite.AGENT_ENTRETIEN));
        animationsCheckBox.setSelected(fonctionnalitesEvenement.contains(Fonctionnalite.ANIMATION));
        participantsCheckBox.setSelected(fonctionnalitesEvenement.contains(Fonctionnalite.PARTICIPANT));
    }

    /**
     * Gère le changement de fonctionnalité.
     */
    private void handleFonctionnaliteChange(Fonctionnalite fonctionnalite, boolean isSelected) {
        if (isSelected) {
            controleurEvenement.getEvenement().getFonctionnalites().add(fonctionnalite);
        } else {
            controleurEvenement.getEvenement().getFonctionnalites().remove(fonctionnalite);
        }
        updateTabsPanel(fonctionnalite, isSelected);
    }

    /**
     * Met à jour le panneau des onglets de gauche.
     */
    private void updateTabsPanel(Fonctionnalite fonctionnalite, boolean add) {
        // Implémentez la logique pour mettre à jour les onglets de gauche
        if (add) {
            System.out.println("Ajouter la fonctionnalité: " + fonctionnalite);
        } else {
            System.out.println("Retirer la fonctionnalité: " + fonctionnalite);
        }
    }

    /**
     * Définit le contrôleur d'événement pour cette vue.
     *
     * @param controleurEvenement Le contrôleur d'événement à utiliser.
     */
    public void setControleurEvenement(ControleurEvenement controleurEvenement) {
        this.controleurEvenement = controleurEvenement;
        // Charger les détails de l'événement après avoir défini le contrôleur
        if (controleurEvenement != null) {
            loadEventDetails();
        }
    }

    // Implémentations et redéfinitions

    @Override
    public void changerFenetre(Stage stage) {
        Vues.loadViewIntoStage(stage, "tab-event.fxml", this);
    }

    @FXML
    protected void ajouterDetail() {
        try {
            Vues.loadViewIntoStage(new Stage(), "new-detail.fxml", this);
            // Recharger les détails de l'événement après l'ajout d'un nouveau détail
            loadEventDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void supprimerDetail() {
        try {
            Vues.loadViewIntoStage(new Stage(), "delete-detail.fxml", this);
            // Recharger les détails de l'événement après la suppression d'un détail
            loadEventDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
