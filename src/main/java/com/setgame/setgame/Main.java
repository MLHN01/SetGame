package com.setgame.setgame;

import com.setgame.setgame.ui.GameBoard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GameBoard gameBoard = new GameBoard();

        Scene scene = new Scene(gameBoard.getLayout(), 800, 600);

        primaryStage.setTitle("Set Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


