package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.AgentEntretien;
import fr.uga.iut2.genevent.vue.IHM;

import java.util.Optional;


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
        if (evenement != null) {
            for (AgentEntretien agent : evenement.getAgentsEntretien()) {
                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();

                if (nom.equals(nomCourant) & prenom.equals(prenomCourant) & email.equals(emailCourant) & telephone.equals(telephoneCourant))
                    ihm.informerUtilisateur("L'agent d'entretien que vous souhaitez ajouter existe déjà", false);
                else
                    evenement.ajouterAgentEntretien(new AgentEntretien(nom, prenom, email, telephone, heureDebut, heureFin));
            }
        } else
            throw new Error("L'agent d'entretien ne peut être ajouté car l'événement du controleur est nul");
    }

    public void supprimerAgentEntretien(AgentEntretien agentEntretien) {
        if (evenement != null) {
            evenement.supprimerAgentEntretien(agentEntretien);
        } else
            throw new Error("L'agent d'entretien ne peut être suppimé car l'événement du controleur est nul");
    }

    public void modifierInformationsAgentEntretien(AgentEntretien agentEntretien,
                                                   Optional<String> nom,
                                                   Optional<String> prenom,
                                                   Optional<String> email,
                                                   Optional<String> telephone,
                                                   Optional<Integer> heureDebut,
                                                   Optional<Integer> heureFin,
                                                   IHM ihm) {
        if (evenement != null) {
            for (AgentEntretien agent : evenement.getAgentsEntretien()) {
                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();

                if (nom.isPresent() | prenom.isPresent() | email.isPresent() | telephone.isPresent()) {

                    if (nom.equals(nomCourant) & prenom.equals(prenomCourant) & email.equals(emailCourant) & telephone.equals(telephoneCourant))
                        ihm.informerUtilisateur("L'agent d'entretien que vous souhaitez ajouter existe déjà", false);
                    else {
                        nom.ifPresent(agentEntretien::setNom);
                        prenom.ifPresent(agentEntretien::setPrenom);
                        email.ifPresent(agentEntretien::setEmail);
                        telephone.ifPresent(agentEntretien::setTelephone);
                    }
                }

                heureDebut.ifPresent(agentEntretien::setHeureDebut);
                heureFin.ifPresent(agentEntretien::setHeureFin);
            }
        } else
            throw new Error("Les informations de l'agent d'entretien ne peuvent être modifiées car l'événement du controleur est nul");
    }
}
