package com.setgame.setgame.util;

import com.setgame.setgame.db_models.Score;
import com.setgame.setgame.ui.CardButtonStyle;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.setgame.setgame.GameObjects.Card;
import com.setgame.setgame.GameObjects.Deck;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

// Implementiert die Spiellogik
public class Game {

    private GridPane gridPane;
    private Label scoreButton, timerLabel;
    private List<Card> cardsOnBoard = new ArrayList<>();
    private List<Card> selectedCards = new ArrayList<>();
    private Deck deck;
    private int score = 0;
    private GameTimer gameTimer;
    private int gameTimeInSeconds = 60;
    private SessionFactory sessionFactory;

    public Game(GridPane gridPane, Label scoreButton, Label timerLabel) {
        this.gridPane = gridPane;
        this.scoreButton = scoreButton;
        this.timerLabel = timerLabel;
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    // Startet ein neues Spiel
    public void startNewGame() {
        clearBoard(); // Leert das Spielfeld
        deck = new Deck(); // Erstellt ein neues Deck
        drawInitialCards(); // Zeichnet die anfänglichen Karten
        score = 0; // Setzt den Punktestand zurück
        updateScoreButton(); // Aktualisiert die Punktestand-Anzeige
        if (gameTimer != null) {
            gameTimer.stop(); // Stoppt den alten Timer, falls vorhanden
        }
        gameTimer = new GameTimer(timerLabel, gameTimeInSeconds, this::handleTimeUp); // Erstellt einen neuen Timer
        gameTimer.start(); // Startet den Timer
    }

    // Aktualisiert die Punktestand-Anzeige
    private void updateScoreButton() {
        scoreButton.setText("" + score);
    }

    // Leert das Spielfeld
    private void clearBoard() {
        gridPane.getChildren().clear();
        cardsOnBoard.clear();
    }

    // Zeichnet die anfänglichen Karten auf das Spielfeld
    private void drawInitialCards() {
        List<Card> initialCards;
        do {
            deck.resetDeck(); // Setzt das Deck zurück
            initialCards = deck.drawCards(12); // Zieht 12 Karten
        } while (SetGameUtils.findSet(initialCards) == null); // Wiederholt, falls kein Set gefunden wird
        int row = 0, col = 0;
        for (Card card : initialCards) {
            card.setRow(row);
            card.setCol(col);
            addCardToBoard(card, row, col);
            if (++col > 2) {
                col = 0;
                row++;
            }
        }
    }

    // Fügt eine Karte zum Spielfeld hinzu
    public void addCardToBoard(Card card, int row, int col) {
        Button cardButton = createCardButton(card);
        cardsOnBoard.add(card);
        gridPane.add(cardButton, col, row);
    }

    // Erstellt einen Button für eine Karte
    private Button createCardButton(Card card) {
        Image image = new Image(getClass().getResourceAsStream(card.cardImagePath()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(98);

        Button cardButton = new Button("", imageView);
        cardButton.setOnAction(event -> handleCardClick(card));
        cardButton.setStyle(CardButtonStyle.NotSelected);

        card.setButton(cardButton);
        return cardButton;
    }

    // Handhabt den Klick auf eine Karte
    private void handleCardClick(Card card) {
        if (card.isSelected()) {
            deselectCard(card);
        } else if (selectedCards.size() < 3) {
            selectCard(card);
        }
        if (selectedCards.size() == 3) {
            processSelectedCards();
        }
    }

    // Deselektiert eine Karte
    private void deselectCard(Card card) {
        card.getButton().setStyle(CardButtonStyle.NotSelected);
        card.setSelected(false);
        selectedCards.remove(card);
    }

    // Selektiert eine Karte
    private void selectCard(Card card) {
        card.setSelected(true);
        card.getButton().setStyle(CardButtonStyle.Selected);
        selectedCards.add(card);
    }

    // Prüft die ausgewählten Karten
    private void processSelectedCards() {
        if (SetGameUtils.isSet(selectedCards)) {
            handleSetFound();
        } else {
            handleSetNotFound();
        }
        selectedCards.clear();
    }

    // Handhabt den Fall, dass ein Set gefunden wurde
    private void handleSetFound() {
        score++;
        updateScoreButton();
        removeSelectedCards();
        refillBoard();
    }

    // Handhabt den Fall, dass kein Set gefunden wurde
    private void handleSetNotFound() {
        new ArrayList<>(selectedCards).forEach(this::deselectCard);
    }

    // Entfernt die ausgewählten Karten vom Spielfeld
    private void removeSelectedCards() {
        selectedCards.forEach(card -> {
            gridPane.getChildren().remove(card.getButton());
            cardsOnBoard.remove(card);
        });
    }

    // Füllt das Spielfeld auf
    private void refillBoard() {
        List<Card> newCards;
        boolean isSetFound;

        do {
            newCards = deck.drawCards(3); // Zieht 3 neue Karten
            List<Card> potentialBoard = new ArrayList<>(cardsOnBoard); // Kopiert die aktuellen Karten auf dem Spielfeld
            potentialBoard.addAll(newCards); // Fügt die neuen Karten hinzu

            isSetFound = SetGameUtils.findSet(potentialBoard) != null; // Prüft, ob ein Set vorhanden ist
            if (!isSetFound) {
                deck.returnCards(newCards); // Gibt die gezogenen Karten zurück ins Deck, falls kein Set gefunden wird
            }

            if (deck.isEmpty()) {
                startNewGame(); // Startet ein neues Spiel, falls das Deck leer ist
                return;
            }

        } while (!isSetFound); // Wiederholt, bis ein Set gefunden wird

        replaceCards(newCards); // Ersetzt die alten Karten durch die neuen Karten auf dem Brett
    }

    // Ersetzt alte Karten durch neue Karten auf dem Spielfeld
    private void replaceCards(List<Card> newCards) {
        for (int i = 0; i < newCards.size(); i++) {
            Card newCard = newCards.get(i);
            Card oldCard = selectedCards.get(i);
            newCard.setRow(oldCard.getRow());
            newCard.setCol(oldCard.getCol());
            addCardToBoard(newCard, newCard.getRow(), newCard.getCol());
        }
    }

    // Hebt ein Set auf dem Spielfeld hervor
    public void highlightSetOnBoard() {
        List<Card> set = SetGameUtils.findSet(cardsOnBoard);
        if (set != null) {
            set.forEach(card -> card.getButton().setStyle(CardButtonStyle.Highlighted));
        } else {
            System.out.println("Kein Set gefunden!");
        }
    }

    // Handhabt den Fall, dass die Zeit abgelaufen ist
    private void handleTimeUp() {
        Platform.runLater(() -> showNameInputDialog());
        // deaktiviert die Karten auf dem Spielfeld
        cardsOnBoard.forEach(card -> card.getButton().setDisable(true));
    }

    // Zeigt einen Dialog zur Eingabe des Spielernamens
    private void showNameInputDialog() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Spiel beendet");
        dialog.setHeaderText("Zeit ist abgelaufen!");
        dialog.setContentText("Bitte geben Sie Ihren Namen ein:");

        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> saveOrUpdateScore(name, score));
    }

    // Speichert oder aktualisiert den Punktestand eines Spielers
    private void saveOrUpdateScore(String playerName, int score) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Score> existingScores = session.createQuery("FROM Score WHERE playerName = :playerName", Score.class)
                .setParameter("playerName", playerName)
                .getResultList();

        Score playerScore;
        if (existingScores.isEmpty()) {
            playerScore = new Score();
            playerScore.setPlayerName(playerName);
        } else {
            playerScore = existingScores.get(0);
        }

        playerScore.setScore(score);
        session.saveOrUpdate(playerScore);

        session.getTransaction().commit();
        session.close();
    }

    // stoppt den Timer
    public void stopTimer() {
        if (gameTimer != null) {
            gameTimer.stop();
        }
    }
}
