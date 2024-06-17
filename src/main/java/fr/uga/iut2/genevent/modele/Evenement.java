package fr.uga.iut2.genevent.modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Evenement implements Serializable {
    private String nom;
    private int maxParticipants;
    private String debut;
    private String fin;
    private ArrayList<Fonctionnalite> fonctionnalites;

    private TypeEvenement type;
    private Lieu lieu;

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

    public Evenement(String nom, String debut, String fin, TypeEvenement typeEvenement, ArrayList<Fonctionnalite> fonctionnalites) {
        this.nom = nom;
        this.debut = debut;
        this.fin = fin;
        this.type = typeEvenement;
        this.fonctionnalites = fonctionnalites;
    }

    // ---------Module Commercants---------

    public void inscrireCommercant(Commercant commercant) {
        this.commercants.add(commercant);
    }

    public void desinscrireCommercant(Commercant commercant) {
        commercant.setEmplacement(null);
        this.commercants.remove(commercant);
    }

    public void ajouterEmplacement(Emplacement emplacement) {
        this.emplacements.add(emplacement);
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

    public void retirerTypeCommerce(TypeCommerce typeCommerce) {
        for (Commercant commercant : typeCommerce.getCommercants()) {
            commercant.setTypeCommerce(null);
        }
        this.typeCommerces.remove(typeCommerce);
    }

    // ---------Module Securite---------

    public void inscireAgentSecurite(AgentSecurite agentSecurite) {
        this.agentsSecurite.add(agentSecurite);
    }

    public void desinscrireAgentSecurite(AgentSecurite agentSecurite) {
        agentSecurite.setZone(null);
        this.agentsSecurite.remove(agentSecurite);
    }

    public void removeZone(Zone zone) {
        for (AgentSecurite agent : agentsSecurite) {
            if (agent.getZone().equals(zone)) {
                agent.setZone(null);
            }
        }
        zones.remove(zone);
    }

    // ---------Module Entretien---------

    public void inscrireAgentEntretien(AgentEntretien agentEntretien) {
        this.agentsEntretien.add(agentEntretien);
    }

    public void desinscrireAgentEntretien(AgentEntretien agentEntretien) {
        this.agentsEntretien.remove(agentEntretien);
    }

    // ---------Module Participants---------

    public void inscrireParticipant(Participant participant) {
        this.participants.add(participant);
    }

    public void desinscrireParticipant(Participant participant) {
        this.participants.remove(participant);
    }

    // ---------Module Animation---------

    public void ajouterAnimation(Animation animation) {
        this.animations.add(animation);
    }

    public void retirerAnimation(Animation animation) {
        this.animations.remove(animation);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDebut() {
        return debut;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public ArrayList<Fonctionnalite> getFonctionnalites() {
        return fonctionnalites;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
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

    public HashMap<TypeCommerce, Integer> getTypeCommerces() {
        return typeCommerces;
    }

    public ArrayList<Commercant> getCommercants() {
        return commercants;
    }

    public ArrayList<Emplacement> getEmplacements() {
        return emplacements;
    }

    public ArrayList<AgentSecurite> getAgentsSecurite() {
        return agentsSecurite;
    }

    public ArrayList<Zone> getZones() {
        return zones;
    }

    public ArrayList<AgentEntretien> getAgentsEntretien() {
        return agentsEntretien;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public ArrayList<Animation> getAnimations() {
        return animations;
    }

    @Override
    public String toString() {
        return this.nom + "\n" + this.debut + " - " + this.fin + "\n" + this.type + "\n" + this.fonctionnalites + "\n";
    }
}
