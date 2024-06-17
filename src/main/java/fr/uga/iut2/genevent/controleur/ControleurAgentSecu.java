package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.AgentSecurite;
import fr.uga.iut2.genevent.modele.Zone;
import fr.uga.iut2.genevent.vue.IHM;

import java.util.Optional;

public class ControleurAgentSecu {
    private Application application;
    private Evenement evenement;

    public ControleurAgentSecu(Application application) {
        this.application = application;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public void ajouterAgentSecurite(String nom, String prenom, String email, String telephone, int heureDebut, int heureFin, Zone zone, IHM ihm) {
        if (evenement != null) {
            for (AgentSecurite agent : evenement.getAgentsSecurite()) {
                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                int heureDebutCourant = agent.getHeureDebut();
                int heureFinCourant = agent.getHeureFin();

                if (nom.equals(nomCourant) & prenom.equals(prenomCourant) & email.equals(emailCourant) & telephone.equals(telephoneCourant))
                    ihm.informerUtilisateur("L'agent de sécurité que vous souhaitez ajouter existe déjà", false);
                else
                    evenement.ajouterAgentSecurite(new AgentSecurite(nom, prenom, email, telephone, heureDebut, heureFin, zone));
            }
        } else
            throw new Error("L'agent de sécurité ne peut être ajouté car l'événement du controleur est nul");
    }

    public void supprimerAgentSecurite(AgentSecurite agentSecurite) {
        if (evenement != null) {
            evenement.supprimerAgentSecurite(agentSecurite);
        } else
            throw new Error("L'agent de sécurité ne peut être supprimé car l'événement du controleur est nul");
    }

    public void modifierInformationsAgentSecurite(AgentSecurite agentSecurite,
                                                  Optional<String> nom,
                                                  Optional<String> prenom,
                                                  Optional<String> email,
                                                  Optional<String> telephone,
                                                  Optional<Integer> heureDebut,
                                                  Optional<Integer> heureFin,
                                                  Optional<Zone> zone,
                                                  IHM ihm) {
        if (evenement != null) {
            for (AgentSecurite agent : evenement.getAgentsSecurite()) {
                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();

                if (nom.isPresent() | prenom.isPresent() | email.isPresent() | telephone.isPresent()) {

                    if (nom.equals(nomCourant) & prenom.equals(prenomCourant) & email.equals(emailCourant) & telephone.equals(telephoneCourant))
                        ihm.informerUtilisateur("L'agent de sécurité que vous souhaitez ajouter existe déjà", false);
                    else {
                        nom.ifPresent(agentSecurite::setNom);
                        prenom.ifPresent(agentSecurite::setPrenom);
                        email.ifPresent(agentSecurite::setEmail);
                        telephone.ifPresent(agentSecurite::setTelephone);
                    }
                }

                heureDebut.ifPresent(agentSecurite::setHeureDebut);
                heureFin.ifPresent(agentSecurite::setHeureFin);
                zone.ifPresent(agentSecurite::setZone);
            }
        } else
            throw new Error("Les informations de l'agent de sécurité ne peuvent être modifiées car l'événement du controleur est nul");
    }
}
