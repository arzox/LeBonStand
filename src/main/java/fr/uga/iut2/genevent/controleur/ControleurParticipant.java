package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;
import fr.uga.iut2.genevent.modele.Participant;
import fr.uga.iut2.genevent.vue.IHM;

import java.util.Optional;

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
        if (evenement != null) {
            Participant participant = new Participant(nom, prenom, email);
            evenement.inscrireParticipant(participant);
        } else
            throw new Error("Le participant ne peut être inscrit car l'événement du controleur est nul");
    }

    public void desinscrireParticipant(Participant participant) {
        if (evenement != null){
            evenement.desinscrireParticipant(participant);
        } else
            throw new Error("Le participant ne peut être désinscri car l'événement du controleur est nul");
    }

    public void modifierInformationsParticipant(Participant participant,
                                                Optional<String> nom,
                                                Optional<String> prenom,
                                                Optional<String> email,
                                                IHM ihm) {
        if (evenement != null) {
            for (Participant participantCourant : evenement.getParticipants()) {
                String nomCourant = participantCourant.getNom();
                String prenomCourant = participantCourant.getPrenom();
                String emailCourant = participantCourant.getEmail();

                if (nom.equals(nomCourant) & prenom.equals(prenomCourant) & email.equals(emailCourant))
                    ihm.informerUtilisateur("Le participant que vous souhaitez inscrire existe déjà", false);
                else {
                    nom.ifPresent(participant::setNom);
                    prenom.ifPresent(participant::setPrenom);
                    email.ifPresent(participant::setEmail);
                }
            }
        } else
            throw new Error("Les informations du participant ne peuvent être modifiées car l'événement du controleur est nul");
    }
}
