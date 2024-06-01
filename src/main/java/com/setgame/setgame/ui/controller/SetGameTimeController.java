package com.setgame.setgame.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SetGameTimeController {

    @FXML
    private TextField gameTimeField;

    private GameBoardController gameBoardController;

    public void setGameBoardController(GameBoardController gameBoardController) {
        this.gameBoardController = gameBoardController;
    }

    @FXML
    private void handleSetTime() {
        int gameTime = Integer.parseInt(gameTimeField.getText());
        gameBoardController.setGameTime(gameTime);
        ((Stage) gameTimeField.getScene().getWindow()).close();
    }
}
