package com.setgame.setgame.ui;

import com.setgame.setgame.Card;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

// Klasse für das Spielfeld
public class GameBoard {

    // Layout-Element für das Spielfeld
    private GridPane gridPane;

    // Anzahl der ausgewählten Karten
    private int selectedCardsCount = 0;

    public GameBoard() {
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-background-color: #f0f0f0;");
    }




    public void addCardToBoard(Card card, int row, int col) {
        Button cardButton = new Button(card.toString());
        cardButton.setPrefSize(600, 100); // Festlegen der Größe für die Buttons
        cardButton.setOnAction(event -> handleCardClick(row, col));
        gridPane.add(cardButton, col, row);
    }

    // Event-Handler-Methode für Klick auf Karte
    private void handleCardClick(int rows, int cols) {
        System.out.println("Karte an Position (" + rows + ", " + cols + ") wurde geklickt.");
        selectedCardsCount++;
        if (selectedCardsCount == 3) {
            System.out.println("3 Karten ausgewählt!");
            selectedCardsCount = 0; // Zurücksetzen
        }

    }

    // Getter für das Layout
    public GridPane getLayout() {
        return gridPane;
    }
}


