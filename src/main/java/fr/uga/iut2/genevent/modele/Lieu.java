package fr.uga.iut2.genevent.modele;

import java.io.Serializable;

/**
 * La classe Lieu représente un lieu avec son nom, adresse, ville et code
 * postal.
 */
public class Lieu implements Serializable {
    private String nom;
    private String adresse;
    private String ville;
    private int codePostal;

    /**
     * Constructeur pour la classe Lieu.
     *
     * @param nom     Le nom du lieu.
     * @param adresse L'adresse du lieu.
     */
    public Lieu(String nom, String adresse) {
        setNom(nom);
        setAdresse(adresse);
    }

    /**
     * Permet d'obtenir le nom du lieu.
     *
     * @return Le nom du lieu.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Permet d'obtenir l'adresse du lieu.
     *
     * @return L'adresse du lieu.
     */
    public String getAdresse() {
        return this.adresse;
    }

    /**
     * Permet d'obtenir la ville du lieu.
     *
     * @return La ville du lieu.
     */
    public String getVille() {
        return ville;
    }

    /**
     * Permet d'obtenir le code postal du lieu.
     *
     * @return Le code postal du lieu.
     */
    public int getCodePostal() {
        return codePostal;
    }

    /**
     * Définit le nom du lieu.
     *
     * @param nom Le nom du lieu.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Définit l'adresse du lieu.
     *
     * @param adresse L'adresse du lieu.
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Définit la ville du lieu.
     *
     * @param ville La ville du lieu.
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * Définit le code postal du lieu.
     *
     * @param codePostal Le code postal du lieu.
     */
    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }
}
