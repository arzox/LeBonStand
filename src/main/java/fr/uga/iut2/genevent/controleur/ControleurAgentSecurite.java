package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.exception.MauvaisChampsException;
import fr.uga.iut2.genevent.modele.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Contrôleur pour le module "Agent de sécurité"
 */
public class ControleurAgentSecurite {
    private Application application;
    private Evenement evenement;

    /**
     * Crée le controleur, doit être uniquement appelée par le constructeur de la
     * classe Controleur.
     * 
     * @param application L'application que le controleur gérera
     */
    public ControleurAgentSecurite(Application application) {
        this.application = application;
    }

    /**
     * Récupère l'événement géré par le contrôleur.
     * 
     * @return L'événement géré par le controleur.
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * Attribue un événement au contrôleur.
     * 
     * @param evenement L'événement à attribuer
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    // Agent de sécurité

    /**
     * Crée un nouvel agent de sécurité et l'ajoute à la liste des agents de
     * sécurité de cet événement.
     * 
     * @param nom Nom de l'agent
     * @return L'agent de sécurité créé.
     * @throws Exception Si l'événement est nul.
     */
    public AgentSecurite ajouterAgentSecurite(String nom) throws Exception {
        if (evenement != null) {

            AgentSecurite nouvelAgent = new AgentSecurite(nom, "Prénom", "Mail",
                    "0601020304", 13, 19, null);

            evenement.ajouterAgentSecurite(nouvelAgent);

            return nouvelAgent;

        } else
            throw new Exception("L'agent de sécurité ne peut être ajouté car l'événement du controleur est nul");
    }

    /**
     * Récupère un agent de sécurité à partir de son nom, prénom, adresse email et
     * numéro de téléphone.
     * 
     * @param nom       Nom de l'agent qu'on souhaite récupérer
     * @param prenom    Prénom de l'agent qu'on souhaite récupérer
     * @param email     Adresse email de l'agent qu'on souhaite récupérer
     * @param telephone Numéro de téléphone de l'agent qu'on souhaite récupérer
     * @return L'agent de sécurité correspondant aux attributs donnés en paramètres
     *         ou null s'il n'existe pas.
     * @throws Exception Si l'événement est nul.
     */
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

    /**
     * Retire de la liste des agents de sécurité de cet événement l'agent de
     * sécurité donné en paramètre.
     * 
     * @param agentSecurite Agent de sécurité à retirer
     * @throws Exception Si l'événement est nul.
     */
    public void supprimerAgentSecurite(AgentSecurite agentSecurite) throws Exception {
        if (evenement != null) {

            evenement.supprimerAgentSecurite(agentSecurite);

        } else
            throw new Exception("L'agent de sécurité ne peut être supprimé car l'événement du controleur est nul");
    }

    /**
     * Modifie le nom de l'agent de sécurité donné en paramètre.
     * 
     * @param agentSecurite L'agent de sécurité dont le nom doit être modifié
     * @param nom           Nouveau nom
     * @throws Exception              Si l'événement est nul.
     * @throws MauvaisChampsException Si le nouveau nom rend l'agent de securité
     *                                identique à un autre agent de sécurité.
     */
    public void modifierNomAgentSecurite(AgentSecurite agentSecurite, String nom) throws Exception {
        if (evenement != null) {

            for (AgentSecurite agent : evenement.getAgentsSecurite()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();

                boolean isNotSameAgent = agentSecurite != agent;
                boolean isNotUnique = nom.equals(nomCourant) & agentSecurite.getPrenom().equals(prenomCourant)
                        & agentSecurite.getEmail().equals(emailCourant)
                        & agentSecurite.getTelephone().equals(telephoneCourant);

                if (isNotSameAgent & isNotUnique) {

                    throw new MauvaisChampsException("En changeant le nom de l'agent de sécurité, " +
                            "celui-ci devient identique à un autre agent de sécurité",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentSecurite.setNom(nom);

        } else
            throw new Exception(
                    "Le nom de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }

    /**
     * Modifie le prénom de l'agent de sécurité donné en paramètre.
     * 
     * @param agentSecurite L'agent de sécurité dont le prénom doit être modifié
     * @param prenom        Nouveau prénom
     * @throws Exception              Si l'événement est nul.
     * @throws MauvaisChampsException Si le nouveau prénom rend l'agent de sécurité
     *                                identique à un autre agent de sécurité.
     */
    public void modifierPrenomAgentSecurite(AgentSecurite agentSecurite, String prenom) throws Exception {
        if (evenement != null) {

            for (AgentSecurite agent : evenement.getAgentsSecurite()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();

                boolean isNotSameAgent = agentSecurite != agent;
                boolean isNotUnique = agentSecurite.getNom().equals(nomCourant) & prenom.equals(prenomCourant)
                        & agentSecurite.getEmail().equals(emailCourant)
                        & agentSecurite.getTelephone().equals(telephoneCourant);

                if (isNotSameAgent & isNotUnique) {

                    throw new MauvaisChampsException("En changeant le prénom de l'agent de sécurité, " +
                            "celui-ci devient identique à un autre agent de sécurité",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentSecurite.setPrenom(prenom);

        } else
            throw new Exception(
                    "Le prénom de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }

    /**
     * Modifie l'adresse email de l'agent de sécurité donnée en paramètre.
     * 
     * @param agentSecurite L'agent de sécurité dont l'adresse email doit être
     *                      modifiée
     * @param email         Nouvelle adresse email
     * @throws Exception              Si l'événement est nul.
     * @throws MauvaisChampsException Si la nouvelle adresse email rend l'agent de
     *                                sécurité identique à un autre agent de
     *                                sécurité.
     */
    public void modifierEmailAgentSecurite(AgentSecurite agentSecurite, String email) throws Exception {
        if (evenement != null) {

            for (AgentSecurite agent : evenement.getAgentsSecurite()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();

                boolean isNotSameAgent = agentSecurite != agent;
                boolean isNotUnique = agentSecurite.getNom().equals(nomCourant)
                        & agentSecurite.getPrenom().equals(prenomCourant)
                        & email.equals(emailCourant) & agentSecurite.getTelephone().equals(telephoneCourant);

                if (isNotSameAgent & isNotUnique) {

                    throw new MauvaisChampsException("En changeant l'adresse email de l'agent de sécurité, " +
                            "celui-ci devient identique à un autre agent de sécurité",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentSecurite.setEmail(email);

        } else
            throw new Exception(
                    "L'email de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }

    /**
     * Modifie le numéro de téléphone de l'agent de sécurité donné en paramètre.
     * 
     * @param agentSecurite L'agent de sécurité dont le numéro de téléphone doit
     *                      être modifié
     * @param telephone     Nouveau numéro de téléphone
     * @throws Exception              Si l'événement est nul.
     * @throws MauvaisChampsException Si le nouveau numéro de téléphone rend l'agent
     *                                de sécurité identique à un autre agent de
     *                                sécurité.
     */
    public void modifierTelephoneAgentSecurite(AgentSecurite agentSecurite, String telephone) throws Exception {
        if (evenement != null) {

            for (AgentSecurite agent : evenement.getAgentsSecurite()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();

                boolean isNotSameAgent = agentSecurite != agent;
                boolean isNotUnique = agentSecurite.getNom().equals(nomCourant)
                        & agentSecurite.getPrenom().equals(prenomCourant)
                        & agentSecurite.getEmail().equals(emailCourant) & telephone.equals(telephoneCourant);

                if (isNotSameAgent & isNotUnique) {

                    throw new MauvaisChampsException("En changeant le numéro de téléphone de l'agent de sécurité, " +
                            "celui-ci devient identique à un autre agent de sécurité",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentSecurite.setTelephone(telephone);

        } else
            throw new Exception(
                    "Le numéro de téléphone de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }

    /**
     * Modifie l'heure de début d'activité de l'agent de sécurité donné en
     * paramètre.
     * 
     * @param agentSecurite L'agent de sécurité dont l'heure de début d'activité
     *                      doit être modifiée
     * @param heureDebut    Nouvelle heure de début d'activité
     * @throws Exception              Si l'événement est nul.
     * @throws MauvaisChampsException Si la nouvelle heure de début d'activité de
     *                                l'agent est ultérieure à l'heure de fin
     *                                d'activité de celui-ci.
     */
    public void modifierHeureDebutAgentSecurite(AgentSecurite agentSecurite, int heureDebut) throws Exception {
        if (evenement != null) {

            boolean isStartAfterEnd = heureDebut > agentSecurite.getHeureFin();

            if (isStartAfterEnd) {

                throw new MauvaisChampsException("L'heure de début ne peut être ultérieure à l'heure de fin",
                        new ArrayList<>(Collections.singleton(false)));
            }
            agentSecurite.setHeureDebut(heureDebut);

        } else
            throw new Exception(
                    "L'heure de début de l'agent de sécurité ne peut être modifié car l'événement du controleur est nul");
    }

    /**
     * Modifie l'heure de fin d'activité de l'agent donné en paramètre.
     * 
     * @param agentSecurite L'agent de sécurité dont l'heure de fin d'activité doit
     *                      être modifiée
     * @param heureFin      Nouvelle heure de fin d'activité
     * @throws Exception              Si l'événement est nul.
     * @throws MauvaisChampsException Si la nouvelle heure de fin d'activité de
     *                                l'agent est antérieure à l'heure de début
     *                                d'activité de celui-ci.
     */
    public void modifierHeureFinAgentSecurite(AgentSecurite agentSecurite, int heureFin) throws Exception {
        if (evenement != null) {

            boolean isStartAfterEnd = agentSecurite.getHeureDebut() > heureFin;

            if (isStartAfterEnd) {

                throw new MauvaisChampsException("L'heure de fin ne peut être antérieure à l'heure de début",
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

    // Zone

    /**
     * Crée une nouvelle zone et l'ajoute à la liste des zones de cet événement.
     * 
     * @param nom Nom de la zone
     * @return La zone créée.
     * @throws Exception Si l'événement est nul.
     */
    public Zone creerZone(String nom) throws Exception {
        if (evenement != null) {

            Zone nouvelleZone = new Zone(nom);
            evenement.ajouterZone(nouvelleZone);

            return nouvelleZone;

        } else
            throw new Exception("La zone ne peut être créée car l'événement du controleur est nul");
    }

    /**
     * Récupère une zone à partir de son nom.
     * 
     * @param nom Nom de la zone qu'on souhaite récupérer
     * @return La zone correspondante au nom donné en paramètre ou null si elle
     *         n'existe pas.
     * @throws Exception Si l'événement est nul.
     */
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

    /**
     * Retire de la liste des zones de cet événement la zone donnée en paramètre.
     * 
     * @param zone La zone à retirer
     * @throws Exception Si l'événement est nul.
     */
    public void supprimerZone(Zone zone) throws Exception {
        if (evenement != null) {

            evenement.supprimerZone(zone);

        } else
            throw new Exception("La zone ne peut être supprimé car l'événement du controleur est nul");
    }

    /**
     * Modifie le nom de la zone donné en paramètre.
     * 
     * @param zone La zone dont le nom doit être modifié
     * @param nom  Nouveau nom
     * @throws Exception              Si l'événement est nul.
     * @throws MauvaisChampsException Si le nouveau nom rend la zone identique à une
     *                                autre zone.
     */
    public void modifierNomZone(Zone zone, String nom) throws Exception {
        if (evenement != null) {

            for (Zone zoneCourante : evenement.getZones()) {

                String nomCourant = zoneCourante.getNom();

                boolean isNotSameZone = zone != zoneCourante;
                boolean isNotUnique = nom.equals(nomCourant);

                if (isNotSameZone & isNotUnique) {

                    throw new MauvaisChampsException(
                            "En changeant le nom de la zone, celle-ci devient identique à une autre zone",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            zone.setNom(nom);

        } else
            throw new Exception("Le nom de la zone ne peut être modifié car l'événement du controleur est nul");
    }
}
