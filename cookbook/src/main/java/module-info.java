module com.example.cookbook {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cookbook to javafx.fxml;
    exports com.example.cookbook;
}