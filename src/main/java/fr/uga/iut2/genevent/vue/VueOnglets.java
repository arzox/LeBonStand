package fr.uga.iut2.genevent.vue;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import fr.uga.iut2.genevent.modele.Fonctionnalite;
import fr.uga.iut2.genevent.util.Vues;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * La classe VueEvenement est responsable des interactions avec
 * l'utilisa·teur/trice en mode graphique pour la vue accueil (liste des
 * événements)
 * <p>
 * Contrôleur de tab-event.fxml (informations générales sur l'événement)
 */
public class VueOnglets extends IHM {
    private IHM currentOnglet;
    public static final String FXML_NAME = "tabs.fxml";

    @FXML
    private Text nomEvenement;
    @FXML
    private VBox panel;

    VueOnglets(IHM onglet) {
        super();
        setCurrentOnglet(onglet);
    }

    @FXML
    public void initialize() {
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

        // add clicked section
        panel.getChildren().forEach(node -> node.setOnMouseClicked(event -> {
            int index = panel.getChildren().indexOf(node);
            if (index > 0) {
                setCurrentOnglet(index);
            }
        }));
    }

    public void setCurrentOnglet(int i) {
        if (i < 0 || i >= panel.getChildren().size()) {
            return;
        }
        panel.getChildren().forEach(node -> node.getStyleClass().remove("button-selected"));
        panel.getChildren().get(i).getStyleClass().add("button-selected");
    }

    @FXML
    private void onAccueil() {
        Stage stage = (Stage) panel.getScene().getWindow();
        new VueAccueil().changerFenetre(stage);
        Vues.loadViewIntoStage(stage, "accueil.fxml", new VueAccueil());
    }

    /**
     * Charge la vue tabs.fxml et crée l'objet Parent correspondant afin qu'il
     * puisse être utilisé par les classes utilisant les onglets sur le côté
     * (panneau de navigation)
     */
    public void load() {
        try {
            // Charger la scène dans le loader et lui affecter le controleur en argument
            setParent(Vues.loadViewAsParent(FXML_NAME, this));
        } catch (Exception e) {
            System.err.println("Erreur pendant le chargement de la vue :\n");
            e.printStackTrace();
        }
    }

    /**
     * Modifie l'état de la fenêtre en argument pour lui appliquer l'onglet spécifié
     * par l'argument {@code fxmlName}, puis ajoute le panneau de navigation
     * vertical pour compléter.
     * 
     * @param stage    - Le stage dont la vue doit être changée
     * @param fxmlName - Vue à appliquer au stage
     */
    @Override
    public void changerFenetre(Stage stage) {
        load();
        ((HBox) getCurrentOnglet().getParent()).getChildren().add(0, getParent());
    }

    @Override
    public void informerUtilisateur(String message, boolean succes) {
        System.out.println(message);
    }

    // Getters et setters

    public void setCurrentOnglet(IHM currentOnglet) {
        this.currentOnglet = currentOnglet;
    }

    public IHM getCurrentOnglet() {
        return currentOnglet;
    }
}
