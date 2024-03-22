module com.example.bubt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bubt to javafx.fxml;
    exports com.example.bubt;
    exports com.example.bubt.controllers;
    opens com.example.bubt.controllers to javafx.fxml;
}