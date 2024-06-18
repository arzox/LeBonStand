package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.exception.MauvaisChampsException;
import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Fonctionnalite;
import fr.uga.iut2.genevent.modele.TypeEvenement;
import fr.uga.iut2.genevent.modele.Lieu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Sous-contrôleur pour les événements
 */
public class ControleurEvenement {

    private Application application;
    private Evenement evenement;

    public ControleurEvenement(Application application) {
        this.application = application;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public Evenement creerEvenement(String nom, TypeEvenement type, ArrayList<Fonctionnalite> fonctionnalites) throws MauvaisChampsException {

        // Création d'un événement
        for (Evenement evenementCourant : application.getEvenements()) {

            String nomCourant = evenementCourant.getNom();
            TypeEvenement typeCourant = evenementCourant.getType();
            boolean isNotUnique = nom.equals(nomCourant) & type == typeCourant;

            if (isNotUnique) {

                throw new MauvaisChampsException("L'événement que vous souhaitez créer existe déjà",
                        new ArrayList<>(Arrays.asList(false, false, true)));
            }
        }
        Evenement evenement = new Evenement(nom, null, null, type, fonctionnalites);

        // Ajout de l'événement à la liste des événements
        application.addEvenement(evenement);

        // Renvoi de l'événement afin d'y attribuer les controleurs dans le controleur FXML dans la vue
        return evenement;
    }

    public void supprimerEvenement(Evenement evenement) {
        application.removeEvenement(evenement);
    }

    public void modifierNomEvenement(Evenement evenement, String nom) throws MauvaisChampsException {

        for (Evenement evenementCourant : application.getEvenements()) {

            String nomCourant = evenementCourant.getNom();
            TypeEvenement typeCourant = evenementCourant.getType();
            boolean isNotUnique = nom.equals(nomCourant) & evenement.getType() == typeCourant;

            if (isNotUnique) {

                throw new MauvaisChampsException("En changeant le nom de l'événement, celui-ci devient identique à un autre événement",
                        new ArrayList<>(Collections.singleton(false)));
            }
        }
        evenement.setNom(nom);
    }

    public void modifierTypeEvenement(Evenement evenement, TypeEvenement type) throws MauvaisChampsException {

        for (Evenement evenementCourant : application.getEvenements()) {

            String nomCourant = evenementCourant.getNom();
            TypeEvenement typeCourant = evenementCourant.getType();
            boolean isNotUnique = evenement.getNom().equals(nomCourant) & type == typeCourant;

            if (isNotUnique) {

                throw new MauvaisChampsException("En changeant le type de l'événement, celui-ci devient identique à un autre événement",
                        new ArrayList<>(Collections.singleton(false)));
            }
        }
        evenement.setType(type);
    }

    public void modifierFonctionnalitesEvenement(Evenement evenement, ArrayList<Fonctionnalite> fonctionnalites) {

        evenement.setFonctionnalites(fonctionnalites);
    }

    public void modifierLieuEvenement(Evenement evenement, Lieu lieu) {

        evenement.setLieu(lieu);
    }

    public void modifierDebutEvenement(Evenement evenement, LocalDate dateDebut) throws MauvaisChampsException {

        boolean isStartAfterEnd = evenement.getDateFin() != null && dateDebut.isAfter(evenement.getDateFin());

        if (isStartAfterEnd) {

            throw new MauvaisChampsException("La date de début de l'événement est ultérieure à sa date de fin",
                    new ArrayList<>(Collections.singleton(false)));
        }
        evenement.setDateDebut(dateDebut);
    }

    public void modifierFinEvenement(Evenement evenement, LocalDate dateFin) throws MauvaisChampsException {

        boolean isStartAfterEnd = evenement.getDateDebut() != null && dateFin.isBefore(evenement.getDateDebut());

        if (isStartAfterEnd) {

            throw new MauvaisChampsException("La date de fin de l'événement est antérieure à sa date de début",
                    new ArrayList<>(Collections.singleton(false)));
        }
        evenement.setDateFin(dateFin);
    }

    public ArrayList<Fonctionnalite> getFonctionnalites() {
        return evenement.getFonctionnalites();
    }


}
