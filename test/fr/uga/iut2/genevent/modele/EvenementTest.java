package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import javax.lang.model.element.TypeElement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class EvenementTest {

    @Test
    void inscrireCommercant() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>(
                Arrays.asList(Fonctionnalite.ANIMATION, Fonctionnalite.AGENT_SECURITE)
        ));
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        e.inscrireCommercant(c);
        assertEquals(1, e.getCommercants().size());
    }

    @Test
    void desinscrireCommercant() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>(
                Arrays.asList(Fonctionnalite.ANIMATION, Fonctionnalite.AGENT_SECURITE)
        ));
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        e.inscrireCommercant(c);
        e.desinscrireCommercant(c);
        assertEquals(0, e.getCommercants().size());
    }

    @Test
    void ajouterEmplacement() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>(
                Arrays.asList(Fonctionnalite.ANIMATION, Fonctionnalite.AGENT_SECURITE)
        ));
        Emplacement em = new Emplacement(1, 10);
        e.ajouterEmplacement(em);
        assertEquals(1, e.getEmplacements().size());
    }

    @Test
    void retirerEmplacement() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>(
                Arrays.asList(Fonctionnalite.ANIMATION, Fonctionnalite.AGENT_SECURITE)
        ));
        Emplacement em = new Emplacement(1, 10);
        e.ajouterEmplacement(em);
        e.retirerEmplacement(em);
        assertEquals(0, e.getEmplacements().size());
    }

    @Test
    void ajouterTypeCommerce() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>(
                Arrays.asList(Fonctionnalite.ANIMATION, Fonctionnalite.AGENT_SECURITE)
        ));
        e.ajouterTypeCommerce(new TypeCommerce("nom"), 1);
        assertEquals(1, e.getTypeCommerces().size());
    }

    @Test
    void retirerTypeCommerce() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>(
                Arrays.asList(Fonctionnalite.ANIMATION, Fonctionnalite.AGENT_SECURITE)
        ));
        TypeCommerce tc = new TypeCommerce("nom");
        e.ajouterTypeCommerce(tc, 1);
        e.retirerTypeCommerce(tc);
        assertEquals(0, e.getTypeCommerces().size());
    }

    @Test
    void ajouterAgentSecurite() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>(
                Arrays.asList(Fonctionnalite.ANIMATION, Fonctionnalite.AGENT_SECURITE)
        ));
        AgentSecurite a = new AgentSecurite("nom", "prenom", "email", "telephone", 8, 18, null);
        e.ajouterAgentSecurite(a);
        assertEquals(1, e.getAgentsSecurite().size());
    }

    @Test
    void supprimerAgentSecurite() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>(
                Arrays.asList(Fonctionnalite.ANIMATION, Fonctionnalite.AGENT_SECURITE)
        ));
        AgentSecurite a = new AgentSecurite("nom", "prenom", "email", "telephone", 8, 18, null);
        e.ajouterAgentSecurite(a);
        e.supprimerAgentSecurite(a);
        assertEquals(0, e.getAgentsSecurite().size());
    }

    @Test
    void removeZone() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>(
                Arrays.asList(Fonctionnalite.ANIMATION, Fonctionnalite.AGENT_SECURITE)
        ));
        Zone z = new Zone("nom");
        e.getZones().add(z);
        e.removeZone(z);
        assertEquals(0, e.getZones().size());
    }

    @Test
    void ajouterAgentEntretien() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>(
                Arrays.asList(Fonctionnalite.ANIMATION, Fonctionnalite.AGENT_SECURITE)
        ));
        AgentEntretien a = new AgentEntretien("nom", "prenom", "email", "telephone", 8, 18);
        e.ajouterAgentEntretien(a);
        assertEquals(1, e.getAgentsEntretien().size());
    }

    @Test
    void supprimerAgentEntretien() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>(
                Arrays.asList(Fonctionnalite.ANIMATION, Fonctionnalite.AGENT_SECURITE)
        ));
        AgentEntretien a = new AgentEntretien("nom", "prenom", "email", "telephone", 8, 18);
        e.ajouterAgentEntretien(a);
        e.supprimerAgentEntretien(a);
        assertEquals(0, e.getAgentsEntretien().size());
    }

    @Test
    void inscrireParticipant() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>(
                Arrays.asList(Fonctionnalite.AGENT_SECURITE)
        ));
        Participant p = new Participant("nom", "prenom", "email");
        e.inscrireParticipant(p);
        assertEquals(1, e.getParticipants().size());
    }

    @Test
    void desinscrireParticipant() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>(
                Arrays.asList(Fonctionnalite.AGENT_SECURITE)
        ));
        Participant p = new Participant("nom", "prenom", "email");
        e.inscrireParticipant(p);
        e.desinscrireParticipant(p);
        assertEquals(0, e.getParticipants().size());
    }

    @Test
    void ajouterAnimation() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        LocalDateTime date2 = LocalDateTime.of(2021, 1, 1, 10, 0);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>());
        Animation a = new Animation("nom", 10, date2, date2);
        e.ajouterAnimation(a);
        assertEquals(1, e.getAnimations().size());
    }

    @Test
    void retirerAnimation() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        LocalDateTime date2 = LocalDateTime.of(2021, 1, 1, 10, 0);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>());
        Animation a = new Animation("nom", 10, date2, date2);
        e.ajouterAnimation(a);
        e.retirerAnimation(a);
        assertEquals(0, e.getAnimations().size());
    }

    @Test
    void getNom() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>());
        assertEquals("nom", e.getNom());
    }

    @Test
    void setNom() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>());
        e.setNom("nom2");
        assertEquals("nom2", e.getNom());
    }

    @Test
    void getDateDebut() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>());
        assertEquals(date, e.getDateDebut());
    }

    @Test
    void setDateDebut() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>());
        LocalDate date2 = LocalDate.of(2021, 1, 2);
        e.setDateDebut(date2);
        assertEquals(date2, e.getDateDebut());
    }

    @Test
    void getDateFin() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>());
        assertEquals(date, e.getDateFin());
    }

    @Test
    void setDateFin() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>());
        LocalDate date2 = LocalDate.of(2021, 1, 2);
        e.setDateFin(date2);
        assertEquals(date2, e.getDateFin());
    }

    @Test
    void getFonctionnalites() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, new ArrayList<Fonctionnalite>(
                Arrays.asList(Fonctionnalite.AGENT_SECURITE)
        ));
        assertEquals(1, e.getFonctionnalites().size());
    }

    @Test
    void setFonctionnalites() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, null);
        e.setFonctionnalites(new ArrayList<Fonctionnalite>(
                Arrays.asList(Fonctionnalite.AGENT_SECURITE)
        ));
        assertEquals(1, e.getFonctionnalites().size());
    }

    @Test
    void getMaxParticipants() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, null);
        assertEquals(0, e.getMaxParticipants());
    }

    @Test
    void setMaxParticipants() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, null);
        e.setMaxParticipants(10);
        assertEquals(10, e.getMaxParticipants());
    }

    @Test
    void getType() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, TypeEvenement.BROCANTE, null);
        assertEquals(TypeEvenement.BROCANTE, e.getType());
    }

    @Test
    void setType() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Evenement e = new Evenement("nom", date, date, null, null);
        e.setType(TypeEvenement.BROCANTE);
        assertEquals(TypeEvenement.BROCANTE, e.getType());
    }

    @Test
    void getLieu() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Lieu l = new Lieu("nom", "adresse");
        Evenement e = new Evenement("nom", date, date, null, null);
        e.setLieu(l);
        assertEquals(l, e.getLieu());
    }

    @Test
    void setLieu() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Lieu l = new Lieu("nom", "adresse");
        Evenement e = new Evenement("nom", date, date, null, null);
        e.setLieu(l);
        assertEquals(l, e.getLieu());
    }

    @Test
    void getTypeCommerces() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        TypeCommerce tc = new TypeCommerce("nom");
        Evenement e = new Evenement("nom", date, date, null, null);
        e.ajouterTypeCommerce(tc, 1);
        assertEquals(1, e.getTypeCommerces().size());
    }

    @Test
    void getCommercants() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        Evenement e = new Evenement("nom", date, date, null, null);
        e.inscrireCommercant(c);
        assertEquals(1, e.getCommercants().size());
    }

    @Test
    void getEmplacements() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Emplacement em = new Emplacement(1, 10);
        Evenement e = new Evenement("nom", date, date, null, null);
        e.ajouterEmplacement(em);
        assertEquals(1, e.getEmplacements().size());
    }

    @Test
    void getAgentsSecurite() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        AgentSecurite a = new AgentSecurite("nom", "prenom", "email", "telephone", 8, 18, null);
        Evenement e = new Evenement("nom", date, date, null, null);
        e.ajouterAgentSecurite(a);
        assertEquals(1, e.getAgentsSecurite().size());
    }

    @Test
    void getZones() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Zone z = new Zone("nom");
        Evenement e = new Evenement("nom", date, date, null, null);
        e.getZones().add(z);
        assertEquals(1, e.getZones().size());
    }

    @Test
    void getAgentsEntretien() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        AgentEntretien a = new AgentEntretien("nom", "prenom", "email", "telephone", 8, 18);
        Evenement e = new Evenement("nom", date, date, null, null);
        e.ajouterAgentEntretien(a);
        assertEquals(1, e.getAgentsEntretien().size());
    }

    @Test
    void getParticipants() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        Participant p = new Participant("nom", "prenom", "email");
        Evenement e = new Evenement("nom", date, date, null, null);
        e.inscrireParticipant(p);
        assertEquals(1, e.getParticipants().size());
    }

    @Test
    void getAnimations() {
        LocalDate date = LocalDate.of(2021, 1, 1);
        LocalDateTime date2 = LocalDateTime.of(2021, 1, 1, 10, 0);
        Animation a = new Animation("nom", 10, date2, date2);
        Evenement e = new Evenement("nom", date, date, null, null);
        e.ajouterAnimation(a);
        assertEquals(1, e.getAnimations().size());
    }
}