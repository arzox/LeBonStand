package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;

public class TypeCommerce {
    private String nom;
    private ArrayList<Commercant> commercants = new ArrayList<>();

    public TypeCommerce(String nom) {
        this.nom = nom;
    }

    public void addCommercant(Commercant commercant) {
        this.commercants.add(commercant);
        commercant.setTypeCommerce(this);
    }

    public void removeCommercant(Commercant commercant) {
        this.commercants.remove(commercant);
        commercant.setTypeCommerce(null);
    }

    public ArrayList<Commercant> getCommercants() {
        return commercants;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
