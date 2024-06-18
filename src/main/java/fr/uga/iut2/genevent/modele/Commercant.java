package fr.uga.iut2.genevent.modele;

/**
 * La classe Commercant représente un commerçant qui est un type d'employé.
 * Un commerçant a un emplacement et un type de commerce.
 */
public class Commercant extends Employe {
    private Emplacement emplacement = null;
    private TypeCommerce typeCommerce = null;

    /**
     * Constructeur de la classe Commercant.
     *
     * @param nom          Le nom du commerçant.
     * @param prenom       Le prénom du commerçant.
     * @param email        L'email du commerçant.
     * @param telephone    Le numéro de téléphone du commerçant.
     * @param heureDebut   L'heure de début du travail du commerçant.
     * @param heureFin     L'heure de fin du travail du commerçant.
     * @param emplacement  L'emplacement du commerce du commerçant.
     * @param typeCommerce Le type de commerce du commerçant.
     */
    public Commercant(String nom, String prenom, String email, String telephone, int heureDebut, int heureFin,
            Emplacement emplacement, TypeCommerce typeCommerce) {
        super(nom, prenom, email, telephone, heureDebut, heureFin);
        setEmplacement(emplacement);
        this.typeCommerce = typeCommerce;
    }

    /**
     * Récupère l'emplacement du commerce du commerçant.
     *
     * @return L'emplacement du commerce du commerçant.
     */
    public Emplacement getEmplacement() {
        return emplacement;
    }

    /**
     * Définit l'emplacement du commerce du commerçant.
     *
     * @param emplacement Le nouvel emplacement du commerce du commerçant.
     */
    public void setEmplacement(Emplacement emplacement) {
        if (this.emplacement != null) {
            this.emplacement.removeCommercant(this);
        }
        this.emplacement = emplacement;
        if (emplacement != null) {
            emplacement.addCommercant(this);
        }
    }

    /**
     * Définit le type de commerce du commerçant.
     *
     * @param typeCommerce Le nouveau type de commerce du commerçant.
     */
    public void setTypeCommerce(TypeCommerce typeCommerce) {
        if (this.typeCommerce != null) {
            this.typeCommerce.removeCommercant(this);
        }
        this.typeCommerce = typeCommerce;
        if (typeCommerce != null) {
            typeCommerce.addCommercant(this);
        }
    }

    /**
     * Récupère le type de commerce du commerçant.
     *
     * @return Le type de commerce du commerçant.
     */
    public TypeCommerce getTypeCommerce() {
        return typeCommerce;
    }
}