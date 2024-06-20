package fr.uga.iut2.genevent.modele;

/**
 * Classe représentant un agent d'entretien héritant de la classe Employe
 */
public class AgentEntretien extends Employe {

    /**
     * Constructeur de la classe AgentEntretien
     * 
     * @param nom        nom de l'agent d'entretien
     * @param prenom     prénom de l'agent d'entretien
     * @param email      email de l'agent d'entretien
     * @param telephone  téléphone de l'agent d'entretien
     * @param heureDebut heure de début de l'agent d'entretien
     * @param heureFin   heure de fin de l'agent d'entretien
     */
    public AgentEntretien(String nom, String prenom, String email, String telephone, int heureDebut, int heureFin) {
        super(nom, prenom, email, telephone, heureDebut, heureFin);
    }
}
