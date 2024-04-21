package com.setgame.setgame.ui;

import java.util.ArrayList;
import java.util.List;

import com.setgame.setgame.Card;
import com.setgame.setgame.util.SetGameUtils;

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

    // Liste für die ausgewählten Karten
    private List<Card> selectedCards = new ArrayList<>();

    // Konstruktor
    public GameBoard() {
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-background-color: #f0f0f0;");
    }

    // Methode, um eine Karte zum Spielfeld hinzuzufügen
    public void addCardToBoard(Card card, int row, int col) {
        Button cardButton = new Button(card.toString());
        cardButton.setPrefSize(400, 100);
        cardButton.setOnAction(event -> handleCardClick(card, row, col));
        gridPane.add(cardButton, col, row);
    }

    // Event-Handler-Methode für Klick auf Karte
    private void handleCardClick(Card card, int rows, int cols) {
        selectedCardsCount++;
        System.out.println(card.toString());
        selectedCards.add(card);

        // Wenn 3 Karten ausgewählt wurden, prüfe, ob es ein Set ist
        if (selectedCardsCount == 3) {

            // Prüfen, ob Set gefunden wurde
            if(SetGameUtils.isSet(selectedCards)) {
                System.out.println("Set gefunden!");
            } else {
                System.out.println("Kein Set gefunden!");
            }
            
            // Zurücksetzen
            selectedCardsCount = 0;
            selectedCards.clear();
        }
    }

    // Getter für das Layout
    public GridPane getLayout() {
        return gridPane;
    }
}


