package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;

public class AgentSecurite extends Employee {
    private Zone zone;

    public AgentSecurite(String nom, String prenom, String email, String telephone, String horaires, Zone zone) {
        super(nom, prenom, email, telephone, horaires);
        this.zone = zone;
        this.zone.addAgentSecurite(this);
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        if (this.zone != null) {
            this.zone.removeAgentSecurite(this);
        }
        this.zone = zone;
        if (zone != null) {
            zone.addAgentSecurite(this);
        }
    }
}
