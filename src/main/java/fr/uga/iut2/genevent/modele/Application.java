package fr.uga.iut2.genevent.modele;

import java.io.Serializable;
import java.util.ArrayList;

public class Application implements Serializable {
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation
    private ArrayList<Evenement> evenements;

    public Application() {
        this.evenements = new ArrayList<>();
    }

    public ArrayList<Evenement> getEvenements() {
        return evenements;
    }

    public void addEvenement(Evenement evenement) {
        this.evenements.add(evenement);
    }

    public void removeEvenement(Evenement evenement) {
        this.evenements.remove(evenement);
    }

}
