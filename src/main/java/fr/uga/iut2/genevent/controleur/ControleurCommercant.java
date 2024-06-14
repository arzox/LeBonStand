package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.*;

import java.util.Optional;

public class ControleurCommercant {

    private Application application;
    private Evenement evenement;

    public ControleurCommercant(Application application) {
        this.application = application;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    public void inscrireCommercant(String nom, String prenom, String email, String tel, int heureDebut, int heureFin,
                                Emplacement emplacement, TypeCommerce typeCommerce) {
        if (evenement != null){
            Commercant commercant = new Commercant(nom, prenom, email, tel, heureDebut, heureFin, emplacement, typeCommerce);
            evenement.inscrireCommercant(commercant);
        }
    }

    public void desinscrireCommercant(Commercant commercant) {
        if (evenement != null){
            evenement.desinscrireCommercant(commercant);
        }
    }

    public void modifierCommercant(Commercant commercant,
                                   Optional<String> nom,
                                   Optional<String> prenom,
                                   Optional<String> email,
                                   Optional<String> tel,
                                   Optional<Integer> heureDebut,
                                   Optional<Integer> heureFin,
                                   Optional<Emplacement> emplacement,
                                   Optional<TypeCommerce> typeCommerce) {
        if (evenement != null){
            nom.ifPresent(commercant::setNom);
            prenom.ifPresent(commercant::setPrenom);
            email.ifPresent(commercant::setEmail);
            tel.ifPresent(commercant::setTelephone);
            heureDebut.ifPresent(commercant::setHeureDebut);
            heureFin.ifPresent(commercant::setHeureFin);
            emplacement.ifPresent(commercant::setEmplacement);
            typeCommerce.ifPresent(commercant::setTypeCommerce);
        }
    }
}
