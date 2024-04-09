package com.setgame.setgame;

import com.setgame.setgame.ui.GameBoard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {

    private GameBoard gameBoard;
    private Deck deck;

    @Override
    public void start(Stage primaryStage) {
        deck = new Deck(); // Deck erstellen und mischen
        gameBoard = new GameBoard(); // Initialisiere dein GameBoard
        drawInitialCards();

        primaryStage.setScene(new Scene(gameBoard.getLayout()));
        primaryStage.show();
    }

    // Methode, um die anfänglichen Karten auf das Spielfeld zu zeichnen
    private void drawInitialCards() {
        List<Card> cardsOnBoard = deck.drawCards(12);
        int row = 0, col = 0;
        for (Card card : cardsOnBoard) {
            gameBoard.addCardToBoard(card, row, col++);
            if (col > 2) {
                col = 0;
                row++;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


