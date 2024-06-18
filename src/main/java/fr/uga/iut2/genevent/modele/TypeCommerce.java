package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;

/**
 * La classe TypeCommerce représente un type de commerce avec un nom et une liste de commerçants associés.
 */
public class TypeCommerce {
    private String nom;
    private ArrayList<Commercant> commercants = new ArrayList<>();

    /**
     * Constructeur pour la classe TypeCommerce.
     *
     * @param nom Le nom du type de commerce.
     */
    public TypeCommerce(String nom) {
        this.nom = nom;
    }

    /**
     * Ajoute un commerçant à la liste des commerçants de ce type de commerce.
     *
     * @param commercant Le commerçant à ajouter.
     */
    public void addCommercant(Commercant commercant) {
        this.commercants.add(commercant);
        commercant.setTypeCommerce(this);
    }

    /**
     * Retire un commerçant de la liste des commerçants de ce type de commerce.
     *
     * @param commercant Le commerçant à retirer.
     */
    public void removeCommercant(Commercant commercant) {
        this.commercants.remove(commercant);
        commercant.setTypeCommerce(null);
    }

    /**
     * Obtient la liste des commerçants de ce type de commerce.
     *
     * @return La liste des commerçants.
     */
    public ArrayList<Commercant> getCommercants() {
        return commercants;
    }

    /**
     * Obtient le nom du type de commerce.
     *
     * @return Le nom du type de commerce.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Définit le nom du type de commerce.
     *
     * @param nom Le nom du type de commerce.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
}
