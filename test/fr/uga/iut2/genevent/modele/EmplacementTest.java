package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmplacementTest {

    @Test
    void getCommercants() {
        Emplacement e = new Emplacement(1, 10);
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        e.addCommercant(c);
        assertEquals(1, e.getCommercants().size());
    }

    @Test
    void removeCommercant() {
        Emplacement e = new Emplacement(1, 10);
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        e.addCommercant(c);
        e.removeCommercant(c);
        assertEquals(0, e.getCommercants().size());
    }

    @Test
    void getNumero() {
        Emplacement e = new Emplacement(1, 10);
        assertEquals(1, e.getNumero());
    }

    @Test
    void setNumero() {
        Emplacement e = new Emplacement(1, 10);
        e.setNumero(2);
        assertEquals(2, e.getNumero());
    }

    @Test
    void getTaille() {
        Emplacement e = new Emplacement(1, 10);
        assertEquals(10, e.getTaille());
    }

    @Test
    void setTaille() {
        Emplacement e = new Emplacement(1, 10);
        e.setTaille(20);
        assertEquals(20, e.getTaille());
    }

    // tests avec problèmes

    @Test
    void addCommercantFail() {
        Emplacement e = new Emplacement(1, 10);
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        e.addCommercant(c);
        e.addCommercant(c);
        assertEquals(1, e.getCommercants().size());
    }

    @Test
    void removeCommercantFail() {
        Emplacement e = new Emplacement(1, 10);
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        e.addCommercant(c);
        e.removeCommercant(c);
        assertEquals(1, e.getCommercants().size());
    }

    @Test
    void setNumeroFail() {
        Emplacement e = new Emplacement(1, 10);
        e.setNumero(2);
        assertEquals(1, e.getNumero());
    }

    @Test
    void setTailleFail() {
        Emplacement e = new Emplacement(1, 10);
        e.setTaille(20);
        assertEquals(10, e.getTaille());
    }

}