package fr.uga.iut2.genevent.util;

import fr.uga.iut2.genevent.modele.AgentSecurite;
import javafx.util.StringConverter;

import java.util.ArrayList;

/**
 * Classe permettant de convertir un objet de type AgentSecurite en String
 */
public class NombreAgentStringConverter extends StringConverter<ArrayList<AgentSecurite>> {
    @Override
    public String toString(ArrayList<AgentSecurite> agentSecurites) {
        return agentSecurites != null ? Integer.toString(agentSecurites.size()) : "";
    }

    @Override
    public ArrayList<AgentSecurite> fromString(String s) {
        return new ArrayList<>();
    }
}
