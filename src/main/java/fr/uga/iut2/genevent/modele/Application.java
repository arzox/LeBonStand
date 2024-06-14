package fr.uga.iut2.genevent.modele;

import java.io.Serializable;
import java.util.ArrayList;

public class Application implements Serializable {
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private ArrayList<Evenement> evenements;

    public Application() {
        evenements = new ArrayList<>();
    }

    public void addEvenement(Evenement evenement) {
        evenements.add(evenement);
    }

    public ArrayList<Evenement> getEvenements() {
        return evenements;
    }
}
