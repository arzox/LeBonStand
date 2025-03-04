package fr.uga.iut2.genevent.vue;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.logging.Level;

import fr.uga.iut2.genevent.Main;
import fr.uga.iut2.genevent.modele.Fonctionnalite;
import fr.uga.iut2.genevent.util.Vues;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * La classe VueEvenement est responsable des interactions avec
 * l'utilisa·teur/trice en mode graphique pour la vue accueil (liste des
 * événements)
 */
public class VueOnglets extends IHM {

    public static final String FXML_NAME = "tabs.fxml";
    public IHM content;

    @FXML
    public Text nomEvenement;
    @FXML
    private VBox panel;

    VueOnglets(IHM onglet) {
        super();
        setContent(onglet);
    }

    @FXML
    private void onAccueilClicked() {
        Main.setLOGGER(Level.INFO, "L'utilisateur a cliqué sur \"Accueil\" : changement de vue");
        Stage accueil = (Stage) nomEvenement.getScene().getWindow();
        new VueAccueil().changerFenetre(accueil, false);
    }

    @FXML
    private void onEventClicked(MouseEvent event) {
        onTabClickedGeneric(new VueEvenement(), event);
    }

    @FXML
    private void onCommercantsClicked(MouseEvent event) {
        onTabClickedGeneric(new VueCommercants(), event);
    }

    @FXML
    private void onSecuriteClicked(MouseEvent event) {
        onTabClickedGeneric(new VueAgentSecurite(), event);
    }

    @FXML
    private void onEntretienClicked(MouseEvent event) {
        onTabClickedGeneric(new VueAgentEntretien(), event);
    }

    @FXML
    private void onAnimationClicked(MouseEvent event) {
        onTabClickedGeneric(new VueAnimations(), event);
    }

    @FXML
    private void onParticipantsClicked(MouseEvent event) {
        onTabClickedGeneric(new VueParticipants(), event);
    }

    @FXML
    private void initialize() {
        setupButton();
    }

    /**
     * Initialise le panneau de navigation :
     * <p>
     * - Applique le nom de l'événement pour le bouton "Événement"
     * <p>
     * - Met à jour les boutons selon les fonctionnalités activées pour cet
     * événement
     */
    private void setupButton() {
        nomEvenement.setText(controleur.getControleurEvenement().getEvenement().getNom());
        ArrayList<Fonctionnalite> allFonctionnalites = new ArrayList<>(EnumSet.allOf(Fonctionnalite.class));
        ArrayList<Fonctionnalite> fonctionnalitesEvenement = controleur.getControleurEvenement().getEvenement()
                .getFonctionnalites();

        List<Node> buttons = panel.getChildren().subList(5, panel.getChildren().size());

        // remove buttons that fonctionnality don't have
        for (int i = buttons.size() - 1; i >= 0; i--) {
            Fonctionnalite fonctionnalite = allFonctionnalites.get(i);
            if (!fonctionnalitesEvenement.contains(fonctionnalite)) {
                buttons.remove(i);
            }
        }

        // TODO : confirmer la suppression de ce bloc de code
        // add clicked section
        // panel.getChildren().subList(3, panel.getChildren().size() - 1).forEach(node -> node.setOnMouseClicked(event -> {
        //     int index = panel.getChildren().indexOf(node);
        //     setCurrentOnglet(index);
        // }));
    }

    /**
     * Factorisation des méthodes de la forme {@code onXXXClicked} de la classe
     * {@code VueOnglets}
     *
     * @param nouvelleVue
     * @param event
     */
    private void onTabClickedGeneric(IHM nouvelleVue, MouseEvent event) {
        Main.setLOGGER(Level.INFO, "Clic sur l'onglet correspondant à " + nouvelleVue.getClass().getSimpleName() + " : changement de vue");
        switchOnglet(nouvelleVue, (Node) event.getSource());
    }

    private void switchOnglet(IHM nouvelleVue, Node button) {
        if (button.getStyleClass().contains("button-selected")) return;
        setContent(nouvelleVue);
        changerFenetre((Stage) nomEvenement.getScene().getWindow(), true);
        updateSelectedOngletStyle(button);
    }


    // TODO : confirmer la suppression de cette méthode
    // public void setCurrentOnglet(int i) {
    //     if (i < 0 || i >= panel.getChildren().size()) {
    //         return;
    //     }
    //     panel.getChildren().forEach(node -> node.getStyleClass().remove("button-selected"));
    //     panel.getChildren().get(i).getStyleClass().add("button-selected");
    // }

    private void updateSelectedOngletStyle(Node button) {
        panel.getChildren().forEach(node -> node.getStyleClass().remove("button-selected"));
        button.getStyleClass().add("button-selected");
    }

    /**
     * Modifie l'état de la fenêtre en argument pour lui appliquer l'onglet spécifié
     * par l'argument {@code fxmlName}, puis ajoute le panneau de navigation
     * vertical pour compléter.
     *
     * @param stage Le stage dont la vue doit être changée
     */
    @Override
    public void changerFenetre(Stage stage, Boolean fullScreen) {
        if (!isLoaded) load();
        getContent().load();
        ((Pane) getContent().getParent()).getChildren().add(0, getParent());
        Vues.showParentOnStage(getContent().getParent(), stage, fullScreen);
    }

    @Override
    public String getFxmlName() {
        return FXML_NAME;
    }

    // Getters et setters

    public IHM getContent() {
        return content;
    }

    public void setContent(IHM content) {
        this.content = content;
        content.setPanelController(this);
    }
}
