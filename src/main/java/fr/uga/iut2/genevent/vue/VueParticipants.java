package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.Controleur;
import fr.uga.iut2.genevent.controleur.ControleurParticipant;
import fr.uga.iut2.genevent.exception.MauvaisChampsException;
import fr.uga.iut2.genevent.modele.Participant;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.HashMap;
import java.util.Map;

public class VueParticipants extends IHM {

    @FXML
    TableView<Participant> participantsTable;

    private Map<Participant, BooleanProperty> checkedMap = new HashMap<>();

    @FXML
    CheckBox checkBox;

    @FXML
    TableColumn<Participant, Boolean> checkBoxColumn;
    @FXML
    TableColumn<Participant, String> nomColumn;
    @FXML
    TableColumn<Participant, String> prenomColumn;
    @FXML
    TableColumn<Participant, String> emailColumn;
    @FXML
    TableColumn<Participant, String> telephoneColumn;
    @FXML
    TableColumn<Participant, Integer> heureDebutColumn;
    @FXML
    TableColumn<Participant, Integer> heureFinColumn;

    ControleurParticipant controleurParticipant;

    public VueParticipants() {
        super();
        controleurParticipant = Controleur.getInstance(null).getControleurParticipant();
    }

    @Override
    public String getFxmlName() {
        return "tab-participants.fxml";
    }

    @FXML
    private void initialize() {
        setupTable();
        setupCheckBox();

        participantsTable.getItems().addAll(controleurParticipant.getParticipants());
    }

    private void setupCheckBox() {
        checkBox.setOnAction(actionEvent -> {
            checkedMap.forEach((commercant, booleanProperty) -> booleanProperty.set(checkBox.isSelected()));
        });
    }
    private void setupTable() {
        checkBoxColumn.setCellValueFactory(cellData -> {
            Participant agentSecurite = cellData.getValue();
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

        participantsTable.setEditable(true);
        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomColumn.setOnEditCommit(event -> {
            try {
                controleurParticipant.modifierNomParticipant(event.getRowValue(), event.getNewValue());
            } catch (MauvaisChampsException e) {
                informerUtilisateur(e.getMessage(), false);
                event.getRowValue().setNom(event.getOldValue());
                participantsTable.refresh();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        prenomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        prenomColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        prenomColumn.setOnEditCommit(event -> {
            try {
                controleurParticipant.modifierPrenomParticipant(event.getRowValue(), event.getNewValue());
            } catch (MauvaisChampsException e) {
                informerUtilisateur(e.getMessage(), false);
                event.getRowValue().setPrenom(event.getOldValue());
                participantsTable.refresh();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setOnEditCommit(event -> {
            try {
                controleurParticipant.modifierEmailParticipant(event.getRowValue(), event.getNewValue());
            } catch (MauvaisChampsException e) {
                informerUtilisateur(e.getMessage(), false);
                event.getRowValue().setEmail(event.getOldValue());
                participantsTable.refresh();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    @FXML
    private void onNewRow() {
        int i = (participantsTable.getItems().size() + 1);
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
        participantsTable.getItems().add(
                controleurParticipant.inscrireParticipant(("Nom" + i)));
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
            checkBox.setSelected(false);
        } else if (!participantsTable.getSelectionModel().getSelectedItems().isEmpty()) {
            participantsTable.getSelectionModel().getSelectedItems().forEach(this::delete);
        }
    }

    private void delete(Participant participant) {
        try {
            controleurParticipant.desinscrireParticipant(participant);
            participantsTable.getItems().remove(participant);
        } catch (MauvaisChampsException e) {
            informerUtilisateur(e.getMessage(), false);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onSave() {
        participantsTable.refresh();
    }
}
