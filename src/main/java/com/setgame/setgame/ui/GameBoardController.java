package com.setgame.setgame.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;

import com.setgame.setgame.Card;
import com.setgame.setgame.Deck;
import com.setgame.setgame.util.SetGameUtils;

public class GameBoardController {

    // Referenz zum GridPane im FXML
    @FXML
    private GridPane gridPane;

    private int selectedCardsCount;
    private List<Card> selectedCards = new ArrayList<>();
    private Deck deck;
    private int score;

    Button scoreButton = new Button();
    Button resetButton = new Button();

    // Methode, die beim aufrufen des FXML-Files ausgeführt wird
    @FXML
    public void initialize() {
        deck = new Deck(); // Deck erstellen und mischen
        drawInitialCards();
        score = 0;
        selectedCardsCount = 0;
        // score feld auf die gridpane hinzufügen
        scoreButton.setText("Score: " + score);
        gridPane.add(scoreButton, 3, 0);

        // reset board button hinzufügen
        resetButton.setText("Reset Board");
        resetButton.setOnAction(event -> {
            clearBoard();
            initialize();
        });

        gridPane.add(resetButton, 3, 1);
    }

    // Methode, um eine Karte auf das Spielfeld zu zeichnen
    public void addCardToBoard(Card card, int row, int col) {
        // Pfad zum Bild der Karte
        String imagePath = "/com/setgame/setgame/CardImages/" + card.getImageFileName();
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150); 
        imageView.setFitHeight(98); 

        // Button erstellen und Bild hinzufügen
        Button cardButton = new Button();
        cardButton.setGraphic(imageView); // Bild zum Button hinzufügen
        cardButton.setOnAction(event -> handleCardClick(card,cardButton));
        card.setButton(cardButton); // Button zur Karte hinzufügen
        gridPane.add(cardButton, col, row); // Button zum GridPane hinzufügen
    }

    // Methode, um auf Klicks auf Karten zu reagieren
    private void handleCardClick(Card card, Button cardButton) {
        selectedCardsCount++; // Anzahl der ausgewählten Karten erhöhen
        cardButton.setDisable(true); // Karte deaktivieren
        System.out.println(card.toString()); 
        selectedCards.add(card); // Karte zur Liste der ausgewählten Karten hinzufügen

        // Wenn 3 Karten ausgewählt wurden, prüfen, ob es ein Set ist
        if (selectedCardsCount == 3) {

            if(SetGameUtils.isSet(selectedCards)) {

                System.out.println("Set gefunden!");
                
                // Score erhöhen
                score++;
                updateScore();

                // Karten entfernen
                for (Card selectedCard : selectedCards) {
                    gridPane.getChildren().remove(selectedCard.getButton());
                }

                // Neue Karten hinzufügen
                List<Card> newCards = deck.drawCards(3);

                int index = 0;
                for (Card newCard : newCards) {
                    int row = selectedCards.get(index).getRow(); // Zeile der entfernten Karte
                    int col = selectedCards.get(index).getCol(); // Spalte der entfernten Karte
                    
                    // Position der neuen Karte setzen
                    newCard.setRow(row); 
                    newCard.setCol(col);
                    
                    addCardToBoard(newCard, row, col);
                    
                    index++;
                }


            } else {
                System.out.println("Kein Set gefunden!");

                // Karten wieder aktivieren
                for(Card selectedCard : selectedCards){
                    selectedCard.getButton().setDisable(false);

                }       
            }
            // Zurücksetzen der ausgewählten Karten
            selectedCardsCount = 0;
            selectedCards.clear();
        }
    }

    // Methode, um die anfänglichen Karten auf das Spielfeld zu zeichnen
    private void drawInitialCards() {
        List<Card> cardsOnBoard = deck.drawCards(18);
        int row = 0, col = 0;
        for (Card card : cardsOnBoard) {
            
            card.setRow(row);
            card.setCol(col);

            addCardToBoard(card, row, col++);

            if (col > 2) {
                col = 0;
                row++;
            }
        }
    }

    private void updateScore() {
        scoreButton.setText("Score: " + score);
    }

    // Methode welche alle Karten vom Spielfeld
    private void clearBoard() {
        gridPane.getChildren().clear();
    
    }
}
