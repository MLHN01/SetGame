package com.setgame.setgame.util;

import java.util.List;
import com.setgame.setgame.Card;
public class SetGameUtils {

    public static boolean isSet(List<Card> cards) {
        if (cards.size() != 3) {
            throw new IllegalArgumentException("Ein Set muss genau 3 Karten enthalten.");
        }

        boolean colorsMatch = cards.stream().map(Card::getColor).distinct().count() <= 1 ||
                cards.stream().map(Card::getColor).distinct().count() == 3;
        boolean shapesMatch = cards.stream().map(Card::getShape).distinct().count() <= 1 ||
                cards.stream().map(Card::getShape).distinct().count() == 3;
        boolean fillingsMatch = cards.stream().map(Card::getFilling).distinct().count() <= 1 ||
                cards.stream().map(Card::getFilling).distinct().count() == 3;
        boolean numbersMatch = cards.stream().map(Card::getNumber).distinct().count() <= 1 ||
                cards.stream().map(Card::getNumber).distinct().count() == 3;

        return colorsMatch && shapesMatch && fillingsMatch && numbersMatch;
    }
}
