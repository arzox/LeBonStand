package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommercantTest {

    @Test
    void getNom() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        assertEquals("nom", c.getNom());
    }

    @Test
    void getPrenom() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        assertEquals("prenom", c.getPrenom());
    }

    @Test
    void getEmail() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        assertEquals("email", c.getEmail());
    }

    @Test
    void getTelephone() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        assertEquals("telephone", c.getTelephone());
    }

    @Test
    void getHeureDebut() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        assertEquals(8, c.getHeureDebut());
    }

    @Test
    void getHeureFin() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        assertEquals(18, c.getHeureFin());
    }

    @Test
    void setNom() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        c.setNom("nom2");
        assertEquals("nom2", c.getNom());
    }

    @Test
    void setPrenom() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        c.setPrenom("prenom2");
        assertEquals("prenom2", c.getPrenom());
    }

    @Test
    void setEmail() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        c.setEmail("email2");
        assertEquals("email2", c.getEmail());
    }

    @Test
    void setTelephone() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        c.setTelephone("telephone2");
        assertEquals("telephone2", c.getTelephone());
    }

    @Test
    void setHeureDebut() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        c.setHeureDebut(9);
        assertEquals(9, c.getHeureDebut());
    }

    @Test
    void setHeureFin() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        c.setHeureFin(19);
        assertEquals(19, c.getHeureFin());
    }

    @Test
    void getEmplacement() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        assertNull(c.getEmplacement());
    }

    @Test
    void setEmplacement() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        Emplacement e = new Emplacement(13, 0);
        c.setEmplacement(e);
        assertEquals(e, c.getEmplacement());
    }

    @Test
    void setTypeCommerce() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        TypeCommerce t = new TypeCommerce("type", 5);
        c.setTypeCommerce(t);
        assertEquals(t, c.getTypeCommerce());
    }

    @Test
    void getTypeCommerce() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        assertNull(c.getTypeCommerce());
    }

    // tests avec des problèmes

    @Test
    void setHeureDebutFail() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        assertThrows(IllegalArgumentException.class, () -> c.setHeureDebut(7));
    }

    @Test
    void setHeureFinFail() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        assertThrows(IllegalArgumentException.class, () -> c.setHeureFin(19));
    }

    @Test
    void setNomFail() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        assertThrows(IllegalArgumentException.class, () -> c.setNom(""));
    }

    @Test
    void setPrenomFail() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        assertThrows(IllegalArgumentException.class, () -> c.setPrenom(""));
    }

    @Test
    void setEmailFail() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        assertThrows(IllegalArgumentException.class, () -> c.setEmail(""));
    }

    @Test
    void setTelephoneFail() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        assertThrows(IllegalArgumentException.class, () -> c.setTelephone(""));
    }

    @Test
    void setEmplacementFail() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        assertThrows(IllegalArgumentException.class, () -> c.setEmplacement(null));
    }

    @Test
    void setTypeCommerceFail() {
        Commercant c = new Commercant("nom", "prenom", "email", "telephone", 8, 18, null, null);
        assertThrows(IllegalArgumentException.class, () -> c.setTypeCommerce(null));
    }

}