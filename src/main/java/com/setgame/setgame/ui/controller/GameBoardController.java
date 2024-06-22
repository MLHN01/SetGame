package com.setgame.setgame.ui.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import com.setgame.setgame.GameObjects.Card;
import com.setgame.setgame.util.Game;
import com.setgame.setgame.util.GameTimer;

public class GameBoardController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button resetButton, showSetButton;
    @FXML
    private Label scoreButton, timerLabel;
    @FXML
    private Button backButton;

    private Game game;

    // Initialisierungsmethode, die aufgerufen wird, wenn das FXML geladen wird
    @FXML
    public void initialize() {
        game = new Game(gridPane, scoreButton, timerLabel);
        game.startNewGame();
        configureResetButton();
        configureBackButton();
        configureShowSetButton();
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
}
