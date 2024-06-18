package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.ControleurCommercant;
import fr.uga.iut2.genevent.modele.Commercant;
import fr.uga.iut2.genevent.modele.Emplacement;
import fr.uga.iut2.genevent.modele.TypeCommerce;
import fr.uga.iut2.genevent.util.EmplacementStringConverter;
import fr.uga.iut2.genevent.util.TypeCommerceStringConverter;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.converter.IntegerStringConverter;

public class VueCommercants extends IHM {
    public static final String FXML_NAME = "tab-commercants.fxml";
    private ControleurCommercant controleurCommercant;

    @FXML
    HBox container;

    @FXML
    TableView<Commercant> commercantsTable;

    @FXML TableColumn<CheckBox, Boolean> checkBoxColumn;
    @FXML TableColumn<Commercant, String> nomColumn;
    @FXML TableColumn<Commercant, String> prenomColumn;
    @FXML TableColumn<Commercant, String> emailColumn;
    @FXML TableColumn<Commercant, String> telephoneColumn;
    @FXML TableColumn<Commercant, Integer> heureDebutColumn;
    @FXML TableColumn<Commercant, Integer> heureFinColumn;
    @FXML TableColumn<Commercant, Emplacement> emplacementColumn;
    @FXML TableColumn<Commercant, TypeCommerce> typeColumn;


    public VueCommercants() {
        super();
        this.controleurCommercant = controleur.getControleurCommercant();
    }

    // Implémentations et redéfinitions

    @Override
    public void informerUtilisateur(String message, boolean succes) {
        System.out.println(message);
    }

    @FXML
    private void initialize() {
        setupTable();
        System.out.println(controleur.getControleurCommercant().getCommercants());
        commercantsTable.getItems().addAll(controleur.getControleurCommercant().getCommercants());
    }

    private void setupTable() {
        commercantsTable.setEditable(true);
        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomColumn.setOnEditCommit(event -> {
            try {
                controleur.getControleurCommercant().modifierNomCommercant(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setNom(event.getOldValue());
                commercantsTable.refresh();
            }
        });

        prenomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        prenomColumn.setOnEditCommit(event -> {
            try {
                controleur.getControleurCommercant().modifierPrenomCommercant(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setPrenom(event.getOldValue());
                commercantsTable.refresh();
            }
        });

        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setOnEditCommit(event -> {
            try {
                controleur.getControleurCommercant().modifierEmailCommercant(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setEmail(event.getOldValue());
                commercantsTable.refresh();
            }
        });

        telephoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        telephoneColumn.setOnEditCommit(event -> {
            try {
                controleur.getControleurCommercant().modifierTelephoneCommercant(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setTelephone(event.getOldValue());
                commercantsTable.refresh();
            }
        });

        heureDebutColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        heureDebutColumn.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        heureDebutColumn.setOnEditCommit(event -> {
            try {
                controleur.getControleurCommercant().modifierHeureDebutCommercant(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setHeureDebut(event.getOldValue());
                commercantsTable.refresh();
            }
        });

        heureFinColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        heureFinColumn.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        heureFinColumn.setOnEditCommit(event -> {
            try {
                controleur.getControleurCommercant().modifierHeureFinCommercant(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setHeureFin(event.getOldValue());
                commercantsTable.refresh();
            }
        });

        emplacementColumn.setCellFactory(TextFieldTableCell.forTableColumn(new EmplacementStringConverter()));
        emplacementColumn.setCellValueFactory(new PropertyValueFactory<>("emplacement"));

        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new TypeCommerceStringConverter()));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeCommerce"));

        nomColumn.setMinWidth(100); // Replace 100 with the minimum width you want
        prenomColumn.setMinWidth(100); // Replace 100 with the minimum width you want
        emailColumn.setMinWidth(100); // Replace 100 with the minimum width you want
        telephoneColumn.setMinWidth(100); // Replace 100 with the minimum width you want
        heureDebutColumn.setMinWidth(100); // Replace 100 with the minimum width you want
        heureFinColumn.setMinWidth(100); // Replace 100 with the minimum width you want
        emplacementColumn.setMinWidth(100); // Replace 100 with the minimum width you want
        typeColumn.setMinWidth(100);

        commercantsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    private void onNewRow() {
        try {
            commercantsTable.getItems().add(controleurCommercant.inscrireCommercant("Nom", "Prenom", "Mail", "06010203", 8, 20, null, null));
            System.out.println(controleurCommercant.getCommercants());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getFxmlName() {
        return FXML_NAME;
    }
}