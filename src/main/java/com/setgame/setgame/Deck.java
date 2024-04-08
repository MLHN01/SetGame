package com.setgame.setgame;

import com.setgame.setgame.enums.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    // Konstruktor, der das Deck initialisiert
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



   // Methode, die eine Karte vom Deck zieht
    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }

    // Methode, die prüft, ob das Deck leer ist
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    // Methode, die die Anzahl der Karten im Deck zurückgibt
    public int size() {
        return cards.size();
    }
}
