package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.exception.MauvaisChampsException;
import fr.uga.iut2.genevent.modele.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Contrôleur pour la catégorie "Agents de sécurité"
 */
public class ControleurAgentSecurite {
    private Application application;
    private Evenement evenement;

    public ControleurAgentSecurite(Application application) {
        this.application = application;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    // Agent de sécurité
    public AgentSecurite ajouterAgentSecurite(String nom, String prenom, String email, String telephone, int heureDebut, int heureFin, Zone zone) throws Exception {
        if (evenement != null) {

            boolean isStartAfterEnd = heureDebut > heureFin;

            for (AgentSecurite agent : evenement.getAgentsSecurite()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = nom.equals(nomCourant) & prenom.equals(prenomCourant) & email.equals(emailCourant) & telephone.equals(telephoneCourant);

                if (isNotUnique & isStartAfterEnd) {

                    throw new MauvaisChampsException("L'agent de sécurité que vous souhaitez ajouter existe déjà et " +
                            "l'heure de début est ultérieure à l'heure de fin",
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

    public AgentSecurite getAgentSecurite(String nom, String prenom, String email, String telephone) throws Exception {
        if (evenement != null) {

            for (AgentSecurite agentSecurite : evenement.getAgentsSecurite()) {

                String nomCourant = agentSecurite.getNom();
                String prenomCourant = agentSecurite.getPrenom();
                String emailCourant = agentSecurite.getEmail();
                String telephoneCourant = agentSecurite.getTelephone();
                boolean agentFound = nom.equals(nomCourant) & prenom.equals(prenomCourant)
                        & email.equals(emailCourant) & telephone.equals(telephoneCourant);

                if (agentFound) {

                    return agentSecurite;
                }
            }
            return null;

        } else
            throw new Exception("L'agent de sécurité ne peut être récupéré car l'événement du controleur est nul");
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
                        & agentSecurite.getEmail().equals(emailCourant) & agentSecurite.getTelephone().equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le nom de l'agent de sécurité, " +
                            "celui-ci devient identique à un autre agent de sécurité",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentSecurite.setNom(nom);

        } else
            throw new Exception("Le nom de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }

    public void modifierPrenomAgentSecurite(AgentSecurite agentSecurite, String prenom) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = agentSecurite.getNom().equals(nomCourant) & prenom.equals(prenomCourant)
                        & agentSecurite.getEmail().equals(emailCourant) & agentSecurite.getTelephone().equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le prénom de l'agent de sécurité, " +
                            "celui-ci devient identique à un autre agent de sécurité",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentSecurite.setPrenom(prenom);

        } else
            throw new Exception("Le prénom de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }

    public void modifierEmailAgentSecurite(AgentSecurite agentSecurite, String email) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = agentSecurite.getNom().equals(nomCourant) & agentSecurite.getPrenom().equals(prenomCourant)
                        & email.equals(emailCourant) & agentSecurite.getTelephone().equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant l'adresse email de l'agent de sécurité, " +
                            "celui-ci devient identique à un autre agent de sécurité",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentSecurite.setEmail(email);

        } else
            throw new Exception("L'email de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }

    public void modifierTelephoneAgentSecurite(AgentSecurite agentSecurite, String telephone) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = agentSecurite.getNom().equals(nomCourant) & agentSecurite.getPrenom().equals(prenomCourant)
                        & agentSecurite.getEmail().equals(emailCourant) & telephone.equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le numéro de téléphone de l'agent de sécurité, " +
                            "celui-ci devient identique à un autre agent de sécurité",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentSecurite.setTelephone(telephone);

        } else
            throw new Exception("Le numéro de téléphone de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
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
            throw new Exception("L'heure de début de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
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
            throw new Exception("L'heure de fin de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }

    public void modifierZoneAgentSecurite(AgentSecurite agentSecurite, Zone zone) throws Exception {
        if (evenement != null) {

            agentSecurite.setZone(zone);

        } else
            throw new Exception("La zone de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }

    // Zone
    public Zone creerZone(String nom) throws Exception {
        if (evenement != null) {

            for (Zone zone : evenement.getZones()) {

                String nomCourant = zone.getNom();
                boolean isNotUnique = nom.equals(nomCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("La zone que vous souhaitez créer existe déjà",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            Zone nouvelleZone = new Zone(nom);
            evenement.ajouterZone(nouvelleZone);

            return nouvelleZone;

        } else
            throw new Exception("La zone ne peut être créée car l'événement du controleur est nul");
    }

    public Zone getZone(String nom) throws Exception {
        if (evenement != null) {

            for (Zone zone : evenement.getZones()) {

                String nomCourant = zone.getNom();
                boolean zoneFound = nom.equals(nomCourant);

                if (zoneFound) {

                    return zone;
                }
            }
            return null;

        } else
            throw new Exception("La zone ne peut être récupérée car l'événement du controleur est nul");
    }

    public void supprimerZone(Zone zone) throws Exception {
        if (evenement != null) {

            evenement.supprimerZone(zone);

        } else
            throw new Exception("La zone ne peut être supprimé car l'événement du controleur est nul");
    }

    public void modifierNomZone(Zone zone, String nom) throws Exception {
        if (evenement != null) {

            for (Zone zoneCourante : evenement.getZones()) {

                String nomCourant = zoneCourante.getNom();
                boolean isNotUnique = nom.equals(nomCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le nom de la zone, celle-ci devient identique à un autre zone",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            zone.setNom(nom);

        } else
            throw new Exception("Le nom de la zone ne peut être modifié car l'événement du controleur est nul");
    }
}
