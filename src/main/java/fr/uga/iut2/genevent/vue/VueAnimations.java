package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.Controleur;
import fr.uga.iut2.genevent.controleur.ControleurAnimation;
import fr.uga.iut2.genevent.modele.Animation;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.LocalDateTimeStringConverter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class VueAnimations extends IHM {

    @FXML
    TableView<Animation> animationsTable;

    private Map<Animation, BooleanProperty> checkedMap = new HashMap<>();

    @FXML
    CheckBox checkBox;

    @FXML
    TableColumn<Animation, Boolean> checkBoxColumn;
    @FXML
    TableColumn<Animation, String> nomColumn;
    @FXML
    TableColumn<Animation, Float> prixColumn;
    @FXML
    TableColumn<Animation, LocalDateTime> heureDebutColumn;
    @FXML
    TableColumn<Animation, LocalDateTime> heureFinColumn;

    ControleurAnimation controleurAnimation;

    public VueAnimations() {
        super();
        controleurAnimation = Controleur.getInstance(null).getControleurAnimation();
    }

    @Override
    public String getFxmlName() {
        return "tab-animations.fxml";
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

        animationsTable.getItems().addAll(controleurAnimation.getAnimations());
    }

    private void setupCheckBox() {
        checkBox.setOnAction(actionEvent -> {
            checkedMap.forEach((commercant, booleanProperty) -> booleanProperty.set(checkBox.isSelected()));
        });
    }
    private void setupTable() {
        checkBoxColumn.setCellValueFactory(cellData -> {
            Animation agentSecurite = cellData.getValue();
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

        animationsTable.setEditable(true);
        nomColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        nomColumn.setOnEditCommit(event -> {
            try {
                controleurAnimation.modifierNomAnimation(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setNom(event.getOldValue());
                animationsTable.refresh();
            }
        });

        prixColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        prixColumn.setOnEditCommit(event -> {
            try {
                controleurAnimation.modifierPrixAnimation(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setPrix(event.getOldValue());
                animationsTable.refresh();
            }
        });

        heureDebutColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateTimeStringConverter()));
        heureDebutColumn.setCellValueFactory(new PropertyValueFactory<>("dateHeureDebut"));
        heureDebutColumn.setOnEditCommit(event -> {
            try {
                controleurAnimation.modifierDebutAnimation(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setDateHeureDebut(event.getOldValue());
                animationsTable.refresh();
            }
        });

        heureFinColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateTimeStringConverter()));
        heureFinColumn.setCellValueFactory(new PropertyValueFactory<>("dateHeureFin"));
        heureFinColumn.setOnEditCommit(event -> {
            try {
                controleurAnimation.modifierFinAnimation(event.getRowValue(), event.getNewValue());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                event.getRowValue().setDateHeureFin(event.getOldValue());
                animationsTable.refresh();
            }
        });
    }

    @FXML
    private void onNewRow() {
        int i = (animationsTable.getItems().size() + 1);
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
        animationsTable.getItems().add(
                controleurAnimation.ajouterAnimation(("Nom" + i)));
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
        } else if (!animationsTable.getSelectionModel().getSelectedItems().isEmpty()) {
            animationsTable.getSelectionModel().getSelectedItems().forEach(this::delete);
        }
    }

    private void delete(Animation animation) {
        try {
            controleurAnimation.supprimerAnimation(animation);
            animationsTable.getItems().remove(animation);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void onSave() {
        animationsTable.refresh();
    }
}
