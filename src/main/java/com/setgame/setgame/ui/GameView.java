package com.setgame.setgame.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class GameView {
    private BorderPane layout;
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
        // Hier würdest du die Karten zum Grid hinzufügen
        layout.setCenter(gameBoard);

        // Scoreboard
        Label scoreLabel = new Label("Score: 0");
        HBox scoreBoard = new HBox(scoreLabel);
        scoreBoard.setAlignment(Pos.CENTER);
        layout.setTop(scoreBoard);

        // Hier fügst du den EventHandler für den Button hinzu
        HBox controls = new HBox(restartButton);
        controls.setAlignment(Pos.CENTER);
        layout.setBottom(controls);
    }

    public BorderPane getLayout() {
        return layout;
    }

    public Button getRestartButton() {
        return restartButton; // Stelle sicher, dass restartButton als Instanzvariable verfügbar ist
    }
}
