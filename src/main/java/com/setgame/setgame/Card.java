package com.setgame.setgame;

import com.setgame.setgame.enums.*;

public class Card {
    private final CardColor color;
    private final CardShape shape;
    private final CardFilling filling;
    private final CardNumber number;

    public Card(CardColor color, CardShape shape, CardFilling filling, CardNumber number) {
        this.color = color;
        this.shape = shape;
        this.filling = filling;
        this.number = number;
    }

    public CardColor getColor() {
        return color;
    }

    public CardShape getShape() {
        return shape;
    }

    public CardFilling getFilling() {
        return filling;
    }

    public CardNumber getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", shape=" + shape +
                ", filling=" + filling +
                ", number=" + number +
                '}';
    }
}