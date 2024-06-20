package fr.uga.iut2.genevent.util;

import fr.uga.iut2.genevent.controleur.Controleur;
import javafx.util.StringConverter;
import fr.uga.iut2.genevent.modele.Emplacement;

/**
 * Classe permettant de convertir un objet de type Emplacement en String
 */
public class EmplacementStringConverter extends StringConverter<Emplacement> {

    private Controleur controleur = Controleur.getInstance(null);

    @Override
    public String toString(Emplacement emplacement) {
        if (emplacement == null) {
            return "";
        }
        return Integer.toString(emplacement.getNumero());
    }

    @Override
    public Emplacement fromString(String numero) {
        System.out.println(numero);
        if (numero.isEmpty())
            return new Emplacement(null, 0);
        return new Emplacement(Integer.parseInt(numero), 0);
    }
}