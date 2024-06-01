package com.setgame.setgame.ui.controller;

import com.setgame.setgame.networking.SetGameServer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.setgame.setgame.networking.SetGameClient;

import java.io.IOException;

public class JoinServerController {

    @FXML
    private TextField gameCodeField;
    @FXML
    private Button joinButton;

    private SetGameClient client;

    @FXML
    public void initialize() {
        joinButton.setOnAction(event -> joinGame());
    }

    private void joinGame() {
        String gameCode = gameCodeField.getText();
        client = new SetGameClient();
        loadGameBoard(gameCode);
    }

    private void loadGameBoard(String gameCode) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/setgame/setgame/fxml/GameBoard.fxml"));
            Parent root = loader.load();
            GameBoardController controller = loader.getController();
            controller.setMultiplayer(true, new SetGameServer(gameCode)); // Pass game code to the controller
            Stage stage = (Stage) joinButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
