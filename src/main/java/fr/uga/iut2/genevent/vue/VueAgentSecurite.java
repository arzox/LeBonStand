package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.Controleur;
import fr.uga.iut2.genevent.controleur.ControleurAgentSecurite;
import fr.uga.iut2.genevent.exception.MauvaisChampsException;
import fr.uga.iut2.genevent.modele.*;
import fr.uga.iut2.genevent.util.NombreAgentStringConverter;
import fr.uga.iut2.genevent.util.ZoneStringConverter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VueAgentSecurite extends IHM {

    @FXML
    TableView<AgentSecurite> agentsTable;

    private Map<AgentSecurite, BooleanProperty> checkedMap = new HashMap<>();

    @FXML
    CheckBox checkBox;

    @FXML
    TableColumn<AgentSecurite, Boolean> checkBoxColumn;
    @FXML
    TableColumn<AgentSecurite, String> nomColumn;
    @FXML
    TableColumn<AgentSecurite, String> prenomColumn;
    @FXML
    TableColumn<AgentSecurite, String> emailColumn;
    @FXML
    TableColumn<AgentSecurite, String> telephoneColumn;
    @FXML
    TableColumn<AgentSecurite, Integer> heureDebutColumn;
    @FXML
    TableColumn<AgentSecurite, Integer> heureFinColumn;
    @FXML
    TableColumn<AgentSecurite, Zone> zoneColumn;

    @FXML
    TableView<Zone> zoneTable;

    @FXML
    TableColumn<Zone, String> nomZoneColumn;
    @FXML
    TableColumn<Zone, ArrayList<AgentSecurite>> agentsColumn;

    ControleurAgentSecurite controleurAgentSecurite;

    public VueAgentSecurite() {
        super();
        controleurAgentSecurite = Controleur.getInstance(null).getControleurAgentSecurite();
    }

    @FXML
    private void initialize() {
        setupTable();
        setupZone();
        setupCheckBox();

        agentsTable.getItems().addAll(controleurAgentSecurite.getEvenement().getAgentsSecurite());
        zoneTable.getItems().addAll(controleurAgentSecurite.getEvenement().getZones());
    }

    @FXML
    private void addAnnex() {
        try {
            Zone zone = controleurAgentSecurite.creerZone("Zone" + (zoneTable.getItems().size() + 1));
            zoneTable.getItems().add(zone);
        } catch (Exception e) {
            informerUtilisateur(e.getMessage(), false);
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void deleteAnnex() {
        if (!zoneTable.getSelectionModel().getSelectedItems().isEmpty()) {
            try {
                Zone zone = zoneTable.getSelectionModel().getSelectedItem();
                controleurAgentSecurite.supprimerZone(zone);
                zoneTable.getItems().remove(zone);
                reloadCommercantTable();
            } catch (Exception e) {
                informerUtilisateur(e.getMessage(), false);
                e.printStackTrace();
            }
        }
    }

    private void reloadCommercantTable() {
        agentsTable.getItems().clear();
        agentsTable.getItems().addAll(controleurAgentSecurite.getEvenement().getAgentsSecurite());
    }

    private void setupZone() {
        nomZoneColumn.setEditable(true);
        nomZoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nomZoneColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomZoneColumn.setOnEditCommit(event -> {
            try {
                controleurAgentSecurite.modifierNomZone(event.getRowValue(), event.getNewValue());
                agentsTable.getItems().clear();
                agentsTable.getItems().addAll(controleurAgentSecurite.getEvenement().getAgentsSecurite());
            } catch (MauvaisChampsException e) {
                informerUtilisateur(e.getMessage(), false);
                zoneTable.refresh();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        agentsColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NombreAgentStringConverter()));
        agentsColumn.setCellValueFactory(new PropertyValueFactory<>("agentsSecurites"));
        agentsColumn.setEditable(false);
    }

    private void setupCheckBox() {
        checkBox.setOnAction(actionEvent -> {
            checkedMap.forEach((commercant, booleanProperty) -> booleanProperty.set(checkBox.isSelected()));
        });
    }

    @Override
    public String getFxmlName() {
        return "tab-securite.fxml";
    }

    private void setupTable() {
        checkBoxColumn.setCellValueFactory(cellData -> {
            AgentSecurite agentSecurite = cellData.getValue();
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
                controleurAgentSecurite.modifierNomAgentSecurite(event.getRowValue(), event.getNewValue());
            } catch (MauvaisChampsException e) {
                informerUtilisateur(e.getMessage(), false);
                event.getRowValue().setNom(event.getOldValue());
                agentsTable.refresh();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        prenomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        prenomColumn.setOnEditCommit(event -> {
            try {
                controleurAgentSecurite.modifierPrenomAgentSecurite(event.getRowValue(), event.getNewValue());
            } catch (MauvaisChampsException e) {
                informerUtilisateur(e.getMessage(), false);
                event.getRowValue().setPrenom(event.getOldValue());
                agentsTable.refresh();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setOnEditCommit(event -> {
            try {
                controleurAgentSecurite.modifierEmailAgentSecurite(event.getRowValue(), event.getNewValue());
            } catch (MauvaisChampsException e) {
                informerUtilisateur(e.getMessage(), false);
                event.getRowValue().setEmail(event.getOldValue());
                agentsTable.refresh();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        telephoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        telephoneColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        telephoneColumn.setOnEditCommit(event -> {
            try {
                controleurAgentSecurite.modifierTelephoneAgentSecurite(event.getRowValue(), event.getNewValue());
            } catch (MauvaisChampsException e) {
                informerUtilisateur(e.getMessage(), false);
                event.getRowValue().setTelephone(event.getOldValue());
                agentsTable.refresh();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        heureDebutColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        heureDebutColumn.setCellValueFactory(new PropertyValueFactory<>("heureDebut"));
        heureDebutColumn.setOnEditCommit(event -> {
            try {
                controleurAgentSecurite.modifierHeureDebutAgentSecurite(event.getRowValue(), event.getNewValue());
            } catch (MauvaisChampsException e) {
                informerUtilisateur(e.getMessage(), false);
                event.getRowValue().setHeureDebut(event.getOldValue());
                agentsTable.refresh();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        heureFinColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        heureFinColumn.setCellValueFactory(new PropertyValueFactory<>("heureFin"));
        heureFinColumn.setOnEditCommit(event -> {
            try {
                controleurAgentSecurite.modifierHeureFinAgentSecurite(event.getRowValue(), event.getNewValue());
            } catch (MauvaisChampsException e) {
                informerUtilisateur(e.getMessage(), false);
                event.getRowValue().setHeureFin(event.getOldValue());
                agentsTable.refresh();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        zoneColumn.setCellFactory(TextFieldTableCell.forTableColumn(new ZoneStringConverter()));
        zoneColumn.setCellValueFactory(new PropertyValueFactory<>("zone"));
        zoneColumn.setOnEditCommit(event -> {
            try {
                Zone emplacement = controleurAgentSecurite.getZone(event.getNewValue().getNom());
                if (emplacement != null) {
                    controleurAgentSecurite.modifierZoneAgentSecurite(event.getRowValue(), emplacement);
                    zoneTable.refresh();
                } else {
                    try {
                        Zone newZone = controleurAgentSecurite.creerZone(event.getNewValue().getNom());
                        controleurAgentSecurite.modifierZoneAgentSecurite(event.getRowValue(), newZone);
                        zoneTable.getItems().add(newZone);
                        zoneTable.refresh();
                    } catch (MauvaisChampsException e) {
                        informerUtilisateur(e.getMessage(), false);
                        event.getRowValue().setZone(event.getOldValue());
                    }
                }
                agentsTable.refresh();
            } catch (MauvaisChampsException e) {
                informerUtilisateur(e.getMessage(), false);
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
                controleurAgentSecurite.ajouterAgentSecurite(("Nom" + i)));
    }

    @FXML
    private void onSupprimer() {
        boolean isAnyAgentSelected = checkedMap.values().stream()
                .anyMatch(BooleanProperty::get);
        if (isAnyAgentSelected) {
            checkedMap.forEach(((agentSecurite, booleanProperty) -> {
                if (booleanProperty.get()) {
                    delete(agentSecurite);
                }
            }));
            checkBox.setSelected(false);
        } else if (!agentsTable.getSelectionModel().getSelectedItems().isEmpty()) {
            agentsTable.getSelectionModel().getSelectedItems().forEach(this::delete);
        }
    }

    private void delete(AgentSecurite agentSecurite) {
        try {
            controleurAgentSecurite.supprimerAgentSecurite(agentSecurite);
            agentsTable.getItems().remove(agentSecurite);
        } catch (MauvaisChampsException e) {
            informerUtilisateur(e.getMessage(), false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onSave() {
        agentsTable.refresh();
    }
}
