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

        Evenement evenement = new Evenement(nom, null, null, type, new ArrayList<>(fonctionnalites));
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
