package fr.uga.iut2.genevent.modele;

/**
 * La classe Participant représente un participant avec un nom, un prénom et un email.
 */
public class Participant {
    private String nom;
    private String prenom;
    private String email;

    /**
     * Constructeur de la classe Participant.
     *
     * @param nom Le nom du participant.
     * @param prenom Le prénom du participant.
     * @param email L'email du participant.
     */
    public Participant(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    /**
     * Récupère le nom du participant.
     *
     * @return Le nom du participant.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Récupère le prénom du participant.
     *
     * @return Le prénom du participant.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Récupère l'email du participant.
     *
     * @return L'email du participant.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Définit le nom du participant.
     *
     * @param nom Le nouveau nom du participant.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Définit le prénom du participant.
     *
     * @param prenom Le nouveau prénom du participant.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Définit l'email du participant.
     *
     * @param email Le nouvel email du participant.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}