package fr.uga.iut2.genevent.modele;

import java.io.Serializable;

public class AgentSecurite extends Employe implements Serializable {
    private Zone zone;

    public AgentSecurite(String nom, String prenom, String email, String telephone, int heureDebut, int heureFin, Zone zone) {
        super(nom, prenom, email, telephone, heureDebut, heureFin);
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
