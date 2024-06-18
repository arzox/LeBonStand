package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;

import java.util.ArrayList;

/**
 * La classe Controleur fournit une structure de base pour gérer le modèle en
 * faisant l'intermédiaire entre celui-ci et le package vue (partie IHM)
 */
public class Controleur {

    private static Controleur instance;

    private Application application;
    ControleurAgentSecurite controleurAgentSecurite;
    ControleurAgentEntretien controleurAgentEntretien;
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
        controleurAgentSecurite = new ControleurAgentSecurite(application);
        controleurAgentEntretien = new ControleurAgentEntretien(application);
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
        controleurAgentEntretien.setEvenement(evenement);
        controleurAgentSecurite.setEvenement(evenement);
        controleurParticipant.setEvenement(evenement);
        controleurCommercant.setEvenement(evenement);
        controleurAnimation.setEvenement(evenement);
    }

    public ControleurAgentSecurite getControleurAgentSecurite() {
        return controleurAgentSecurite;
    }

    public ControleurAgentEntretien getControleurAgentEntretien() {
        return controleurAgentEntretien;
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
