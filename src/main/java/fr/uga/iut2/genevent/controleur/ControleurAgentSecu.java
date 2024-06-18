package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.exception.MauvaisChampsException;
import fr.uga.iut2.genevent.modele.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

/**
 * Sous-contrôleur pour la catégorie "Agents de sécurité"
 */
public class ControleurAgentSecu {
    private Application application;
    private Evenement evenement;

    public ControleurAgentSecu(Application application) {
        this.application = application;
    }

    // Setters

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public AgentSecurite ajouterAgentSecurite(String nom, String prenom, String email, String telephone, int heureDebut,
            int heureFin, Zone zone) throws Exception {
        if (evenement != null) {

            boolean isStartAfterEnd = heureDebut > heureFin;

            for (AgentSecurite agent : evenement.getAgentsSecurite()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = nom.equals(nomCourant) & prenom.equals(prenomCourant) & email.equals(emailCourant)
                        & telephone.equals(telephoneCourant);

                if (isNotUnique & isStartAfterEnd) {

                    throw new MauvaisChampsException(
                            "L'agent de sécurité que vous souhaitez ajouter existe déjà et l'heure de début est ultérieure à l'heure de fin",
                            new ArrayList<>(Arrays.asList(false, false, false, false, false, false, true)));

                } else if (isNotUnique) {

                    throw new MauvaisChampsException("L'agent de sécurité que vous souhaitez ajouter existe déjà",
                            new ArrayList<>(Arrays.asList(false, false, false, false, true, true, true)));
                }
            }
            if (isStartAfterEnd) {

                throw new MauvaisChampsException("L'heure de début est ultérieure à l'heure de fin",
                        new ArrayList<>(Arrays.asList(true, true, true, true, false, false, true)));
            }
            AgentSecurite nouvelAgent = new AgentSecurite(nom, prenom, email, telephone, heureDebut, heureFin, zone);
            evenement.ajouterAgentSecurite(nouvelAgent);

            return nouvelAgent;

        } else
            throw new Exception("L'agent de sécurité ne peut être ajouté car l'événement du controleur est nul");
    }

    public AgentSecurite getAgentSecurite(String nom, String prenom, String email, String telephone) {
        for (AgentSecurite agentSecurite : evenement.getAgentsSecurite()) {

            String nomCourant = agentSecurite.getNom();
            String prenomCourant = agentSecurite.getPrenom();
            String emailCourant = agentSecurite.getEmail();
            String telephoneCourant = agentSecurite.getTelephone();

            if (nom.equals(nomCourant) & prenom.equals(prenomCourant) & email.equals(emailCourant)
                    & telephone.equals(telephoneCourant)) {

                return agentSecurite;
            }
        }
        return null;
    }

    public void supprimerAgentSecurite(AgentSecurite agentSecurite) throws Exception {
        if (evenement != null) {

            evenement.supprimerAgentSecurite(agentSecurite);

        } else
            throw new Exception("L'agent de sécurité ne peut être supprimé car l'événement du controleur est nul");
    }

    public void modifierNomAgentSecurite(AgentSecurite agentSecurite, String nom) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = nom.equals(nomCourant) & agentSecurite.getPrenom().equals(prenomCourant)
                        & agentSecurite.getEmail().equals(emailCourant)
                        & agentSecurite.getTelephone().equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException(
                            "En changeant le nom de l'agent de sécurité, celui-ci devient identique à un autre agent de sécurité",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentSecurite.setNom(nom);

        } else
            throw new Exception(
                    "Le nom de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }

    public void modifierPrenomAgentSecurite(AgentSecurite agentSecurite, String prenom) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = agentSecurite.getNom().equals(nomCourant) & prenom.equals(prenomCourant)
                        & agentSecurite.getEmail().equals(emailCourant)
                        & agentSecurite.getTelephone().equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException(
                            "En changeant le prénom de l'agent de sécurité, celui-ci devient identique à un autre agent de sécurité",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentSecurite.setPrenom(prenom);

        } else
            throw new Exception(
                    "Le prénom de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }

    public void modifierEmailAgentSecurite(AgentSecurite agentSecurite, String email) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = agentSecurite.getNom().equals(nomCourant)
                        & agentSecurite.getPrenom().equals(prenomCourant)
                        & email.equals(emailCourant) & agentSecurite.getTelephone().equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException(
                            "En changeant l'adresse email de l'agent de sécurité, celui-ci devient identique à un autre agent de sécurité",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentSecurite.setEmail(email);

        } else
            throw new Exception(
                    "L'email de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }

    public void modifierTelephoneAgentSecurite(AgentSecurite agentSecurite, String telephone) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = agentSecurite.getNom().equals(nomCourant)
                        & agentSecurite.getPrenom().equals(prenomCourant)
                        & agentSecurite.getEmail().equals(emailCourant) & telephone.equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException(
                            "En changeant le numéro de téléphone de l'agent de sécurité, celui-ci devient identique à un autre agent de sécurité",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentSecurite.setTelephone(telephone);

        } else
            throw new Exception(
                    "Le numéro de téléphone de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }

    public void modifierHeureDebutAgentSecurite(AgentSecurite agentSecurite, int heureDebut) throws Exception {
        if (evenement != null) {

            boolean isStartAfterEnd = heureDebut > agentSecurite.getHeureFin();

            if (isStartAfterEnd) {

                throw new MauvaisChampsException("L'heure de début est ultérieure à l'heure de fin",
                        new ArrayList<>(Collections.singleton(false)));
            }
            agentSecurite.setHeureDebut(heureDebut);

        } else
            throw new Exception(
                    "L'heure de début de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }

    public void modifierHeureFinAgentSecurite(AgentSecurite agentSecurite, int heureFin) throws Exception {
        if (evenement != null) {

            boolean isStartAfterEnd = agentSecurite.getHeureDebut() > heureFin;

            if (isStartAfterEnd) {

                throw new MauvaisChampsException("L'heure de fin est antérieure à l'heure de début",
                        new ArrayList<>(Collections.singleton(false)));
            }
            agentSecurite.setHeureFin(heureFin);

        } else
            throw new Exception(
                    "L'heure de fin de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }

    public void modifierZoneAgentSecurite(AgentSecurite agentSecurite, Zone zone) throws Exception {
        if (evenement != null) {

            agentSecurite.setZone(zone);

        } else
            throw new Exception(
                    "La zone de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }
}
