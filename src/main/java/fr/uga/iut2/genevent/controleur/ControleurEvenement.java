package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Fonctionnalite;
import fr.uga.iut2.genevent.modele.TypeEvenement;
import fr.uga.iut2.genevent.vue.IHM;

import java.util.*;

/**
 * Sous-contrôleur pour les événements
 */
public class ControleurEvenement {

    private Application application;
    private Evenement evenement;

    public ControleurEvenement(Application application) {
        this.application = application;
    }

    public Evenement creerEvenement(String nom, TypeEvenement type, List<Fonctionnalite> fonctionnalites, IHM ihm) {

        Evenement evenement = new Evenement(nom, null, null, type, new ArrayList<>(Arrays.asList(Fonctionnalite.values())));
        evenement.initieCommerce();
        modifierFonctionnalites(evenement, fonctionnalites);
        ihm.informerUtilisateur("Evenement créé avec succès", true);
        application.addEvenement(evenement);
        return evenement;
    }

    public void supprimerEvenement(Evenement evenement, IHM ihm) {
        application.removeEvenement(evenement);
        ihm.informerUtilisateur("Evenement supprimé avec succès", true);
    }

    public void setDebutEvenement(Evenement evenement, String dateDebut, IHM ihm) {
        evenement.setDebut(dateDebut);
        ihm.informerUtilisateur("Date de début de l'événement modifiée avec succès", true);
    }

    public void setFinEvenement(Evenement evenement, String dateFin, IHM ihm) {
        evenement.setFin(dateFin);
        ihm.informerUtilisateur("Date de fin de l'événement modifiée avec succès", true);
    }

    public void modifierEvenement(Evenement evenement,
                                  Optional<String> nom,
                                    Optional<TypeEvenement> type,
                                    Optional<String> dateDebut,
                                    Optional<String> dateFin) {
        nom.ifPresent(evenement::setNom);
        type.ifPresent(evenement::setType);
        dateDebut.ifPresent(evenement::setDebut);
        dateFin.ifPresent(evenement::setFin);
    }

    public void modifierFonctionnalites(Evenement evenement, List<Fonctionnalite> fonctionnalites) {
        // Create a set of all Fonctionnalite values
        Set<Fonctionnalite> allFonctionnalites = EnumSet.allOf(Fonctionnalite.class);

        // Remove the Fonctionnalite values that are in the fonctionnalites list
        allFonctionnalites.removeAll(fonctionnalites);

        // Iterate over the Fonctionnalite values that are in the enum but not in the list
        for (Fonctionnalite fonctionnalite : allFonctionnalites) {
            switch (fonctionnalite) {
                case AGENT_ENTRETIEN:
                    evenement.supprimerEntretien();
                    break;
                case AGENT_SECURITE:
                    evenement.supprimerSecurite();
                    break;
                case PARTICIPANT:
                    evenement.supprimerParticipant();
                    break;
                case ANIMATION:
                    evenement.supprimerAnimation();
                    break;
                default:
                    // Handle unexpected case
                    break;
            }
        }

        // Iterate over the Fonctionnalite values that are in the list
        for (Fonctionnalite fonctionnalite : fonctionnalites) {
            switch (fonctionnalite) {
                case AGENT_ENTRETIEN:
                    evenement.initieEntretien();
                    break;
                case AGENT_SECURITE:
                    evenement.initieSecurite();
                    break;
                case PARTICIPANT:
                    evenement.initieParticipant();
                    break;
                case ANIMATION:
                    evenement.initieAnimation();
                    break;
                default:
                    // Handle unexpected case
                    break;
            }
        }
    }

    public ArrayList<Fonctionnalite> getFonctionnalites() {
        return evenement.getFonctionnalites();
    }

    // Getters et setters

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
}
