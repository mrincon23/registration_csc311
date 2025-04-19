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

    /**
     * Initializes the controller by setting up validation and disabling the submit button.
     */
    @FXML
    public void initialize() {
        setupValidation(firstNameField, namePattern);
        setupValidation(lastNameField, namePattern);
        setupValidation(emailField, emailPattern);
        setupValidation(dobField, dobPattern);
        setupValidation(zipField, zipPattern);

        addButton.setDisable(true);
    }

    /**
     * Adds focus-based validation to a specific field using the given regex pattern.
     *
     * @param field   the TextField to validate
     * @param pattern the Pattern used for validation
     */
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

    /**
     * Validates if a given date string is in MM/dd/yyyy format.
     *
     * @param dateStr the date string to validate
     * @return true if the date is valid; false otherwise
     */
    private boolean isValidDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Validates all form fields and enables/disables the Add button accordingly.
     */
    private void validateAllFields() {
        boolean valid = namePattern.matcher(firstNameField.getText()).matches() &&
                        namePattern.matcher(lastNameField.getText()).matches() &&
                        emailPattern.matcher(emailField.getText()).matches() &&
                        dobPattern.matcher(dobField.getText()).matches() &&
                        zipPattern.matcher(zipField.getText()).matches();
        addButton.setDisable(!valid);
    }

    /**
     * Shows an error alert dialog with a specified title and message.
     *
     * @param title   the alert title
     * @param message the message content
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(title);
        alert.setContentText(message + " is not valid.");
        alert.showAndWait();
    }

    /**
     * Handles the Add button click event by loading the success screen if inputs are valid.
     *
     * @throws IOException if the FXML file cannot be loaded
     * @see FXMLLoader
     */
    @FXML
    private void handleAddAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("success.fxml"));
        Scene newScene = new Scene(loader.load());
        Stage stage = (Stage) addButton.getScene().getWindow();
        stage.setScene(newScene);
    }
}