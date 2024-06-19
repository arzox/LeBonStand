package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.exception.MauvaisChampsException;
import fr.uga.iut2.genevent.modele.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Contrôleur pour la catégorie "Participants"
 */
public class ControleurParticipant {

    private Application application;
    private Evenement evenement;

    /**
     * Crée le contrôleur, doit être uniquement appelée par le constructeur de la classe Controleur.
     * @param application L'application que le contrôleur gérera
     */
    public ControleurParticipant(Application application) {
        this.application = application;
    }

    /**
     * Attribue un événement au contrôleur.
     * @param evenement L'événement à attribuer
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    // Participant

    /**
     * Crée un nouveau participant et l'ajoute à la liste des participants de cet événement.
     * @param nom Nom du participant
     * @param prenom Prénom du participant
     * @param email Email du participant
     * @return Le participant créé.
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si le nom, prénom et adresse email sont identiques à ceux d'un autre participant.
     */
    public Participant inscrireParticipant(String nom, String prenom, String email) throws Exception {
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
            Participant nouveauParticipant = new Participant(nom, prenom, email);
            evenement.inscrireParticipant(nouveauParticipant);

            return nouveauParticipant;

        } else
            throw new Exception("Le participant ne peut être inscrit car l'événement du contrôleur est nul");
    }

    /**
     * Récupère un participant à partir de son nom, prénom et adresse email.
     * @param nom Le nom du participant qu'on souhaite récupérer
     * @param prenom Le prénom du participant qu'on souhaite récupérer
     * @param email L'adresse email du participant qu'on souhaite récupérer
     * @return Le participant correspondant aux attributs donnés en paramètres ou null s'il n'existe pas.
     * @throws Exception Si l'événement est nul.
     */
    public Participant getParticipant(String nom, String prenom, String email) throws Exception {
        if (evenement != null) {

            for (Participant participant : evenement.getParticipants()) {

                String nomCourant = participant.getNom();
                String prenomCourant = participant.getPrenom();
                String emailCourant = participant.getEmail();

                if (nom.equals(nomCourant) & prenom.equals(prenomCourant) & email.equals(emailCourant)) {

                    return participant;
                }
            }
            return null;

        } else
            throw new Exception("Le participant ne peut être récupéré car l'événement du contrôleur est nul");
    }

    /**
     * Retire de la liste des participants de cet événement le participant donné en paramètre.
     * @param participant Le participant à retirer
     * @throws Exception Si l'événement est nul.
     */
    public void desinscrireParticipant(Participant participant) throws Exception {
        if (evenement != null) {

            evenement.desinscrireParticipant(participant);

        } else
            throw new Exception("Le participant ne peut être désinscrit car l'événement du contrôleur est nul");
    }

    /**
     * Modifie le nom du participant donné en paramètre.
     * @param participant Le participant dont le nom doit être modifié
     * @param nom Nouveau nom
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si le nouveau nom rend le participant identique à un autre participant.
     */
    public void modifierNomParticipant(Participant participant, String nom) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                boolean isNotUnique = nom.equals(nomCourant) & participant.getPrenom().equals(prenomCourant)
                        & participant.getEmail().equals(emailCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le nom du participant, " +
                            "celui-ci devient identique à un autre participant",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            participant.setNom(nom);

        } else
            throw new Exception("Le nom du participant ne peut être modifié car l'événement du contrôleur est nul");
    }

    /**
     * Modifie le prénom du participant donné en paramètre.
     * @param participant Le participant dont le prénom doit être modifié
     * @param prenom Nouveau prénom
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si le nouveau prénom rend le participant identique à un autre participant.
     */
    public void modifierPrenomParticipant(Participant participant, String prenom) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                boolean isNotUnique = participant.getNom().equals(nomCourant) & prenom.equals(prenomCourant)
                        & participant.getEmail().equals(emailCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le prénom du participant, " +
                            "celui-ci devient identique à un autre participant",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            participant.setPrenom(prenom);

        } else
            throw new Exception("Le prénom du participant ne peut être modifié car l'événement du contrôleur est nul");
    }

    /**
     * Modifie l'adresse email du participant donnée en paramètre.
     * @param participant Le participant dont l'adresse email doit être modifiée
     * @param email Nouvelle adresse email
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si la nouvelle adresse email rend le participant identique à un autre participant.
     */
    public void modifierEmailParticipant(Participant participant, String email) throws Exception {
        if (evenement != null) {

            for (AgentEntretien agent : evenement.getAgentsEntretien()) {

                String nomCourant = agent.getNom();
                String prenomCourant = agent.getPrenom();
                String emailCourant = agent.getEmail();
                boolean isNotUnique = participant.getNom().equals(nomCourant) & participant.getPrenom().equals(prenomCourant)
                        & email.equals(emailCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant l'adresse email du participant, " +
                            "celui-ci devient identique à un autre participant",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            participant.setEmail(email);

        } else
            throw new Exception("L'email du participant ne peut être modifié car l'événement du contrôleur est nul");
    }
}
