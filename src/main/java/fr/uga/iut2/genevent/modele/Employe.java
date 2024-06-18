package fr.uga.iut2.genevent.modele;

import java.io.Serializable;

/**
 * La classe Employe représente un employé avec un nom, un prénom, un email, un
 * numéro de téléphone, une heure de début et une heure de fin.
 */
public abstract class Employe implements Serializable {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private int heureDebut;
    private int heureFin;

    /**
     * Constructeur de la classe Employe.
     *
     * @param nom        Le nom de l'employé.
     * @param prenom     Le prénom de l'employé.
     * @param email      L'email de l'employé.
     * @param telephone  Le numéro de téléphone de l'employé.
     * @param heureDebut L'heure de début du travail de l'employé.
     * @param heureFin   L'heure de fin du travail de l'employé.
     */
    public Employe(String nom, String prenom, String email, String telephone, int heureDebut, int heureFin) {
        setNom(nom);
        setPrenom(prenom);
        setEmail(email);
        setTelephone(telephone);
        setHeureDebut(heureDebut);
        setHeureFin(heureFin);
    }

    /**
     * Récupère le nom de l'employé.
     *
     * @return Le nom de l'employé.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Récupère le prénom de l'employé.
     *
     * @return Le prénom de l'employé.
     */
    public String getPrenom() {
        return this.prenom;
    }

    /**
     * Récupère l'email de l'employé.
     *
     * @return L'email de l'employé.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Récupère le numéro de téléphone de l'employé.
     *
     * @return Le numéro de téléphone de l'employé.
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * Récupère l'heure de début du travail de l'employé.
     *
     * @return L'heure de début du travail de l'employé.
     */
    public int getHeureDebut() {
        return this.heureDebut;
    }

    /**
     * Récupère l'heure de fin du travail de l'employé.
     *
     * @return L'heure de fin du travail de l'employé.
     */
    public int getHeureFin() {
        return heureFin;
    }

    /**
     * Définit le nom de l'employé.
     *
     * @param nom Le nouveau nom de l'employé.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Définit le prénom de l'employé.
     *
     * @param prenom Le nouveau prénom de l'employé.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Définit l'email de l'employé.
     *
     * @param email Le nouvel email de l'employé.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Définit le numéro de téléphone de l'employé.
     *
     * @param telephone Le nouveau numéro de téléphone de l'employé.
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Définit l'heure de début du travail de l'employé.
     *
     * @param heureDebut La nouvelle heure de début du travail de l'employé.
     */
    public void setHeureDebut(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    /**
     * Définit l'heure de fin du travail de l'employé.
     *
     * @param heureFin La nouvelle heure de fin du travail de l'employé.
     */
    public void setHeureFin(int heureFin) {
        this.heureFin = heureFin;
    }
}