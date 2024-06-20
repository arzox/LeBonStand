package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.modele.Application;
import fr.uga.iut2.genevent.modele.Employe;
import fr.uga.iut2.genevent.modele.Evenement;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * La classe Controleur fournit une structure de base pour gérer le modèle en
 * faisant l'intermédiaire entre celui-ci et le package vue (partie IHM)
 */
public class Controleur {

    private static Controleur instance;

    private Application application;
    ControleurAgentSecurite controleurAgentSecurite;
    ControleurAgentEntretien controleurAgentEntretien;
    ControleurParticipant controleurParticipant;
    ControleurCommercant controleurCommercant;
    ControleurEvenement controleurEvenement;
    ControleurAnimation controleurAnimation;

    /**
     * Méthode appelée pour créer un controleur s'il n'en existe pas déjà ou pour
     * récupérer le controleur déjà existant.
     * 
     * @param application L'application que le controleur gérera.
     * @return un nouveau controleur ou le controleur déjà existant.
     */
    public static Controleur getInstance(Application application) {
        if (instance == null) {
            instance = new Controleur(application);
        }
        return instance;
    }

    /**
     * Méthode appelée uniquement par la méthode getInstance afin de créer un
     * nouveau controleur.
     * 
     * @param application L'application que le controleur gérera.
     */
    private Controleur(Application application) {
        this.application = application;
        controleurAgentSecurite = new ControleurAgentSecurite(application);
        controleurAgentEntretien = new ControleurAgentEntretien(application);
        controleurParticipant = new ControleurParticipant(application);
        controleurCommercant = new ControleurCommercant(application);
        controleurEvenement = new ControleurEvenement(application);
        controleurAnimation = new ControleurAnimation(application);
    }

    /**
     * Permet de changer d'événement courant : cette méthode est appelée lorsqu'on
     * choisit un événement sur la page d'accueil, depuis les classes du package
     * {@code vue}
     *
     * @param evenement L'événement qui doit devenir l'événement courant
     */
    public void setEvenementCourant(Evenement evenement) {
        controleurEvenement.setEvenement(evenement);
        controleurAgentEntretien.setEvenement(evenement);
        controleurAgentSecurite.setEvenement(evenement);
        controleurParticipant.setEvenement(evenement);
        controleurCommercant.setEvenement(evenement);
        controleurAnimation.setEvenement(evenement);
    }

    public ControleurAgentSecurite getControleurAgentSecurite() {
        return controleurAgentSecurite;
    }

    public ControleurAgentEntretien getControleurAgentEntretien() {
        return controleurAgentEntretien;
    }

    public ControleurParticipant getControleurParticipant() {
        return controleurParticipant;
    }

    public ControleurCommercant getControleurCommercant() {
        return controleurCommercant;
    }

    public ControleurEvenement getControleurEvenement() {
        return controleurEvenement;
    }

    public ControleurAnimation getControleurAnimation() {
        return controleurAnimation;
    }

    public ArrayList<Evenement> getEvents() {
        if (application == null) {
            return new ArrayList<>();
        }
        return application.getEvenements();
    }

    public void envoyerMail(ArrayList<Employe> employes) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.MAIL)) {
            try {
                StringBuilder mailto = new StringBuilder("mailto:");
                for (Employe employe : employes) {
                    mailto.append(URLEncoder.encode(employe.getEmail(), StandardCharsets.UTF_8.toString())).append(";");
                }
                // Retirer le dernier point-virgule
                if (mailto.length() > 7) {
                    mailto.setLength(mailto.length() - 1);
                }
                mailto.append("?subject=").append(URLEncoder.encode("Sujet de l'email", StandardCharsets.UTF_8.toString()))
                        .append("&body=").append(URLEncoder.encode("Corps de l'email", StandardCharsets.UTF_8.toString()));

                Desktop.getDesktop().mail(new URI(mailto.toString()));
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Erreur d'entrée/sortie : " + e.getMessage());
            } catch (URISyntaxException e) {
                e.printStackTrace();
                System.out.println("Erreur de syntaxe URI : " + e.getMessage());
            }
        } else {
            System.out.println("Le client mail n'est pas supporté sur ce système.");
        }
    }
    public void supprimerEvenement(Evenement evenement) {
        application.removeEvenement(evenement);
    }
}
