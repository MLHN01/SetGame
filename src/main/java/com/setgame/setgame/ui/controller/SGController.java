package com.setgame.setgame.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;
import com.setgame.setgame.GameObjects.Card;
import com.setgame.setgame.GameObjects.Deck;
import com.setgame.setgame.ui.CardButtonStyle;
import com.setgame.setgame.util.SetGameUtils;

public class SGController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane gridPane;
    @FXML
    private Button resetButton, showSetButton;
    @FXML
    private Label scoreButton;
    private List<Card> cardsOnBoard = new ArrayList<>();
    private List<Card> selectedCards = new ArrayList<>();
    private Deck deck;
    private int score = 0;

    @FXML
    public void initialize() {
        updateScoreButton();
        configureResetButton();
        configureShowSetButton();
        deck = new Deck();
        drawInitialCards();
    }

    private void updateScoreButton() {
        scoreButton.setText("" + score);
    }

    private void configureResetButton() {
        resetButton.setText("Reset");
        resetButton.setOnAction(event -> resetGame());
    }

    private void configureShowSetButton() {
        showSetButton.setText("Show Set");
        showSetButton.setOnAction(event -> highlightSetOnBoard());
    }

    // Methode, die das Spiel zurücksetzt
    private void resetGame() {
        clearBoard();
        deck.resetDeck();
        drawInitialCards();
        score = 0;
        updateScoreButton();
    }



    // Methode, die eine Karte zum Brett hinzufügt
    public void addCardToBoard(Card card, int row, int col) {
        Button cardButton = createCardButton(card);
        cardsOnBoard.add(card);
        gridPane.add(cardButton, col, row);
    }

    // Methode, die einen Button für eine Karte erstellt
    private Button createCardButton(Card card) {
        Image image = new Image(getClass().getResourceAsStream(card.cardImagePath()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(98);

        Button cardButton = new Button("", imageView);
        cardButton.setOnAction(event -> handleCardClick(card));
        // add systel with thick border
        cardButton.setStyle(CardButtonStyle.NotSelected);
        
        card.setButton(cardButton);
        return cardButton;
    }


    // Methode, die aufgerufen wird, wenn eine Karte geklickt wird
    private void handleCardClick(Card card) {
        if (card.isSelected()) {
            deselectCard(card);
        } else if (selectedCards.size() < 3) {
            selectCard(card);
        }
        if (selectedCards.size() == 3) {
            processSelectedCards();
        }
    }

    // Methode, die eine Karte deselektiert
    private void deselectCard(Card card) {
        card.getButton().setStyle(CardButtonStyle.NotSelected);
        card.setSelected(false);
        selectedCards.remove(card);
    }

    // Methode, die eine Karte selektiert
    private void selectCard(Card card) {
        card.setSelected(true);
        card.getButton().setStyle(CardButtonStyle.Selected);
        selectedCards.add(card);
    }

    // Methode, die die ausgewählten Karten prüft
    private void processSelectedCards() {
        if (SetGameUtils.isSet(selectedCards)) {
            handleSetFound();
        } else {
            handleSetNotFound();
        }
        selectedCards.clear();
    }

    // Methode, die aufgerufen wird, wenn ein Set gefunden wurde
    private void handleSetFound() {
        score++;
        updateScoreButton();
        removeSelectedCards();
        refillBoard();
    }

    // Methode, die aufgerufen wird, wenn kein Set gefunden wurde
    private void handleSetNotFound() {

        new ArrayList<>(selectedCards).forEach(this::deselectCard);
    }

    // Methode, die die ausgewählten Karten vom Brett entfernt
    private void removeSelectedCards() {
        selectedCards.forEach(card -> {
            gridPane.getChildren().remove(card.getButton());
            cardsOnBoard.remove(card);
        });
    }

    // Methode, die das Brett auffüllt
    private void refillBoard() {
        List<Card> newCards;
        boolean isSetFound;
    
        do {
            newCards = deck.drawCards(3);  // Ziehe drei neue Karten
            List<Card> potentialBoard = new ArrayList<>(cardsOnBoard);  // Kopiere die aktuell auf dem Brett liegenden Karten
            potentialBoard.addAll(newCards);  // Füge die neuen Karten hinzu
    
            isSetFound = SetGameUtils.findSet(potentialBoard) != null;  // Prüfe, ob ein Set vorhanden ist
            if (!isSetFound) {
                deck.returnCards(newCards);  // Gebe die gezogenen Karten zurück ins Deck, falls kein Set gefunden wird
            }
        } while (!isSetFound);  // Wiederhole, bis ein Set gefunden wird
    
        replaceCards(newCards);  // Ersetze die alten Karten durch die neuen Karten auf dem Brett
    }
     
    // Methode, die alte Karten durch neue Karten auf dem Brett ersetzt
    private void replaceCards(List<Card> newCards) {
        for (int i = 0; i < newCards.size(); i++) {
            Card newCard = newCards.get(i);
            Card oldCard = selectedCards.get(i);
            newCard.setRow(oldCard.getRow());
            newCard.setCol(oldCard.getCol());
            addCardToBoard(newCard, newCard.getRow(), newCard.getCol());
        }
    }
    
    // Methode, die die Anfangskarten auf das Brett zeichnet
    private void drawInitialCards() {
        List<Card> initialCards;
        do {
            deck.resetDeck();
            initialCards = deck.drawCards(12);
        } while (SetGameUtils.findSet(initialCards) == null);
        int row = 0, col = 0;
        for (Card card : initialCards) {
            card.setRow(row);
            card.setCol(col);
            addCardToBoard(card, row, col);
            if (++col > 2) {
                col = 0;
                row++;
            }
        }
    }
    

    // Methode, die ein Set auf dem Brett hervorhebt
    private void highlightSetOnBoard() {
        List<Card> set = SetGameUtils.findSet(cardsOnBoard);
        if (set != null) {
            set.forEach(card -> card.getButton().setStyle(CardButtonStyle.Highlighted));
        } else {
            System.out.println("Kein Set gefunden!");
        }
    }

    // Methode, die das Brett leert
    private void clearBoard() {
        gridPane.getChildren().clear();
        cardsOnBoard.clear();
    }
}
