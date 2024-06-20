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
 * Contrôleur pour le module "Evenement".
 */
public class ControleurEvenement {

    private Application application;
    private Evenement evenement;

    /**
     * Crée le contrôleur, doit être uniquement appelée par le constructeur de la
     * classe Controleur.
     * 
     * @param application L'application que le contrôleur gérera
     */
    public ControleurEvenement(Application application) {
        this.application = application;
    }

    /**
     * Récupère l'événement géré par le contrôleur.
     * 
     * @return L'événement actuel
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * Attribue un événement au contrôleur.
     * 
     * @param evenement L'événement à attribuer
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    // Événement

    /**
     * Crée un nouvel événement et l'ajoute à la liste des événements de cette
     * application.
     * 
     * @param nom             Nom de l'événement
     * @param type            Type de l'événement
     * @param fonctionnalites Liste des fonctionnalités de l'événement
     * @return L'événement créé.
     * @throws MauvaisChampsException Si un événement avec le même nom et type
     *                                existe déjà.
     */
    public Evenement creerEvenement(String nom, TypeEvenement type, ArrayList<Fonctionnalite> fonctionnalites,
            String imagePath)
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
        Evenement evenement = new Evenement(nom, null, null, type, fonctionnalites, imagePath);
        application.addEvenement(evenement);

        // Renvoi de l'événement afin d'y attribuer les contrôleurs dans le contrôleur
        // FXML dans la vue
        return evenement;
    }

    /**
     * Retire de la liste des événements de cette application l'événement donné en
     * paramètre.
     * 
     * @param evenement L'événement à supprimer
     */
    public void supprimerEvenement(Evenement evenement) {
        application.removeEvenement(evenement);
    }

    /**
     * Modifie le nom de l'événement donné en paramètre.
     * 
     * @param evenement L'événement dont le nom doit être modifié
     * @param nom       Nouveau nom
     * @throws MauvaisChampsException Si le nouveau nom rend l'événement identique à
     *                                un autre événement.
     */
    public void modifierNomEvenement(Evenement evenement, String nom) throws MauvaisChampsException {

        for (Evenement evenementCourant : application.getEvenements()) {

            String nomCourant = evenementCourant.getNom();
            TypeEvenement typeCourant = evenementCourant.getType();

            boolean isNotSameEvenement = evenement != evenementCourant;
            boolean isNotUnique = nom.equals(nomCourant) & evenement.getType() == typeCourant;

            if (isNotSameEvenement & isNotUnique) {

                throw new MauvaisChampsException("En changeant le nom de l'événement, " +
                        "celui-ci devient identique à un autre événement",
                        new ArrayList<>(Collections.singleton(false)));
            }
        }
        evenement.setNom(nom);
    }

    /**
     * Modifie le type de l'événement donné en paramètre.
     * 
     * @param evenement L'événement dont le type doit être modifié
     * @param type      Nouveau type
     * @throws MauvaisChampsException Si le nouveau type rend l'événement identique
     *                                à un autre événement.
     */
    public void modifierTypeEvenement(Evenement evenement, TypeEvenement type) throws MauvaisChampsException {

        for (Evenement evenementCourant : application.getEvenements()) {

            String nomCourant = evenementCourant.getNom();
            TypeEvenement typeCourant = evenementCourant.getType();

            boolean isNotSameEvenement = evenement != evenementCourant;
            boolean isNotUnique = evenement.getNom().equals(nomCourant) & type == typeCourant;

            if (isNotSameEvenement & isNotUnique) {

                throw new MauvaisChampsException("En changeant le type de l'événement, " +
                        "celui-ci devient identique à un autre événement",
                        new ArrayList<>(Collections.singleton(false)));
            }
        }
        evenement.setType(type);
    }

    /**
     * Modifie les fonctionnalités de l'événement donné en paramètre.
     * 
     * @param evenement       L'événement dont les fonctionnalités doivent être
     *                        modifiées
     * @param fonctionnalites Nouvelles fonctionnalités
     */
    public void modifierFonctionnalitesEvenement(Evenement evenement, ArrayList<Fonctionnalite> fonctionnalites) {

        evenement.setFonctionnalites(fonctionnalites);
    }

    /**
     * Modifie le lieu de l'événement donné en paramètre.
     * 
     * @param evenement L'événement dont le nom doit être modifié
     * @param lieu      Nouveau lieu
     */
    public void modifierLieuEvenement(Evenement evenement, Lieu lieu) {

        evenement.setLieu(lieu);
    }

    /**
     * Modifie la date de début de l'événement donné en paramètre.
     * 
     * @param evenement L'événement dont la date de début doit être modifié
     * @param dateDebut Nouvelle date de début
     * @throws MauvaisChampsException Si la nouvelle date de début de cet événement
     *                                est ultérieure à sa date de fin.
     */
    public void modifierDebutEvenement(Evenement evenement, LocalDate dateDebut) throws MauvaisChampsException {

        boolean isStartAfterEnd = evenement.getDateFin() != null && dateDebut.isAfter(evenement.getDateFin());

        if (isStartAfterEnd) {

            throw new MauvaisChampsException("La date de début de l'événement ne peut être ultérieure à sa date de fin",
                    new ArrayList<>(Collections.singleton(false)));
        }
        evenement.setDateDebut(dateDebut);
    }

    /**
     * Modifie la date de fin d'un événement.
     * 
     * @param evenement L'événement à modifier
     * @param dateFin   Nouvelle date de fin
     * @throws MauvaisChampsException Si la nouvelle date de fin de cet événement
     *                                est antérieure à sa date de début.
     */
    public void modifierFinEvenement(Evenement evenement, LocalDate dateFin) throws MauvaisChampsException {

        boolean isStartAfterEnd = evenement.getDateDebut() != null && dateFin.isBefore(evenement.getDateDebut());

        if (isStartAfterEnd) {

            throw new MauvaisChampsException("La date de fin de l'événement ne peut être antérieure à sa date de début",
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
     * @return Le lieu créé.
     */
    public Lieu creerLieu(String nom, String adresse, String ville, int codePostal) {

        Lieu nouveauLieu = new Lieu(nom, adresse, ville, codePostal);
        evenement.setLieu(nouveauLieu);

        return nouveauLieu;
    }

    /**
     * Modifie le nom du lieu donné en paramètre.
     * 
     * @param lieu Lieu à modifier
     * @param nom  Nouveau nom
     */
    public void modifierNomLieu(Lieu lieu, String nom) {

        lieu.setNom(nom);
    }

    /**
     * Modifie l'adresse du lieu donné en paramètre.
     * 
     * @param lieu    Lieu à modifier
     * @param adresse Nouvelle adresse
     */
    public void modifierAdresseLieu(Lieu lieu, String adresse) {

        lieu.setAdresse(adresse);
    }

    /**
     * Modifie la ville du lieu donné en paramètre.
     * 
     * @param lieu  Lieu à modifier
     * @param ville Nouvelle ville
     */
    public void modifierVilleLieu(Lieu lieu, String ville) {

        lieu.setVille(ville);
    }

    /**
     * Modifie le code postal du lieu donné en paramètre.
     * 
     * @param lieu       Lieu à modifier
     * @param codePostal Nouveau code postal
     */
    public void modifierCodePostalLieu(Lieu lieu, int codePostal) {

        lieu.setCodePostal(codePostal);
    }

    /**
     * Récupère les fonctionnalités de l'événement.
     * 
     * @return Liste des fonctionnalités de l'événement.
     */
    public ArrayList<Fonctionnalite> getFonctionnalites() {
        return evenement.getFonctionnalites();
    }

    // Image
    public void setImagePath(Evenement evenement, String imagePath) {
        evenement.setImagePath(imagePath);
    }

    public String getImagePath(Evenement evenement) {
        return evenement.getImagePath();
    }

    public ArrayList<Evenement> getTousEvenements() {
        return application.getEvenements();
    }
}
