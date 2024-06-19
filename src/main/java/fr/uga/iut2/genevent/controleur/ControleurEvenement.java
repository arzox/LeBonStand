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
 * Contrôleur pour les événements
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

    // Événement
    public Evenement creerEvenement(String nom, TypeEvenement type, ArrayList<Fonctionnalite> fonctionnalites)
            throws MauvaisChampsException {

        // Création d'un événement
        if (type == null) {

            throw new MauvaisChampsException("Veuillez choisir un type pour l'événement",
                    new ArrayList<>(Arrays.asList(true, false, true)));
        }

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

        // Renvoi de l'événement afin d'y attribuer les controleurs dans le controleur
        // FXML dans la vue
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

                throw new MauvaisChampsException("En changeant le nom de l'événement, " +
                        "celui-ci devient identique à un autre événement",
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

                throw new MauvaisChampsException("En changeant le type de l'événement, " +
                        "celui-ci devient identique à un autre événement",
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

    // Lieu

    /**
     * Crée un nouveau lieu et l'associe à cet événement.
     * 
     * @param nom        Nom du lieu
     * @param adresse    Adresse du lieu
     * @param ville      Ville du lieu
     * @param codePostal Code postal du lieu
     * @return le lieu créé.
     */
    public Lieu creerLieu(String nom, String adresse, String ville, int codePostal) {

        Lieu nouveauLieu = new Lieu(nom, adresse, ville, codePostal);
        evenement.setLieu(nouveauLieu);

        return nouveauLieu;
    }

    /**
     * Modifie le nom du lieu donné par un nom donné
     * 
     * @param lieu Lieu dont le nom est modifié
     * @param nom  Nouveau nom à donner
     */
    public void modifierNomLieu(Lieu lieu, String nom) {

        lieu.setNom(nom);
    }

    /**
     * Modifie l'adresse du lieu donné par une adresse donnée
     * 
     * @param lieu    Lieu dont l'adresse est modifiée
     * @param adresse Nouvelle adresse à donner
     */
    public void modifierAdresseLieu(Lieu lieu, String adresse) {

        lieu.setAdresse(adresse);
    }

    /**
     * Modifie la ville du lieu donné par une ville donnée
     * 
     * @param lieu  Lieu dont la ville est modifiée
     * @param ville Nouvelle ville à donner
     */
    public void modifierVilleLieu(Lieu lieu, String ville) {

        lieu.setVille(ville);
    }

    /**
     * Modifie le code postal du lieu donné par un code postal donnée
     * 
     * @param lieu       Lieu dont le code postal est modifié
     * @param codePostal Nouveau code postal à donner
     */
    public void modifierCodePostalLieu(Lieu lieu, int codePostal) {

        lieu.setCodePostal(codePostal);
    }

    public ArrayList<Fonctionnalite> getFonctionnalites() {
        return evenement.getFonctionnalites();
    }

}
