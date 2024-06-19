package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantTest {

    @Test
    void getNom() {
        Participant participant = new Participant("nom", "prenom", "email");
        assertEquals("nom", participant.getNom());
    }

    @Test
    void getPrenom() {
        Participant participant = new Participant("nom", "prenom", "email");
        assertEquals("prenom", participant.getPrenom());
    }

    @Test
    void getEmail() {
        Participant participant = new Participant("nom", "prenom", "email");
        assertEquals("email", participant.getEmail());
    }

    @Test
    void setNom() {
        Participant participant = new Participant("nom", "prenom", "email");
        participant.setNom("nouveau nom");
        assertEquals("nouveau nom", participant.getNom());
    }

    @Test
    void setPrenom() {
        Participant participant = new Participant("nom", "prenom", "email");
        participant.setPrenom("nouveau prenom");
        assertEquals("nouveau prenom", participant.getPrenom());
    }

    @Test
    void setEmail() {
        Participant participant = new Participant("nom", "prenom", "email");
        participant.setEmail("nouveau email");
        assertEquals("nouveau email", participant.getEmail());
    }
}