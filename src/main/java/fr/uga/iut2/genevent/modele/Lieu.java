package fr.uga.iut2.genevent.modele;

public class Lieu {
    private String nom;
    private String adresse;

    public Lieu(String nom, String adresse) {
        this.nom = nom;
        this.adresse = adresse;
    }

    public String getNom() {
        return this.nom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
