package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;

/**
 * La classe Emplacement représente un emplacement avec un numéro, une taille et
 * une liste de commerçants.
 */
public class Emplacement {
    private int numero;
    private int taille;
    private ArrayList<Commercant> commercants = new ArrayList<>();

    /**
     * Constructeur de la classe Emplacement.
     *
     * @param numero Le numéro de l'emplacement.
     * @param taille La taille de l'emplacement.
     */
    public Emplacement(int numero, int taille) {
        this.numero = numero;
        this.taille = taille;
    }

    /**
     * Récupère la liste des commerçants de l'emplacement.
     *
     * @return La liste des commerçants de l'emplacement.
     */
    public ArrayList<Commercant> getCommercants() {
        return commercants;
    }

    /**
     * Ajoute un commerçant à l'emplacement.
     *
     * @param commercant Le commerçant à ajouter à l'emplacement.
     */
    public void addCommercant(Commercant commercant) {
        this.commercants.add(commercant);
        commercant.setEmplacement(this);
    }

    /**
     * Supprime un commerçant de l'emplacement.
     *
     * @param commercant Le commerçant à supprimer de l'emplacement.
     */
    public void removeCommercant(Commercant commercant) {
        this.commercants.remove(commercant);
        commercant.setEmplacement(null);
    }

    /**
     * Récupère le numéro de l'emplacement.
     *
     * @return Le numéro de l'emplacement.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Définit le numéro de l'emplacement.
     *
     * @param numero Le nouveau numéro de l'emplacement.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Récupère la taille de l'emplacement.
     *
     * @return La taille de l'emplacement.
     */
    public int getTaille() {
        return taille;
    }

    /**
     * Définit la taille de l'emplacement.
     *
     * @param taille La nouvelle taille de l'emplacement.
     */
    public void setTaille(int taille) {
        this.taille = taille;
    }
}