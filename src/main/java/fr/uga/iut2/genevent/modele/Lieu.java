package fr.uga.iut2.genevent.modele;

public class Lieu {
    private String nom;
    private String adresse;
    private String codePostal;

    public Lieu(String nom, String adresse, String codePostal) {
        this.nom = nom;
        this.adresse = adresse;
        this.codePostal = codePostal;
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

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    public String getCodePostal() {
        return this.codePostal;
    }
}
