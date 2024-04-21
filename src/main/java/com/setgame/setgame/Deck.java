package com.setgame.setgame;

import com.setgame.setgame.enums.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

// Klasse, die ein Deck repräsentiert
public class Deck {

    // Liste, die die Karten im Deck speichert
    private List<Card> cards = new ArrayList<>();

    // Konstruktor, der das Deck initialisiert
    public Deck() {
        resetDeck();
    }

    // Methode, die mehrere Karten vom Deck zieht
    public List<Card> drawCards(int numberOfCards) {
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            if (cards.isEmpty()) {
                break;
            }
            drawnCards.add(cards.remove(0));
        }
        return drawnCards;
    }

    // Methode, die das Deck zurücksetzt
    public void resetDeck(){
        cards.clear();
        for (CardColor color : CardColor.values()) {
            for (CardShape shape : CardShape.values()) {
                for (CardFilling filling : CardFilling.values()) {
                    for (CardNumber number : CardNumber.values()) {
                        cards.add(new Card(color, shape, filling, number, "card_" + color + "_" + shape + "_" + filling + "_" + number + ".png"));
                    }
                }
            }
        }
        Collections.shuffle(cards);
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
