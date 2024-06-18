package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;

public class Emplacement {
    private int numero;
    private int taille;
    private ArrayList<Commercant> commercants = new ArrayList<>();

    public Emplacement(int numero, int taille) {
        this.numero = numero;
        this.taille = taille;
    }

    public ArrayList<Commercant> getCommercants() {
        return commercants;
    }

    public void addCommercant(Commercant commercant) {
        this.commercants.add(commercant);
        commercant.setEmplacement(this);
    }

    public void removeCommercant(Commercant commercant) {
        this.commercants.remove(commercant);
        commercant.setEmplacement(null);
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    @Override
    public String toString() {
        return String.valueOf(numero);
    }
}
