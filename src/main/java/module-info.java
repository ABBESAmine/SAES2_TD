module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens fr.iut.montreuil to javafx.fxml;
    exports fr.iut.montreuil;
    exports fr.iut.montreuil.controller;
    opens fr.iut.montreuil.controller to javafx.fxml;
}