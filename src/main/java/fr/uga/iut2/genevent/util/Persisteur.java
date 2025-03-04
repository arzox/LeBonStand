package fr.uga.iut2.genevent.util;

import fr.uga.iut2.genevent.Main;
import fr.uga.iut2.genevent.modele.Application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;

/**
 * La classe Persisteur est responsable de l'enregistrement et de la
 * restauration de l'état du modèle.
 * <p>
 * C'est une classe utilitaire, toutes les méthodes sont statiques.
 * La classe n'a pas vocation a être instanciée.
 */
public final class Persisteur {

    private static final String NOM_BDD = "persistence/application.bdd";

    private Persisteur() {
        // interdit l'instanciation de la classe utilitaire via un constructeur privé
        throw new IllegalStateException("Classe utilitaire.");
    }

    /**
     * Enregistre l'état de l'application dans un fichier persistant.
     * <p>
     * Le fichier de persistance est le fichier "{@value Persisteur#NOM_BDD}".
     *
     * @param application L'application dont l'état est persisté.
     *
     * @throws FileNotFoundException si le fichier de persistance est un
     *                               dossier, ne peut pas être créé ou ne peut pas
     *                               être ouvert.
     *
     * @throws IOException           si une erreur d'entrée/sortie survient pendant
     *                               l'enregistrement.
     */
    public static final void sauverEtat(final Application application) throws FileNotFoundException, IOException {
        try (
                FileOutputStream fos = new FileOutputStream(Persisteur.NOM_BDD);
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(application);
            // Les classes `FileOutputStream` et `ObjectOutputStream`
            // implémentent l'interface `AutoCloseable` : pas besoin de faire
            // un appel explicite à `.close()`.
            Main.setLOGGER(Level.INFO, "Sauvegarde de l'état réussie");
            System.out.flush();
        } catch (FileNotFoundException fnfe) {
            Main.setLOGGER(Level.SEVERE, "Erreur à la création/ouverture du fichier de persistance.");
            System.err.flush();
            throw fnfe;
        } catch (IOException ioe) {
            Main.setLOGGER(Level.SEVERE, "Erreur lors de l'écriture du fichier de persistance.");
            System.err.flush();
            throw ioe;
        }
    }

    /**
     * Alimente une instance d'application avec l'état du fichier de
     * persistance.
     * <p>
     * Le fichier de persistance est le fichier "{@value Persisteur#NOM_BDD}".
     *
     * @return Une nouvelle instance vierge d'application si le fichier de
     *         persistance n'existe pas, une instance dans l'état enregistré sinon.
     *
     * @throws ClassNotFoundException si le fichier de persistance contient une
     *                                classe inconnue (fichier corrompu).
     *
     * @throws IOException            si le fichier de persistance est corrompu ou
     *                                qu'une
     *                                erreur d'entrée/sortie survient.
     */
    public static final Application lireEtat() throws ClassNotFoundException, IOException {
        Application application;

        try (
                FileInputStream fis = new FileInputStream(Persisteur.NOM_BDD);
                ObjectInputStream ois = new ObjectInputStream(fis);) {
            application = (Application) ois.readObject();
            System.out.println("Restauration de l'état réussie.");
            System.out.flush();
            // Les classes `FileInputStream` et `ObjectInputStream`
            // implémentent l'interface `AutoCloseable` : pas besoin de faire
            // un appel explicite à `.close()`.
        } catch (FileNotFoundException ignored) {
            Main.setLOGGER(Level.WARNING, "Fichier de persistance inexistant : création d'une nouvelle instance.");
            System.out.flush();
            application = new Application();
        } catch (IOException ioe) {
            Main.setLOGGER(Level.SEVERE, "Erreur de lecture du fichier de persistance.");
            System.err.flush();
            throw ioe;
        } catch (ClassNotFoundException cnfe) {
            Main.setLOGGER(Level.SEVERE, "Fichier de persistance corrompu.");
            System.err.flush();
            throw cnfe;
        }

        return application;
    }
}
