package com.setgame.setgame.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class GameTimer {
    private Label timerLabel;
    private int timeInSeconds;
    private Timeline timeline;
    private Runnable onTimeUp;

    public GameTimer(Label timerLabel, int timeInSeconds, Runnable onTimeUp) {
        this.timerLabel = timerLabel;
        this.timeInSeconds = timeInSeconds;
        this.onTimeUp = onTimeUp;
        createTimeline();
    }

    private void createTimeline() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (timeInSeconds > 0) {
                timeInSeconds--;
                updateTimerLabel();
            } else {
                timeline.stop();
                onTimeUp.run();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void start() {
        timeline.play();
    }

    public void stop() {
        timeline.stop();
    }

    public void setTime(int timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
        updateTimerLabel();
    }

    private void updateTimerLabel() {
        int minutes = timeInSeconds / 60;
        int seconds = timeInSeconds % 60;
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
    }
}
