package fr.uga.iut2.genevent.modele;

public abstract class Employee {
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String horaires;

    public Employee(String nom, String prenom, String email, String telephone, String horaires) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.horaires = horaires;
    }

    public String getHoraires() {
        return this.horaires;
    }

    public void setHoraires(String horaires) {
        this.horaires = horaires;
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
}
