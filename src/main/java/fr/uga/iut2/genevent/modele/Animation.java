package fr.uga.iut2.genevent.modele;

public class Animation {
    private String nom;
    private float prix;
    private String debut;
    private String fin;

    public Animation(String nom, float prix, String debut, String fin) {
        this.nom = nom;
        this.prix = prix;
        this.debut = debut;
        this.fin = fin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }
}
