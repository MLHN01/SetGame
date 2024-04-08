package com.setgame.setgame.ui;

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

        initializeCards(3, 4); // 3 Reihen, 4 Spalten
    }

    // Initialisiere die Karten auf dem Spielfeld
    private void initializeCards(int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Button card = new Button();
                card.setPrefSize(120, 180);

                final int r = row, c = col;
                card.setOnAction(event -> handleCardClick(r, c)); // Event-Handler für Klick auf Karte
                gridPane.add(card, col, row);
            }
        }
    }

    // Event-Handler-Methode für Klick auf Karte
    private void handleCardClick(int row, int col) {
        System.out.println("Karte angeklickt bei: Reihe " + row + ", Spalte " + col);

    }

    // Getter für das Layout
    public GridPane getLayout() {
        return gridPane;
    }
}


