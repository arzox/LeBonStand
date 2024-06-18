package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;

/**
 * L'énumération TypeEvenement représente les différents types d'événements.
 */
public enum TypeEvenement {

    MARCHE_PRODUCTEUR ("Marché de producteurs"),
    MARCHE_NOEL ("Marché de Noël"),
    VIDE_GRENIER ("Vide-grenier"),
    BROCANTE ("Brocante"),
    AUTRE ("Autre");

    private final String type;

    /**
     * Constructeur de l'énumération TypeEvenement.
     *
     * @param type Le type d'événement.
     */
    TypeEvenement(String type) {
        this.type = type;
    }

    /**
     * Récupère la liste des types d'événements.
     *
     * @return La liste des types d'événements.
     */
    public static ArrayList<String> getTypesEvenement() {
        ArrayList<String> types = new ArrayList<>();
        for (TypeEvenement type : TypeEvenement.values()) {
            types.add(type.type);
        }
        return types;
    }

    /**
     * Récupère le type d'événement à partir d'une chaîne de caractères.
     *
     * @param type La chaîne de caractères représentant le type d'événement.
     * @return Le type d'événement correspondant, ou null si aucun type d'événement ne correspond.
     */
    public static TypeEvenement fromString(String type) {
        for (TypeEvenement typeEvenement : TypeEvenement.values()) {
            if (typeEvenement.type.equals(type)) {
                return typeEvenement;
            }
        }
        return null;
    }
}