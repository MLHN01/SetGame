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

    @FXML
    private GridPane gridPane;

    private int selectedCardsCount = 0;
    private List<Card> selectedCards = new ArrayList<>();
    private Deck deck;

    @FXML
    public void initialize() {
        deck = new Deck(); // Deck erstellen und mischen
        drawInitialCards();
    }

    // Methode, um eine Karte auf das Spielfeld zu zeichnen
    public void addCardToBoard(Card card, int row, int col) {
        // Pfad zum Bild der Karte
        String imagePath = "/com/setgame/setgame/CardImages/" + card.getImageFileName();
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150); 
        imageView.setFitHeight(98); 

        Button cardButton = new Button();
        cardButton.setGraphic(imageView); // Bild zum Button hinzufügen
        cardButton.setOnAction(event -> handleCardClick(card,cardButton));

        gridPane.add(cardButton, col, row);
    }

    // Methode, um auf Klicks auf Karten zu reagieren
    private void handleCardClick(Card card, Button cardButton) {
        selectedCardsCount++;
        cardButton.setDisable(true);
        System.out.println(card.toString());
        selectedCards.add(card);

        if (selectedCardsCount == 3) {
            if(SetGameUtils.isSet(selectedCards)) {
                System.out.println("Set gefunden!");
            } else {
                System.out.println("Kein Set gefunden!");
            }
            selectedCardsCount = 0;
            selectedCards.clear();
        }
    }

    // Methode, um die anfänglichen Karten auf das Spielfeld zu zeichnen
    private void drawInitialCards() {
        List<Card> cardsOnBoard = deck.drawCards(12);
        int row = 0, col = 0;
        for (Card card : cardsOnBoard) {
            addCardToBoard(card, row, col++);
            if (col > 2) {
                col = 0;
                row++;
            }
        }
    }
}
