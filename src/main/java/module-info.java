module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;

    exports App;
    opens App to javafx.fxml;

    exports Controller;
    opens Controller to javafx.fxml;
}