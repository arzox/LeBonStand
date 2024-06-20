package fr.uga.iut2.genevent.util;

import fr.uga.iut2.genevent.modele.Zone;
import javafx.util.StringConverter;

/**
 * Classe permettant de convertir un objet de type Zone en String
 */
public class ZoneStringConverter extends StringConverter<Zone> {
    @Override
    public String toString(Zone zone) {
        return zone != null ? zone.toString() : "";
    }

    @Override
    public Zone fromString(String s) {
        return new Zone(s);
    }
}
