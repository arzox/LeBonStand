package fr.uga.iut2.genevent.util;

import fr.uga.iut2.genevent.controleur.Controleur;
import javafx.util.StringConverter;
import fr.uga.iut2.genevent.modele.Emplacement;

public class EmplacementStringConverter extends StringConverter<Emplacement> {

    private Controleur controleur = Controleur.getInstance(null);

    @Override
    public String toString(Emplacement emplacement) {
        return emplacement != null ? emplacement.toString() : "";
    }

    @Override
    public Emplacement fromString(String numero) {
        return new Emplacement(Integer.parseInt(numero), 5);
    }
}