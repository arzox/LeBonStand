package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.ControleurEvenement;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Fonctionnalite;
import fr.uga.iut2.genevent.modele.Lieu;
import fr.uga.iut2.genevent.modele.TypeEvenement;
import fr.uga.iut2.genevent.util.Vues;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VueEvenement extends IHM {

    private final VueOnglets vueOnglets;
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

    public VueEvenement(VueOnglets vueOnglets) {
        super();
        this.vueOnglets = vueOnglets;
    }

    public void setControleurEvenement(ControleurEvenement controleurEvenement) {
        this.controleurEvenement = controleurEvenement;
    }

    @FXML
    public void initialize() {
        typeEvenement.getItems().setAll(TypeEvenement.values());
    }

    @Override
    public void changerFenetre(Stage stage) {
        vueOnglets.load();
        Parent parent = Vues.loadViewIntoStage(stage, "tab-event.fxml", this);
        BorderPane root = new BorderPane();
        root.setLeft(vueOnglets.getOngletsRoot());
        root.setCenter(parent);
        stage.setScene(new javafx.scene.Scene(root));
    }

    @FXML
    private void enregistrerEvenement() {
        String nom = nomEvenement.getText();
        TypeEvenement type = typeEvenement.getValue();
        List<Fonctionnalite> fonctionnalites = new ArrayList<>();
        if (cbSecurite.isSelected()) fonctionnalites.add(Fonctionnalite.AGENT_SECURITE);
        if (cbEntretien.isSelected()) fonctionnalites.add(Fonctionnalite.AGENT_ENTRETIEN);
        if (cbAnimations.isSelected()) fonctionnalites.add(Fonctionnalite.ANIMATION);
        if (cbParticipants.isSelected()) fonctionnalites.add(Fonctionnalite.PARTICIPANT);

        Evenement evenement = controleurEvenement.creerEvenement(nom, type, fonctionnalites, this);

        String adresseText = adresse.getText();
        String villeText = ville.getText();
        String codePostalText = codePostal.getText();
        evenement.setLieu(new Lieu(adresseText, villeText, codePostalText));

        LocalDate debut = dateDebut.getValue();
        LocalDate fin = dateFin.getValue();

        controleurEvenement.setDebutEvenement(evenement, debut.toString(), this);
        controleurEvenement.setFinEvenement(evenement, fin.toString(), this);

        rafraichirVue();
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
}
