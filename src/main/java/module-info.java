module com.example.infinity {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires static lombok;
    requires java.desktop;
    requires javafx.media;
    requires annotations;
    requires java.logging;
    requires org.yaml.snakeyaml;

    opens com.example.infinity to javafx.fxml;
    exports com.example.infinity;
    exports com.example.infinity.controller;
    opens com.example.infinity.controller to javafx.fxml;
    exports com.example.infinity.application;
    opens com.example.infinity.application to javafx.fxml;
}