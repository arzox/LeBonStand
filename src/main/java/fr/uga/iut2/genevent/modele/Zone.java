package fr.uga.iut2.genevent.modele;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * La classe Zone représente une zone avec un nom et une liste d'agents de
 * sécurité assignés.
 */
public class Zone implements Serializable {
    private String nom;
    private ArrayList<AgentSecurite> agentsSecurites = new ArrayList<>();

    /**
     * Constructeur pour la classe Zone.
     *
     * @param nom Le nom de la zone.
     */
    public Zone(String nom) {
        this.nom = nom;
    }

    /**
     * Ajoute un agent de sécurité à la liste des agents de sécurité de cette zone.
     *
     * @param agentSecurite L'agent de sécurité à ajouter.
     */
    public void addAgentSecurite(AgentSecurite agentSecurite) {
        if (agentSecurite != null) {
            this.agentsSecurites.add(agentSecurite);
            if (agentSecurite.getZone() == null || !agentSecurite.getZone().equals(this)) {
                agentSecurite.setZone(this);
            }
        }
    }

    /**
     * Retire un agent de sécurité de la liste des agents de sécurité de cette zone.
     *
     * @param agentSecurite L'agent de sécurité à retirer.
     */
    public void removeAgentSecurite(AgentSecurite agentSecurite) {
        if (agentSecurite != null) {
            if (agentSecurite.getZone() != null) {
                this.agentsSecurites.remove(agentSecurite);
                if (agentSecurite.getZone().equals(this)) {
                    agentSecurite.setZone(null);
                }
            }
        }
    }

    /**
     * Obtient la liste des agents de sécurité assignés à cette zone.
     *
     * @return La liste des agents de sécurité.
     */
    public ArrayList<AgentSecurite> getAgentsSecurites() {
        return agentsSecurites;
    }

    /**
     * Obtient le nom de la zone.
     *
     * @return Le nom de la zone.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de la zone.
     *
     * @param nom Le nom de la zone.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
