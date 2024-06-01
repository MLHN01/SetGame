package com.setgame.setgame.util;

import com.setgame.setgame.ui.CardButtonStyle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

import com.setgame.setgame.GameObjects.Card;
import com.setgame.setgame.GameObjects.Deck;

// Implementiert die Spiellogik
public class Game {

    private GridPane gridPane;
    private Label scoreButton, timerLabel;
    private List<Card> cardsOnBoard = new ArrayList<>();
    private List<Card> selectedCards = new ArrayList<>();
    private Deck deck;
    private int score = 0;
    private GameTimer gameTimer;
    private int gameTimeInSeconds = 300;

    public Game(GridPane gridPane, Label scoreButton, Label timerLabel) {
        this.gridPane = gridPane;
        this.scoreButton = scoreButton;
        this.timerLabel = timerLabel;
    }

    // Startet ein neues Spiel
    public void startNewGame() {
        clearBoard(); // Leert das Spielfeld
        deck = new Deck(); // Erstellt ein neues Deck
        drawInitialCards(); // Zeichnet die anfänglichen Karten
        score = 0; // Setzt den Punktestand zurück
        updateScoreButton(); // Aktualisiert die Punktestand-Anzeige
        if (gameTimer != null) {
            gameTimer.stop(); // Stoppt den alten Timer, falls vorhanden
        }
        gameTimer = new GameTimer(timerLabel, gameTimeInSeconds, this::handleTimeUp); // Erstellt einen neuen Timer
        gameTimer.start(); // Startet den Timer
    }

    // Aktualisiert die Punktestand-Anzeige
    private void updateScoreButton() {
        scoreButton.setText("" + score);
    }

    // Leert das Spielfeld
    private void clearBoard() {
        gridPane.getChildren().clear();
        cardsOnBoard.clear();
    }

    // Zeichnet die anfänglichen Karten auf das Spielfeld
    private void drawInitialCards() {
        List<Card> initialCards;
        do {
            deck.resetDeck(); // Setzt das Deck zurück
            initialCards = deck.drawCards(12); // Zieht 12 Karten
        } while (SetGameUtils.findSet(initialCards) == null); // Wiederholt, falls kein Set gefunden wird
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

    // Fügt eine Karte zum Spielfeld hinzu
    public void addCardToBoard(Card card, int row, int col) {
        Button cardButton = createCardButton(card);
        cardsOnBoard.add(card);
        gridPane.add(cardButton, col, row);
    }

    // Erstellt einen Button für eine Karte
    private Button createCardButton(Card card) {
        Image image = new Image(getClass().getResourceAsStream(card.cardImagePath()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(98);

        Button cardButton = new Button("", imageView);
        cardButton.setOnAction(event -> handleCardClick(card));
        cardButton.setStyle(CardButtonStyle.NotSelected);

        card.setButton(cardButton);
        return cardButton;
    }

    // Handhabt den Klick auf eine Karte
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

    // Deselektiert eine Karte
    private void deselectCard(Card card) {
        card.getButton().setStyle(CardButtonStyle.NotSelected);
        card.setSelected(false);
        selectedCards.remove(card);
    }

    // Selektiert eine Karte
    private void selectCard(Card card) {
        card.setSelected(true);
        card.getButton().setStyle(CardButtonStyle.Selected);
        selectedCards.add(card);
    }

    // Prüft die ausgewählten Karten
    private void processSelectedCards() {
        if (SetGameUtils.isSet(selectedCards)) {
            handleSetFound();
        } else {
            handleSetNotFound();
        }
        selectedCards.clear();
    }

    // Handhabt den Fall, dass ein Set gefunden wurde
    private void handleSetFound() {
        score++;
        updateScoreButton();
        removeSelectedCards();
        refillBoard();
    }

    // Handhabt den Fall, dass kein Set gefunden wurde
    private void handleSetNotFound() {
        new ArrayList<>(selectedCards).forEach(this::deselectCard);
    }

    // Entfernt die ausgewählten Karten vom Spielfeld
    private void removeSelectedCards() {
        selectedCards.forEach(card -> {
            gridPane.getChildren().remove(card.getButton());
            cardsOnBoard.remove(card);
        });
    }

    // Füllt das Spielfeld auf
    private void refillBoard() {
        List<Card> newCards;
        boolean isSetFound;

        do {
            newCards = deck.drawCards(3); // Zieht 3 neue Karten
            List<Card> potentialBoard = new ArrayList<>(cardsOnBoard); // Kopiert die aktuellen Karten auf dem Spielfeld
            potentialBoard.addAll(newCards); // Fügt die neuen Karten hinzu

            isSetFound = SetGameUtils.findSet(potentialBoard) != null; // Prüft, ob ein Set vorhanden ist
            if (!isSetFound) {
                deck.returnCards(newCards); // Gibt die gezogenen Karten zurück ins Deck, falls kein Set gefunden wird
            }

            if (deck.isEmpty()) {
                startNewGame(); // Startet ein neues Spiel, falls das Deck leer ist
                return;
            }

        } while (!isSetFound); // Wiederholt, bis ein Set gefunden wird

        replaceCards(newCards); // Ersetzt die alten Karten durch die neuen Karten auf dem Brett
    }

    // Ersetzt alte Karten durch neue Karten auf dem Spielfeld
    private void replaceCards(List<Card> newCards) {
        for (int i = 0; i < newCards.size(); i++) {
            Card newCard = newCards.get(i);
            Card oldCard = selectedCards.get(i);
            newCard.setRow(oldCard.getRow());
            newCard.setCol(oldCard.getCol());
            addCardToBoard(newCard, newCard.getRow(), newCard.getCol());
        }
    }

    // Hebt ein Set auf dem Spielfeld hervor
    public void highlightSetOnBoard() {
        List<Card> set = SetGameUtils.findSet(cardsOnBoard);
        if (set != null) {
            set.forEach(card -> card.getButton().setStyle(CardButtonStyle.Highlighted));
        } else {
            System.out.println("Kein Set gefunden!");
        }
    }

    // Handhabt den Fall, dass die Zeit abgelaufen ist
    private void handleTimeUp() {
        System.out.println("Zeit ist um!");
    }
}
