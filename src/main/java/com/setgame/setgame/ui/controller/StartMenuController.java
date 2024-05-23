package com.setgame.setgame.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartMenuController {

    @FXML
    private Button startButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button scoreboardButton; // Optional, falls vorhanden

    // Event-Handler für den Start-Button
    @FXML
    private void handleStart(ActionEvent event) {
        try {
            // Laden der Hauptspiel-Szene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/setgame/setgame/fxml/GameBoard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) startButton.getScene().getWindow(); // Bühne vom Start-Button holen
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Event-Handler für den Exit-Button
    @FXML
    private void handleExit(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close(); // Schließt das Fenster
    }

    // Event-Handler für den Scoreboard-Button, falls vorhanden
    @FXML
    private void handleScoreboard(ActionEvent event) {
        try {
            // Laden der Scoreboard-Szene
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
