package com.setgame.setgame.ui;

import com.setgame.setgame.Deck;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

// Controller-Klasse für das Spiel
public class GameController {
    private GameView gameView;
    private Deck deck;

    public GameController(GameView gameView) {
        this.gameView = gameView;
        this.deck = new Deck(); // Initialisiere dein Deck hier, füge Karten hinzu, etc.
        initialize();
    }

    private void initialize() {
        // Setze Event Handler für UI-Komponenten
        // Zum Beispiel für den Neustart-Button
        gameView.getRestartButton().setOnAction(this::handleRestartGame);
        // Weitere Initialisierungen...
    }

    // Event-Handler-Methode
    private void handleRestartGame(ActionEvent event) {
        System.out.println("Spiel wird neugestartet...");
        // Füge Logik hier hinzu, um das Spiel neu zu starten
    }

    // Weitere Methoden für die Spiellogik...
}
