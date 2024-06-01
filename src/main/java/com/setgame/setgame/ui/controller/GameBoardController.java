package com.setgame.setgame.ui.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.setgame.setgame.util.Game;
import com.setgame.setgame.networking.SetGameClient;
import com.setgame.setgame.networking.SetGameServer;

import java.io.IOException;

public class GameBoardController {

    @FXML
    private GridPane gridPane;
    @FXML
    private Button resetButton, showSetButton, setGameTimeButton, startGameButton;
    @FXML
    private Label scoreButton, timerLabel;
    @FXML
    private Button backButton;

    private Game game;
    private boolean isMultiplayer;
    private SetGameClient client;
    private SetGameServer server;

    @FXML
    public void initialize() {
        configureResetButton();
        configureBackButton();
        configureShowSetButton();
        configureSetGameTimeButton();
        configureStartGameButton();
    }

    public void setMultiplayer(boolean isMultiplayer, SetGameServer server) {
        this.isMultiplayer = isMultiplayer;
        this.server = server;
        if (isMultiplayer) {
            startServer();
            connectClient();
            game = new Game(gridPane, scoreButton, timerLabel, client);
        } else {
            game = new Game(gridPane, scoreButton, timerLabel);
        }
        game.startNewGame();
    }

    private void startServer() {
        server.start();
        try {
            // Warten, bis der Server vollständig gestartet ist
            Thread.sleep(2000); // Passen Sie die Wartezeit nach Bedarf an
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void connectClient() {
        client = new SetGameClient();
        if (client.isConnected()) {
            System.out.println("Erfolgreich mit dem Server verbunden.");
        } else {
            System.out.println("Verbindung zum Server fehlgeschlagen.");
        }
    }

    public void setGameTime(int gameTime) {
        game.setGameTime(gameTime);
    }

    private void configureResetButton() {
        resetButton.setText("Reset");
        resetButton.setOnAction(event -> game.startNewGame());
    }

    private void configureBackButton() {
        backButton.setText("Back");
        backButton.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/setgame/setgame/fxml/StartMenu.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void configureShowSetButton() {
        showSetButton.setText("Show Set");
        showSetButton.setOnAction(event -> game.highlightSetOnBoard());
    }

    private void configureSetGameTimeButton() {
        setGameTimeButton.setText("Set Game Time");
        setGameTimeButton.setOnAction(event -> {
            if (isMultiplayer) {
                setGameTime(300); // Beispiel: Setzen der Spielzeit auf 300 Sekunden
            }
        });
    }

    private void configureStartGameButton() {
        startGameButton.setText("Start Game");
        startGameButton.setOnAction(event -> {
            if (isMultiplayer) {
                client.startGame();
                client.startGame();
            }
        });
    }

    private void handleTimeUp() {
        if (isMultiplayer) {
            client.timeUp();
        }
        System.out.println("Zeit ist um!");
    }
}
