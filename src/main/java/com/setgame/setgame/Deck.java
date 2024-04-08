package com.setgame.setgame;

import com.setgame.setgame.enums.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    public Deck() {
        for (CardColor color : CardColor.values()) {
            for (CardShape shape : CardShape.values()) {
                for (CardFilling filling : CardFilling.values()) {
                    for (CardNumber number : CardNumber.values()) {
                        cards.add(new Card(color, shape, filling, number));
                    }
                }
            }
        }
        Collections.shuffle(cards);
    }

    // Methoden zum Ziehen einer Karte oder zum Überprüfen des Decks
}
