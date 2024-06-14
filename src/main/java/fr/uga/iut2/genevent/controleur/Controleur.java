package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.vue.IHM;
import fr.uga.iut2.genevent.vue.JavaFXGUI;


public class Controleur {

    private static Controleur instance;
    private final IHM startup;
    
    private Controleur() {
        this.startup = new JavaFXGUI();
        ControleurAgentSecu controleurAgentSecu = new ControleurAgentSecu();
        ControleurAgentEntretient controleurAgentEntretient = new ControleurAgentEntretient();
        ControleurClient controleurClient = new ControleurClient();
        ControleurCommercant controleurCommercant = new ControleurCommercant();
        ControleurEvenement controleurEvenement = new ControleurEvenement();
    }
    
    public static Controleur getInstance() {
        if (instance == null) {
            instance = new Controleur();
        }
        return instance;
    }

    public void demarrer() {
        this.startup.demarrerInteraction();
    }



    /*
    public void saisirUtilisateur() {
        this.ihm.saisirUtilisateur();
    }

    public void creerUtilisateur(IHM.InfosUtilisateur infos) {
        boolean nouvelUtilisateur = this.genevent.ajouteUtilisateur(
                infos.email,
                infos.nom,
                infos.prenom
        );
        if (nouvelUtilisateur) {
            this.ihm.informerUtilisateur(
                    "Nouvel·le utilisa·teur/trice : " + infos.prenom + " " + infos.nom + " <" + infos.email + ">",
                    true
            );
        } else {
            this.ihm.informerUtilisateur(
                    "L'utilisa·teur/trice " + infos.email + " est déjà connu·e de GenEvent.",
                    false
            );
        }
    }

    public void saisirEvenement() {
        this.ihm.saisirNouvelEvenement(this.genevent.getEvenements().keySet());
    }

    public void creerEvenement(IHM.InfosNouvelEvenement infos) {
        // création d'un Utilisateur si nécessaire
        boolean nouvelUtilisateur = this.genevent.ajouteUtilisateur(
                infos.admin.email,
                infos.admin.nom,
                infos.admin.prenom
        );
        if (nouvelUtilisateur) {
            this.ihm.informerUtilisateur("Nouvel·le utilisa·teur/trice : " + infos.admin.prenom + " " + infos.admin.nom + " <" + infos.admin.email + ">",
                    true
            );
        }

        this.genevent.nouvelEvenement(
                infos.nom,
                infos.dateDebut,
                infos.dateFin,
                infos.admin.email
        );
        this.ihm.informerUtilisateur(
                "Nouvel évènement : " + infos.nom + ", administré par " + infos.admin.email,
                true
        );
    }*/
}
