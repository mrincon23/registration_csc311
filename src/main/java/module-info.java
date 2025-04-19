module edu.farmingdale.registration_csc311 {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.farmingdale.registration_csc311 to javafx.fxml;
    exports edu.farmingdale.registration_csc311;
}