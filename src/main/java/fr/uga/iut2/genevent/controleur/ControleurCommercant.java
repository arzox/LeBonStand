package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.exception.MauvaisChampsException;
import fr.uga.iut2.genevent.modele.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Contrôleur pour le module "Commerçant"
 */
public class ControleurCommercant {

    private Application application;
    private Evenement evenement;

    /**
     * Constructeur du contrôleur de commerçants.
     * @param application L'application de gestion d'événements
     */
    public ControleurCommercant(Application application) {
        this.application = application;
    }

    /**
     * Récupère l'événement géré par le contrôleur.
     * @return L'événement géré par le controleur.
     */
    public Evenement getEvenement() {
        return evenement;
    }

    /**
     * Attribue un événement au contrôleur.
     * @param evenement L'événement à attribuer
     */
    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    // Commerçant

    /**
     * Crée un nouveau commerçant et l'ajoute à la liste des commerçants de cet événement.
     * @param nom Nom du commerçant.
     * @return Le commerçant ajouté.
     * @throws Exception Si l'événement est nul.
     */
    public Commercant inscrireCommercant(String nom) throws Exception {
        if (evenement != null) {

            Commercant nouveauCommercant = new Commercant(nom, "Prénom", "Mail",
                    "0601020304", 8, 20, null, null);

            evenement.inscrireCommercant(nouveauCommercant);

            return nouveauCommercant;

        } else
            throw new Exception("Le commerçant ne peut être inscrit car l'événement du controleur est nul");
    }

    /**
     * Récupère un commerçant à partir de son nom, prénom, adresse email et numéro de téléphone.
     * @param nom Nom du commerçant qu'on souhaite récupérer
     * @param prenom Prénom du commerçant qu'on souhaite récupérer
     * @param email Adresse email du commerçant qu'on souhaite récupérer
     * @param telephone Numéro de téléphone du commerçant qu'on souhaite récupérer
     * @return Le commerçant correspondant aux attributs donnés en paramètres ou null s'il n'existe pas.
     * @throws Exception Si l'événement est nul.
     */
    public Commercant getCommercant(String nom, String prenom, String email, String telephone) throws Exception {
        if (evenement != null) {

            for (Commercant commercant : evenement.getCommercants()) {

                String nomCourant = commercant.getNom();
                String prenomCourant = commercant.getPrenom();
                String emailCourant = commercant.getEmail();
                String telephoneCourant = commercant.getTelephone();

                boolean commercantFound = nom.equals(nomCourant) & prenom.equals(prenomCourant)
                        & email.equals(emailCourant) & telephone.equals(telephoneCourant);

                if (commercantFound) {

                    return commercant;
                }
            }
            return null;

        } else
            throw new Exception("Le commerçant ne peut être récupéré car l'événement du controleur est nul");
    }

    /**
     * Retire de la liste des commerçants de cet événement le commerçant donné en paramètre.
     * @param commercant Commerçant à retirer
     * @throws Exception Si l'événement est nul.
     */
    public void desinscrireCommercant(Commercant commercant) throws Exception {
        if (evenement != null) {

            evenement.desinscrireCommercant(commercant);

        } else
            throw new Exception("Le commerçant ne peut être désinscrit car l'événement du controleur est nul");
    }

    /**
     * Modifie le nom de le commerçant donné en paramètre.
     * @param commercant Le commerçant dont le nom doit être modifié
     * @param nom Nouveau nom
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si le nouveau nom rend l'agent de securité identique à un autre commerçant.
     */
    public void modifierNomCommercant(Commercant commercant, String nom) throws Exception {
        if (evenement != null) {

            for (Commercant commercantCourant : evenement.getCommercants()) {

                String nomCourant = commercantCourant.getNom();
                String prenomCourant = commercantCourant.getPrenom();
                String emailCourant = commercantCourant.getEmail();
                String telephoneCourant = commercantCourant.getTelephone();

                boolean isNotSameCommercant = commercant != commercantCourant;
                boolean isNotUnique = nom.equals(nomCourant) & commercant.getPrenom().equals(prenomCourant)
                        & commercant.getEmail().equals(emailCourant) & commercant.getTelephone().equals(telephoneCourant);

                if (isNotSameCommercant & isNotUnique) {

                    throw new MauvaisChampsException("En changeant le nom du commerçant, celui-ci devient identique à un autre commerçant",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            commercant.setNom(nom);

        } else
            throw new Exception("Le nom du commerçant ne peut être mofifié car l'événement du controleur est nul");
    }

    /**
     * Modifie le prénom de le commerçant donné en paramètre.
     * @param commercant Le commerçant dont le prénom doit être modifié
     * @param prenom Nouveau prénom
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si le nouveau prénom rend le commerçant identique à un autre commerçant.
     */
    public void modifierPrenomCommercant(Commercant commercant, String prenom) throws Exception {
        if (evenement != null) {

            for (Commercant commercantCourant : evenement.getCommercants()) {

                String nomCourant = commercantCourant.getNom();
                String prenomCourant = commercantCourant.getPrenom();
                String emailCourant = commercantCourant.getEmail();
                String telephoneCourant = commercantCourant.getTelephone();

                boolean isNotSameCommercant = commercant != commercantCourant;
                boolean isNotUnique = commercant.getNom().equals(nomCourant) & prenom.equals(prenomCourant)
                        & commercant.getEmail().equals(emailCourant) & commercant.getTelephone().equals(telephoneCourant);

                if (isNotSameCommercant & isNotUnique) {

                    throw new MauvaisChampsException("En changeant le prénom du commerçant, " +
                            "celui-ci devient identique à un autre commerçant",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            commercant.setPrenom(prenom);

        } else
            throw new Exception("Le prénom du commerçant ne peut être mofifié car l'événement du controleur est nul");
    }

    /**
     * Modifie l'adresse email de le commerçant donnée en paramètre.
     * @param commercant Le commerçant dont l'adresse email doit être modifiée
     * @param email Nouvelle adresse email
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si la nouvelle adresse email rend le commerçant identique à un autre commerçant.
     */
    public void modifierEmailCommercant(Commercant commercant, String email) throws Exception {
        if (evenement != null) {

            for (Commercant commercantCourant : evenement.getCommercants()) {

                String nomCourant = commercantCourant.getNom();
                String prenomCourant = commercantCourant.getPrenom();
                String emailCourant = commercantCourant.getEmail();
                String telephoneCourant = commercantCourant.getTelephone();

                boolean isNotSameCommercant = commercant != commercantCourant;
                boolean isNotUnique = commercant.getNom().equals(nomCourant) & commercant.getPrenom().equals(prenomCourant)
                        & email.equals(emailCourant) & commercant.getTelephone().equals(telephoneCourant);

                if (isNotSameCommercant & isNotUnique) {

                    throw new MauvaisChampsException("En changeant l'adresse email du commerçant, " +
                            "celui-ci devient identique à un autre commerçant",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            commercant.setEmail(email);

        } else
            throw new Exception("L'email du commerçant ne peut être mofifié car l'événement du controleur est nul");
    }

    /**
     * Modifie le numéro de téléphone de le commerçant donné en paramètre.
     * @param commercant Le commerçant dont le numéro de téléphone doit être modifié
     * @param telephone Nouveau numéro de téléphone
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si le nouveau numéro de téléphone rend le commerçant identique à un autre commerçant.
     */
    public void modifierTelephoneCommercant(Commercant commercant, String telephone) throws Exception {
        if (evenement != null) {

            for (Commercant commercantCourant : evenement.getCommercants()) {

                String nomCourant = commercantCourant.getNom();
                String prenomCourant = commercantCourant.getPrenom();
                String emailCourant = commercantCourant.getEmail();
                String telephoneCourant = commercantCourant.getTelephone();

                boolean isNotSameCommercant = commercant != commercantCourant;
                boolean isNotUnique = commercant.getNom().equals(nomCourant) & commercant.getPrenom().equals(prenomCourant)
                        & commercant.getEmail().equals(emailCourant) & telephone.equals(telephoneCourant);

                if (isNotSameCommercant & isNotUnique) {

                    throw new MauvaisChampsException("En changeant le numéro de téléphone du commerçant, " +
                            "celui-ci devient identique à un autre commerçant",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            commercant.setTelephone(telephone);

        } else
            throw new Exception("Le numéro de téléphone du commerçant ne peut être mofifié car l'événement du controleur est nul");
    }

    /**
     * Modifie l'heure de début d'activité de commerçant donné en paramètre.
     * @param commercant Le commerçant dont l'heure de début d'activité doit être modifiée
     * @param heureDebut Nouvelle heure de début d'activité
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si la nouvelle heure de début d'activité de l'agent est ultérieure à l'heure de fin d'activité de celui-ci.
     */
    public void modifierHeureDebutCommercant(Commercant commercant, int heureDebut) throws Exception {
        if (evenement != null) {

            boolean isStartAfterEnd = heureDebut > commercant.getHeureFin();

            if (isStartAfterEnd) {

                throw new MauvaisChampsException("L'heure de début ne peut être ultérieure à l'heure de fin",
                        new ArrayList<>(Collections.singleton(false)));
            }
            commercant.setHeureDebut(heureDebut);

        } else
            throw new Exception("L'heure de début du commerçant ne peut être mofifié car l'événement du controleur est nul");
    }

    /**
     * Modifie l'heure de fin d'activité du commerçant donné en paramètre.
     * @param commercant Le commerçant dont l'heure de fin d'activité doit être modifiée
     * @param heureFin Nouvelle heure de fin d'activité
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si la nouvelle heure de fin d'activité du commerçant est antérieure à l'heure de début d'activité de celui-ci.
     */
    public void modifierHeureFinCommercant(Commercant commercant, int heureFin) throws Exception {
        if (evenement != null) {

            boolean isStartAfterEnd = commercant.getHeureDebut() > heureFin;

            if (isStartAfterEnd) {

                throw new MauvaisChampsException("L'heure de fin ne peut être antérieure à l'heure de début",
                        new ArrayList<>(Collections.singleton(false)));
            }
            commercant.setHeureFin(heureFin);

        } else
            throw new Exception("L'heure de fin du commerçant ne peut être mofifié car l'événement du controleur est nul");
    }

    /**
     * Modifie l'emplacement du commerçant donné en paramètre.
     * @param commercant Le commerçant dont l'emplacement doit être modifié
     * @param emplacement Nouvel emplacement
     * @throws Exception Si l'événement est nul.
     */
    public void modifierEmplacementCommercant(Commercant commercant, Emplacement emplacement) throws Exception {
        if (evenement != null) {

            commercant.setEmplacement(emplacement);

        } else
            throw new Exception("L'emplacement du commerçant ne peut être mofifié car l'événement du controleur est nul");
    }

    /**
     * Modifie le type de commerce du commerçant donné en paramètre.
     * @param commercant Le commerçant dont le type de commerce doit être modifié
     * @param typeCommerce Nouveau type de commerce
     * @throws Exception Si l'événement est nul.
     */
    public void modifierTypeCommerceCommercant(Commercant commercant, TypeCommerce typeCommerce) throws Exception {
        if (evenement != null) {

            commercant.setTypeCommerce(typeCommerce);

        } else
            throw new Exception("Le type de commerce du commerçant ne peut être mofifié car l'événement du controleur est nul");
    }

    // Emplacement

    /**
     * Crée un nouvel emplacement et l'ajoute à la liste des emplacements de cet événement.
     * @return L'emplacement créé.
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si la taille est nulle ou négative.
     */
    public Emplacement creerEmplacement() throws Exception {
        if (evenement != null) {

            int numero = evenement.getEmplacements().size() + 1;

            Emplacement nouvelEmplacement = new Emplacement(numero, 0);
            evenement.ajouterEmplacement(nouvelEmplacement);

            return nouvelEmplacement;

        } else
            throw new Exception("L'emplacement ne peut être créé car l'événement du controleur est nul");
    }

    /**
     * Récupère un emplacement à partir de son numéro.
     * @param numero Numéro de l'emplacement qu'on souhaite récupérer
     * @return L'emplacement correspondant au numéro donné en paramètre ou null si il n'existe pas.
     * @throws Exception Si l'événement est nul.
     */
    public Emplacement getEmplacement(int numero) throws Exception {
        if (evenement != null) {

            for (Emplacement emplacement : evenement.getEmplacements()) {

                int numeroCourant = emplacement.getNumero();

                boolean emplacementFound = numero == numeroCourant;

                if (emplacementFound) {

                    return emplacement;
                }
            }
            return null;

        } else
            throw new Exception("L'emplacement ne peut être récupéré car l'événement du controleur est nul");
    }

    /**
     * Retire de la liste des emplacements de cet événement l'emplacement donné en paramètre.
     * @param emplacement L'emplacement à retirer
     * @throws Exception Si l'événement est nul.
     */
    public void supprimerEmplacement(Emplacement emplacement) throws Exception {
        if (evenement != null) {

            evenement.retirerEmplacement(emplacement);

        } else
            throw new Exception("L'emplacement ne peut être supprimé car l'événement du controleur est nul");
    }

    /**
     * Modifie la taille de l'emplacement donné en paramètre.
     * @param emplacement L'emplacement dont la taille doit être modifiée
     * @param taille Nouvelle taille
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si la taille est nulle ou négative.
     */
    public void modifierTailleEmplacement(Emplacement emplacement, int taille) throws Exception {
        if (evenement != null) {

            boolean isNegative = taille <= 0;

            if (isNegative) {

                throw new MauvaisChampsException("La taille ne peut pas être nulle ou négative",
                        new ArrayList<>(Collections.singleton(false)));
            }
            emplacement.setTaille(taille);

        } else
            throw new Exception("La taille de l'emplacement ne peut être modifiée car l'événement du controleur est nul");
    }

    // Type de commerce

    /**
     * Crée un nouveau type de commerce et l'ajoute à la liste des types de commerce de cet événement.
     * @param nom Nom du type de commerce
     * @return Le type de commerce créé.
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si le nouveau nom rend le type de commerce identique à un autre type de commerce ou
     * si le quota est nul ou négatif.
     */
    public TypeCommerce creerTypeCommerce(String nom) throws Exception {
        if (evenement != null) {

            TypeCommerce nouveauTypeCommerce = new TypeCommerce(nom, 1);
            evenement.ajouterTypeCommerce(nouveauTypeCommerce);

            return nouveauTypeCommerce;

        } else
            throw new Exception("Le type de commerce ne peut être créé car l'événement du controleur est nul");
    }

    /**
     * Récupère un type de commerce à partir de son nom.
     * @param nom Nom du type de commerce qu'on souhaite récupérer
     * @return Le type de commerce correspondant au nom donné en paramètre ou null si il n'existe pas.
     * @throws Exception Si l'événement est nul.
     */
    public TypeCommerce getTypeCommerce(String nom) throws Exception {
        if (evenement != null) {

            for (TypeCommerce typeCommerce : evenement.getTypeCommerces()) {

                String nomCourant = typeCommerce.getNom();

                boolean typeFound = nom.equals(nomCourant);

                if (typeFound) {

                    return typeCommerce;
                }
            }
            return null;

        } else
            throw new Exception("Le type de commerce ne peut être récupéré car l'événement du controleur est nul");
    }

    /**
     * Retire de la liste des types de commerce de cet événement le type de commerce donné en paramètre.
     * @param type Le type de commerce à retirer
     * @throws Exception Si l'événement est nul.
     */
    public void supprimerTypeCommerce(TypeCommerce type) throws Exception {
        if (evenement != null) {

            evenement.retirerTypeCommerce(type);

        } else
            throw new Exception("Le type de commerce ne peut être supprimé car l'événement du controleur est nul");
    }

    /**
     * Modifie le nom du type de commerce donné en paramètre.
     * @param type Le type de commerce dont le nom doit être modifié
     * @param nom Nouveau nom
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si le nouveau nom rend le type de commerce identique à un autre type de commerce.
     */
    public void modifierNomTypeCommerce(TypeCommerce type, String nom) throws Exception {
        if (evenement != null) {

            for (TypeCommerce typeCourant : evenement.getTypeCommerces()) {

                String nomCourant = typeCourant.getNom();

                boolean isNotSameType = type != typeCourant;
                boolean isNotUnique = nom.equals(nomCourant);

                if (isNotSameType & isNotUnique) {

                    throw new MauvaisChampsException("En changeant le nom du type de commerce, celui-ci devient identique à un autre type de commerce",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            type.setNom(nom);

        } else
            throw new Exception("Le nom du type de commerce ne peut être modifié car l'événement du controleur est nul");
    }

    /**
     * Modifie le quota du type de commerce donné en paramètre.
     * @param type Le type de commerce dont le quota doit être modifié
     * @param quota Nouveau quota
     * @throws Exception Si l'événement est nul.
     * @throws MauvaisChampsException Si le nouveau quota est nul ou négatif.
     */
    public void modifierQuotaTypeCommerce(TypeCommerce type, int quota) throws Exception {
        if (evenement != null) {

            boolean isNegative = quota <= 0;

            if (isNegative) {

                throw new MauvaisChampsException("Le quota ne peut pas être nul ou négatif",
                        new ArrayList<>(Collections.singleton(false)));
            }
            if (getTypeCommerce(type.getNom()) != null) {
                getTypeCommerce(type.getNom()).setQuota(quota);
            }
        } else
            throw new Exception(
                    "Le quota du type de commerce ne peut être modifié car l'événement du controleur est nul");
    }

    public ArrayList<Emplacement> getEmplacements() {
        return evenement.getEmplacements();
    }

    public ArrayList<TypeCommerce> getTypeCommerces() {
        return new ArrayList<>(evenement.getTypeCommerces());
    }
}
