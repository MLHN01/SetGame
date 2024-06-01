package com.setgame.setgame.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartMenuController {

    @FXML
    private Button startButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button scoreboardButton; // Optional, falls vorhanden

    @FXML
    private void handleStart(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/setgame/setgame/fxml/GameModeSelection.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleExit(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close(); // Schließt das Fenster
    }

    @FXML
    private void handleScoreboard(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/setgame/setgame/fxml/ScoreBoard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) scoreboardButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
