package fr.uga.iut2.genevent.vue;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * La classe JavaFXGUI délègue les interactions graphiques aux classes
 * {@code VueXXX} du même package. Elle constitue l'intermédiaire entre la
 * classe {@link fr.uga.iut2.genevent.controleur.Controleur} et les classes de
 * la forme {@code VueXXX}, qui elles sont les contrôleurs des vues
 * {@code .fxml}.
 * <p>
 * Attention, pour pouvoir faire le lien avec le
 * {@link fr.uga.iut2.genevent.controleur.Controleur}, {@code JavaFXGUI} n'est
 * pas une sous-classe de {@link javafx.application.Application} !
 * <p>
 * Le démarrage de l'application diffère des exemples classiques trouvés dans la
 * documentation de JavaFX : l'interface est démarrée à l'initiative du
 * {@link fr.uga.iut2.genevent.controleur.Controleur} via l'appel de la méthode
 * {@link #demarrerInteraction()}.
 */
public class JavaFXGUI {

    private final CountDownLatch eolBarrier; // /!\ ne pas supprimer /!\ : suivi de la durée de vie de l'interface

    /**
     * Constructeur de la classe JavaFXGUI.
     * 
     * @see fr.uga.iut2.genevent.vue.JavaFXGUI
     */
    public JavaFXGUI() {
        this.eolBarrier = new CountDownLatch(1); // /!\ ne pas supprimer /!\
    }

    // A confirmer, mais je crois bien que ce n'est plus nécessaire car JavaFXGUI
    // n'est plus un contrôleur JavaFX...
    // @FXML
    // private void exitMenuItemAction() {
    //     Platform.exit();
    //     exitAction();
    // }

    /**
     * Point d'entrée principal pour le code de l'interface JavaFX.
     *
     * @param primaryStage stage principale de l'interface JavaFX, sur laquelle
     *                     définir des scenes.
     *
     * @throws IOException si le chargement de la vue FXML échoue.
     *
     * @see javafx.application.Application#start(Stage)
     */
    private void start(Stage primaryStage) throws IOException {
        // Création d'un objet VueAccueil qui servira de contrôleur
        VueAccueil accueil = new VueAccueil();
        // On applique la nouvelle vue au Stage
        accueil.changerFenetre(primaryStage);

        // On applique quelques propriétés supplémentaires
        primaryStage.setMaximized(false);
        primaryStage.setTitle("LeBonStand");
    }

    /**
     * Action par défaut pour les commandes de type "quitter"
     */
    private void exitAction() {
        // fermeture de l'interface JavaFX : on notifie sa fin de vie
        Platform.runLater(() -> {
            this.eolBarrier.countDown();
            Platform.exit();
        });
    }

    public void demarrerInteraction() {
        // démarrage de l'interface JavaFX
        Platform.startup(() -> {
            Stage primaryStage = new Stage();
            primaryStage.setOnCloseRequest((WindowEvent t) -> exitAction());
            try {
                this.start(primaryStage);
            } catch (IOException exc) {
                throw new RuntimeException(exc);
            }
        });

        // attente de la fin de vie de l'interface JavaFX
        try {
            this.eolBarrier.await();
        } catch (InterruptedException exc) {
            System.err.println("Erreur d'exécution de l'interface.");
            System.err.flush();
        }
    }
}
