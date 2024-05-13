package com.setgame.setgame.GameObjects;

// Klasse, die ein Spieler-Objekt repräsentiert, für einen späteren Multiplayer-Modus
public class Player {
    private String name;
    private int score;

    // Konstruktor
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }

    // Getter und Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    
}
