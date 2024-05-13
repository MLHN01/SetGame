package com.setgame.setgame;

import com.setgame.setgame.enums.*;

import javafx.scene.control.Button;

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

    // Gibt die Position der Karte auf dem Spielfeld an
    private int row;
    private int col;

    // Gibt den Button an, der die Karte repräsentiert
    private Button button;


    public Card(CardColor color, CardShape shape, CardFilling filling, CardNumber number, String imageFileName) {
        this.color = color;
        this.shape = shape;
        this.filling = filling;
        this.number = number;
        this.imageFileName = imageFileName;
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


    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    
    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

}