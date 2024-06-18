package fr.uga.iut2.genevent.modele;

import java.io.Serializable;

public class AgentEntretien extends Employe implements Serializable {

    public AgentEntretien(String nom, String prenom, String email, String telephone, int heureDebut, int heureFin) {
        super(nom, prenom, email, telephone, heureDebut, heureFin);
    }
}
