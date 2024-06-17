package fr.uga.iut2.genevent.util;

import java.net.URL;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    public static Parent loadViewIntoStage(Stage stage, String fxmlName, Object controleur) {
        try {
            // Charger la scène dans le loader et lui affecter le controleur en argument
            FXMLLoader loader = new FXMLLoader(Vues.class.getResource("/fr/uga/iut2/genevent/vue/" + fxmlName));
            loader.setController(controleur);

            Platform.runLater(() -> {
                try {
                    Scene newScene = new Scene(loader.load());
                    stage.setScene(newScene);
                    stage.show();

                    // Appliquer le css global
                    newScene.getStylesheets().clear();
                    newScene.getStylesheets().add(Vues.class.getResource("/fr/uga/iut2/genevent/style/style.css").toExternalForm());

                    // Ajouter la scène au stage en argument
                    stage.setScene(newScene);
                    stage.show();
                } catch (Exception e) {
                    System.err.println("Erreur pendant le chargement de la vue :\n");
                    e.printStackTrace();
                }
            });
            return loader;
        } catch (Exception e) {
            System.err.println("Erreur pendant le chargement de la vue :\n");
            e.printStackTrace();
            return null;
        }
    }
}
