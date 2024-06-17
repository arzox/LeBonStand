package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;

/**
 * Sous-contrôleur pour la catégorie "Agents d'entretien"
 */
public class ControleurAgentEntretient {

    private Application application;
    private Evenement evenement;

    public ControleurAgentEntretient(Application application) {
        this.application = application;
    }

    // Setters

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

}
