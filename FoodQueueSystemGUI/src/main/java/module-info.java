module com.example.foodqueuesystemgui {
    requires javafx.controls;
    requires javafx.fxml;

    exports com.classesVersion;
    opens com.classesVersion to javafx.fxml;
    exports com.example.foodqueuesystemgui;
    opens com.example.foodqueuesystemgui to javafx.fxml;
}