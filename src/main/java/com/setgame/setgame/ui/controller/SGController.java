package com.setgame.setgame.ui.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SGController implements Initializable {

    @FXML
    private Label timerLabel;

    private int minutes = 0;
    private int seconds = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create a timeline for updating the counter
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            updateCounter();
        }));
        timeline.setCycleCount(Animation.INDEFINITE); // Repeat indefinitely
        timeline.play();
    }

    private void updateCounter() {
        seconds++;
        if (seconds == 60) {
            seconds = 0;
            minutes++;
        }

        // Update the counter label
        String minutesStr = String.format("%02d", minutes);
        String secondsStr = String.format("%02d", seconds);
        timerLabel.setText(minutesStr + ":" + secondsStr);
    }
}