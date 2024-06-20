package fr.uga.iut2.genevent.modele;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe représentant l'application Genevent, c'est elle qu'on sauvegarde avec
 * serializable
 */
public class Application implements Serializable {
    private static final long serialVersionUID = 1L; // nécessaire pour la sérialisation
    private ArrayList<Evenement> evenements;

    /**
     * Constructeur de la classe Application
     */
    public Application() {
        this.evenements = new ArrayList<>();
    }

    /**
     * Getter de la liste des événements
     * 
     * @return liste des événements
     */
    public ArrayList<Evenement> getEvenements() {
        return evenements;
    }

    /**
     * Setter de la liste des événements
     * 
     * @param evenement liste des événements
     */
    public void addEvenement(Evenement evenement) {
        this.evenements.add(evenement);
    }

    /**
     * Supprime un événement de la liste des événements
     * 
     * @param evenement événement à supprimer
     */
    public void removeEvenement(Evenement evenement) {
        this.evenements.remove(evenement);
    }
}
