package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.util.Vues;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class VueAccueil extends IHM {

    @FXML
    private FlowPane eventsFlowPane;

    @FXML
    private Button annulerBouton;

    private Evenement toDelete;

    @FXML
    public void initialize() {
        loadEvents();
    }

    private void loadEvents() {
        // Exemple de liste d'événements. Remplacez ceci par votre propre logique pour récupérer les événements.
        List<Evenement> eventNames = controleur.getEvents();

        // Clear the current children before adding new events
        eventsFlowPane.getChildren().clear();

        for (Evenement event : eventNames) {
            VBox eventButton = createEventButton(event);
            eventsFlowPane.getChildren().add(0, eventButton);
        }

        // Ensure the new event button is always the last child
        VBox newEventButton = createNewEventButton();
        eventsFlowPane.getChildren().add(newEventButton);
    }

    private VBox createEventButton(Evenement event) {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("eventIcon");
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setMinHeight(169.0);
        vBox.setMinWidth(160.0);
        vBox.setPrefHeight(169.0);
        vBox.setPrefWidth(160.0);
        vBox.setSpacing(10);

        StackPane stackPane = new StackPane();
        stackPane.setMaxHeight(120);
        stackPane.setMaxWidth(120);

        ImageView mainImage = new ImageView();
        mainImage.setFitHeight(120);
        mainImage.setFitWidth(120);
        mainImage.setPickOnBounds(true);
        mainImage.setPreserveRatio(true);
        mainImage.setImage(new Image(getClass().getResourceAsStream("/fr/uga/iut2/genevent/images/marche_noel.jpg")));

        ImageView trash = new ImageView();
        trash.setFitHeight(50);
        trash.setFitWidth(50);
        trash.setImage(new Image(getClass().getResourceAsStream("/fr/uga/iut2/genevent/images/trash.png")));
        trash.getStyleClass().add("trash");
        trash.setOnMouseClicked(e -> supprimerEvenement(e, event));

        stackPane.getChildren().addAll(mainImage, trash);
        StackPane.setAlignment(trash, Pos.BOTTOM_LEFT);

        Text text = new Text(event.getNom());

        vBox.getChildren().addAll(stackPane, text);
        vBox.setOnMouseClicked(e -> openEvent(event));

        return vBox;
    }

    private VBox createNewEventButton() {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("eventIcon");
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setMinHeight(169.0);
        vBox.setMinWidth(160.0);
        vBox.setPrefHeight(169.0);
        vBox.setPrefWidth(160.0);
        vBox.setSpacing(10);
        vBox.setOnMouseClicked(e -> nouvelEvenementCliquer());

        ImageView plusImage = new ImageView();
        plusImage.setFitHeight(104.0);
        plusImage.setFitWidth(199.0);
        plusImage.setPickOnBounds(true);
        plusImage.setPreserveRatio(true);
        plusImage.setImage(new Image(getClass().getResourceAsStream("/fr/uga/iut2/genevent/images/plus.png")));

        Text text = new Text("Nouvel Évènement");

        vBox.getChildren().addAll(plusImage, text);

        return vBox;
    }

    private void openEvent(Evenement event) {
        controleur.setEvenementCourant(event);
        try {
            Vues.loadViewIntoStage((Stage) eventsFlowPane.getScene().getWindow(), "evenement.fxml", new VueEvenement());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    protected void nouvelEvenementCliquer() {
        try {
            Vues.loadViewIntoStage(new Stage(), "new-event.fxml", new VueCreation((Stage) eventsFlowPane.getScene().getWindow()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void supprimerEvenement(MouseEvent e, Evenement event) {
        e.consume();
        try {
            toDelete = event;
            Vues.loadViewIntoStage(new Stage(), "event-delete.fxml", this);
            // Reload events after deleting one
            loadEvents();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void onCancel() {
        Stage stage = (Stage) annulerBouton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onValider() {
        controleur.supprimerEvenement(toDelete);
        ((Stage) annulerBouton.getScene().getWindow()).close();
        loadEvents();
    }

    @Override
    public void informerUtilisateur(String msg, boolean succes) {
        System.out.println(msg);
    }
}
