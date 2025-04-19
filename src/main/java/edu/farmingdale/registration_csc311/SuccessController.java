package edu.farmingdale.registration_csc311;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class SuccessController {

    @FXML
    private Label successLabel;

    @FXML
    public void initialize() {
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), successLabel);
        fadeIn.setFromValue(0.0); // start transparent
        fadeIn.setToValue(1.0);   // fade to fully visible
        fadeIn.play();            // start animation
    }
}
