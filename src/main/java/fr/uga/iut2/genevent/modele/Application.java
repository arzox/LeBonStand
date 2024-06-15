package fr.uga.iut2.genevent.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Application implements Serializable {
    private static final long serialVersionUID = 1L;  // nécessaire pour la sérialisation

    private List<Evenement> evenements = new ArrayList<>();

    public List<Evenement> getEvenements() {
        return evenements;
    }

    public void addEvenement(Evenement evenement) {
        this.evenements.add(evenement);
    }

    public void removeEvenement(Evenement evenement) {
        this.evenements.remove(evenement);
    }

}
