package com.setgame.setgame.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GameBoard {
    private GridPane gridPane;
    private int selectedCardsCount = 0;

    public GameBoard() {
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-background-color: #f0f0f0;");

        initializeCards(3, 4); // Für Set Game typische 3x4 Anordnung
    }

    private void initializeCards(int rows, int cols) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Button card = new Button();
                card.setPrefSize(120, 180);
                // Hier würdest du die Darstellung der Karte anpassen,
                // z.B. mit Bildern oder spezifischen Symbolen
                final int r = row, c = col;
                card.setOnAction(event -> handleCardClick(r, c));
                gridPane.add(card, col, row);
            }
        }
    }

    private void handleCardClick(int row, int col) {
        System.out.println("Karte angeklickt bei: Reihe " + row + ", Spalte " + col);
        // Hier würde die Logik implementiert, um die Auswahl der Karten zu verwalten
        // und zu überprüfen, ob ein Set gefunden wurde.
    }

    public GridPane getLayout() {
        return gridPane;
    }
}


