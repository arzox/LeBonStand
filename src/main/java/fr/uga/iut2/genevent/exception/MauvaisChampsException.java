package fr.uga.iut2.genevent.exception;

import java.util.ArrayList;

public class MauvaisChampsException extends Exception {
    private ArrayList<Boolean> champs;

    public MauvaisChampsException(String message, ArrayList<Boolean> champs) {
        super(message);
        this.champs = champs;
    }

    public ArrayList<Boolean> getChamps() {
        return champs;
    }
}
