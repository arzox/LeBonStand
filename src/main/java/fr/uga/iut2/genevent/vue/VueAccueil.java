package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.util.Vues;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class VueAccueil extends IHM {

    @FXML
    private FlowPane eventsFlowPane;

    private boolean isInitialized = false;

    @FXML
    public void initialize() {
        loadEvents();
    }

    private void loadEvents() {
        // Exemple de liste d'événements. Remplacez ceci par votre propre logique pour récupérer les événements.
        List<String> eventNames = List.of("Event 1", "Event 2", "Event 3");

        // Clear the current children before adding new events
        eventsFlowPane.getChildren().clear();

        for (String eventName : eventNames) {
            Button eventButton = createEventButton(eventName);
            eventsFlowPane.getChildren().add(0, eventButton);
        }

        // Ensure the new event button is always the last child
        Button newEventButton = createNewEventButton();
        eventsFlowPane.getChildren().add(newEventButton);
    }

    private Button createEventButton(String eventName) {
        Button button = new Button();
        button.setMnemonicParsing(false);
        button.setStyle("-fx-background-color: white; -fx-border-color: black;");

        VBox vBox = new VBox();
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setMinHeight(169.0);
        vBox.setMinWidth(160.0);
        vBox.setPrefHeight(169.0);
        vBox.setPrefWidth(160.0);

        ImageView mainImage = new ImageView();
        mainImage.setFitHeight(104.0);
        mainImage.setFitWidth(199.0);
        mainImage.setPickOnBounds(true);
        mainImage.setPreserveRatio(true);
        mainImage.setImage(new Image(getClass().getResourceAsStream("/fr/uga/iut2/genevent/Images/marche_noel.jpg")));

        Text text = new Text(eventName);

        vBox.getChildren().addAll(mainImage, text);
        button.setGraphic(vBox);

        return button;
    }

    private Button createNewEventButton() {
        Button button = new Button();
        button.setId("buttonNewEvent");
        button.setMnemonicParsing(false);
        button.setPrefHeight(121.0);
        button.setPrefWidth(121.0);
        button.setStyle("-fx-background-color: white; -fx-border-color: black;");

        VBox vBox = new VBox();
        vBox.setAlignment(javafx.geometry.Pos.CENTER);
        vBox.setMinHeight(169.0);
        vBox.setMinWidth(160.0);
        vBox.setPrefHeight(169.0);
        vBox.setPrefWidth(160.0);
        vBox.setOnMouseClicked(e -> nouvelEvenementCliquer());

        ImageView plusImage = new ImageView();
        plusImage.setFitHeight(104.0);
        plusImage.setFitWidth(199.0);
        plusImage.setPickOnBounds(true);
        plusImage.setPreserveRatio(true);
        plusImage.setImage(new Image(getClass().getResourceAsStream("/fr/uga/iut2/genevent/Images/plus.png")));

        Text text = new Text("Nouvel Évènement");

        vBox.getChildren().addAll(plusImage, text);
        button.setGraphic(vBox);

        return button;
    }

    @FXML
    protected void nouvelEvenementCliquer() {
        try {
            Vues.loadViewIntoStage(new Stage(), "new-event.fxml", this);
            // Reload events after adding a new one
            loadEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void supprimerEvenement() {
        try {
            Vues.loadViewIntoStage(new Stage(), "event-delete.fxml", this);
            // Reload events after deleting one
            loadEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changerFenetre(Stage stage) {
        try {
            FXMLLoader mainViewLoader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
            mainViewLoader.setController(this);
            Scene mainScene = new Scene(mainViewLoader.load());
            stage.setScene(mainScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void informerUtilisateur(String msg, boolean succes) {
        System.out.println(msg);
    }
}
