package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgentSecuriteTest {

    @Test
    void getNom() {
        assertEquals("nom", new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1")).getNom());
    }

    @Test
    void getPrenom() {
        assertEquals("prenom", new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1")).getPrenom());
    }

    @Test
    void getEmail() {
        assertEquals("email", new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1")).getEmail());
    }

    @Test
    void getTelephone() {
        assertEquals("telephone", new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1")).getTelephone());
    }

    @Test
    void getHeureDebut() {
        assertEquals(1, new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1")).getHeureDebut());
    }

    @Test
    void getHeureFin() {
        assertEquals(2, new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1")).getHeureFin());
    }

    @Test
    void setNom() {
        AgentSecurite agent = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1"));
        agent.setNom("nouveauNom");
        assertEquals("nouveauNom", agent.getNom());
    }

    @Test
    void setPrenom() {
        AgentSecurite agent = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1"));
        agent.setPrenom("nouveauPrenom");
        assertEquals("nouveauPrenom", agent.getPrenom());
    }

    @Test
    void setEmail() {
        AgentSecurite agent = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1"));
        agent.setEmail("nouveauEmail");
        assertEquals("nouveauEmail", agent.getEmail());
    }

    @Test
    void setTelephone() {
        AgentSecurite agent = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1"));
        agent.setTelephone("nouveauTelephone");
        assertEquals("nouveauTelephone", agent.getTelephone());
    }

    @Test
    void setHeureDebut() {
        AgentSecurite agent = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1"));
        agent.setHeureDebut(3);
        assertEquals(3, agent.getHeureDebut());
    }

    @Test
    void setHeureFin() {
        AgentSecurite agent = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1"));
        agent.setHeureFin(3);
        assertEquals(3, agent.getHeureFin());
    }

    @Test
    void getZone() {
        AgentSecurite agent = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1"));
        assertEquals("zone1", agent.getZone().getNom());
    }

    @Test
    void setZone() {
        AgentSecurite agent = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1"));
        agent.setZone(new Zone("zone2"));
        assertEquals("zone2", agent.getZone().getNom());
    }

    // tests avec des problèmes :
    @Test
    void setNomFail() {
        AgentSecurite agent = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1"));
        agent.setNom("nouveauNom");
        assertEquals("nom", agent.getNom());
    }

    @Test
    void setPrenomFail() {
        AgentSecurite agent = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1"));
        agent.setPrenom("nouveauPrenom");
        assertEquals("prenom", agent.getPrenom());
    }

    @Test
    void setEmailFail() {
        AgentSecurite agent = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1"));
        agent.setEmail("nouveauEmail");
        assertEquals("email", agent.getEmail());
    }

    @Test
    void setTelephoneFail() {
        AgentSecurite agent = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1"));
        agent.setTelephone("nouveauTelephone");
        assertEquals("telephone", agent.getTelephone());
    }

    @Test
    void setHeureDebutFail() {
        AgentSecurite agent = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1"));
        agent.setHeureDebut(3);
        assertEquals(1, agent.getHeureDebut());
    }

    @Test
    void setHeureFinFail() {
        AgentSecurite agent = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1"));
        agent.setHeureFin(3);
        assertEquals(2, agent.getHeureFin());
    }

    @Test
    void setZoneFail() {
        AgentSecurite agent = new AgentSecurite("nom", "prenom", "email",
                "telephone", 1, 2, new Zone("zone1"));
        agent.setZone(new Zone("zone2"));
        System.out.println(agent.getZone().getNom());
        assertEquals("zone1", agent.getZone().getNom());
    }
}