package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.exception.MauvaisChampsException;
import fr.uga.iut2.genevent.modele.Animation;
import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Evenement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Contrôleur pour le module "Animation"
 */
public class ControleurAnimation {

    private Application application;
    private Evenement evenement;

    /**
     * Crée le contrôleur, doit être uniquement appelée par le constructeur de la classe Controleur.
     * @param application L'application que le contrôleur gérera
     */
    public ControleurAnimation(Application application) {
        this.application = application;
    }

    /**
     * Permet d'attribuer un événement au contrôleur.
     * @param evenement Événement attribué au contrôleur
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    // Animation

    /**
     * Crée une nouvelle animation et l'ajoute à la liste des animations de cet événement.
     * @param nom Nom de l'animation
     * @param prix Prix de l'animation
     * @param dateHeureDebut Date et heure de début de l'animation
     * @param dateHeureFin Date et heure de fin de l'animation
     * @return L'animation créée.
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si le nom est identique à celui d'une autre animation ou
     * si la date et l'heure de début de l'animation est ultérieure à la date et l'heure de fin de l'animation ou encore
     * si les dates ne sont pas comprises entre les dates de début et de fin de cet événement.
     */
    public Animation ajouterAnimation(String nom, float prix, LocalDateTime dateHeureDebut, LocalDateTime dateHeureFin) throws Exception {
        if (evenement != null) {

            LocalDate dateDebut = dateHeureDebut.toLocalDate();
            LocalDate dateFin = dateHeureFin.toLocalDate();
            boolean isStartAfterEnd = dateHeureDebut.isAfter(dateHeureFin);
            boolean isStartBeforeEventStart = dateDebut.isBefore(evenement.getDateDebut());
            boolean isEndAfterEventEnd = dateFin.isAfter(evenement.getDateFin());

            for (Animation animationCourante : evenement.getAnimations()) {

                String nomCourant = animationCourante.getNom();
                boolean isNotUnique = nom.equals(nomCourant);

                if (isNotUnique & isStartAfterEnd) {

                    throw new MauvaisChampsException("L'animation que vous souhaitez ajouter existe déjà et " +
                            "la date et l'heure de début est ultérieure à sa date et son heure de fin",
                            new ArrayList<>(Arrays.asList(false, true, false, false)));

                } else if (isNotUnique & isStartBeforeEventStart & isEndAfterEventEnd) {

                    throw new MauvaisChampsException("L'animation que vous souhaitez ajouter existe déjà, " +
                            "la date de début de l'animation est antérieure à l'heure de début de l'événement et " +
                            "la date de fin de l'animation est ultérieure à l'heure de fin de l'événement",
                            new ArrayList<>(Arrays.asList(false, true, false, false)));

                } else if (isNotUnique & isStartBeforeEventStart) {

                    throw new MauvaisChampsException("L'animation que vous souhaitez ajouter existe déjà et " +
                            "la date de début de l'animation est antérieure à l'heure de début de l'événement",
                            new ArrayList<>(Arrays.asList(false, true, false, true)));

                } else if (isNotUnique & isEndAfterEventEnd) {

                    throw new MauvaisChampsException("L'animation que vous souhaitez ajouter existe déjà et " +
                            "la date de fin de l'animation est ultérieure à l'heure de fin de l'événement",
                            new ArrayList<>(Arrays.asList(false, true, true, false)));

                } else if (isNotUnique) {

                    throw new MauvaisChampsException("L'animation que vous souhaitez ajouter existe déjà",
                            new ArrayList<>(Arrays.asList(false, true, true, true)));
                }
            }

            if (isStartAfterEnd) {

                throw new MauvaisChampsException("La date et l'heure de début de l'animation est ultérieure à sa date et son heure de fin",
                        new ArrayList<>(Arrays.asList(true, true, false, false)));

            } else if (isStartBeforeEventStart & isEndAfterEventEnd) {

                throw new MauvaisChampsException("La date de début de l'animation est antérieure à l'heure de début de l'événement et " +
                        "la date de fin de l'animation est ultérieure à l'heure de fin de l'événement",
                        new ArrayList<>(Arrays.asList(true, true, false, false)));

            } else if (isStartBeforeEventStart) {

                throw new MauvaisChampsException("La date de début de l'animation est antérieure à la date de début de l'événement",
                        new ArrayList<>(Arrays.asList(true, true, false, true)));

            } else if (isEndAfterEventEnd) {

                throw new MauvaisChampsException("La date de fin de l'animation est ultérieure à la date de fin de l'événement",
                        new ArrayList<>(Arrays.asList(true, true, true, false)));
            }
            Animation nouvelleAnimation = new Animation(nom, prix, dateHeureDebut, dateHeureFin);
            evenement.ajouterAnimation(nouvelleAnimation);

            return nouvelleAnimation;

        } else
            throw new Exception("L'animation ne peut être ajoutée car l'événement du contrôleur est nul");
    }

    /**
     * Récupère une animation à partir de son nom.
     * @param nom Nom de l'animation qu'on souhaite récupérer
     * @return L'animation correspondante au nom donné en paramètre ou null si elle n'existe pas.
     * @throws Exception Si l'événement est nul.
     */
    public Animation getAnimation(String nom) throws Exception {
        if (evenement != null) {

            for (Animation animation : evenement.getAnimations()) {

                String nomCourant = animation.getNom();
                boolean animationFound = nom.equals(nomCourant);

                if (animationFound) {

                    return animation;
                }
            }
            return null;

        } else
            throw new Exception("L'animation ne peut être récupérée car l'événement du contrôleur est nul");
    }

    /**
     * Retire de la liste des animations de cet événement l'animation donnée en paramètre.
     * @param animation Animation à retirer
     * @throws Exception Si l'événement est nul.
     */
    public void supprimerAnimation(Animation animation) throws Exception {
        if (evenement != null) {

            evenement.supprimerAnimation(animation);

        } else
            throw new Exception("L'animation ne peut être supprimée car l'événement du contrôleur est nul");
    }

    /**
     * Modifie le nom de l'animation donné en paramètre.
     * @param animation L'animation dont le nom doit être modifié
     * @param nom Nouveau nom
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si le nouveau nom rend l'animation identique à une autre animation.
     */
    public void modifierNomAnimation(Animation animation, String nom) throws Exception {
        if (evenement != null) {

            for (Animation animationCourante : evenement.getAnimations()) {

                String nomCourant = animationCourante.getNom();
                boolean isNotUnique = nom.equals(nomCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le nom de l'animation, celle-ci devient identique à une autre animation",
                            new ArrayList<>(Arrays.asList(false, true, true, true)));
                }
            }
            animation.setNom(nom);

        } else
            throw new Exception("Le nom de l'animation ne peut être modifié car l'événement du contrôleur est nul");
    }

    /**
     * Modifie le prix de l'animation donné en paramètre.
     * @param animation L'animation dont le prix doit être modifié
     * @param prix Nouveau prix
     * @throws Exception Si l'événement est nul.
     */
    public void modifierPrixAnimation(Animation animation, float prix) throws Exception {
        if (evenement != null) {

            animation.setPrix(prix);

        } else
            throw new Exception("Le prix de l'animation ne peut être modifié car l'événement du contrôleur est nul");
    }

    /**
     * Modifie la date et l'heure de début de l'animation données en paramètre.
     * @param animation L'animation dont la date et l'heure de début doivent être modifiées
     * @param dateHeureDebut Nouvelle date et heure de début
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si la nouvelle date et heure de début de l'animation est ultérieure à sa date et heure de fin ou
     * si la nouvelle date de début n'est pas comprise entre les dates de début et de fin de cet événement.
     */
    public void modifierDebutAnimation(Animation animation, LocalDateTime dateHeureDebut) throws Exception {
        if (evenement != null) {

            LocalDate dateDebut = dateHeureDebut.toLocalDate();
            boolean isStartAfterEnd = dateHeureDebut.isAfter(animation.getDateHeureFin());
            boolean isStartBeforeEventStart = dateDebut.isBefore(evenement.getDateDebut());

            if (isStartAfterEnd) {

                throw new MauvaisChampsException("La date et l'heure de début de l'animation est ultérieure à sa date et son heure de fin",
                        new ArrayList<>(Collections.singleton(false)));

            } else if (isStartBeforeEventStart) {

                throw new MauvaisChampsException("La date de début de l'animation est antérieure à la date de début de l'événement",
                        new ArrayList<>(Collections.singleton(false)));
            }
            animation.setDateHeureDebut(dateHeureDebut);

        } else
            throw new Exception("La date de début de l'animation ne peut être modifiée car l'événement du contrôleur est nul");
    }

    /**
     * Modifie la date et l'heure de fin de l'animation donnée en paramètre.
     * @param animation L'animation dont la date et l'heure de fin doivent être modifiées
     * @param dateHeureFin Nouvelle date et heure de fin
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si la nouvelle date et heure de fin de l'animation est antérieure à sa date et heure de début ou
     * si la nouvelle date de fin n'est pas comprise entre les dates de début et de fin de cet événement.
     */
    public void modifierFinAnimation(Animation animation, LocalDateTime dateHeureFin) throws Exception {
        if (evenement != null) {

            LocalDate dateFin = dateHeureFin.toLocalDate();
            boolean isEndBeforeStart = dateHeureFin.isBefore(animation.getDateHeureDebut());
            boolean isEndAfterEventEnd = dateFin.isAfter(evenement.getDateFin());

            if (isEndBeforeStart) {

                throw new MauvaisChampsException("La date et l'heure de fin de l'animation est antérieure à sa date et son heure de début",
                        new ArrayList<>(Collections.singleton(false)));

            } else if (isEndAfterEventEnd) {

                throw new MauvaisChampsException("La date de fin de l'animation est ultérieure à la date de fin de l'événement",
                        new ArrayList<>(Collections.singleton(false)));
            }
            animation.setDateHeureFin(dateHeureFin);

        } else
            throw new Exception("La date de fin de l'animation ne peut être modifiée car l'événement du contrôleur est nul");
    }
}
