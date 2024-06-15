package fr.uga.iut2.genevent.vue;

import fr.uga.iut2.genevent.controleur.Controleur;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.commons.validator.routines.EmailValidator;


/**
 * La classe JavaFXGUI est responsable des interactions avec
 * l'utilisa·teur/trice en mode graphique.
 * <p>
 * Attention, pour pouvoir faire le lien avec le
 * {@link fr.uga.iut2.genevent.controleur.Controleur}, JavaFXGUI n'est pas une
 * sous-classe de {@link javafx.application.Application} !
 * <p>
 * Le démarrage de l'application diffère des exemples classiques trouvés dans
 * la documentation de JavaFX : l'interface est démarrée à l'initiative du
 * {@link fr.uga.iut2.genevent.controleur.Controleur} via l'appel de la méthode
 * {@link #demarrerInteraction()}.
 */
public class JavaFXGUI extends IHM {

    private final CountDownLatch eolBarrier;  // /!\ ne pas supprimer /!\ : suivi de la durée de vie de l'interface

    // éléments vue nouvel·le utilisa·teur/trice
    @FXML private TextField newUserForenameTextField;
    @FXML private TextField newUserSurnameTextField;
    @FXML private TextField newUserEmailTextField;
    @FXML private Button newUserOkButton;
    @FXML private Button newUserCancelButton;

    public JavaFXGUI() {
        super();
        this.eolBarrier = new CountDownLatch(1);  // /!\ ne pas supprimer /!\
    }

    /**
     * Point d'entrée principal pour le code de l'interface JavaFX.
     *
     * @param primaryStage stage principale de l'interface JavaFX, sur laquelle
     *     définir des scenes.
     *
     * @throws IOException si le chargement de la vue FXML échoue.
     *
     * @see javafx.application.Application#start(Stage)
     */
    private void start(Stage primaryStage) throws IOException {
        primaryStage.setMaximized(true);
        FXMLLoader mainViewLoader = new FXMLLoader(getClass().getResource("Accueil.fxml"));
        mainViewLoader.setController(new ControleurAccueil());
        Scene mainScene = new Scene(mainViewLoader.load());

        primaryStage.setTitle("LeBonStand");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


//-----  Éléments du dialogue  -------------------------------------------------

    private void exitAction() {
        // fermeture de l'interface JavaFX : on notifie sa fin de vie
        this.eolBarrier.countDown();
    }

    // menu principal  -----

//    @FXML
//    private void newUserMenuItemAction() {
//        this.controleur.saisirUtilisateur();
//    }

    @FXML
    private void exitMenuItemAction() {
        Platform.exit();
        this.exitAction();
    }

//-----  Implémentation des méthodes abstraites  -------------------------------

    public void demarrerInteraction() {
        // démarrage de l'interface JavaFX
        Platform.startup(() -> {
            Stage primaryStage = new Stage();
            primaryStage.setOnCloseRequest((WindowEvent t) -> this.exitAction());
            try {
                this.start(primaryStage);
            }
            catch (IOException exc) {
                throw new RuntimeException(exc);
            }
        });

        // attente de la fin de vie de l'interface JavaFX
        try {
            this.eolBarrier.await();
        }
        catch (InterruptedException exc) {
            System.err.println("Erreur d'exécution de l'interface.");
            System.err.flush();
        }
    }

    @Override
    public void changerFenetre(Stage stage) {
        // TODO Auto-generated method stub
    }

    @Override
    public void informerUtilisateur(String message, boolean succes) {
        System.out.println(message);
    }
}
