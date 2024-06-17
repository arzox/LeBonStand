package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.*;

import java.util.Optional;

/**
 * Sous-contrôleur pour la catégorie "Commerçants"
 */
public class ControleurCommercant {

    private Application application;
    private Evenement evenement;

    public ControleurCommercant(Application application) {
        this.application = application;
    }

    public void inscrireCommercant(String nom, String prenom, String email, String tel, String horaires,
                                Emplacement emplacement, TypeCommerce typeCommerce) {
        if (evenement != null){
            Commercant commercant = new Commercant(nom, prenom, email, tel, horaires, emplacement, typeCommerce);
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
                                   Optional<String> horaires,
                                   Optional<Emplacement> emplacement,
                                   Optional<TypeCommerce> typeCommerce) {
        if (evenement != null){
            nom.ifPresent(commercant::setNom);
            prenom.ifPresent(commercant::setPrenom);
            email.ifPresent(commercant::setEmail);
            tel.ifPresent(commercant::setTelephone);
            horaires.ifPresent(commercant::setHoraires);
            emplacement.ifPresent(commercant::setEmplacement);
            typeCommerce.ifPresent(commercant::setTypeCommerce);
        }
    }

    // Setters

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
}
