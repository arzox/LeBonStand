package fr.uga.iut2.genevent;

import fr.uga.iut2.genevent.controleur.Controleur;
import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.util.Persisteur;
import fr.uga.iut2.genevent.vue.JavaFXGUI;

import java.io.IOException;


public class Main {

    public static final int EXIT_ERR_LOAD = 2;
    public static final int EXIT_ERR_SAVE = 3;

    public static void main(String[] args) {
        Application application = new Application();
//        application.addEvenement(new Evenement("Marche Noel", "2021-06-01", "2021-06-02", TypeEvenement.MARCHE_NOEL, new ArrayList<>(Arrays.asList(Fonctionnalite.AGENT_ENTRETIEN, Fonctionnalite.PARTICIPANT))));
//        try {
//            Persisteur.sauverEtat(application);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        try {
            application = Persisteur.lireEtat();
        }
        catch (ClassNotFoundException | IOException ignored) {
            System.err.println("Erreur irrécupérable pendant le chargement de l'état : fin d'exécution !");
            System.err.flush();
            System.exit(Main.EXIT_ERR_LOAD);
        }

        System.out.println(application.getEvenements());


        Controleur controleur = Controleur.getInstance(application);

        // Set evenement courant pour tester
        controleur.setEvenementCourant(application.getEvenements().get(0));

        try {
            JavaFXGUI startup = new JavaFXGUI();
            startup.demarrerInteraction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // `Controleur.demarrer` garde le contrôle de l'exécution tant que
        // l'utilisa·teur/trice n'a pas saisi la commande QUITTER.

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
