package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Participant;

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

    public void inscrireParticipant(String nom, String prenom, String email) {
        if (evenement != null){
            Participant participant = new Participant(nom, prenom, email);
            evenement.inscrireParticipant(participant);
        }
    }

    public void desinscrireParticipant(Participant participant) {
        if (evenement != null){
            evenement.desinscrireParticipant(participant);
        }
    }

    public void modifierParticipant(Participant participant,
                                    Optional<String> nom,
                                    Optional<String> prenom,
                                    Optional<String> email) {
        if (evenement != null){
            nom.ifPresent(participant::setNom);
            prenom.ifPresent(participant::setPrenom);
            email.ifPresent(participant::setEmail);
        }
    }

}
