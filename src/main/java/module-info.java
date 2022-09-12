module com.example.caesar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.anumag.caesar to javafx.fxml;
    exports com.anumag.caesar;
}