package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.vue.IHM;
import fr.uga.iut2.genevent.vue.JavaFXGUI;


public class Controleur {

    private static Controleur instance;
    private Application application;
    ControleurAgentSecu controleurAgentSecu;
    ControleurAgentEntretient controleurAgentEntretient;
    ControleurParticipant controleurParticipant;
    ControleurCommercant controleurCommercant;
    ControleurEvenement controleurEvenement;
    ControleurAnimation controleurAnimation;

    private Controleur(Application application) {
        this.application = application;
        controleurAgentSecu = new ControleurAgentSecu(application);
        controleurAgentEntretient = new ControleurAgentEntretient(application);
        controleurParticipant = new ControleurParticipant(application);
        controleurCommercant = new ControleurCommercant(application);
        controleurEvenement = new ControleurEvenement(application);
        controleurAnimation = new ControleurAnimation(application);
    }

    public void setEvenementCourant(Evenement evenement) {
        controleurEvenement.setEvenement(evenement);
        controleurAgentEntretient.setEvenement(evenement);
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
}
