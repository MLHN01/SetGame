package com.setgame.setgame.util;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.util.Timer;
import java.util.TimerTask;

// Repräsentiert einen Timer für das Spiel
public class GameTimer {

    private Timer timer;
    private int timeLeft;
    private Label timerLabel;
    private Runnable onTimeUp;

    public GameTimer(Label timerLabel, int timeInSeconds, Runnable onTimeUp) {
        this.timerLabel = timerLabel;
        this.timeLeft = timeInSeconds;
        this.onTimeUp = onTimeUp;
    }

    // Startet den Timer
    public void start() {
        timer = new Timer(true);
        // Timer startet sofort und wird alle 1000ms (1 Sekunde) ausgeführt
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (timeLeft > 0) {
                        timeLeft--;
                        updateTimerLabel();
                    } else {
                        timer.cancel();
                        onTimeUp.run();
                    }
                });
            }
        }, 1000, 1000);
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private void updateTimerLabel() {
        int minutes = timeLeft / 60;
        int seconds = timeLeft % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }
}
