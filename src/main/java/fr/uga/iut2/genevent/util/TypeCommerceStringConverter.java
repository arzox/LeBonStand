package fr.uga.iut2.genevent.util;

import fr.uga.iut2.genevent.modele.TypeCommerce;
import javafx.util.StringConverter;

public class TypeCommerceStringConverter extends StringConverter<TypeCommerce> {

    @Override
    public String toString(TypeCommerce typeCommerce) {
        return typeCommerce != null ? typeCommerce.toString() : "";
    }

    @Override
    public TypeCommerce fromString(String nom) {
        return new TypeCommerce(nom, 0);
    }
}
