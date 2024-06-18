package fr.uga.iut2.genevent.controleur;

import fr.uga.iut2.genevent.exception.MauvaisChampsException;
import fr.uga.iut2.genevent.modele.*;

import java.util.*;

/**
 * Contrôleur pour la catégorie "Commerçants"
 */
public class ControleurCommercant {

    private Application application;
    private Evenement evenement;

    public ControleurCommercant(Application application) {
        this.application = application;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    // Commerçant
    public Commercant inscrireCommercant(String nom, String prenom, String email, String telephone, int heureDebut, int heureFin,
                                Emplacement emplacement, TypeCommerce typeCommerce) throws Exception {
        if (evenement != null) {

            boolean isStartAfterEnd = heureDebut > heureFin;

            for (Commercant commercant : evenement.getCommercants()) {

                String nomCourant = commercant.getNom();
                String prenomCourant = commercant.getPrenom();
                String emailCourant = commercant.getEmail();
                String telephoneCourant = commercant.getTelephone();
                boolean isNotUnique = nom.equals(nomCourant) & prenom.equals(prenomCourant)
                        & email.equals(emailCourant) & telephone.equals(telephoneCourant);

                if (isNotUnique & isStartAfterEnd) {

                    throw new MauvaisChampsException("Le commerçant que vous souhaitez inscrire existe déjà et " +
                            "l'heure de début est ultérieure à l'heure de fin",
                            new ArrayList<>(Arrays.asList(false, false, false, false, false, false, true, true)));

                } else if (isNotUnique) {

                    throw new MauvaisChampsException("Le commerçant que vous souhaitez inscrire existe déjà",
                            new ArrayList<>(Arrays.asList(false, false, false, false, true, true, true, true)));
                }
            }
            if (isStartAfterEnd) {

                throw new MauvaisChampsException("L'heure de début est ultérieure à l'heure de fin",
                        new ArrayList<>(Arrays.asList(true, true, true, true, false, false, true, true)));
            }
            Commercant nouveauCommercant = new Commercant(nom, prenom, email, telephone, heureDebut, heureFin, emplacement, typeCommerce);
            evenement.inscrireCommercant(nouveauCommercant);

            return nouveauCommercant;

        } else
            throw new Exception("Le commerçant ne peut être inscrit car l'événement du controleur est nul");
    }

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

    public void desinscrireCommercant(Commercant commercant) throws Exception {
        if (evenement != null) {

            evenement.desinscrireCommercant(commercant);

        } else
            throw new Exception("Le commerçant ne peut être désinscrit car l'événement du controleur est nul");
    }

    public void modifierNomCommercant(Commercant commercant, String nom) throws Exception {
        if (evenement != null) {

            for (Commercant commercantCourant : evenement.getCommercants()) {

                String nomCourant = commercantCourant.getNom();
                String prenomCourant = commercantCourant.getPrenom();
                String emailCourant = commercantCourant.getEmail();
                String telephoneCourant = commercantCourant.getTelephone();
                boolean isNotUnique = nom.equals(nomCourant) & commercant.getPrenom().equals(prenomCourant)
                        & commercant.getEmail().equals(emailCourant) & commercant.getTelephone().equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le nom du commerçant, celui-ci devient identique à un autre commerçant",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            commercant.setNom(nom);

        } else
            throw new Exception("Le nom du commerçant ne peut être mofifié car l'événement du controleur est nul");
    }

    public void modifierPrenomCommercant(Commercant commercant, String prenom) throws Exception {
        if (evenement != null) {

            for (Commercant commercantCourant : evenement.getCommercants()) {

                String nomCourant = commercantCourant.getNom();
                String prenomCourant = commercantCourant.getPrenom();
                String emailCourant = commercantCourant.getEmail();
                String telephoneCourant = commercantCourant.getTelephone();
                boolean isNotUnique = commercant.getNom().equals(nomCourant) & prenom.equals(prenomCourant)
                        & commercant.getEmail().equals(emailCourant) & commercant.getTelephone().equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le prénom du commerçant, " +
                            "celui-ci devient identique à un autre commerçant",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            commercant.setPrenom(prenom);

        } else
            throw new Exception("Le prénom du commerçant ne peut être mofifié car l'événement du controleur est nul");
    }

    public void modifierEmailCommercant(Commercant commercant, String email) throws Exception {
        if (evenement != null) {

            for (Commercant commercantCourant : evenement.getCommercants()) {

                String nomCourant = commercantCourant.getNom();
                String prenomCourant = commercantCourant.getPrenom();
                String emailCourant = commercantCourant.getEmail();
                String telephoneCourant = commercantCourant.getTelephone();
                boolean isNotUnique = commercant.getNom().equals(nomCourant) & commercant.getPrenom().equals(prenomCourant)
                        & email.equals(emailCourant) & commercant.getTelephone().equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant l'adresse email du commerçant, " +
                            "celui-ci devient identique à un autre commerçant",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            commercant.setEmail(email);

        } else
            throw new Exception("L'email du commerçant ne peut être mofifié car l'événement du controleur est nul");
    }

    public void modifierTelephoneCommercant(Commercant commercant, String telephone) throws Exception {
        if (evenement != null) {

            for (Commercant commercantCourant : evenement.getCommercants()) {

                String nomCourant = commercantCourant.getNom();
                String prenomCourant = commercantCourant.getPrenom();
                String emailCourant = commercantCourant.getEmail();
                String telephoneCourant = commercantCourant.getTelephone();
                boolean isNotUnique = commercant.getNom().equals(nomCourant) & commercant.getPrenom().equals(prenomCourant)
                        & commercant.getEmail().equals(emailCourant) & telephone.equals(telephoneCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("En changeant le numéro de téléphone du commerçant, " +
                            "celui-ci devient identique à un autre commerçant",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            commercant.setTelephone(telephone);

        } else
            throw new Exception("Le numéro de téléphone du commerçant ne peut être mofifié car l'événement du controleur est nul");
    }

    public void modifierHeureDebutCommercant(Commercant commercant, int heureDebut) throws Exception {
        if (evenement != null) {

            boolean isStartAfterEnd = heureDebut > commercant.getHeureFin();

            if (isStartAfterEnd) {

                throw new MauvaisChampsException("L'heure de début est ultérieure à l'heure de fin",
                        new ArrayList<>(Collections.singleton(false)));
            }
            commercant.setHeureDebut(heureDebut);

        } else
            throw new Exception("L'heure de début du commerçant ne peut être mofifié car l'événement du controleur est nul");
    }

    public void modifierHeureFinCommercant(Commercant commercant, int heureFin) throws Exception {
        if (evenement != null) {

            boolean isStartAfterEnd = commercant.getHeureDebut() > heureFin;

            if (isStartAfterEnd) {

                throw new MauvaisChampsException("L'heure de fin est antérieure à l'heure de début",
                        new ArrayList<>(Collections.singleton(false)));
            }
            commercant.setHeureFin(heureFin);

        } else
            throw new Exception("L'heure de fin du commerçant ne peut être mofifié car l'événement du controleur est nul");
    }

    public void modifierEmplacementCommercant(Commercant commercant, Emplacement emplacement) throws Exception {
        if (evenement != null) {

            commercant.setEmplacement(emplacement);

        } else
            throw new Exception("L'emplacement du commerçant ne peut être mofifié car l'événement du controleur est nul");
    }

    public void modifierTypeCommerceCommercant(Commercant commercant, TypeCommerce typeCommerce) throws Exception {
        if (evenement != null) {

            commercant.setTypeCommerce(typeCommerce);

        } else
            throw new Exception("Le type de commerce du commerçant ne peut être mofifié car l'événement du controleur est nul");
    }

    // Emplacement
    public Emplacement creerEmplacement(int taille) throws Exception {
        if (evenement != null) {

            boolean isNegative = taille <= 0;

            if (isNegative) {

                throw new MauvaisChampsException("La taille ne peut pas être nulle ou négative",
                        new ArrayList<>(Collections.singleton(false)));
            }
            int numero = evenement.getEmplacements().size() + 1;
            Emplacement nouvelEmplacement = new Emplacement(numero, taille);
            evenement.ajouterEmplacement(nouvelEmplacement);

            return nouvelEmplacement;

        } else
            throw new Exception("L'emplacement ne peut être créé car l'événement du controleur est nul");
    }

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

    public void supprimerEmplacement(Emplacement emplacement) throws Exception {
        if (evenement != null) {

            evenement.retirerEmplacement(emplacement);

        } else
            throw new Exception("L'emplacement ne peut être supprimé car l'événement du controleur est nul");
    }

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
    public TypeCommerce creerTypeCommerce(String nom, int quota) throws Exception {
        if (evenement != null) {

            boolean isNegative = quota <= 0;

            for (Map.Entry<TypeCommerce, Integer> type : evenement.getTypeCommerces().entrySet()) {

                String nomCourant = type.getKey().getNom();
                boolean isNotUnique = nom.equals(nomCourant);

                if (isNotUnique & isNegative) {

                    throw new MauvaisChampsException("Le type de commerce que vous souhaitez créer existe déjà et " +
                            "le quota ne peut pas être nul ou négatif",
                            new ArrayList<>(Arrays.asList(false, false)));
                }
                else if (isNotUnique) {

                    throw new MauvaisChampsException("Le type de commerce que vous souhaitez créer existe déjà",
                            new ArrayList<>(Arrays.asList(false, true)));
                }
            }
            if (isNegative) {

                throw new MauvaisChampsException("Le quota ne peut pas être nul ou négatif",
                        new ArrayList<>(Arrays.asList(true, false)));
            }
            TypeCommerce nouveauTypeCommerce = new TypeCommerce(nom);
            evenement.ajouterTypeCommerce(nouveauTypeCommerce, quota);

            return nouveauTypeCommerce;

        } else
            throw new Exception("Le type de commerce ne peut être créé car l'événement du controleur est nul");
    }

    public TypeCommerce getTypeCommerce(String nom) throws Exception {
        if (evenement != null) {

            for (Map.Entry<TypeCommerce, Integer> dict : evenement.getTypeCommerces().entrySet()) {

                TypeCommerce type = dict.getKey();
                String nomCourant = type.getNom();
                boolean typeFound = nom.equals(nomCourant);

                if (typeFound) {

                    return type;
                }
            }
            return null;

        } else
            throw new Exception("Le type de commerce ne peut être récupéré car l'événement du controleur est nul");
    }

    public void supprimerTypeCommerce(TypeCommerce type) throws Exception {
        if (evenement != null) {

            evenement.retirerTypeCommerce(type);

        } else
            throw new Exception("Le type de commerce ne peut être supprimé car l'événement du controleur est nul");
    }

    public void modifierNomTypeCommerce(TypeCommerce type, String nom) throws Exception {
        if (evenement != null) {

            for (Map.Entry<TypeCommerce, Integer> typeCourant : evenement.getTypeCommerces().entrySet()) {

                String nomCourant = typeCourant.getKey().getNom();
                boolean isNotUnique = nom.equals(nomCourant);

                if (isNotUnique) {

                    throw new MauvaisChampsException("Le type de commerce que vous souhaitez créer existe déjà",
                            new ArrayList<>(Collections.singleton(false)));
                }
            }
            type.setNom(nom);

        } else
            throw new Exception("Le nom du type de commerce ne peut être modifié car l'événement du controleur est nul");

    }

    public void modifierQuotaTypeCommerce(TypeCommerce type, int quota) throws Exception {
        if (evenement != null) {

            boolean isNegative = quota <= 0;

            if (isNegative) {

                throw new MauvaisChampsException("Le quota ne peut pas être nul ou négatif",
                        new ArrayList<>(Collections.singleton(false)));
            }
            if (evenement.getTypeCommerces().containsKey(type)) {

                evenement.getTypeCommerces().put(type, quota);
            }
        } else
            throw new Exception("Le quota du type de commerce ne peut être modifié car l'événement du controleur est nul");
    }
}
