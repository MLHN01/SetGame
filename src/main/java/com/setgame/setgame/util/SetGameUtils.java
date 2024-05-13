package com.setgame.setgame.util;

import java.util.List;
import java.util.function.Function;
import com.setgame.setgame.Card;
import com.setgame.setgame.enums.CardColor;
import com.setgame.setgame.enums.CardFilling;
import com.setgame.setgame.enums.CardNumber;
import com.setgame.setgame.enums.CardShape;

// Implementiert die Set Game Logik.
public class SetGameUtils {

    // Überprüft, ob eine Liste von Karten ein gültiges Set bildet.
    public static boolean isSet(List<Card> cards) {
        if (cards.size() != 3) {
            throw new IllegalArgumentException("Ein Set muss genau 3 Karten enthalten.");
        }

        // Überprüft, ob die Eigenschaften der Karten alle gleich oder alle verschieden sind.
        return isAllSameOrDifferent(cards, Card::getColor) &&
               isAllSameOrDifferent(cards, Card::getShape) &&
               isAllSameOrDifferent(cards, Card::getFilling) &&
               isAllSameOrDifferent(cards, Card::getNumber);
    }


    /*----------------------------------------------------------------------*/
    // Generische Lösung:
    /*----------------------------------------------------------------------*/

    // Methode zur Überprüfung, ob alle Werte einer Eigenschaft gleich oder verschieden sind.
    private static <T> boolean isAllSameOrDifferent(List<Card> cards, Function<Card, T> extractor) {
        
        // Zählt die Anzahl der einzigartigen Werte.
        long uniqueCount = cards.stream()
                                .map(extractor) // Holt eine bestimmte Eigenschaft aus jeder Karte.
                                .distinct()     // Entfernt Duplikate.
                                .count();       // Zählt die Anzahl der einzigartigen Werte.
 
        // Gültig, wenn alle Werte gleich (Count = 1) oder alle verschieden (Count = 3) sind.
        return uniqueCount == 1 || uniqueCount == 3;
    }

    /*----------------------------------------------------------------------*/
    // Nicht generische Lösung:
    /*----------------------------------------------------------------------*/

    // Überprüft, ob alle Karten die gleiche Farbe haben oder alle verschiedene Farben.
    private static boolean isAllSameOrDifferentColor(List<Card> cards) {
        CardColor firstColor = cards.get(0).getColor();
        boolean allSame = cards.stream().allMatch(card -> card.getColor() == firstColor);
        boolean allDifferent = cards.stream().distinct().count() == 3;
        return allSame || allDifferent;
    }

    // Überprüft, ob alle Karten die gleiche Form haben oder alle verschiedene Formen.
    private static boolean isAllSameOrDifferentShape(List<Card> cards) {
        CardShape firstShape = cards.get(0).getShape();
        boolean allSame = cards.stream().allMatch(card -> card.getShape() == firstShape);
        boolean allDifferent = cards.stream().distinct().count() == 3;
        return allSame || allDifferent;
    }

    // Überprüft, ob alle Karten die gleiche Füllung haben oder alle verschiedene Füllungen.
    private static boolean isAllSameOrDifferentFilling(List<Card> cards) {
        CardFilling firstFilling = cards.get(0).getFilling();
        boolean allSame = cards.stream().allMatch(card -> card.getFilling() == firstFilling);
        boolean allDifferent = cards.stream().distinct().count() == 3;
        return allSame || allDifferent;
    }

    // Überprüft, ob alle Karten die gleiche Anzahl haben oder alle verschiedene Anzahlen.
    private static boolean isAllSameOrDifferentNumber(List<Card> cards) {
        CardNumber firstNumber = cards.get(0).getNumber();
        boolean allSame = cards.stream().allMatch(card -> card.getNumber() == firstNumber);
        boolean allDifferent = cards.stream().distinct().count() == 3;
        return allSame || allDifferent;
    }


    // Findet ein gültiges Set in einer Liste von Karten.
    public static List<Card> findSet(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            for (int j = i + 1; j < cards.size(); j++) {
                for (int k = j + 1; k < cards.size(); k++) {
                    List<Card> set = List.of(cards.get(i), cards.get(j), cards.get(k));
                    if (isSet(set)) {
                        return set;
                    }
                }
            }
        }
        return null;
    }
}
