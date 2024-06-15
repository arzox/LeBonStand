module genevent {
    requires commons.validator;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens fr.uga.iut2.genevent.vue to javafx.fxml;

    exports fr.uga.iut2.genevent;
}
