package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.exception.MauvaisChampsException;
import fr.uga.iut2.genevent.modele.AgentSecurite;
import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.AgentEntretien;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Contrôleur pour le module "Agent d'entretien"
 */
public class ControleurAgentEntretien {

    private Application application;
    private Evenement evenement;

    /**
     * Crée le contrôleur, doit être uniquement appelée par le constructeur de la classe Controleur.
     * @param application L'application que le contrôleur gérera
     */
    public ControleurAgentEntretien(Application application) {
        this.application = application;
    }

    /**
     * Récupère l'événement géré par le contrôleur.
     * @return L'événement géré par le controleur.
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * Attribue un événement au contrôleur.
     * @param evenement L'événement à attribuer
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    // Agent d'entretien

    /**
     * Crée un nouvel agent d'entretien et l'ajoute à la liste des agents d'entretien de cet événement.
     * @param nom Nom de l'agent
     * @param prenom Prénom de l'agent
     * @param email Adresse email de l'agent
     * @param telephone Numéro de téléphone de l'agent
     * @param heureDebut Heure de début d'activité
     * @param heureFin Heure de fin d'activité
     * @return L'agent d'entretien créé.
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si le nom, prénom, adresse email et numéro de téléphone sont identiques à ceux d'un autre agent d'entretien ou
     * si l'heure de début d'activité de l'agent est ultérieure à l'heure de fin d'activité de celui-ci.
     */
    public AgentEntretien ajouterAgentEntretien(String nom, String prenom, String email, String telephone, int heureDebut, int heureFin) throws Exception {
        if (evenement != null) {

            boolean isStartAfterEnd = heureDebut > heureFin;

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = nom.equals(nomCourant) & prenom.equals(prenomCourant)
                        & email.equals(emailCourant) & telephone.equals(telephoneCourant);

                if (isNotUnique & isStartAfterEnd) {

                    throw new MauvaisChampsException("L'agent d'entretien que vous souhaitez ajouter existe déjà et l'heure de début est ultérieure à l'heure de fin",
                            new ArrayList<>(Arrays.asList(false, false, false, false, false, false)));

                } else if (isNotUnique) {

                    throw new MauvaisChampsException("L'agent d'entretien que vous souhaitez ajouter existe déjà",
                            new ArrayList<>(Arrays.asList(false, false, false, false, true, true)));
                }
            }
            if (isStartAfterEnd) {

                throw new MauvaisChampsException("L'heure de début est ultérieure à l'heure de fin",
                        new ArrayList<>(Arrays.asList(true, true, true, true, false, false)));
            }
            AgentEntretien nouvelAgent = new AgentEntretien(nom, prenom, email, telephone, heureDebut, heureFin);
            evenement.ajouterAgentEntretien(nouvelAgent);

            return nouvelAgent;

        } else
            throw new Exception("L'agent d'entretien ne peut être ajouté car l'événement du contrôleur est nul");
    }

    /**
     * Récupère un agent d'entretien à partir de son nom, prénom, adresse email et numéro de téléphone.
     * @param nom Nom de l'agent qu'on souhaite récupérer
     * @param prenom Prénom de l'agent qu'on souhaite récupérer
     * @param email Adresse email de l'agent qu'on souhaite récupérer
     * @param telephone Numéro de téléphone de l'agent qu'on souhaite récupérer
     * @return L'agent d'entretien correspondant aux attributs donnés en paramètres ou null s'il n'existe pas.
     * @throws Exception Si l'événement est nul.
     */
    public AgentEntretien getAgentEntretien(String nom, String prenom, String email, String telephone) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agentEntretien : evenement.getAgentsEntretien()) {

                String nomCourant = agentEntretien.getNom();
                String prenomCourant = agentEntretien.getPrenom();
                String emailCourant = agentEntretien.getEmail();
                String telephoneCourant = agentEntretien.getTelephone();
                boolean agentFound = nom.equals(nomCourant) & prenom.equals(prenomCourant)
                        & email.equals(emailCourant) & telephone.equals(telephoneCourant);

                if (agentFound) {

                    return agentEntretien;
                }
            }
            return null;

        } else
            throw new Exception("L'agent d'entretien ne peut être récupéré car l'événement du contrôleur est nul");
    }

    /**
     * Retire de la liste des agents d'entretien de cet événement l'agent d'entretien donné en paramètre.
     * @param agentEntretien Agent d'entretien à retirer
     * @throws Exception Si l'événement est nul.
     */
    public void supprimerAgentEntretien(AgentEntretien agentEntretien) throws Exception {
        if (evenement != null) {
            evenement.supprimerAgentEntretien(agentEntretien);
        } else
            throw new Exception("L'agent d'entretien ne peut être suppimé car l'événement du contrôleur est nul");
    }

    /**
     * Modifie le nom de l'agent d'entretien donné en paramètre.
     * @param agentEntretien L'agent d'entretien dont le nom doit être modifié
     * @param nom Nouveau nom
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si le nouveau nom rend l'agent d'entretien identique à un autre agent d'entretien.
     */
    public void modifierNomAgentEntretien(AgentEntretien agentEntretien, String nom) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = nom.equals(nomCourant) & agentEntretien.getPrenom().equals(prenomCourant)
                        & agentEntretien.getEmail().equals(emailCourant) & agentEntretien.getTelephone().equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le nom de l'agent d'entretien, " +
                            "celui-ci devient identique à un autre agent d'entretien",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentEntretien.setNom(nom);

        } else
            throw new Exception("Le nom de l'agent d'entretien ne peut être modifié car l'événement du contrôleur est nul");
    }

    /**
     * Modifie le prénom de l'agent d'entretien donné en paramètre.
     * @param agentEntretien L'agent d'entretien dont le prénom doit être modifié
     * @param prenom Nouveau prénom
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si le nouveau prénom rend l'agent d'entretien identique à un autre agent d'entretien.
     */
    public void modifierPrenomAgentEntretien(AgentEntretien agentEntretien, String prenom) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = agentEntretien.getNom().equals(nomCourant) & prenom.equals(prenomCourant)
                        & agentEntretien.getEmail().equals(emailCourant) & agentEntretien.getTelephone().equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le prénom de l'agent d'entretien, " +
                            "celui-ci devient identique à un autre agent d'entretien",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentEntretien.setPrenom(prenom);

        } else
            throw new Exception("Le prénom de l'agent d'entretien ne peut être modifié car l'événement du contrôleur est nul");
    }

    /**
     * Modifie l'adresse email de l'agent d'entretien donnée en paramètre.
     * @param agentEntretien L'agent d'entretien dont l'adresse email doit être modifié
     * @param email Nouvelle adresse email
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si la nouvelle adresse email rend l'agent d'entretien identique à un autre agent d'entretien.
     */
    public void modifierEmailAgentEntretien(AgentEntretien agentEntretien, String email) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = agentEntretien.getNom().equals(nomCourant) & agentEntretien.getPrenom().equals(prenomCourant)
                        & email.equals(emailCourant) & agentEntretien.getTelephone().equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant l'adresse email de l'agent d'entretien, " +
                            "celui-ci devient identique à un autre agent d'entretien",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentEntretien.setEmail(email);

        } else
            throw new Exception("L'email de l'agent d'entretien ne peut être modifié car l'événement du contrôleur est nul");
    }

    /**
     * Modifie le numéro de téléphone de l'agent d'entretien donné en paramètre.
     * @param agentEntretien L'agent d'entretien dont le numéro de téléphone doit être modifié
     * @param telephone Nouveau numéro de téléphone
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si le nouveau numéro de téléphone rend l'agent d'entretien identique à un autre agent d'entretien.
     */
    public void modifierTelephoneAgentEntretien(AgentEntretien agentEntretien, String telephone) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = agentEntretien.getNom().equals(nomCourant) & agentEntretien.getPrenom().equals(prenomCourant)
                        & agentEntretien.getEmail().equals(emailCourant) & telephone.equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le numéro de téléphone de l'agent d'entretien, " +
                            "celui-ci devient identique à un autre agent d'entretien",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentEntretien.setTelephone(telephone);

        } else
            throw new Exception("Le numéro de téléphone de l'agent d'entretien ne peut être modifié car l'événement du contrôleur est nul");
    }

    /**
     * Modifie l'heure de début d'activité de l'agent d'entretien donné en paramètre.
     * @param agentEntretien L'agent d'entretien dont l'heure de début d'activité doit être modifiée
     * @param heureDebut Nouvelle heure de début d'activité
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si la nouvelle heure de début d'activité de l'agent est ultérieure à l'heure de fin d'activité de celui-ci.
     */
    public void modifierHeureDebutAgentEntretien(AgentEntretien agentEntretien, int heureDebut) throws Exception {
        if (evenement != null) {

            boolean isStartAfterEnd = heureDebut > agentEntretien.getHeureFin();

            if (isStartAfterEnd) {

                throw new MauvaisChampsException("L'heure de début est ultérieure à l'heure de fin",
                        new ArrayList<>(Collections.singleton(false)));
            }
            agentEntretien.setHeureDebut(heureDebut);

        } else
            throw new Exception("L'heure de début de l'agent d'entretien ne peut être modifié car l'événement du contrôleur est nul");
    }

    /**
     * Modifie l'heure de fin d'activité de l'agent d'entretien donné en paramètre.
     * @param agentEntretien L'agent d'entretien dont l'heure de fin d'activité doit être modifiée
     * @param heureFin Nouvelle heure de fin d'activité
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si la nouvelle heure de fin d'activité de l'agent est antérieure à l'heure de début d'activité de celui-ci.
     */
    public void modifierHeureFinAgentEntretien(AgentEntretien agentEntretien, int heureFin) throws Exception {
        if (evenement != null) {

            boolean isStartAfterEnd = agentEntretien.getHeureDebut() > heureFin;

            if (isStartAfterEnd) {

                throw new MauvaisChampsException("L'heure de fin est antérieure à l'heure de début",
                        new ArrayList<>(Collections.singleton(false)));
            }
            agentEntretien.setHeureFin(heureFin);

        } else
            throw new Exception("L'heure de fin de l'agent d'entretien ne peut être modifié car l'événement du contrôleur est nul");
    }

    public ArrayList<AgentEntretien> getAgentEntretiens() {
        return evenement.getAgentsEntretiens();
    }
}
