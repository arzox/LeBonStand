package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeCommerceTest {

    @Test
    void addCommercant() {
        TypeCommerce typeCommerce = new TypeCommerce("nom");
        Commercant commercant = new Commercant("nom", "prenom", "email",
                "telephone", 0, 0, null, typeCommerce);
        typeCommerce.addCommercant(commercant);
        assertTrue(typeCommerce.getCommercants().contains(commercant));
    }

    @Test
    void removeCommercant() {
        TypeCommerce typeCommerce = new TypeCommerce("nom");
        Commercant commercant = new Commercant("nom", "prenom", "email",
                "telephone", 0, 0, null, typeCommerce);
        typeCommerce.addCommercant(commercant);
        typeCommerce.removeCommercant(commercant);
        assertFalse(typeCommerce.getCommercants().contains(commercant));
    }

    @Test
    void getCommercants() {
        TypeCommerce typeCommerce = new TypeCommerce("nom");
        Commercant commercant = new Commercant("nom", "prenom", "email",
                "telephone", 0, 0, null, typeCommerce);
        typeCommerce.addCommercant(commercant);
        assertTrue(typeCommerce.getCommercants().contains(commercant));
    }

    @Test
    void getNom() {
        TypeCommerce typeCommerce = new TypeCommerce("nom");
        assertEquals("nom", typeCommerce.getNom());
    }

    @Test
    void setNom() {
        TypeCommerce typeCommerce = new TypeCommerce("nom");
        typeCommerce.setNom("nouveau nom");
        assertEquals("nouveau nom", typeCommerce.getNom());
    }
}