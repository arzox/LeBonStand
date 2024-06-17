package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;

public class Controleur {

    private static Controleur instance;
    private Application application;
    ControleurAgentSecu controleurAgentSecu;
    ControleurAgentEntretien controleurAgentEntretien;
    ControleurParticipant controleurParticipant;
    ControleurCommercant controleurCommercant;
    ControleurEvenement controleurEvenement;
    ControleurAnimation controleurAnimation;

    private Controleur(Application application) {
        this.application = application;
        controleurAgentSecu = new ControleurAgentSecu(application);
        controleurAgentEntretien = new ControleurAgentEntretien(application);
        controleurParticipant = new ControleurParticipant(application);
        controleurCommercant = new ControleurCommercant(application);
        controleurEvenement = new ControleurEvenement(application);
        controleurAnimation = new ControleurAnimation(application);
    }

    public ControleurEvenement getControleurEvenement() {
        return controleurEvenement;
    }

    public void setEvenementCourant(Evenement evenement) {
        controleurEvenement.setEvenement(evenement);
        controleurAgentEntretien.setEvenement(evenement);
        controleurAgentSecu.setEvenement(evenement);
        controleurParticipant.setEvenement(evenement);
        controleurCommercant.setEvenement(evenement);
        controleurAnimation.setEvenement(evenement);
    }
    
    public static Controleur getInstance(Application application) {
        if (instance == null) {
            instance = new Controleur(application);
        }
        return instance;
    }

    /*
    public void creerUtilisateur(IHM.InfosUtilisateur infos) {
        boolean nouvelUtilisateur = this.genevent.ajouteUtilisateur(
                infos.email,
                infos.nom,
                infos.prenom
        );
        if (nouvelUtilisateur) {
            this.ihm.informerUtilisateur(
                    "Nouvel·le utilisa·teur/trice: " + infos.prenom + " " + infos.nom + " <" + infos.email + ">",
                    true
            );
        } else {
            this.ihm.informerUtilisateur(
                    "L'utilisa·teur/trice " + infos.email + " est déjà connu·e de GenEvent.",
                    false
            );
        }
    }

    public ControleurCommercant getControleurCommercant() {
        return controleurCommercant;
    }

    public ControleurEvenement getControleurEvenement() {
        return controleurEvenement;
    }

    public ControleurAnimation getControleurAnimation() {
        return controleurAnimation;
    }*/
}
