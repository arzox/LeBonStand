package fr.uga.iut2.genevent.vue;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * La classe JavaFXGUI délègue les interactions graphiques aux classes {@code VueXXX}.
 * du même package. Elle constitue l'intermédiaire entre la classe
 * {@link fr.uga.iut2.genevent.controleur.Controleur} et les classes de la forme
 * {@code VueXXX}, qui elles sont les contrôleurs des vues {@code .fxml}.
 * <p>
 * Attention, pour pouvoir faire le lien avec le
 * {@link fr.uga.iut2.genevent.controleur.Controleur}, {@code JavaFXGUI} n'est pas une
 * sous-classe de {@link javafx.application.Application} !
 * <p>
 * Le démarrage de l'application diffère des exemples classiques trouvés dans
 * la documentation de JavaFX : l'interface est démarrée à l'initiative du
 * {@link fr.uga.iut2.genevent.controleur.Controleur} via l'appel de la méthode
 * {@link #demarrerInteraction()}.
 */
public class JavaFXGUI extends IHM {

    private final CountDownLatch eolBarrier; // /!\ ne pas supprimer /!\ : suivi de la durée de vie de l'interface

    // éléments vue nouvel·le utilisa·teur/trice
    @FXML
    private TextField newUserForenameTextField;
    @FXML
    private TextField newUserSurnameTextField;
    @FXML
    private TextField newUserEmailTextField;
    @FXML
    private Button newUserOkButton;
    @FXML
    private Button newUserCancelButton;

    public JavaFXGUI() {
        super();
        this.eolBarrier = new CountDownLatch(1); // /!\ ne pas supprimer /!\
    }

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
        VueEvenement vueEvenement = new VueEvenement(new VueOnglets());
        vueEvenement.changerFenetre(primaryStage);

        primaryStage.getIcons().add(new Image(
                getClass().getResource("/fr/uga/iut2/genevent/images/LBS-blanc-orange.png").toExternalForm()));
        primaryStage.setMaximized(true);
        primaryStage.setTitle("LeBonStand");
    }

    // Éléments du dialogue

    private void exitAction() {
        // fermeture de l'interface JavaFX : on notifie sa fin de vie
        this.eolBarrier.countDown();
    }

    // menu principal

    // @FXML
    // private void newUserMenuItemAction() {
    // this.controleur.saisirUtilisateur();
    // }

    @FXML
    private void exitMenuItemAction() {
        Platform.exit();
        this.exitAction();
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

    // Implémentation et redéfinitions

    @Override
    public void changerFenetre(Stage stage) {
        // TODO Auto-generated method stub
    }
}
