package fr.uga.iut2.genevent.modele;

/**
 * Classe représentant un agent de sécurité, héritant de la classe Employe.
 */
public class AgentSecurite extends Employe {
    private Zone zone;

    /**
     * Constructeur de la classe AgentSecurite.
     *
     * @param nom Le nom de l'agent de sécurité.
     * @param prenom Le prénom de l'agent de sécurité.
     * @param email L'email de l'agent de sécurité.
     * @param telephone Le numéro de téléphone de l'agent de sécurité.
     * @param heureDebut L'heure de début du travail de l'agent de sécurité.
     * @param heureFin L'heure de fin du travail de l'agent de sécurité.
     * @param zone La zone de travail de l'agent de sécurité.
     */
    public AgentSecurite(String nom, String prenom, String email, String telephone, int heureDebut, int heureFin, Zone zone) {
        super(nom, prenom, email, telephone, heureDebut, heureFin);
        setZone(zone);
    }

    /**
     * Récupère la zone de travail de l'agent de sécurité.
     *
     * @return La zone de travail de l'agent de sécurité.
     */
    public Zone getZone() {
        return zone;
    }

    /**
     * Définit la zone de travail de l'agent de sécurité.
     *
     * @param zone La nouvelle zone de travail de l'agent de sécurité.
     */
    public void setZone(Zone zone) {
        if (this.zone != null && this.zone.getAgentsSecurites().contains(this)) {
            this.zone.removeAgentSecurite(this);
        }
        this.zone = zone;
        if (this.zone != null && !this.zone.getAgentsSecurites().contains(this)) {
            this.zone.addAgentSecurite(this);
        }
    }
}
