package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.ControleurCommercant;
import fr.uga.iut2.genevent.modele.Commercant;
import fr.uga.iut2.genevent.modele.Emplacement;
import fr.uga.iut2.genevent.modele.TypeCommerce;
import fr.uga.iut2.genevent.util.EmplacementStringConverter;
import fr.uga.iut2.genevent.util.TypeCommerceStringConverter;
import fr.uga.iut2.genevent.util.Vues;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.converter.IntegerStringConverter;

import java.util.HashMap;
import java.util.Map;

public class VueCommercants extends IHM {
    public static final String FXML_NAME = "tab-commercants.fxml";
    private ControleurCommercant controleurCommercant;

    @FXML
    HBox container;

    @FXML
    TableView<Commercant> commercantsTable;

    private Map<Commercant, BooleanProperty> checkedMap = new HashMap<>();

    @FXML TableColumn<Commercant, Boolean> checkBoxColumn;
    @FXML TableColumn<Commercant, String> nomColumn;
    @FXML TableColumn<Commercant, String> prenomColumn;
    @FXML TableColumn<Commercant, String> emailColumn;
    @FXML TableColumn<Commercant, String> telephoneColumn;
    @FXML TableColumn<Commercant, Integer> heureDebutColumn;
    @FXML TableColumn<Commercant, Integer> heureFinColumn;
    @FXML TableColumn<Commercant, Emplacement> emplacementColumn;
    @FXML TableColumn<Commercant, TypeCommerce> typeColumn;

    @FXML
    TableView<Object> annexTable;

    @FXML TableColumn<Object, String> idAnnex;
    @FXML TableColumn<Object, String> valueAnnex;

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
        setupAnnex();
        System.out.println(controleur.getControleurCommercant().getCommercants());
        commercantsTable.getItems().addAll(controleur.getControleurCommercant().getCommercants());
        annexTable.getItems().addAll(controleur.getControleurCommercant().getEmplacements());
    }

    private void setupAnnex() {
        idAnnex.setCellFactory(TextFieldTableCell.forTableColumn());
        idAnnex.setCellValueFactory(new PropertyValueFactory<>("numero"));
        idAnnex.setEditable(false);

        valueAnnex.setCellFactory(TextFieldTableCell.forTableColumn());
        valueAnnex.setCellValueFactory(new PropertyValueFactory<>("taille"));
        valueAnnex.setOnEditCommit(event -> {
            try {
                Emplacement emplacement = controleurCommercant.getEmplacement(Integer.parseInt(event.getNewValue()));
                controleurCommercant.modifierTailleEmplacement(emplacement, Integer.parseInt(event.getNewValue()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                try {
                    controleurCommercant.creerEmplacement(0);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    annexTable.refresh();
                }
            }
        });
    }

    private void setupTable() {
        checkBoxColumn.setCellValueFactory(cellData -> {
            Commercant commercant = cellData.getValue();
            BooleanProperty checkedProperty = checkedMap.get(commercant);
            if (checkedProperty == null) {
                checkedProperty = new SimpleBooleanProperty(false);
                checkedMap.put(commercant, checkedProperty);
            }
            return checkedProperty;
        });
        checkBoxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxColumn));

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
        emplacementColumn.setOnEditCommit(event -> {
            try {
                Emplacement emplacement = controleurCommercant.getEmplacement(event.getNewValue().getNumero());
                controleurCommercant.modifierEmplacementCommercant(event.getRowValue(), emplacement);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                try {
                    Emplacement emplacement = controleurCommercant.creerEmplacement(0);
                    controleurCommercant.modifierEmplacementCommercant(event.getRowValue(), emplacement);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    event.getRowValue().setEmplacement(event.getOldValue());
                    commercantsTable.refresh();
                }
            }
        });

        typeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new TypeCommerceStringConverter()));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeCommerce"));
        typeColumn.setOnEditCommit(event -> {
            try {
                TypeCommerce typeCommerce = controleurCommercant.getTypeCommerce(event.getNewValue().getNom());
                controleurCommercant.modifierTypeCommerceCommercant(event.getRowValue(), typeCommerce);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                try {
                    controleurCommercant.creerTypeCommerce(event.getNewValue().getNom(), 0);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    event.getRowValue().setTypeCommerce(event.getOldValue());
                    commercantsTable.refresh();
                }
            }
        });

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
        int i = (commercantsTable.getItems().size() + 1);
        while (true) {
            try {
                addLine(i);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                i++;
            }
        }
    }

    private void addLine(int i) throws Exception {
        commercantsTable.getItems().add(controleurCommercant.inscrireCommercant(("Nom" + i), "Prenom", "Mail", "06010203", 8, 20, null, null));
    }

    @FXML
    private void onSupprimer() {
        if (commercantsTable.getSelectionModel().getSelectedItem() == null) {
            try {
                controleurCommercant.desinscrireCommercant(commercantsTable.getSelectionModel().getSelectedItem());
                commercantsTable.getItems().remove(commercantsTable.getSelectionModel().getSelectedItem());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            commercantsTable.getSelectionModel().getSelectedItems().forEach(commercant -> {
                try {
                    controleurCommercant.desinscrireCommercant(commercant);
                    commercantsTable.getItems().remove(commercant);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
        }
    }

    @FXML
    private void onSave() {
        commercantsTable.refresh();
    }

    @Override
    public String getFxmlName() {
        return FXML_NAME;
    }
}