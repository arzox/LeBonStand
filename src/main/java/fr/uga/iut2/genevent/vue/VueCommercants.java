package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.modele.Commercant;
import fr.uga.iut2.genevent.modele.Emplacement;
import fr.uga.iut2.genevent.modele.TypeCommerce;
import fr.uga.iut2.genevent.util.EmplacementStringConverter;
import fr.uga.iut2.genevent.util.TypeCommerceStringConverter;
import fr.uga.iut2.genevent.util.Vues;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.converter.IntegerStringConverter;

public class VueCommercants extends IHM {
    private VueOnglets vueOnglets;

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


    public VueCommercants(VueOnglets vueOnglets) {
        super();
        this.vueOnglets = vueOnglets;
    }

    @Override
    public void informerUtilisateur(String message, boolean succes) {
        System.out.println(message);
    }

    @FXML
    private void initialize() {
        try {
            Parent ongletsRoot = Vues.loadView("tabs.fxml", vueOnglets);
            container.getChildren().add(0, ongletsRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }

        setupTable();
    }

    private void setupTable() {
        commercantsTable.setEditable(true);
        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomColumn.setOnEditCommit(event -> {
            Commercant commercant = controleur.getControleurCommercant().
        });

        prenomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        telephoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));

        heureDebutColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        heureDebutColumn.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));

        heureFinColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        heureFinColumn.setCellValueFactory(new PropertyValueFactory<>("heureFin"));

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
        commercantsTable.getItems().add(new Commercant("Nom", "Prenom", "Mail", "06010203", 8, 20, null, null));
    }
}