package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;

public class Zone {
    private String nom;
    private ArrayList<AgentSecurite> agentsSecurites = new ArrayList<>();

    public Zone(String nom) {
        this.nom = nom;
    }

    public void addAgentSecurite(AgentSecurite agentSecurite) {
        this.agentsSecurites.add(agentSecurite);
        agentSecurite.setZone(this);
    }

    public void removeAgentSecurite(AgentSecurite agentSecurite) {
        this.agentsSecurites.remove(agentSecurite);
        agentSecurite.setZone(null);
    }

    public ArrayList<AgentSecurite> getAgentsSecurites() {
        return agentsSecurites;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
