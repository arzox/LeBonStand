package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.AgentEntretien;
import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.vue.IHM;


public class ControleurAgentEntretient {

    private Application application;
    private Evenement evenement;

    public ControleurAgentEntretient(Application application) {
        this.application = application;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public void ajouterAgentEntretien(String nom, String prenom, String email, String telephone, int heureDebut, int heureFin, IHM ihm) {
        for (AgentEntretien agent : evenement.getAgentsEntretien()) {
            String nomCourant = agent.getNom();
            String prenomCourant = agent.getPrenom();
            String emailCourant = agent.getEmail();
            String telephoneCourant = agent.getTelephone();
            int heureDebutCourant = agent.getHeureDebut();
            int heureFinCourant = agent.getHeureFin();

            if (nom.equals(nomCourant) & prenom.equals(prenomCourant) & email.equals(emailCourant) & telephone.equals(telephoneCourant))
                ihm.informerUtilisateur("L'agent d'entretien que vous souhaitez ajouter existe déjà", false);
            else
                evenement.ajouterAgentEntretien(new AgentEntretien(nom, prenom, email, telephone, heureDebut, heureFin));
        }
    }

    public void supprimerAgentEntretien(AgentEntretien agentEntretien, IHM ihm) {
        evenement.supprimerAgentEntretien(agentEntretien);
    }
}
