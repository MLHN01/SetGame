package com.setgame.setgame.util;

import com.setgame.setgame.GameObjects.Card;

import java.util.List;

public class GameState {
    private List<Card> cardsOnBoard;

    public GameState(List<Card> cardsOnBoard) {
        this.cardsOnBoard = cardsOnBoard;
    }

    public List<Card> getCardsOnBoard() {
        return cardsOnBoard;
    }

    public void setCardsOnBoard(List<Card> cardsOnBoard) {
        this.cardsOnBoard = cardsOnBoard;
    }
}
