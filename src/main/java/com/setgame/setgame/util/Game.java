package com.setgame.setgame.util;

import com.google.gson.Gson;
import com.setgame.setgame.ui.CardButtonStyle;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import com.setgame.setgame.GameObjects.Card;
import com.setgame.setgame.GameObjects.Deck;
import com.setgame.setgame.networking.SetGameClient;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private GridPane gridPane;
    private Label scoreButton, timerLabel;
    private List<Card> cardsOnBoard = new ArrayList<>();
    private List<Card> selectedCards = new ArrayList<>();
    private Deck deck;
    private int score = 0;
    private GameTimer gameTimer;
    private int gameTimeInSeconds = 300;
    private SetGameClient client;
    private Gson gson = new Gson();
    private boolean isMultiplayer;

    // Constructor for Singleplayer
    public Game(GridPane gridPane, Label scoreButton, Label timerLabel) {
        this.gridPane = gridPane;
        this.scoreButton = scoreButton;
        this.timerLabel = timerLabel;
    }

    // Constructor for Multiplayer
    public Game(GridPane gridPane, Label scoreButton, Label timerLabel, SetGameClient client) {
        this(gridPane, scoreButton, timerLabel);
        this.client = client;
        this.isMultiplayer = true;
    }

    public void startNewGame() {
        clearBoard();
        deck = new Deck();
        drawInitialCards();
        score = 0;
        updateScoreButton();
        if (gameTimer != null) {
            gameTimer.stop();
        }
        gameTimer = new GameTimer(timerLabel, gameTimeInSeconds, this::handleTimeUp);
        gameTimer.start();
        if (isMultiplayer) {
            syncGameState();
        }
    }

    public void setGameTime(int gameTime) {
        this.gameTimeInSeconds = gameTime;
        if (gameTimer != null) {
            gameTimer.setTime(gameTimeInSeconds);
        }
    }

    private void updateScoreButton() {
        scoreButton.setText("" + score);
    }

    private void clearBoard() {
        gridPane.getChildren().clear();
        cardsOnBoard.clear();
    }

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
        if (isMultiplayer) {
            syncGameState();
        }
    }

    public void addCardToBoard(Card card, int row, int col) {
        Button cardButton = createCardButton(card);
        cardsOnBoard.add(card);
        gridPane.add(cardButton, col, row);
    }

    private Button createCardButton(Card card) {
        // Add the image and actions here
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

    private void deselectCard(Card card) {
        card.getButton().setStyle(CardButtonStyle.NotSelected);
        card.setSelected(false);
        selectedCards.remove(card);
    }

    private void selectCard(Card card) {
        card.setSelected(true);
        card.getButton().setStyle(CardButtonStyle.Selected);
        selectedCards.add(card);
    }

    private void processSelectedCards() {
        if (SetGameUtils.isSet(selectedCards)) {
            handleSetFound();
        } else {
            handleSetNotFound();
        }
        selectedCards.clear();
    }

    private void handleSetFound() {
        score++;
        updateScoreButton();
        removeSelectedCards();
        refillBoard();
        if (isMultiplayer) {
            syncGameState();
        }
    }

    private void handleSetNotFound() {
        new ArrayList<>(selectedCards).forEach(this::deselectCard);
    }

    private void removeSelectedCards() {
        selectedCards.forEach(card -> {
            gridPane.getChildren().remove(card.getButton());
            cardsOnBoard.remove(card);
        });
    }

    private void refillBoard() {
        List<Card> newCards;
        boolean isSetFound;

        do {
            newCards = deck.drawCards(3);
            List<Card> potentialBoard = new ArrayList<>(cardsOnBoard);
            potentialBoard.addAll(newCards);

            isSetFound = SetGameUtils.findSet(potentialBoard) != null;
            if (!isSetFound) {
                deck.returnCards(newCards);
            }

            if (deck.isEmpty()) {
                startNewGame();
                return;
            }

        } while (!isSetFound);

        replaceCards(newCards);
        if (isMultiplayer) {
            syncGameState();
        }
    }

    private void replaceCards(List<Card> newCards) {
        for (int i = 0; i < newCards.size(); i++) {
            Card newCard = newCards.get(i);
            Card oldCard = selectedCards.get(i);
            newCard.setRow(oldCard.getRow());
            newCard.setCol(oldCard.getCol());
            addCardToBoard(newCard, newCard.getRow(), newCard.getCol());
        }
    }

    public void highlightSetOnBoard() {
        List<Card> set = SetGameUtils.findSet(cardsOnBoard);
        if (set != null) {
            set.forEach(card -> card.getButton().setStyle(CardButtonStyle.Highlighted));
        } else {
            System.out.println("Kein Set gefunden!");
        }
    }

    private void syncGameState() {
        GameState gameState = new GameState(this.cardsOnBoard);
        String gameStateJson = gson.toJson(gameState);
        client.sendMessage(gameStateJson);
    }

    public void updateGameState(String gameStateJson) {
        GameState gameState = gson.fromJson(gameStateJson, GameState.class);
        this.cardsOnBoard.clear();
        this.cardsOnBoard.addAll(gameState.getCardsOnBoard());
        Platform.runLater(this::redrawBoard);
    }


    private void redrawBoard() {
        clearBoard();
        for (Card card : cardsOnBoard) {
            card.restoreTransientFields(); // Stellen Sie die transienten Felder wieder her
            Button cardButton = createCardButton(card);
            card.setButton(cardButton);
            if (card.isSelected()) {
                cardButton.setStyle(CardButtonStyle.Selected);
            } else {
                cardButton.setStyle(CardButtonStyle.NotSelected);
            }
            addCardToBoard(card, card.getRow(), card.getCol());
        }
    }


    private void handleTimeUp() {
        if (isMultiplayer) {
            client.timeUp();
        }
        System.out.println("Zeit ist um!");
    }
}
