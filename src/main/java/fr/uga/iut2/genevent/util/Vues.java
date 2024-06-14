package fr.uga.iut2.genevent.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Classe utilitaire relative aux vues : chargement de vue
 */
public class Vues {
    /**
     * Charge le fichier FXML (au préalable dans le dossier racine de ressources pour le projet) et place son contenu dans le stage donné en argument. Si le fichier n'est pas trouvé, retourne <strong>null</strong> et affiche la trace de l'erreur correspondante. Ajoute aussi le style global à la vue.
     * @param stage Le stage dont le contenu doit être remplacé
     * @param fxmlName Le nom du fichier à être utilisé pour charger la vue, ou alors le chemin relatif à partir du dossier racine pour les ressources
     * @return l'objet FXMLLoader utilisé pour charger la vue, <strong>null</strong> si le fichier n'a pas pu être chargé.
     */
    public static FXMLLoader loadViewIntoStage(Stage stage, String fxmlName) {
        try {
            FXMLLoader loader = new FXMLLoader(Vues.class.getResource(fxmlName));
            Scene newScene = new Scene(loader.load());
            newScene.getStylesheets().clear();
            newScene.getStylesheets().add(Vues.class.getResource("style.css").toExternalForm());

            stage.setScene(newScene);
            stage.show();
            return loader;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
