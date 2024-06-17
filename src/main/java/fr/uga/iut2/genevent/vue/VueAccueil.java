package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.util.Vues;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

/**
 * La classe VueAccueil est responsable des interactions avec
 * l'utilisa·teur/trice en mode graphique pour la vue accueil (liste des
 * événements)
 * <p>
 * Contrôleur de : accueil.fxml, new-event.fxml, delete-event.fxml (boîte de
 * confirmation pour supprimer un événement)
 */
public class VueAccueil extends IHM {

    @FXML
    private FlowPane eventsFlowPane;

    @FXML
    public void initialize() {
        loadEvents();
    }

    /**
     * Met à jour les éléments de l'accueil pour correspondre aux événements déjà
     * créés. En d'autres termes, ajoute les événements enregistrés dans la liste
     * des événements affichés par l'accueil.
     */
    private void loadEvents() {
        // TODO : récupérer les événements depuis le contrôleur
        // Exemple de liste d'événements. Remplacez ceci par votre propre logique pour
        // récupérer les événements.
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
        mainImage.setImage(new Image(getClass().getResourceAsStream("/fr/uga/iut2/genevent/images/marche_noel.jpg")));

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
        plusImage.setImage(new Image(getClass().getResourceAsStream("/fr/uga/iut2/genevent/images/plus.png")));

        Text text = new Text("Nouvel Évènement");

        vBox.getChildren().addAll(plusImage, text);
        button.setGraphic(vBox);

        return button;
    }

    // Implémentations et redéfinitions
    
    @Override
    public void changerFenetre(Stage stage) {
        Vues.loadViewIntoStage(stage, "accueil.fxml", this);
    }

    // Triggers référencés par le fichier .fxml

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
            Vues.loadViewIntoStage(new Stage(), "delete-event.fxml", this);
            // Reload events after deleting one
            loadEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
