package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;

public class ControleurAgentSecu {
    private Application application;
    private Evenement evenement;

    public ControleurAgentSecu(Application application) {
        this.application = application;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
}
