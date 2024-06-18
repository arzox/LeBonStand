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

    public ControleurAgentEntretien(Application application) {
        this.application = application;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public void ajouterAgentEntretien(String nom, String prenom, String email, String telephone, int heureDebut, int heureFin) throws Exception {
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
            evenement.ajouterAgentEntretien(new AgentEntretien(nom, prenom, email, telephone, heureDebut, heureFin));

        } else
            throw new Exception("L'agent d'entretien ne peut être ajouté car l'événement du controleur est nul");
    }

    public void supprimerAgentEntretien(AgentEntretien agentEntretien) throws Exception {
        if (evenement != null) {
            evenement.supprimerAgentEntretien(agentEntretien);
        } else
            throw new Exception("L'agent d'entretien ne peut être suppimé car l'événement du controleur est nul");
    }

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
