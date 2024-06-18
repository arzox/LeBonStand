package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.exception.MauvaisChampsException;
import fr.uga.iut2.genevent.modele.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

/**
 * Sous-contrôleur pour la catégorie "Participants"
 */
public class ControleurParticipant {

    private Application application;
    private Evenement evenement;

    public ControleurParticipant(Application application) {
        this.application = application;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public void inscrireParticipant(String nom, String prenom, String email) throws Exception {
        if (evenement != null) {

            for (Participant participantCourant : evenement.getParticipants()) {

                String nomCourant = participantCourant.getNom();
                String prenomCourant = participantCourant.getPrenom();
                String emailCourant = participantCourant.getEmail();
                boolean isNotUnique = nom.equals(nomCourant) & prenom.equals(prenomCourant) & email.equals(emailCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("Le participant que vous souhaitez inscrire existe déjà",
                            new ArrayList<>(Arrays.asList(false, false, false)));
                }
            }
            evenement.inscrireParticipant(new Participant(nom, prenom, email));

        } else
            throw new Exception("Le participant ne peut être inscrit car l'événement du controleur est nul");
    }

    public void desinscrireParticipant(Participant participant) throws Exception {
        if (evenement != null) {

            evenement.desinscrireParticipant(participant);

        } else
            throw new Exception("Le participant ne peut être désinscrit car l'événement du controleur est nul");
    }

    public void modifierNomParticipant(Participant participant, String nom) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                boolean isNotUnique = nom.equals(nomCourant) & participant.getPrenom().equals(prenomCourant)
                        & participant.getEmail().equals(emailCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le nom du participant, celui-ci devient identique à un autre participant",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            participant.setNom(nom);

        } else
            throw new Exception("Le nom du participant ne peut être modifié car l'événement du controleur est nul");
    }

    public void modifierPrenomParticipant(Participant participant, String prenom) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                boolean isNotUnique = participant.getNom().equals(nomCourant) & prenom.equals(prenomCourant)
                        & participant.getEmail().equals(emailCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le prénom du participant, celui-ci devient identique à un autre participant",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            participant.setPrenom(prenom);

        } else
            throw new Exception("Le prénom du participant ne peut être modifié car l'événement du controleur est nul");
    }

    public void modifierEmailParticipant(Participant participant, String email) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                boolean isNotUnique = participant.getNom().equals(nomCourant) & participant.getPrenom().equals(prenomCourant)
                        & email.equals(emailCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant l'adresse email du participant, celui-ci devient identique à un autre participant",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            participant.setEmail(email);

        } else
            throw new Exception("L'email du participant ne peut être modifié car l'événement du controleur est nul");
    }
}
