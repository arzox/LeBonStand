package fr.uga.iut2.genevent.modele;

public class Commercant extends Employee{
    private Emplacement emplacement = null;
    private TypeCommerce typeCommerce = null;

    public Commercant(String nom, String prenom, String email, String telephone, String horaires, Emplacement emplacement, TypeCommerce typeCommerce) {
        super(nom, prenom, email, telephone, horaires);
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
