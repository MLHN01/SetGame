package com.setgame.setgame.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.setgame.setgame.networking.SetGameServer;

import java.io.IOException;

public class GameModeSelectionController {

    @FXML
    private Button singleplayerButton;
    @FXML
    private Button createServerButton;
    @FXML
    private Button joinServerButton;
    @FXML
    private Button backButton;

    @FXML
    private void handleSingleplayer() {
        loadGameBoard(false, null);
        System.out.println("Singleplayer Selected");
    }

    @FXML
    private void handleCreateServer() {
        SetGameServer server = new SetGameServer("1234");  // Beispiel GameCode
        loadGameBoard(true, server);
    }

    @FXML
    private void handleJoinServer() {
        loadJoinServerDialog();
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/setgame/setgame/fxml/StartMenu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadGameBoard(boolean isMultiplayer, SetGameServer server) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/setgame/setgame/fxml/GameBoard.fxml"));
            Parent root = loader.load();

            GameBoardController controller = loader.getController();
            controller.setMultiplayer(isMultiplayer, server);

            Stage stage = (Stage) singleplayerButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadJoinServerDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/setgame/setgame/fxml/JoinServer.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Join Server");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
