package fr.uga.iut2.genevent.exception;

import fr.uga.iut2.genevent.Main;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Exception levée lorsqu'un ou plusieurs champs ne sont pas valides
 */
public class MauvaisChampsException extends Exception {
    private ArrayList<Boolean> champs;

    /**
     * Constructeur de l'exception
     * @param message Message d'erreur
     * @param champs Liste de booléens indiquant si les champs sont valides ou non
     */
    public MauvaisChampsException(String message, ArrayList<Boolean> champs) {
        super(message);
        Main.setLOGGER(Level.WARNING, message);
        this.champs = champs;
    }

    public ArrayList<Boolean> getChamps() {
        return champs;
    }
}
