package com.setgame.setgame.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.setgame.setgame.Card;
import com.setgame.setgame.Deck;
import com.setgame.setgame.util.HibernateUtil;
import com.setgame.setgame.util.SetGameUtils;

public class GameBoardController {

    @FXML
    private GridPane gridPaneMain;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button scoreButton;

    @FXML
    private Button resetButton;

    private int selectedCardsCount = 0;
    private List<Card> selectedCards = new ArrayList<>();
    private Deck deck;
    private int score = 0;

    // Hibernate Session öffnen
    Session session = HibernateUtil.getSessionFactory().openSession();

    @FXML
    public void initialize() {
        
        // score button
        scoreButton.setText("Score: " + score);

        // reset board button action
        resetButton.setText("Reset Board");
        resetButton.setOnAction(event -> {
            deck.resetDeck();
            clearBoard();
            drawInitialCards();
            score = 0;
            scoreButton.setText("Score: " + score);
        });

        deck = new Deck(); // Deck erstellen und mischen
        drawInitialCards();
    }

    // Methode, um eine Karte auf das Spielfeld zu zeichnen
    public void addCardToBoard(Card card, int row, int col) {
        // Pfad zum Bild der Karte
        String imagePath = "/com/setgame/setgame/CardImages/" + card.getImageFileName();
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150); 
        imageView.setFitHeight(98); 

        Button cardButton = new Button();
        cardButton.setGraphic(imageView); // Bild zum Button hinzufügen
        cardButton.setOnAction(event -> handleCardClick(card,cardButton));
        cardButton.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
        card.setButton(cardButton);

        gridPane.add(cardButton, col, row);
    }

    // Methode, um auf Klicks auf Karten zu reagieren
    private void handleCardClick(Card card, Button cardButton) {

        if(card.isSelected()){
            cardButton.setStyle("-fx-border-color: black; -fx-border-width: 3px;");
            card.setSelected(false);
            selectedCards.remove(card);
            selectedCardsCount--;
            return;
        }

        selectedCardsCount++;

        card.setSelected(true);
        cardButton.setStyle("-fx-border-color: red; -fx-border-width: 3px;");

        System.out.println(card.toString());
        selectedCards.add(card);

        if (selectedCardsCount == 3) {
            if(SetGameUtils.isSet(selectedCards)) {
                System.out.println("Set gefunden!");
                
                // Score erhöhen
                score++;
                scoreButton.setText("Score: " + score);

                // Karten entfernen
                for (Card selectedCard : selectedCards) {
                    gridPane.getChildren().remove(selectedCard.getButton());
                }

                // Neue Karten hinzufügen
                List<Card> newCards = deck.drawCards(3);

                int index = 0;
                for (Card newCard : newCards) {
                    int row = selectedCards.get(index).getRow();
                    int col = selectedCards.get(index).getCol();
                    
                    newCard.setRow(row);
                    newCard.setCol(col);
                    
                    addCardToBoard(newCard, row, col);
                    
                    index++;
                }


            } else {
                System.out.println("Kein Set gefunden!");

                // Karten wieder aktivieren
                for(Card selectedCard : selectedCards){

                    selectedCard.getButton().setStyle("-fx-border-color: black; -fx-border-width: 3px;");
                    selectedCard.setSelected(false);

                }       
            }
            selectedCardsCount = 0;
            selectedCards.clear();
        }
    }

    // Methode, um die anfänglichen Karten auf das Spielfeld zu zeichnen
    private void drawInitialCards() {
        List<Card> cardsOnBoard = deck.drawCards(12);
        int row = 0, col = 0;
        for (Card card : cardsOnBoard) {
            
            card.setRow(row);
            card.setCol(col);

            addCardToBoard(card, row, col++);

            if (col > 2) {
                col = 0;
                row++;
            }
        }
    }

    private void clearBoard() {
        gridPane.getChildren().clear();
    }
}
