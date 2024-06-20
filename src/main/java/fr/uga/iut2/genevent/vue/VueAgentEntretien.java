package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.Controleur;
import fr.uga.iut2.genevent.controleur.ControleurAgentEntretien;
import fr.uga.iut2.genevent.modele.AgentEntretien;
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

public class VueAgentEntretien extends IHM {

    @FXML
    TableView<AgentEntretien> agentsTable;

    private Map<AgentEntretien, BooleanProperty> checkedMap = new HashMap<>();

    @FXML
    CheckBox checkBox;

    @FXML
    TableColumn<AgentEntretien, Boolean> checkBoxColumn;
    @FXML
    TableColumn<AgentEntretien, String> nomColumn;
    @FXML
    TableColumn<AgentEntretien, String> prenomColumn;
    @FXML
    TableColumn<AgentEntretien, String> emailColumn;
    @FXML
    TableColumn<AgentEntretien, String> telephoneColumn;
    @FXML
    TableColumn<AgentEntretien, Integer> heureDebutColumn;
    @FXML
    TableColumn<AgentEntretien, Integer> heureFinColumn;

    ControleurAgentEntretien controleurAgentEntretien;

    public VueAgentEntretien() {
        super();
        controleurAgentEntretien = Controleur.getInstance(null).getControleurAgentEntretien();
    }

    @Override
    public void informerUtilisateur(String message, boolean succes) {
        System.out.println(message);
        System.out.println(succes);
    }

    @FXML
    private void initialize() {
        setupTable();
        setupCheckBox();

        agentsTable.getItems().addAll(controleurAgentEntretien.getAgentEntretiens());
    }

    private void setupCheckBox() {
        checkBox.setOnAction(actionEvent -> {
            checkedMap.forEach((commercant, booleanProperty) -> booleanProperty.set(checkBox.isSelected()));
        });
    }

    @Override
    public String getFxmlName() {
        return "tab-entretien.fxml";
    }

    private void setupTable() {
        checkBoxColumn.setCellValueFactory(cellData -> {
            AgentEntretien agentSecurite = cellData.getValue();
            BooleanProperty checkedProperty = checkedMap.get(agentSecurite);
            if (checkedProperty == null) {
                checkedProperty = new SimpleBooleanProperty(false);
                checkedMap.put(agentSecurite, checkedProperty);
            }
            checkedProperty.addListener(((observableValue, old, newValue) -> {
                if (newValue) checkBox.setSelected(true);
                else if (checkedMap.values().stream().noneMatch(BooleanProperty::get)) checkBox.setSelected(false);
            }));
            return checkedProperty;
        });
        checkBoxColumn.setCellFactory(CheckBoxTableCell.forTableColumn(checkBoxColumn));

        agentsTable.setEditable(true);
        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomColumn.setOnEditCommit(event -> {
            try {
                controleurAgentEntretien.modifierNomAgentEntretien(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setNom(event.getOldValue());
                agentsTable.refresh();
            }
        });

        prenomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        prenomColumn.setOnEditCommit(event -> {
            try {
                controleurAgentEntretien.modifierPrenomAgentEntretien(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setPrenom(event.getOldValue());
                agentsTable.refresh();
            }
        });

        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setOnEditCommit(event -> {
            try {
                controleurAgentEntretien.modifierEmailAgentEntretien(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setEmail(event.getOldValue());
                agentsTable.refresh();
            }
        });

        telephoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        telephoneColumn.setOnEditCommit(event -> {
            try {
                controleurAgentEntretien.modifierTelephoneAgentEntretien(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setTelephone(event.getOldValue());
                agentsTable.refresh();
            }
        });

        heureDebutColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        heureDebutColumn.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        heureDebutColumn.setOnEditCommit(event -> {
            try {
                controleurAgentEntretien.modifierHeureDebutAgentEntretien(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setHeureDebut(event.getOldValue());
                agentsTable.refresh();
            }
        });

        heureFinColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        heureFinColumn.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        heureFinColumn.setOnEditCommit(event -> {
            try {
                controleurAgentEntretien.modifierHeureFinAgentEntretien(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setHeureFin(event.getOldValue());
                agentsTable.refresh();
            }
        });
    }

    @FXML
    private void onNewRow() {
        int i = (agentsTable.getItems().size() + 1);
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
        agentsTable.getItems().add(
                controleurAgentEntretien.ajouterAgentEntretien(("Nom" + i), "Prenom", "Mail", "06010203", 8, 20));
    }

    @FXML
    private void onSupprimer() {
        boolean isAnyAgentSelected = checkedMap.values().stream()
                .anyMatch(BooleanProperty::get);
        if (isAnyAgentSelected) {
            checkedMap.forEach(((agentEntretien, booleanProperty) -> {
                if (booleanProperty.get()) {
                    delete(agentEntretien);
                }
            }));
            if (agentsTable.getItems().isEmpty()) checkBox.setSelected(false);
        } else if (!agentsTable.getSelectionModel().getSelectedItems().isEmpty()) {
            agentsTable.getSelectionModel().getSelectedItems().forEach(this::delete);
        }
    }

    private void delete(AgentEntretien agentEntretien) {
        try {
            controleurAgentEntretien.supprimerAgentEntretien(agentEntretien);
            agentsTable.getItems().remove(agentEntretien);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onSave() {
        agentsTable.refresh();
    }
}
