package edu.farmingdale.registration_csc311;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Controller for registration form input validation and navigation
 */

public class HelloController {
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField dobField;

    @FXML
    private TextField zipField;

    @FXML
    private Button addButton;

    private final Pattern namePattern = Pattern.compile("^^[a-zA-Z]{2,25}$");
    private final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@farmingdale\\.edu$");
    private final Pattern dobPattern = Pattern.compile("^(0[1-9]|1[0-2])/([0][1-9]|[12][0-9]|3[01])/\\d{4}$");
    private final Pattern zipPattern = Pattern.compile("^\\d{5}$");


    @FXML
    public void initialize() {
        setupValidation(firstNameField, namePattern);
        setupValidation(lastNameField, namePattern);
        setupValidation(emailField, emailPattern);
        setupValidation(dobField, dobPattern);
        setupValidation(zipField, zipPattern);

        addButton.setDisable(true);
    }


    private void setupValidation(TextField field, Pattern pattern) {
        field.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                if (!pattern.matcher(field.getText()).matches()) {
                    field.setStyle("-fx-border-color: red;");
                    showAlert("Invalid Input", field.getPromptText() != null ? field.getPromptText() : field.getId());
                } else {
                    field.setStyle(null);
                }
                validateAllFields();
            }
        });
    }
}