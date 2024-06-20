package fr.uga.iut2.genevent.util;

import java.io.IOException;

import fr.uga.iut2.genevent.vue.IHM;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Classe utilitaire relative aux vues : chargement de vue
 */
public class Vues {
    /**
     * Charge le fichier FXML (au préalable dans le dossier racine de ressources
     * pour le projet) et place son contenu dans le stage donné en argument. Ajoute
     * aussi le style global à la vue.
     *
     * @param stage    Le stage dont le contenu doit être remplacé
     *
     * @param fxmlName Le nom du fichier à être utilisé pour charger la vue, ou
     *                 alors le chemin relatif à partir du dossier racine pour les
     *                 ressources
     *
     * @return l'objet Parent utilisé pour charger la vue
     */
    public static Parent loadViewIntoStage(Stage stage, String fxmlName, IHM controleur, Boolean fullScreen) {
        try {
            // Charger la scène dans le loader et lui affecter le controleur en argument
            Parent parent = loadViewAsParent(fxmlName, controleur);
            Platform.runLater(() -> {
                showParentOnStage(parent, stage, fullScreen);
            });
            return parent;
        } catch (Exception e) {
            System.err.println("Erreur pendant le chargement de la vue :\n");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Charge la vue spécifiée par l'argument {@code fxml}, lui affecte le
     * controleur en argument et renvoie l'objet Parent correspondant
     *
     * @param fmxl       - Nom du fichier fxml à charger
     * @param controleur - Contrôleur à affecter à la vue
     * @return Objet Parent résultant du chargement
     * @throws IOException Si le fichier est inexistant ou corrompu
     */
    public static Parent loadViewAsParent(String fmxl, IHM controleur) throws IOException {
        FXMLLoader loader = new FXMLLoader(Vues.class.getResource("/fr/uga/iut2/genevent/vue/" + fmxl));
        loader.setController(controleur);
        return loader.load();
    }

    /**
     * Charge l'objet Parent et applique le style global
     *
     * @param parent
     * @return
     */
    public static Scene loadParentWithStyle(Parent parent) {
        Scene newScene = new Scene(parent);

        // Appliquer le css global
        newScene.getStylesheets().clear();
        newScene.getStylesheets().add(Vues.class.getResource("/fr/uga/iut2/genevent/style/style.css").toExternalForm());

        return newScene;
    }

    /**
     * Ajoute l'objet Parent en argument au Stage en argument et affiche le résultat
     * dans la fenêtre, tout en affectant le fichier css global
     *
     * @param parent
     * @param stage
     */
    public static void showParentOnStage(Parent parent, Stage stage, Boolean fullScreen) {
        stage.setScene(stage.getScene());
        stage.setScene(loadParentWithStyle(parent));

        if (fullScreen) {
            stage.setMaximized(false);
            stage.setMaximized(true);
            stage.setX(0);
            stage.setY(0);
        } else {
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double centerX = screenBounds.getWidth() / 2.0;
            double centerY = screenBounds.getHeight() / 2.0;
            stage.setX(centerX - stage.getWidth() / 2.0);
            stage.setY(centerY - stage.getHeight() / 2.0);
        }
        stage.show();
        stage.getIcons().add(new Image(
                Vues.class.getResource("/fr/uga/iut2/genevent/images/LBS-blanc-orange.png").toExternalForm()));
    }
}
