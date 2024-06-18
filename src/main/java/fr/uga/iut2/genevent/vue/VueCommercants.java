package fr.uga.iut2.genevent.vue;

public class VueCommercants extends IHM {
    public static final String FXML_NAME = "test.fxml";

    public VueCommercants() {
        super();
    }

    // Implémentations et redéfinitions

    @Override
    public void informerUtilisateur(String message, boolean succes) {
        System.out.println(message);
    }

    @Override
    public String getFxmlName() {
        return FXML_NAME;
    }
}