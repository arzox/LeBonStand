module genevent {
    // requires commons.validator;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires transitive java.logging;
    requires java.desktop;

    opens fr.uga.iut2.genevent.vue to javafx.fxml;
    opens fr.uga.iut2.genevent.modele to javafx.base;

    exports fr.uga.iut2.genevent;
}
