module com.example.bubt {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;
    requires java.desktop;

    opens com.example.bubt to javafx.fxml;
    opens com.example.bubt.controllers to javafx.fxml;
    opens com.example.bubt.controllers.AdminControllers to javafx.fxml;

    opens com.example.bubt.utils to javafx.base;

    exports com.example.bubt;
    exports com.example.bubt.controllers;
    exports com.example.bubt.controllers.AdminControllers;
}
