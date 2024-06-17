package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Animation;
import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;

import java.util.Optional;

/**
 * Sous-contrôleur pour la catégorie "Animations"
 */
public class ControleurAnimation {

    private Application application;
    private Evenement evenement;

    public ControleurAnimation(Application application) {
        this.application = application;
    }

    public void ajouterAnimation(String nom, float prix, String debut, String fin) {
        if (evenement != null){
            Animation animation = new Animation(nom, prix, debut, fin);
            evenement.ajouterAnimation(animation);
        }
    }

    public void supprimerAnimation(Animation animation) {
        if (evenement != null){
            evenement.retirerAnimation(animation);
        }
    }

    public void modifierAnimation(Animation animation,
                                  Optional<String> nom,
                                  Optional<Float> prix,
                                  Optional<String> debut,
                                  Optional<String> fin) {
        if (evenement != null){
            nom.ifPresent(animation::setNom);
            prix.ifPresent(animation::setPrix);
            debut.ifPresent(animation::setDebut);
            fin.ifPresent(animation::setFin);
        }
    }

    // Setters

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
}
