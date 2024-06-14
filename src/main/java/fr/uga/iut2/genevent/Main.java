package fr.uga.iut2.genevent;

import fr.uga.iut2.genevent.controleur.Controleur;
import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.util.Persisteur;
import java.io.IOException;


public class Main {

    public static final int EXIT_ERR_LOAD = 2;
    public static final int EXIT_ERR_SAVE = 3;

    public static void main(String[] args) {
        Application application = new Application();
//        try {
//            application = Persisteur.lireEtat();
//        }
//        catch (ClassNotFoundException | IOException ignored) {
//            System.err.println("Erreur irrécupérable pendant le chargement de l'état : fin d'exécution !");
//            System.err.flush();
//            System.exit(Main.EXIT_ERR_LOAD);
//        }

        Controleur controleur = Controleur.getInstance();
        // `Controleur.demarrer` garde le contrôle de l'exécution tant que
        // l'utilisa·teur/trice n'a pas saisi la commande QUITTER.
        controleur.demarrer();

        try {
            Persisteur.sauverEtat(application);
        }
        catch (IOException ignored) {
            System.err.println("Erreur irrécupérable pendant la sauvegarde de l'état : fin d'exécution !");
            System.err.flush();
            System.exit(Main.EXIT_ERR_SAVE);
        }
    }
}
