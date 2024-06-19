package fr.uga.iut2.genevent.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

/**
 * La classe Evenement représente un événement avec ses différentes
 * caractéristiques et modules.
 */
public class Evenement implements Serializable {
    private String nom;
    private TypeEvenement type;
    private ArrayList<Fonctionnalite> fonctionnalites;
    private Lieu lieu;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int maxParticipants;

    // Module Commerçants
    private ArrayList<TypeCommerce> typeCommerces = new ArrayList<>();
    private ArrayList<Commercant> commercants = new ArrayList<>();
    private ArrayList<Emplacement> emplacements = new ArrayList<>();

    // Module AgentsSecurite
    private ArrayList<AgentSecurite> agentsSecurite = new ArrayList<>();
    private ArrayList<Zone> zones = new ArrayList<>();

    // Module Entretien
    private ArrayList<AgentEntretien> agentsEntretien = new ArrayList<>();

    // Module Participants
    private ArrayList<Participant> participants = new ArrayList<>();

    // Module Animation
    private ArrayList<Animation> animations = new ArrayList<>();

    /**
     * Constructeur pour la classe Evenement.
     *
     * @param nom Le nom de l'événement.
     * @param dateDebut La date de début de l'événement.
     * @param dateFin La date de fin de l'événement.
     * @param typeEvenement Le type de l'événement.
     * @param fonctionnalites La liste des fonctionnalités de l'événement.
     */
    public Evenement(String nom, LocalDate dateDebut, LocalDate dateFin, TypeEvenement typeEvenement,
            ArrayList<Fonctionnalite> fonctionnalites) {
        setNom(nom);
        setDateDebut(dateDebut);
        setDateFin(dateFin);
        setType(typeEvenement);
        setFonctionnalites(fonctionnalites);
    }

    // ---------Module Commercants---------

    /**
     * Inscrit un commerçant à l'événement.
     *
     * @param commercant Le commerçant à inscrire.
     */
    public void inscrireCommercant(Commercant commercant) {
        this.commercants.add(commercant);
    }

    /**
     * Obtient la liste des commerçants inscrits à l'événement.
     *
     * @return La liste des commerçants.
     */
    public ArrayList<Commercant> getCommercants() {
        return commercants;
    }

    /**
     * Désinscrit un commerçant de l'événement.
     *
     * @param commercant Le commerçant à désinscrire.
     */
    public void desinscrireCommercant(Commercant commercant) {
        commercant.setEmplacement(null);
        this.commercants.remove(commercant);
    }

    /**
     * Ajoute un emplacement à l'événement.
     *
     * @param emplacement L'emplacement à ajouter.
     */
    public void ajouterEmplacement(Emplacement emplacement) {
        this.emplacements.add(emplacement);
    }

    /**
     * Obtient la liste des emplacements de l'événement.
     *
     * @return La liste des emplacements.
     */
    public ArrayList<Emplacement> getEmplacements() {
        return emplacements;
    }

    /**
     * Retire un emplacement de l'événement.
     *
     * @param emplacement L'emplacement à retirer.
     */
    public void retirerEmplacement(Emplacement emplacement) {
        for (Commercant commercant : emplacement.getCommercants()) {
            commercant.setEmplacement(null);
        }
        this.emplacements.remove(emplacement);
    }

    /**
     * Ajoute un type de commerce à l'événement.
     *
     * @param typeCommerce Le type de commerce à ajouter.
     */
    public void ajouterTypeCommerce(TypeCommerce typeCommerce) {
        this.typeCommerces.add(typeCommerce);
    }

    /**
     * Obtient les types de commerces présents à l'événement.
     *
     * @return Les types de commerces.
     */
    public ArrayList<TypeCommerce> getTypeCommerces() {
        return typeCommerces;
    }

    /**
     * Retire un type de commerce de l'événement.
     *
     * @param typeCommerce Le type de commerce à retirer.
     */
    public void retirerTypeCommerce(TypeCommerce typeCommerce) {
        for (Commercant commercant : typeCommerce.getCommercants()) {
            commercant.setTypeCommerce(null);
        }
        this.typeCommerces.remove(typeCommerce);
    }

    // ---------Module Securite---------

    /**
     * Ajoute un agent de sécurité à l'événement.
     *
     * @param agentSecurite L'agent de sécurité à ajouter.
     */
    public void ajouterAgentSecurite(AgentSecurite agentSecurite) {
        this.agentsSecurite.add(agentSecurite);
    }

    /**
     * Obtient la liste des agents de sécurité de l'événement.
     *
     * @return La liste des agents de sécurité.
     */
    public ArrayList<AgentSecurite> getAgentsSecurite() {
        return agentsSecurite;
    }

    /**
     * Supprime un agent de sécurité de l'événement.
     *
     * @param agentSecurite L'agent de sécurité à supprimer.
     */
    public void supprimerAgentSecurite(AgentSecurite agentSecurite) {
        agentSecurite.setZone(null);
        this.agentsSecurite.remove(agentSecurite);
    }

    /**
     * Ajoute une zone donnée à la liste des zones.
     * @param zone Zone ajoutée
     */
    public void ajouterZone(Zone zone) {
        zones.add(zone);
    }

    /**
     * Obtient la liste des zones de sécurité de l'événement.
     *
     * @return La liste des zones de sécurité.
     */
    public ArrayList<Zone> getZones() {
        return zones;
    }

    /**
     * Retire une zone de l'événement.
     *
     * @param zone La zone à retirer.
     */
    public void supprimerZone(Zone zone) {
        for (AgentSecurite agent : agentsSecurite) {
            if (agent.getZone().equals(zone)) {
                agent.setZone(null);
            }
        }
        zones.remove(zone);
    }

    // ---------Module Entretien---------

    /**
     * Ajoute un agent d'entretien à l'événement.
     *
     * @param agentEntretien L'agent d'entretien à ajouter.
     */
    public void ajouterAgentEntretien(AgentEntretien agentEntretien) {
        this.agentsEntretien.add(agentEntretien);
    }

    /**
     * Obtient la liste des agents d'entretien de l'événement.
     *
     * @return La liste des agents d'entretien.
     */
    public ArrayList<AgentEntretien> getAgentsEntretien() {
        return agentsEntretien;
    }

    /**
     * Supprime un agent d'entretien de l'événement.
     *
     * @param agentEntretien L'agent d'entretien à supprimer.
     */
    public void supprimerAgentEntretien(AgentEntretien agentEntretien) {
        this.agentsEntretien.remove(agentEntretien);
    }

    // ---------Module Participants---------

    /**
     * Inscrit un participant à l'événement.
     *
     * @param participant Le participant à inscrire.
     */
    public void inscrireParticipant(Participant participant) {
        this.participants.add(participant);
    }

    /**
     * Obtient la liste des participants de l'événement.
     *
     * @return La liste des participants.
     */
    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    /**
     * Désinscrit un participant de l'événement.
     *
     * @param participant Le participant à désinscrire.
     */
    public void desinscrireParticipant(Participant participant) {
        this.participants.remove(participant);
    }

    // ---------Module Animation---------

    /**
     * Ajoute une animation à l'événement.
     *
     * @param animation L'animation à ajouter.
     */
    public void ajouterAnimation(Animation animation) {
        this.animations.add(animation);
    }

    /**
     * Obtient la liste des animations de l'événement.
     *
     * @return La liste des animations.
     */
    public ArrayList<Animation> getAnimations() {
        return animations;
    }

    /**
     * Retire une animation de l'événement.
     *
     * @param animation L'animation à retirer.
     */
    public void supprimerAnimation(Animation animation) {
        this.animations.remove(animation);
    }

    // Getters et Setters

    /**
     * Obtient le nom de l'événement.
     *
     * @return Le nom de l'événement.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Définit le nom de l'événement.
     *
     * @param nom Le nom de l'événement.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Obtient la date de début de l'événement.
     *
     * @return La date de début de l'événement.
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * Définit la date de début de l'événement.
     *
     * @param dateDebut La date de début de l'événement.
     */
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Obtient la date de fin de l'événement.
     *
     * @return La date de fin de l'événement.
     */
    public LocalDate getDateFin() {
        return dateFin;
    }

    /**
     * Définit la date de fin de l'événement.
     *
     * @param dateFin La date de fin de l'événement.
     */
    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    /**
     * Obtient la liste des fonctionnalités de l'événement.
     *
     * @return La liste des fonctionnalités.
     */
    public ArrayList<Fonctionnalite> getFonctionnalites() {
        return fonctionnalites;
    }

    /**
     * Définit la liste des fonctionnalités de l'événement.
     *
     * @param fonctionnalites La liste des fonctionnalités.
     */
    public void setFonctionnalites(ArrayList<Fonctionnalite> fonctionnalites) {
        this.fonctionnalites = fonctionnalites;
    }

    public void addFonctionnalite(Fonctionnalite fonctionnalite) {
        if (!this.fonctionnalites.contains(fonctionnalite)) {
            this.fonctionnalites.add(fonctionnalite);
        }
    }

    public void removeFonctionnalite(Fonctionnalite fonctionnalite) {
        this.fonctionnalites.remove(fonctionnalite);
    }

    /**
     * Obtient le type de l'événement.
     *
     * @return Le type de l'événement.
     */
    public TypeEvenement getType() {
        return type;
    }

    /**
     * Définit le type de l'événement.
     *
     * @param type Le type de l'événement.
     */
    public void setType(TypeEvenement type) {
        this.type = type;
    }

    /**
     * Obtient le lieu de l'événement.
     *
     * @return Le lieu de l'événement.
     */
    public Lieu getLieu() {
        return lieu;
    }

    /**
     * Définit le lieu de l'événement.
     *
     * @param lieu Le lieu de l'événement.
     */
    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    /**
     * Obtient le nombre maximum de participants à l'événement.
     *
     * @return Le nombre maximum de participants.
     */
    public int getMaxParticipants() {
        return maxParticipants;
    }

    /**
     * Définit le nombre maximum de participants à l'événement.
     *
     * @param maxParticipants Le nombre maximum de participants.
     */
    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    /**
     * Retourne une chaîne de caractères représentant l'événement.
     *
     * @return Une chaîne de caractères représentant l'événement.
     */
    @Override
    public String toString() {
        return this.nom + "\n" + this.dateDebut + " - " + this.dateFin + "\n" + this.type + "\n" + this.fonctionnalites + "\n";
    }
}
