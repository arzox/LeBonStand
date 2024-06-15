package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.modele.Fonctionnalite;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class VueEvenement extends IHM {

    @FXML
    private Text nomEvenement;

    @FXML
    private VBox panel;

    VueEvenement() {
        super();
    }

    @FXML
    public void initialize() {
        setupButton();
    }

    public void changerFenetre(Stage stage) {
        FXMLLoader mainViewLoader = new FXMLLoader(getClass().getResource("evenement.fxml"));
        mainViewLoader.setController(this);
        try {
            Scene mainScene = new Scene(mainViewLoader.load());
            stage.setScene(mainScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupButton() {
        nomEvenement.setText(controleur.getControleurEvenement().getEvenement().getNom());
        ArrayList<Fonctionnalite> allFonctionnalites = new ArrayList<>(EnumSet.allOf(Fonctionnalite.class));
        ArrayList<Fonctionnalite> fonctionnalitesEvenement = controleur.getControleurEvenement().getEvenement().getFonctionnalites();

        List<Node> buttons = panel.getChildren().subList(4, panel.getChildren().size());

        for (int i = allFonctionnalites.size() - 1; i >= 0; i--) {
            Fonctionnalite fonctionnalite = allFonctionnalites.get(i);
            if (!fonctionnalitesEvenement.contains(fonctionnalite)) {
                buttons.remove(i);
            }
        }
    }

    @Override
    public void informerUtilisateur(String message, boolean succes) {
        System.out.println(message);
    }
}
