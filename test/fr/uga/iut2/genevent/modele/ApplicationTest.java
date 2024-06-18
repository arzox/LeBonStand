package fr.uga.iut2.genevent.modele;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    @Test
    void getEvenements() {
        ArrayList<Evenement> evenements = new ArrayList<>();
        Application application = new Application();
        assertEquals(evenements, application.getEvenements());
    }

    @Test
    void addEvenement() {
        ArrayList<Evenement> evenements = new ArrayList<>();
        Application application = new Application();
        Evenement evenement = new Evenement("nom", null, null, null, null);
        application.addEvenement(evenement);
        evenements.add(evenement);
        assertEquals(evenements, application.getEvenements());
    }

    @Test
    void removeEvenement() {
        ArrayList<Evenement> evenements = new ArrayList<>();
        Application application = new Application();
        Evenement evenement = new Evenement("nom", null, null, null, null);
        application.addEvenement(evenement);
        evenements.add(evenement);
        application.removeEvenement(evenement);
        evenements.remove(evenement);
        assertEquals(evenements, application.getEvenements());
    }

    // Tests avec des problèmes

    @Test
    void addEvenementProbleme() {
        ArrayList<Evenement> evenements = new ArrayList<>();
        Application application = new Application();
        Evenement evenement = new Evenement("nom", null, null, null, null);
        application.addEvenement(evenement);
        evenements.add(new Evenement("nom", null, null, null, null));
        assertEquals(evenements, application.getEvenements());
    }

    @Test
    void removeEvenementProbleme() {
        ArrayList<Evenement> evenements = new ArrayList<>();
        Application application = new Application();
        Evenement evenement = new Evenement("nom", null, null, null, null);
        application.addEvenement(evenement);
        evenements.add(new Evenement("nom", null, null, null, null));
        application.removeEvenement(evenement);
        evenements.remove(new Evenement("nom", null, null, null, null));
        assertEquals(evenements, application.getEvenements());
    }

}