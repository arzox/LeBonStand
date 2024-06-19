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
import java.util.List;
import java.util.Observable;

/**
 * Contrôleur pour les événements
 */
public class ControleurEvenement extends Observable {

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
        setChanged();
        notifyObservers();
    }

    // Événement
    public Evenement creerEvenement(String nom, TypeEvenement type, ArrayList<Fonctionnalite> fonctionnalites, String imagePath)
            throws MauvaisChampsException {
        if (type == null) {
            throw new MauvaisChampsException("Veuillez choisir un type pour l'événement",
                    new ArrayList<>(Arrays.asList(true, false, true)));
        }

        for (Evenement evenementCourant : application.getEvenements()) {
            String nomCourant = evenementCourant.getNom();
            TypeEvenement typeCourant = evenementCourant.getType();
            boolean isNotUnique = nom.equals(nomCourant) && type == typeCourant;

            if (isNotUnique) {
                throw new MauvaisChampsException("L'événement que vous souhaitez créer existe déjà",
                        new ArrayList<>(Arrays.asList(false, false, true)));
            }
        }
        Evenement evenement = new Evenement(nom, null, null, type, fonctionnalites, imagePath);
        application.addEvenement(evenement);
        setChanged();
        notifyObservers();
        return evenement;
    }

    public void supprimerEvenement(Evenement evenement) {
        application.removeEvenement(evenement);
        setChanged();
        notifyObservers();
    }

    public void modifierNomEvenement(Evenement evenement, String nom) throws MauvaisChampsException {
        for (Evenement evenementCourant : application.getEvenements()) {
            String nomCourant = evenementCourant.getNom();
            TypeEvenement typeCourant = evenementCourant.getType();
            boolean isNotUnique = nom.equals(nomCourant) && evenement.getType() == typeCourant;

            if (isNotUnique) {
                throw new MauvaisChampsException("En changeant le nom de l'événement, " +
                        "celui-ci devient identique à un autre événement",
                        new ArrayList<>(Collections.singleton(false)));
            }
        }
        evenement.setNom(nom);
        setChanged();
        notifyObservers();
    }

    public void modifierTypeEvenement(Evenement evenement, TypeEvenement type) throws MauvaisChampsException {
        for (Evenement evenementCourant : application.getEvenements()) {
            String nomCourant = evenementCourant.getNom();
            TypeEvenement typeCourant = evenementCourant.getType();
            boolean isNotUnique = evenement.getNom().equals(nomCourant) && type == typeCourant;

            if (isNotUnique) {
                throw new MauvaisChampsException("En changeant le type de l'événement, " +
                        "celui-ci devient identique à un autre événement",
                        new ArrayList<>(Collections.singleton(false)));
            }
        }
        evenement.setType(type);
        setChanged();
        notifyObservers();
    }

    public void modifierFonctionnalitesEvenement(Evenement evenement, ArrayList<Fonctionnalite> fonctionnalites) {
        evenement.setFonctionnalites(fonctionnalites);
        setChanged();
        notifyObservers();
    }

    public void modifierLieuEvenement(Evenement evenement, Lieu lieu) {
        evenement.setLieu(lieu);
        setChanged();
        notifyObservers();
    }

    public void modifierDebutEvenement(Evenement evenement, LocalDate dateDebut) throws MauvaisChampsException {
        boolean isStartAfterEnd = evenement.getDateFin() != null && dateDebut.isAfter(evenement.getDateFin());

        if (isStartAfterEnd) {
            throw new MauvaisChampsException("La date de début de l'événement est ultérieure à sa date de fin",
                    new ArrayList<>(Collections.singleton(false)));
        }
        evenement.setDateDebut(dateDebut);
        setChanged();
        notifyObservers();
    }

    public void modifierFinEvenement(Evenement evenement, LocalDate dateFin) throws MauvaisChampsException {
        boolean isStartAfterEnd = evenement.getDateDebut() != null && dateFin.isBefore(evenement.getDateDebut());

        if (isStartAfterEnd) {
            throw new MauvaisChampsException("La date de fin de l'événement est antérieure à sa date de début",
                    new ArrayList<>(Collections.singleton(false)));
        }
        evenement.setDateFin(dateFin);
        setChanged();
        notifyObservers();
    }

    // Lieu
    public Lieu creerLieu(String nom, String adresse, String ville, int codePostal) {
        Lieu nouveauLieu = new Lieu(nom, adresse, ville, codePostal);
        evenement.setLieu(nouveauLieu);
        setChanged();
        notifyObservers();
        return nouveauLieu;
    }

    public void modifierNomLieu(Lieu lieu, String nom) {
        lieu.setNom(nom);
        setChanged();
        notifyObservers();
    }

    public void modifierAdresseLieu(Lieu lieu, String adresse) {
        lieu.setAdresse(adresse);
        setChanged();
        notifyObservers();
    }

    public void modifierVilleLieu(Lieu lieu, String ville) {
        lieu.setVille(ville);
        setChanged();
        notifyObservers();
    }

    public void modifierCodePostalLieu(Lieu lieu, int codePostal) {
        lieu.setCodePostal(codePostal);
        setChanged();
        notifyObservers();
    }

    public ArrayList<Fonctionnalite> getFonctionnalites() {
        return evenement.getFonctionnalites();
    }

    // Image
    public void setImagePath(Evenement evenement, String imagePath) {
        evenement.setImagePath(imagePath);
        setChanged();
        notifyObservers();
    }

    public String getImagePath(Evenement evenement) {
        return evenement.getImagePath();
    }

    public List<Evenement> getTousEvenements() {
        return application.getEvenements();
    }
}
