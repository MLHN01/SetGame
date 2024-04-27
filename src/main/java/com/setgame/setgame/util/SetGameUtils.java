package com.setgame.setgame.util;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import com.setgame.setgame.Card;

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

    // Hilfsmethode zur Überprüfung, ob alle Werte einer Eigenschaft gleich oder verschieden sind.
    private static <T> boolean isAllSameOrDifferent(List<Card> cards, Function<Card, T> extractor) {
        long uniqueCount = cards.stream()
                                .map(extractor)
                                .distinct()
                                .count();

        // Gültig, wenn alle Werte gleich (Count = 1) oder alle verschieden (Count = 3) sind.
        return uniqueCount == 1 || uniqueCount == 3;
    }
}
