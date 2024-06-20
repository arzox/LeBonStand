package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.ControleurCommercant;
import fr.uga.iut2.genevent.modele.Commercant;
import fr.uga.iut2.genevent.modele.Emplacement;
import fr.uga.iut2.genevent.modele.TypeCommerce;
import fr.uga.iut2.genevent.util.EmplacementStringConverter;
import fr.uga.iut2.genevent.util.TypeCommerceStringConverter;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

import java.util.HashMap;
import java.util.Map;

public class VueCommercants extends IHM {
    public static final String FXML_NAME = "tab-commercants.fxml";
    private ControleurCommercant controleurCommercant;

    @FXML
    TableView<Commercant> commercantsTable;

    @FXML
    CheckBox checkBox;

    private Map<Commercant, BooleanProperty> checkedMap = new HashMap<>();

    @FXML
    TableColumn<Commercant, Boolean> checkBoxColumn;
    @FXML
    TableColumn<Commercant, String> nomColumn;
    @FXML
    TableColumn<Commercant, String> prenomColumn;
    @FXML
    TableColumn<Commercant, String> emailColumn;
    @FXML
    TableColumn<Commercant, String> telephoneColumn;
    @FXML
    TableColumn<Commercant, Integer> heureDebutColumn;
    @FXML
    TableColumn<Commercant, Integer> heureFinColumn;
    @FXML
    TableColumn<Commercant, Emplacement> emplacementColumn;
    @FXML
    TableColumn<Commercant, TypeCommerce> typeColumn;

    @FXML
    TableView<Emplacement> emplacementTable;

    @FXML TableColumn<Emplacement, Integer> numeroColumn;
    @FXML TableColumn<Emplacement, Integer> tailleColumn;

    @FXML
    TableView<TypeCommerce> typeTable;

    @FXML TableColumn<TypeCommerce, String> typeCommerceColumn;
    @FXML TableColumn<TypeCommerce, Integer> quotaColumn;

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
        setupEmplacement();
        setupTypeCommerce();
        showEmplacement();
        setupCheckBox();
        commercantsTable.getItems().addAll(controleur.getControleurCommercant().getEvenement().getCommercants());
        emplacementTable.getItems().addAll(controleur.getControleurCommercant().getEmplacements());
        typeTable.getItems().addAll(controleur.getControleurCommercant().getTypeCommerces());

    }

    private void setupCheckBox() {
        checkBox.setOnAction(actionEvent -> {
            checkedMap.forEach((commercant, booleanProperty) -> booleanProperty.set(checkBox.isSelected()));
        });
    }

    private void showEmplacement() {
        typeTable.setOpacity(0);
        typeTable.setMouseTransparent(true);
    }

    private void showTypeCommercants() {
        typeTable.setOpacity(1);
        typeTable.setMouseTransparent(false);
    }

    private void setupEmplacement() {
        numeroColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        numeroColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
        numeroColumn.setEditable(false);

        tailleColumn.setEditable(true);
        tailleColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tailleColumn.setCellValueFactory(new PropertyValueFactory<>("taille"));
        tailleColumn.setOnEditCommit(event -> {
            try {
                controleurCommercant.modifierTailleEmplacement(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                emplacementTable.refresh();
            }
        });
    }

    private void setupTypeCommerce() {
        typeCommerceColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        typeCommerceColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        typeCommerceColumn.setOnEditCommit(event -> {
            try {
                controleurCommercant.modifierNomTypeCommerce(event.getRowValue(), event.getNewValue());
                commercantsTable.getItems().clear();
                commercantsTable.getItems().addAll(controleurCommercant.getEvenement().getCommercants());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setNom(event.getOldValue());
                typeTable.refresh();
            }
        });

        quotaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        quotaColumn.setCellValueFactory(new PropertyValueFactory<>("quota"));
        quotaColumn.setOnEditCommit(event -> {
            try {
                controleurCommercant.modifierQuotaTypeCommerce(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                emplacementTable.refresh();
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
            checkedProperty.addListener(((observableValue, old, newValue) -> {
                if (newValue) checkBox.setSelected(true);
                else if (checkedMap.values().stream().noneMatch(BooleanProperty::get)) checkBox.setSelected(false);
            }));
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
                controleur.getControleurCommercant().modifierTelephoneCommercant(event.getRowValue(),
                        event.getNewValue());
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
                controleur.getControleurCommercant().modifierHeureDebutCommercant(event.getRowValue(),
                        event.getNewValue());
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
                controleur.getControleurCommercant().modifierHeureFinCommercant(event.getRowValue(),
                        event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setHeureFin(event.getOldValue());
                commercantsTable.refresh();
            }
        });

        emplacementColumn.setCellFactory(tc -> {
            TextFieldTableCell<Commercant, Emplacement> cell = new TextFieldTableCell<>(new EmplacementStringConverter());
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    showEmplacement();
                }
            });
            return cell;
        });
        emplacementColumn.setCellValueFactory(new PropertyValueFactory<>("emplacement"));
        emplacementColumn.setOnEditCommit(event -> {
            try {
                Emplacement emplacement = controleurCommercant.getEmplacement(event.getNewValue().getNumero());
                if (emplacement != null) {
                    controleurCommercant.modifierEmplacementCommercant(event.getRowValue(), emplacement);
                } else {
                    try {
                        Emplacement newEmplacement = controleurCommercant.creerEmplacement(0);
                        controleurCommercant.modifierEmplacementCommercant(event.getRowValue(), newEmplacement);
                        emplacementTable.getItems().add(newEmplacement);
                        emplacementTable.refresh();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        event.getRowValue().setEmplacement(event.getOldValue());
                    }
                }
                commercantsTable.refresh();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        typeColumn.setCellFactory(tc -> {
            TextFieldTableCell<Commercant, TypeCommerce> cell = new TextFieldTableCell<>(new TypeCommerceStringConverter());
            cell.setOnMouseClicked(e -> {
                if (!cell.isEmpty()) {
                    showTypeCommercants();
                }
            });
            return cell;
        });
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeCommerce"));
        typeColumn.setOnEditCommit(event -> {
            try {
                TypeCommerce typeCommerce = controleurCommercant.getTypeCommerce(event.getNewValue().getNom());
                if (typeCommerce != null) {
                    controleurCommercant.modifierTypeCommerceCommercant(event.getRowValue(), typeCommerce);
                } else {
                    try {
                        TypeCommerce newTypeCommerce = controleurCommercant.creerTypeCommerce(event.getNewValue().getNom(), 1);
                        controleurCommercant.modifierTypeCommerceCommercant(event.getRowValue(), newTypeCommerce);
                        typeTable.getItems().add(newTypeCommerce);
                        typeTable.refresh();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        event.getRowValue().setTypeCommerce(event.getOldValue());
                        commercantsTable.refresh();
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
        commercantsTable.getItems().add(
                controleurCommercant.inscrireCommercant(("Nom" + i), "Prenom", "Mail", "06010203", 8, 20, null, null));
    }

    @FXML
    private void onSupprimer() {
        boolean isAnyCommercantSelected = checkedMap.values().stream()
                .anyMatch(BooleanProperty::get);
        if (isAnyCommercantSelected) {
            checkedMap.forEach(((commercant, booleanProperty) -> {
                if (booleanProperty.get()) {
                    delete(commercant);
                }
            }));
            checkBox.setSelected(false);
        } else if (!commercantsTable.getSelectionModel().getSelectedItems().isEmpty()) {
            commercantsTable.getSelectionModel().getSelectedItems().forEach(this::delete);
        }
    }

    private void delete(Commercant commercant) {
        try {
            controleurCommercant.desinscrireCommercant(commercant);
            commercantsTable.getItems().remove(commercant);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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