package fr.uga.iut2.genevent.vue;

public class VueCommercants extends IHM {
    public VueCommercants() {
        super();
    }

    @Override
    public void informerUtilisateur(String message, boolean succes) {
        System.out.println(message);
    }


}