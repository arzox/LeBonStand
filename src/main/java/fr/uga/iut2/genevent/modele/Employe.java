package fr.uga.iut2.genevent.modele;

import java.io.Serializable;

public abstract class Employe implements Serializable {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private int heureDebut;
    private int heureFin;

    public Employe(String nom, String prenom, String email, String telephone, int heureDebut, int heureFin) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getEmail() {
        return this.email;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public int getHeureDebut() {
        return this.heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setHeureDebut(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    public void setHeureFin(int heureFin) {
        this.heureFin = heureFin;
    }
}
