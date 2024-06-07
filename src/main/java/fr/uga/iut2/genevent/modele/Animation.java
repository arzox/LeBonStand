package fr.uga.iut2.genevent.modele;

public class Animation {
    private String nom;
    private float price;
    private String debut;
    private String fin;

    public Animation(String nom, float price, String debut, String fin) {
        this.nom = nom;
        this.price = price;
        this.debut = debut;
        this.fin = fin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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
