package com.setgame.setgame.util;

import java.util.List;
import com.setgame.setgame.Card;

// Die Klasse SetGameUtils enthält eine Methode isSet, die prüft, ob eine Liste von Karten ein Set ist.
public class SetGameUtils {

    // Die Methode isSet prüft, ob eine Liste von Karten ein Set ist.
    public static boolean isSet(List<Card> cards) {
        if (cards.size() != 3) {
            throw new IllegalArgumentException("Ein Set muss genau 3 Karten enthalten.");
        }


        // Prüft, ob die Farben der Karten alle gleich oder alle unterschiedlich sind.
        boolean colorsMatch = cards.stream().map(Card::getColor).distinct().count() <= 1 ||
                cards.stream().map(Card::getColor).distinct().count() == 3;

        // Prüft, ob die Formen der Karten alle gleich oder alle unterschiedlich sind.
        boolean shapesMatch = cards.stream().map(Card::getShape).distinct().count() <= 1 ||
                cards.stream().map(Card::getShape).distinct().count() == 3;

        // Prüft, ob die Füllungen der Karten alle gleich oder alle unterschiedlich sind.
        boolean fillingsMatch = cards.stream().map(Card::getFilling).distinct().count() <= 1 ||
                cards.stream().map(Card::getFilling).distinct().count() == 3;

        // Prüft, ob die Zahlen der Karten alle gleich oder alle unterschiedlich sind.
        boolean numbersMatch = cards.stream().map(Card::getNumber).distinct().count() <= 1 ||
                cards.stream().map(Card::getNumber).distinct().count() == 3;

        // Gibt true zurück, wenn alle Bedingungen erfüllt sind, andernfalls false.
        return colorsMatch && shapesMatch && fillingsMatch && numbersMatch;
    }
}
