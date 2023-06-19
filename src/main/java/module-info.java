module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    //requires junit;
    //requires org.testng;
    //requires org.junit.jupiter;

    opens fr.iut.montreuil to javafx.fxml;
    exports fr.iut.montreuil;
    exports fr.iut.montreuil.controller;
    opens fr.iut.montreuil.controller to javafx.fxml;
}