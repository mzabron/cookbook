module com.example.cookbook {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens com.example.cookbook to javafx.fxml;
    exports com.example.cookbook;
}