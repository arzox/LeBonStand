package fr.uga.iut2.genevent.modele;

import java.util.ArrayList;

public enum TypeEvenement {

    MARCHE_PRODUCTEUR ("Marché de producteurs"),
    MARCHE_NOEL ("Marché de Noël"),
    VIDE_GRENIER ("Vide-grenier"),
    BROCANTE ("Brocante"),
    AUTRE ("Autre");

    private final String type;

    TypeEvenement(String type) {
        this.type = type;
    }

    public static ArrayList<String> getTypesEvenement() {
        ArrayList<String> types = new ArrayList<>();
        for (TypeEvenement type : TypeEvenement.values()) {
            types.add(type.type);
        }
        return types;
    }

    public static TypeEvenement fromString(String type) {
        for (TypeEvenement typeEvenement : TypeEvenement.values()) {
            if (typeEvenement.type.equals(type)) {
                return typeEvenement;
            }
        }
        return null;
    }
}
