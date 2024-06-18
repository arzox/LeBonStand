package fr.uga.iut2.genevent.modele;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * La classe Animation représente une animation avec un nom, un prix et des dates de début et de fin.
 */
public class Animation {
    private String nom;
    private float prix;
    private LocalDateTime dateHeureDebut;
    private LocalDateTime dateHeureFin;

    /**
     * Constructeur de la classe Animation.
     *
     * @param nom Le nom de l'animation.
     * @param prix Le prix de l'animation.
     * @param dateDebut La date et l'heure de début de l'animation.
     * @param dateFin La date et l'heure de fin de l'animation.
     */
    public Animation(String nom, float prix, LocalDateTime dateDebut, LocalDateTime dateFin) {
        this.nom = nom;
        this.prix = prix;
        this.dateHeureDebut = dateDebut;
        this.dateHeureFin = dateFin;
    }

    /**
     * Récupère le nom de l'animation.
     *
     * @return Le nom de l'animation.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'animation.
     *
     * @param nom Le nouveau nom de l'animation.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Récupère le prix de l'animation.
     *
     * @return Le prix de l'animation.
     */
    public float getPrix() {
        return prix;
    }

    /**
     * Définit le prix de l'animation.
     *
     * @param prix Le nouveau prix de l'animation.
     */
    public void setPrix(float prix) {
        this.prix = prix;
    }

    /**
     * Récupère la date et l'heure de début de l'animation.
     *
     * @return La date et l'heure de début de l'animation.
     */
    public LocalDateTime getDateHeureDebut() {
        return dateHeureDebut;
    }

    /**
     * Définit la date et l'heure de début de l'animation.
     *
     * @param dateHeureDebut La nouvelle date et l'heure de début de l'animation.
     */
    public void setDateHeureDebut(LocalDateTime dateHeureDebut) {
        this.dateHeureDebut = dateHeureDebut;
    }

    /**
     * Récupère la date et l'heure de fin de l'animation.
     *
     * @return La date et l'heure de fin de l'animation.
     */
    public LocalDateTime getDateHeureFin() {
        return dateHeureFin;
    }

    /**
     * Définit la date et l'heure de fin de l'animation.
     *
     * @param dateHeureFin La nouvelle date et l'heure de fin de l'animation.
     */
    public void setDateHeureFin(LocalDateTime dateHeureFin) {
        this.dateHeureFin = dateHeureFin;
    }
}
