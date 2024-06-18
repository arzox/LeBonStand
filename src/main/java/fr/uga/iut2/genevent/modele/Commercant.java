package fr.uga.iut2.genevent.modele;

import java.io.Serializable;

public class Commercant extends Employe implements Serializable {
    private Emplacement emplacement = null;
    private TypeCommerce typeCommerce = null;

    public Commercant(String nom, String prenom, String email, String telephone, int heureDebut, int heureFin, Emplacement emplacement, TypeCommerce typeCommerce) {
        super(nom, prenom, email, telephone, heureDebut, heureFin);
        setEmplacement(emplacement);
        this.typeCommerce = typeCommerce;
    }

    public Emplacement getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(Emplacement emplacement) {
        if (this.emplacement != null) {
            this.emplacement.removeCommercant(this);
        }
        this.emplacement = emplacement;
        if (emplacement != null) {
            emplacement.addCommercant(this);
        }
    }

    public void setTypeCommerce(TypeCommerce typeCommerce) {
        if (this.typeCommerce != null) {
            this.typeCommerce.removeCommercant(this);
        }
        this.typeCommerce = typeCommerce;
        if (typeCommerce != null) {
            typeCommerce.addCommercant(this);
        }
    }

    public TypeCommerce getTypeCommerce() {
        return typeCommerce;
    }
}
