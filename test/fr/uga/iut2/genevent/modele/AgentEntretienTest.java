package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgentEntretienTest {


    @Test
    void getNom() {
        assertEquals("nom", new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2).getNom());
    }

    @Test
    void getPrenom() {
        assertEquals("prenom", new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2).getPrenom());
    }

    @Test
    void getEmail() {
        assertEquals("email", new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2).getEmail());
    }

    @Test
    void getTelephone() {
        assertEquals("telephone", new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2).getTelephone());
    }

    @Test
    void getHeureDebut() {
        assertEquals(1, new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2).getHeureDebut());
    }

    @Test
    void getHeureFin() {
        assertEquals(2, new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2).getHeureFin());
    }


    @Test
    void setNom() {
        AgentEntretien agent = new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2);
        agent.setNom("nouveauNom");
        assertEquals("nouveauNom", agent.getNom());
    }

    @Test
    void setPrenom() {
        AgentEntretien agent = new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2);
        agent.setPrenom("nouveauPrenom");
        assertEquals("nouveauPrenom", agent.getPrenom());
    }

    @Test
    void setEmail() {
        AgentEntretien agent = new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2);
        agent.setEmail("nouveauEmail");
        assertEquals("nouveauEmail", agent.getEmail());
    }

    @Test
    void setTelephone() {
        AgentEntretien agent = new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2);
        agent.setTelephone("nouveauTelephone");
        assertEquals("nouveauTelephone", agent.getTelephone());
    }

    @Test
    void setHeureDebut() {
        AgentEntretien agent = new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2);
        agent.setHeureDebut(3);
        assertEquals(3, agent.getHeureDebut());
    }

    @Test
    void setHeureFin() {
        AgentEntretien agent = new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2);
        agent.setHeureFin(3);
        assertEquals(3, agent.getHeureFin());
    }

    //et pour quand ça marche pas :

    @Test
    void setHeureFinFail() {
        AgentEntretien agent = new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2);
        assertThrows(IllegalArgumentException.class, () -> agent.setHeureFin(0));
    }

    @Test
    void setHeureDebutFail() {
        AgentEntretien agent = new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2);
        assertThrows(IllegalArgumentException.class, () -> agent.setHeureDebut(3));
    }

    @Test
    void setNomFail() {
        AgentEntretien agent = new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2);
        assertThrows(IllegalArgumentException.class, () -> agent.setNom(""));
    }

    @Test
    void setPrenomFail() {
        AgentEntretien agent = new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2);
        assertThrows(IllegalArgumentException.class, () -> agent.setPrenom(""));
    }

    @Test
    void setEmailFail() {
        AgentEntretien agent = new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2);
        assertThrows(IllegalArgumentException.class, () -> agent.setEmail(""));
    }

    @Test
    void setTelephoneFail() {
        AgentEntretien agent = new AgentEntretien("nom", "prenom", "email", "telephone", 1, 2);
        assertThrows(IllegalArgumentException.class, () -> agent.setTelephone(""));
    }
}