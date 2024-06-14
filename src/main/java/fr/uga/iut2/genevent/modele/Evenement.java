package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;
import java.util.HashMap;

public class Evenement {
    private String nom;
    private int maxParticipants;
    private String debut;
    private String fin;

    private TypeEvenement type;
    private Lieu lieu;

    // Module Commerçants
    private HashMap<TypeCommerce, Integer> typeCommerces = null;
    private ArrayList<Commercant> commercants = null;
    private ArrayList<Emplacement> emplacements = null;

    // Module AgentsSecurite
    private ArrayList<AgentSecurite> agentsSecurite = null;
    private ArrayList<Zone> zones = null;

    // Module Entretien
    private ArrayList<AgentEntretien> agentsEntretien = null;

    // Module Participants
    private ArrayList<Participant> participants = null;

    // Module Animation
    private ArrayList<Animation> animations = null;

    public Evenement(String nom, String debut, String fin, TypeEvenement typeEvenement) {
        this.nom = nom;
        this.debut = debut;
        this.fin = fin;
        this.type = typeEvenement;
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



    public void initieCommerce() {
        this.commercants = new ArrayList<>();
        this.emplacements = new ArrayList<>();
        this.typeCommerces = new HashMap<>();
    }

    public void initieSecurite() {
        this.agentsSecurite = new ArrayList<>();
        this.zones = new ArrayList<>();
    }

    public void supprimerSecurite() {
        this.agentsSecurite = null;
        this.zones = null;
    }

    public void initieEntretien() {
        this.agentsEntretien = new ArrayList<>();
    }

    public void supprimerEntretien() {
        this.agentsEntretien = null;
    }

    public void initieParticipant() {
        this.participants = new ArrayList<>();
    }

    public void supprimerParticipant() {
        this.participants = null;
    }

    public void initieAnimation() {
        this.animations = new ArrayList<>();
    }

    public void supprimerAnimation() {
        this.animations = null;
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
}
