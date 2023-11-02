module com.example.auction {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires xstream;


    opens com.example.auction to javafx.fxml, xstream;
    exports com.example.auction;
}