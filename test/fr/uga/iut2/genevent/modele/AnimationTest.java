package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class AnimationTest {

    @Test
    void getNom() {
        assertEquals("nom", new Animation("nom", 1, null, null).getNom());
    }

    @Test
    void setNom() {
        Animation animation = new Animation("nom", 1, null, null);
        animation.setNom("nouveauNom");
        assertEquals("nouveauNom", animation.getNom());
    }

    @Test
    void getPrix() {
        assertEquals(1, new Animation("nom", 1, null, null).getPrix());
    }

    @Test
    void setPrix() {
        Animation animation = new Animation("nom", 1, null, null);
        animation.setPrix(2);
        assertEquals(2, animation.getPrix());
    }

    @Test
    void getDateHeureDebut() {
        Animation animation = new Animation("nom", 1, null, null);
        assertNull(animation.getDateHeureDebut());
    }

    @Test
    void setDateHeureDebut() {
        Animation animation = new Animation("nom", 1, null, null);
        animation.setDateHeureDebut(null);
        assertNull(animation.getDateHeureDebut());
    }

    @Test
    void getDateHeureFin() {
        Animation animation = new Animation("nom", 1, null, null);
        assertNull(animation.getDateHeureFin());
    }

    @Test
    void setDateHeureFin() {
        Animation animation = new Animation("nom", 1, null, null);
        animation.setDateHeureFin(null);
        assertNull(animation.getDateHeureFin());
    }

    // tests avec des problèmes

    @Test
    void setNomFail() {
        Animation animation = new Animation("nom", 1, null, null);
        assertDoesNotThrow(() -> animation.setNom(null));
        assertNull(animation.getNom());
    }

    @Test
    void setPrixFail() {
        // le but est que le test ne soit pas passé
        Animation animation = new Animation("nom", 1, null, null);
        assertDoesNotThrow(() -> animation.setPrix(-1));
        assertEquals(-1, animation.getPrix());
    }

    @Test
    void setDateHeureDebutFail() {
        Animation animation = new Animation("nom", 1, null, null);
        animation.setDateHeureDebut(null);
        assertNotNull(animation.getDateHeureDebut());
    }

    @Test
    void setDateHeureFinFail() {
        Animation animation = new Animation("nom", 1, null, null);
        animation.setDateHeureFin(null);
        assertNotNull(animation.getDateHeureFin());
    }
}