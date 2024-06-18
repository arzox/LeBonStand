package fr.uga.iut2.genevent;

import fr.uga.iut2.genevent.controleur.Controleur;
import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Fonctionnalite;
import fr.uga.iut2.genevent.modele.TypeEvenement;
import fr.uga.iut2.genevent.util.Persisteur;
import fr.uga.iut2.genevent.vue.JavaFXGUI;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class Main {

    private static Logger LOGGER =
            Logger.getLogger(Main.class.getPackageName());

    // Récupérarion du gestionnaire de logs.
    private static final LogManager logManager = LogManager.getLogManager();
    // Configuration du logger
    // EditConfiguration > Modify options > add VM options :
    // -Djava.util.logging.config.file=conf/debug-logging.properties
    static{
        try {
            logManager.readConfiguration( new FileInputStream("conf/debug-lbs.properties") );
        } catch ( IOException exception ) {
            LOGGER.log( Level.SEVERE, "Cannot read configuration file",
                    exception );
        }
    }

    public static final int EXIT_ERR_LOAD = 2;
    public static final int EXIT_ERR_SAVE = 3;

    public static void main(String[] args) {
        // Créer une instance du modèle (application correspond à la racine du modèle)
        Application application = new Application();

        application.addEvenement(new Evenement("Marche Noel", LocalDate.of(2021, 6, 1), LocalDate.of(2021, 6 ,2), TypeEvenement.MARCHE_NOEL, new ArrayList<>(Arrays.asList(Fonctionnalite.AGENT_ENTRETIEN, Fonctionnalite.PARTICIPANT))));
        application.addEvenement(new Evenement("Brocante", LocalDate.of(2022, 7, 2), LocalDate.of(2022, 7, 3), TypeEvenement.BROCANTE, new ArrayList<>(Arrays.asList(Fonctionnalite.AGENT_ENTRETIEN, Fonctionnalite.PARTICIPANT))));
        try {
            LOGGER.log(Level.INFO, "Sauvegarde de l'état initial");
            Persisteur.sauverEtat(application);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erreur irrécupérable pendant la sauvegarde de l'état : fin d'exécution !");
            throw new RuntimeException(e);
        }

        try {
            LOGGER.log(Level.INFO, "Chargement de l'état initial");
            application = Persisteur.lireEtat();
        }
        catch (ClassNotFoundException | IOException ignored) {
            LOGGER.log(Level.SEVERE, "Erreur irrécupérable pendant le chargement de l'état : fin d'exécution !");
            System.err.println("Erreur irrécupérable pendant le chargement de l'état : fin d'exécution !");
            System.err.flush();
            System.exit(Main.EXIT_ERR_LOAD);
        }

        System.out.println(application.getEvenements());

        Controleur controleur = Controleur.getInstance(application);

        // Set evenement courant pour tester
        controleur.setEvenementCourant(application.getEvenements().get(0));

        try {
            LOGGER.log(Level.INFO, "Démarrage de l'interface graphique");
            JavaFXGUI startup = new JavaFXGUI();
            startup.demarrerInteraction();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erreur irrécupérable pendant l'interaction avec l'utilisateur·trice : fin d'exécution !");
            e.printStackTrace();
        }
        // `Controleur.demarrer` garde le contrôle de l'exécution tant que
        // l'utilisa·teur/trice n'a pas saisi la commande QUITTER.

        try {
            LOGGER.log(Level.INFO, "Sauvegarde de l'état final");
            Persisteur.sauverEtat(application);
        }
        catch (IOException ignored) {
            LOGGER.log(Level.SEVERE, "Erreur irrécupérable pendant la sauvegarde de l'état : fin d'exécution !");
            System.err.println("Erreur irrécupérable pendant la sauvegarde de l'état : fin d'exécution !");
            System.err.flush();
            System.exit(Main.EXIT_ERR_SAVE);
        }
    }

    public static void setLOGGER(Level level, String message) {
        LOGGER.log(level, message);
    }
}
