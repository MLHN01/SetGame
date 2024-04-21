package com.setgame.setgame.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

// Repräsentiert die Ansicht des Spiels
public class GameView {
    // Layout-Element für die Ansicht
    private BorderPane layout;

    // Button für Neustart
    private Button restartButton; // Button als Instanzvariable

    public GameView() {
        initializeLayout();
    }

    private void initializeLayout() {
        layout = new BorderPane();
        restartButton = new Button("Neustart");

        // Spielbrett
        GridPane gameBoard = new GridPane();
        gameBoard.setAlignment(Pos.CENTER);
        gameBoard.setHgap(10);
        gameBoard.setVgap(10);

        layout.setCenter(gameBoard);

        // Scoreboard
        Label scoreLabel = new Label("Score: 0");
        HBox scoreBoard = new HBox(scoreLabel);
        scoreBoard.setAlignment(Pos.CENTER);
        layout.setTop(scoreBoard);

        HBox controls = new HBox(restartButton);
        controls.setAlignment(Pos.CENTER);
        layout.setBottom(controls);
    }

    // Getter für das Layout
    public BorderPane getLayout() {
        return layout;
    }

    // Getter für den Neustart-Button
    public Button getRestartButton() {
        return restartButton;
    }
}
