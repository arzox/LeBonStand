package fr.uga.iut2.genevent.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;

public class Evenement implements Serializable {
    private String nom;
    private TypeEvenement type;
    private ArrayList<Fonctionnalite> fonctionnalites;
    private Lieu lieu;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int maxParticipants;

    // Module Commerçants
    private HashMap<TypeCommerce, Integer> typeCommerces = new HashMap<>();
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

    public Evenement(String nom, LocalDate dateDebut, LocalDate dateFin, TypeEvenement typeEvenement, ArrayList<Fonctionnalite> fonctionnalites) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.type = typeEvenement;
        this.fonctionnalites = fonctionnalites;
    }

    // ---------Module Commercants---------

    public void inscrireCommercant(Commercant commercant) {
        this.commercants.add(commercant);
    }

    public ArrayList<Commercant> getCommercants() {
        return commercants;
    }

    public void desinscrireCommercant(Commercant commercant) {
        commercant.setEmplacement(null);
        this.commercants.remove(commercant);
    }

    public void ajouterEmplacement(Emplacement emplacement) {
        this.emplacements.add(emplacement);
    }

    public ArrayList<Emplacement> getEmplacements() {
        return emplacements;
    }

    public void retirerEmplacement(Emplacement emplacement) {
        for (Commercant commercant : emplacement.getCommercants()) {
            commercant.setEmplacement(null);
        }
        this.emplacements.remove(emplacement);
    }

    public void ajouterTypeCommerce(TypeCommerce typeCommerce, int nombre) {
        this.typeCommerces.put(typeCommerce, nombre);
    }

    public HashMap<TypeCommerce, Integer> getTypeCommerces() {
        return typeCommerces;
    }

    public void retirerTypeCommerce(TypeCommerce typeCommerce) {
        for (Commercant commercant : typeCommerce.getCommercants()) {
            commercant.setTypeCommerce(null);
        }
        this.typeCommerces.remove(typeCommerce);
    }

    // ---------Module Securite---------

    public void ajouterAgentSecurite(AgentSecurite agentSecurite) {
        this.agentsSecurite.add(agentSecurite);
    }

    public ArrayList<AgentSecurite> getAgentsSecurite() {
        return agentsSecurite;
    }

    public void supprimerAgentSecurite(AgentSecurite agentSecurite) {
        agentSecurite.setZone(null);
        this.agentsSecurite.remove(agentSecurite);
    }

    public void ajouterZone(Zone zone) {
        zones.add(zone);
    }

    public ArrayList<Zone> getZones() {
        return zones;
    }

    public void supprimerZone(Zone zone) {
        for (AgentSecurite agent : agentsSecurite) {
            if (agent.getZone().equals(zone)) {
                agent.setZone(null);
            }
        }
        zones.remove(zone);
    }

    // ---------Module Entretien---------

    public void ajouterAgentEntretien(AgentEntretien agentEntretien) {
        this.agentsEntretien.add(agentEntretien);
    }

    public ArrayList<AgentEntretien> getAgentsEntretien() {
        return agentsEntretien;
    }

    public void supprimerAgentEntretien(AgentEntretien agentEntretien) {
        this.agentsEntretien.remove(agentEntretien);
    }

    // ---------Module Participants---------

    public void inscrireParticipant(Participant participant) {
        this.participants.add(participant);
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void desinscrireParticipant(Participant participant) {
        this.participants.remove(participant);
    }

    // ---------Module Animation---------

    public void ajouterAnimation(Animation animation) {
        this.animations.add(animation);
    }

    public ArrayList<Animation> getAnimations() {
        return animations;
    }

    public void supprimerAnimation(Animation animation) {
        this.animations.remove(animation);
    }

    // Getters et Setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public ArrayList<Fonctionnalite> getFonctionnalites() {
        return fonctionnalites;
    }

    public void setFonctionnalites(ArrayList<Fonctionnalite> fonctionnalites) {
        this.fonctionnalites = fonctionnalites;
    }

    public TypeEvenement getType() {
        return type;
    }

    public void setType(TypeEvenement type) {
        this.type = type;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    @Override
    public String toString() {
        return this.nom + "\n" + this.dateDebut + " - " + this.dateFin + "\n" + this.type + "\n" + this.fonctionnalites + "\n";
    }
}
