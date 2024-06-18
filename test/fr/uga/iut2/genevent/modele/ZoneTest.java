package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZoneTest {

    @Test
    void addAgentSecurite() {
        Zone zone = new Zone("zone1");
        AgentSecurite agentSecurite = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, zone);
        zone.addAgentSecurite(agentSecurite);
        assertTrue(zone.getAgentsSecurites().contains(agentSecurite));
        assertEquals(zone, agentSecurite.getZone());
    }

    @Test
    void removeAgentSecurite() {
        Zone zone = new Zone("zone1");
        AgentSecurite agentSecurite = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, zone);
        zone.addAgentSecurite(agentSecurite);
        zone.removeAgentSecurite(agentSecurite);
        assertFalse(zone.getAgentsSecurites().contains(agentSecurite));
        assertNull(agentSecurite.getZone());
    }

    @Test
    void getAgentsSecurites() {
        Zone zone = new Zone("zone1");
        AgentSecurite agentSecurite = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, zone);
        zone.addAgentSecurite(agentSecurite);
        assertTrue(zone.getAgentsSecurites().contains(agentSecurite));
    }

    @Test
    void getNom() {
        assertEquals("zone1", new Zone("zone1").getNom());
    }

    @Test
    void setNom() {
        Zone zone = new Zone("zone1");
        zone.setNom("nouveauNom");
        assertEquals("nouveauNom", zone.getNom());
    }

    // tests avec des problèmes

    @Test
    void addAgentSecuriteFail() {
        Zone zone = new Zone("zone1");
        AgentSecurite agentSecurite = null;
        zone.addAgentSecurite(agentSecurite);
        assertTrue(zone.getAgentsSecurites().contains(agentSecurite));
        assertThrows(NullPointerException.class, () -> agentSecurite.getZone());
    }

    @Test
    void removeAgentSecuriteFail() {
        Zone zone = new Zone("zone1");
        AgentSecurite agentSecurite = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, zone);
        zone.addAgentSecurite(agentSecurite);
        agentSecurite = null;
        AgentSecurite finalAgentSecurite = agentSecurite;
        assertThrows(NullPointerException.class, () -> zone.removeAgentSecurite(finalAgentSecurite));
        assertFalse(zone.getAgentsSecurites().contains(agentSecurite));
        assertThrows(NullPointerException.class, () -> finalAgentSecurite.getZone());
    }

    @Test
    void setNomFail() {
        Zone zone = new Zone("zone1");
        zone.setNom("nouveauNom");
        assertEquals("zone1", zone.getNom());
    }
}