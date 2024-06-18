package fr.uga.iut2.genevent.modele;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Animation {
    private String nom;
    private float prix;
    private LocalDateTime dateHeureDebut;
    private LocalDateTime dateHeureFin;

    public Animation(String nom, float prix, LocalDateTime dateDebut, LocalDateTime dateFin) {
        this.nom = nom;
        this.prix = prix;
        this.dateHeureDebut = dateDebut;
        this.dateHeureFin = dateFin;
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

    public LocalDateTime getDateHeureDebut() {
        return dateHeureDebut;
    }

    public void setDateHeureDebut(LocalDateTime dateHeureDebut) {
        this.dateHeureDebut = dateHeureDebut;
    }

    public LocalDateTime getDateHeureFin() {
        return dateHeureFin;
    }

    public void setDateHeureFin(LocalDateTime dateHeureFin) {
        this.dateHeureFin = dateHeureFin;
    }
}
