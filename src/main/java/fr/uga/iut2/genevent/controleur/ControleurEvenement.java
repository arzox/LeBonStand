package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Fonctionnalite;
import fr.uga.iut2.genevent.modele.TypeEvenement;
import fr.uga.iut2.genevent.vue.IHM;

import java.util.List;

public class ControleurEvenement {

    private Application application;

    public ControleurEvenement(Application application) {
        this.application = application;
    }

    public Evenement creerEvenement(String nom, TypeEvenement type, List<Fonctionnalite> fonctionnalites, IHM ihm) {

        Evenement evenement = new Evenement(nom, null, null, type);
        evenement.initieCommerce();
        for (Fonctionnalite fonctionnalite : fonctionnalites) {
            if (fonctionnalite == Fonctionnalite.AGENT_ENTRETIEN) {
                evenement.initieEntretien();
            } else if (fonctionnalite == Fonctionnalite.AGENT_SECURITE) {
                evenement.initieSecurite();
            } else if (fonctionnalite == Fonctionnalite.PARTICIPANT) {
                evenement.initieParticipant();
            } else if (fonctionnalite == Fonctionnalite.ANIMATION) {
                evenement.initieAnimation();
            }
        }
        ihm.informerUtilisateur("Evenement créé avec succès", true);
        application.addEvenement(evenement);
        return evenement;
    }

    public void supprimerEvenement(Evenement evenement, IHM ihm) {
        application.removeEvenement(evenement);
        ihm.informerUtilisateur("Evenement supprimé avec succès", true);
    }

}
