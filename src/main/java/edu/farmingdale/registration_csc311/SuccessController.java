package edu.farmingdale.registration_csc311;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

/**
 * Controller for the success screen after registration.
 */
public class SuccessController {

    @FXML
    private Label successLabel;

    /**
     * Initializes the success screen with a fade-in animation for the label.
     */
    @FXML
    public void initialize() {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), successLabel);
        fadeIn.setFromValue(0.0); // start transparent
        fadeIn.setToValue(1.0);   // fade to fully visible
        fadeIn.play();            // start animation
    }
}
