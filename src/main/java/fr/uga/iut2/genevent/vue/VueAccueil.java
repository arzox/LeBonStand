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
import javafx.stage.WindowEvent;

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

    public static final String FXML_NAME = "accueil.fxml";
    public static final String DELETE = "delete-event.fxml";

    @FXML
    private FlowPane eventsFlowPane;

    @FXML
    private Button annulerBouton;

    private Evenement toDelete;
    private Stage otherVue;

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
        trash.setFitHeight(30);
        trash.setFitWidth(30);
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
        if (otherVue != null) {
            otherVue.close();
        }
        try {
            VueOnglets vueOnglets = new VueOnglets(new VueEvenement());
            Stage onglet = (Stage) eventsFlowPane.getScene().getWindow();
            vueOnglets.changerFenetre(onglet);
            onglet.setMaximized(false);
            onglet.setMaximized(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    protected void nouvelEvenementCliquer() {
        if (isAlreadyOpened())
            return;
        try {

            VueCreation vueCreation = new VueCreation((Stage) eventsFlowPane.getScene().getWindow());
            vueCreation.changerFenetre(otherVue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isAlreadyOpened() {
        if (otherVue != null) {
            return true;
        }
        otherVue = new Stage();
        otherVue.setOnCloseRequest(_e -> {
            otherVue = null;
        });
        return false;
    }

    @FXML
    protected void supprimerEvenement(MouseEvent e, Evenement event) {
        e.consume();
        if (isAlreadyOpened())
            return;
        try {
            toDelete = event;
            Vues.loadViewIntoStage(otherVue, DELETE, this);
            // Reload events after deleting one
            loadEvents();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void onCancel() {
        Stage stage = (Stage) annulerBouton.getScene().getWindow();
        stage.fireEvent(
                new WindowEvent(
                        stage,
                        WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    private void onValider() {
        controleur.supprimerEvenement(toDelete);
        Stage stage = (Stage) annulerBouton.getScene().getWindow();
        stage.fireEvent(
                new WindowEvent(
                        stage,
                        WindowEvent.WINDOW_CLOSE_REQUEST));
        loadEvents();
    }

    // Implémentations et redéfinitions

    @Override
    public void informerUtilisateur(String msg, boolean succes) {
        System.out.println(msg);
    }

    @Override
    public String getFxmlName() {
        return FXML_NAME;
    }
}
