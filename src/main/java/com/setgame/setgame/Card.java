package com.setgame.setgame;

import com.setgame.setgame.enums.*;

// Klasse, welche eine Karte repräsentiert
public class Card {
    // Gibt die Farbe der Karte an
    private final CardColor color;

    // Gibt die Form der Karte an
    private final CardShape shape;

    // Gibt die Füllung der Karte an
    private final CardFilling filling;

    // Gibt die Anzahl der Symbole auf der Karte an
    private final CardNumber number;

    // Gibt den Dateinamen des Bildes der Karte an
    private String imageFileName;

    public Card(CardColor color, CardShape shape, CardFilling filling, CardNumber number, String imageFileName) {
        this.color = color;
        this.shape = shape;
        this.filling = filling;
        this.number = number;
        this.imageFileName = imageFileName;
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

    public String getImageFileName() {
        return imageFileName;
    }

    // Methode, welche eine Karte als String zurückgibt
    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", shape=" + shape +
                ", filling=" + filling +
                ", number=" + number +
                ", imageFileName='" + imageFileName + 
                '}';
    }
}