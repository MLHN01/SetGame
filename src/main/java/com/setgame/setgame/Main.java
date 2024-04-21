package com.setgame.setgame;

import com.setgame.setgame.ui.GameBoardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    private GameBoardController gameBoard;
    private Deck deck;

    // Programmeinstieg für JavaFX
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/setgame/setgame/ui/GameBoard.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Set Game Board");
        primaryStage.show();
    }

    // Methode, um die anfänglichen Karten auf das Spielfeld zu zeichnen
    private void drawInitialCards() {
        List<Card> cardsOnBoard = deck.drawCards(12);
        int row = 0, col = 0;
        for (Card card : cardsOnBoard) {
            gameBoard.addCardToBoard(card, row, col++);
            if (col > 3) {
                col = 0;
                row++;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


