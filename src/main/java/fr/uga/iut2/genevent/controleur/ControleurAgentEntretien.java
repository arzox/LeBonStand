package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.exception.MauvaisChampsException;
import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.AgentEntretien;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Sous-contrôleur pour la catégorie "Agents d'entretien"
 */
public class ControleurAgentEntretien {

    private Application application;
    private Evenement evenement;

    /**
     * Constructeur de la classe ControleurAgentEntretien.
     *
     * @param application L'application à laquelle ce contrôleur est associé.
     */
    public ControleurAgentEntretien(Application application) {
        this.application = application;
    }

    /**
     * Définit l'événement associé à ce contrôleur.
     *
     * @param evenement L'événement à associer à ce contrôleur.
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    /**
     * Ajoute un agent d'entretien à l'événement.
     *
     * @param nom Le nom de l'agent d'entretien.
     * @param prenom Le prénom de l'agent d'entretien.
     * @param email L'email de l'agent d'entretien.
     * @param telephone Le numéro de téléphone de l'agent d'entretien.
     * @param heureDebut L'heure de début du travail de l'agent d'entretien.
     * @param heureFin L'heure de fin du travail de l'agent d'entretien.
     * @return L'agent d'entretien qui a été ajouté.
     * @throws Exception Si l'événement du contrôleur est nul ou si l'agent d'entretien existe déjà.
     */
    public AgentEntretien ajouterAgentEntretien(String nom, String prenom, String email, String telephone, int heureDebut, int heureFin) throws Exception {
        if (evenement != null) {

            boolean isStartAfterEnd = heureDebut > heureFin;

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = nom.equals(nomCourant) & prenom.equals(prenomCourant) & email.equals(emailCourant) & telephone.equals(telephoneCourant);

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
            throw new Exception("L'agent d'entretien ne peut être ajouté car l'événement du controleur est nul");
    }

    /**
     * Récupère un agent d'entretien de l'événement.
     *
     * @param nom Le nom de l'agent d'entretien.
     * @param prenom Le prénom de l'agent d'entretien.
     * @param email L'email de l'agent d'entretien.
     * @param telephone Le numéro de téléphone de l'agent d'entretien.
     * @return L'agent d'entretien correspondant aux informations fournies, ou null si aucun agent d'entretien ne correspond.
     */
    public AgentEntretien getAgentEntretien(String nom, String prenom, String email, String telephone) {
        for (AgentEntretien agentEntretien : evenement.getAgentsEntretien()) {

            String nomCourant = agentEntretien.getNom();
            String prenomCourant = agentEntretien.getPrenom();
            String emailCourant = agentEntretien.getEmail();
            String telephoneCourant = agentEntretien.getTelephone();

            if (nom.equals(nomCourant) & prenom.equals(prenomCourant) & email.equals(emailCourant) & telephone.equals(telephoneCourant)) {

                return agentEntretien;
            }
        }
        return null;
    }

    /**
     * Supprime un agent d'entretien de l'événement.
     *
     * @param agentEntretien L'agent d'entretien à supprimer.
     * @throws Exception Si l'événement du contrôleur est nul.
     */
    public void supprimerAgentEntretien(AgentEntretien agentEntretien) throws Exception {
        if (evenement != null) {
            evenement.supprimerAgentEntretien(agentEntretien);
        } else
            throw new Exception("L'agent d'entretien ne peut être suppimé car l'événement du controleur est nul");
    }

    /**
     * Modifie le nom d'un agent d'entretien.
     *
     * @param agentEntretien L'agent d'entretien dont le nom doit être modifié.
     * @param nom Le nouveau nom de l'agent d'entretien.
     * @throws Exception Si l'événement du contrôleur est nul ou si le nom de l'agent d'entretien est déjà utilisé.
     */
    public void modifierNomAgentEntretien(AgentEntretien agentEntretien, String nom) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = nom.equals(nomCourant) & agentEntretien.getPrenom().equals(prenomCourant) & agentEntretien.getEmail().equals(emailCourant)
                        & agentEntretien.getTelephone().equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le nom de l'agent d'entretien, celui-ci devient identique à un autre agent d'entretien",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentEntretien.setNom(nom);

        } else
            throw new Exception("Le nom de l'agent d'entretien ne peut être modifié car l'événement du controleur est nul");
    }

    /**
     * Modifie le prénom d'un agent d'entretien.
     *
     * @param agentEntretien L'agent d'entretien dont le prénom doit être modifié.
     * @param prenom Le nouveau prénom de l'agent d'entretien.
     * @throws Exception Si l'événement du contrôleur est nul ou si le prénom de l'agent d'entretien est déjà utilisé.
     */
    public void modifierPrenomAgentEntretien(AgentEntretien agentEntretien, String prenom) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                String telephoneCourant = agent.getTelephone();
                boolean isNotUnique = agentEntretien.getNom().equals(nomCourant) & prenom.equals(prenomCourant) & agentEntretien.getEmail().equals(emailCourant)
                        & agentEntretien.getTelephone().equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le prénom de l'agent d'entretien, celui-ci devient identique à un autre agent d'entretien",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentEntretien.setPrenom(prenom);

        } else
            throw new Exception("Le prénom de l'agent d'entretien ne peut être modifié car l'événement du controleur est nul");
    }

    /**
     * Modifie l'email d'un agent d'entretien.
     *
     * @param agentEntretien L'agent d'entretien dont l'email doit être modifié.
     * @param email Le nouvel email de l'agent d'entretien.
     * @throws Exception Si l'événement du contrôleur est nul ou si l'email de l'agent d'entretien est déjà utilisé.
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

                    throw new MauvaisChampsException("En changeant l'adresse email de l'agent d'entretien, celui-ci devient identique à un autre agent d'entretien",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentEntretien.setEmail(email);

        } else
            throw new Exception("L'email de l'agent d'entretien ne peut être modifié car l'événement du controleur est nul");
    }

    /**
     * Modifie le numéro de téléphone d'un agent d'entretien.
     *
     * @param agentEntretien L'agent d'entretien dont le numéro de téléphone doit être modifié.
     * @param telephone Le nouveau numéro de téléphone de l'agent d'entretien.
     * @throws Exception Si l'événement du contrôleur est nul ou si le numéro de téléphone de l'agent d'entretien est déjà utilisé.
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

                    throw new MauvaisChampsException("En changeant le numéro de téléphone de l'agent d'entretien, celui-ci devient identique à un autre agent d'entretien",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            agentEntretien.setTelephone(telephone);

        } else
            throw new Exception("Le numéro de téléphone de l'agent d'entretien ne peut être modifié car l'événement du controleur est nul");
    }

    /**
     * Modifie l'heure de début du travail d'un agent d'entretien.
     *
     * @param agentEntretien L'agent d'entretien dont l'heure de début doit être modifiée.
     * @param heureDebut La nouvelle heure de début du travail de l'agent d'entretien.
     * @throws Exception Si l'événement du contrôleur est nul ou si l'heure de début est ultérieure à l'heure de fin.
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
            throw new Exception("L'heure de début de l'agent d'entretien ne peut être modifié car l'événement du controleur est nul");
    }

    /**
     * Modifie l'heure de fin du travail d'un agent d'entretien.
     *
     * @param agentEntretien L'agent d'entretien dont l'heure de fin doit être modifiée.
     * @param heureFin La nouvelle heure de fin du travail de l'agent d'entretien.
     * @throws Exception Si l'événement du contrôleur est nul ou si l'heure de fin est antérieure à l'heure de début.
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
            throw new Exception("L'heure de fin de l'agent d'entretien ne peut être modifié car l'événement du controleur est nul");
    }
}
