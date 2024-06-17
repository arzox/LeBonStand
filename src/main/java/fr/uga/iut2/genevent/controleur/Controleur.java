package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;

/**
 * La classe Controleur fournit une structure de base pour gérer le modèle en
 * faisant l'intermédiaire entre celui-ci et le package vue (partie IHM)
 */
import java.util.ArrayList;


public class Controleur {

    private static Controleur instance;

    private Application application;
    ControleurAgentSecu controleurAgentSecu;
    ControleurAgentEntretient controleurAgentEntretient;
    ControleurParticipant controleurParticipant;
    ControleurCommercant controleurCommercant;
    ControleurEvenement controleurEvenement;
    ControleurAnimation controleurAnimation;

    public static Controleur getInstance(Application application) {
        if (instance == null) {
            instance = new Controleur(application);
        }
        return instance;
    }

    private Controleur(Application application) {
        this.application = application;
        controleurAgentSecu = new ControleurAgentSecu(application);
        controleurAgentEntretient = new ControleurAgentEntretient(application);
        controleurParticipant = new ControleurParticipant(application);
        controleurCommercant = new ControleurCommercant(application);
        controleurEvenement = new ControleurEvenement(application);
        controleurAnimation = new ControleurAnimation(application);
    }

    /**
     * Permet de changer d'événement courant : cette méthode est appelée lorsqu'on
     * choisit un événement sur la page d'accueil, depuis les classes du package
     * {@code vue}
     *
     * @param evenement - L'événement qui doit devenir l'événement courant
     */
    public void setEvenementCourant(Evenement evenement) {
        controleurEvenement.setEvenement(evenement);
        controleurAgentEntretient.setEvenement(evenement);
        controleurAgentSecu.setEvenement(evenement);
        controleurParticipant.setEvenement(evenement);
        controleurCommercant.setEvenement(evenement);
        controleurAnimation.setEvenement(evenement);
    }


    // Getters

    public ControleurAgentSecu getControleurAgentSecu() {
        return controleurAgentSecu;
    }

    public ControleurAgentEntretient getControleurAgentEntretient() {
        return controleurAgentEntretient;
    }

    public ControleurParticipant getControleurParticipant() {
        return controleurParticipant;
    }

    public ControleurCommercant getControleurCommercant() {
        return controleurCommercant;
    }

    public ControleurEvenement getControleurEvenement() {
        return controleurEvenement;
    }

    public ControleurAnimation getControleurAnimation() {
        return controleurAnimation;
    }

    public ArrayList<Evenement> getEvents() {
        return application.getEvenements();
    }

    public void supprimerEvenement(Evenement evenement) {
        application.removeEvenement(evenement);
    }
}
