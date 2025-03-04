package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LieuTest {

    @Test
    void getNom() {
        Lieu lieu = new Lieu("nom", "adresse", "ville", 12345);
        assertEquals("nom", lieu.getNom());
    }

    @Test
    void getAdresse() {
        Lieu lieu = new Lieu("nom", "adresse", "ville", 12345);
        assertEquals("adresse", lieu.getAdresse());
    }

    @Test
    void getVille() {
        Lieu lieu = new Lieu("nom", "adresse", "ville", 12345);
        lieu.setVille("ville");
        assertEquals("ville", lieu.getVille());
    }

    @Test
    void getCodePostal() {
        Lieu lieu = new Lieu("nom", "adresse", "ville", 12345);
        lieu.setCodePostal(12345);
        assertEquals(12345, lieu.getCodePostal());
    }

    @Test
    void setNom() {
        Lieu lieu = new Lieu("nom", "adresse", "ville", 12345);
        lieu.setNom("nouveau nom");
        assertEquals("nouveau nom", lieu.getNom());
    }

    @Test
    void setAdresse() {
        Lieu lieu = new Lieu("nom", "adresse", "ville", 12345);
        lieu.setAdresse("nouvelle adresse");
        assertEquals("nouvelle adresse", lieu.getAdresse());
    }
}