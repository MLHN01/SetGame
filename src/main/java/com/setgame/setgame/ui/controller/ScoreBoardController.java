package com.setgame.setgame.ui.controller;

import com.setgame.setgame.db_models.Score;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.setgame.setgame.util.HibernateUtil;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScoreBoardController implements Initializable {

    @FXML
    private TableView<Score> table;

    @FXML
    private TableColumn<Score, String> playerName;

    @FXML
    private TableColumn<Score, Integer> rank;

    @FXML
    private TableColumn<Score, Integer> score;

    @FXML
    private Button backButton;

    private ObservableList<Score> list = FXCollections.observableArrayList();

    private SessionFactory sessionFactory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        score.setCellValueFactory(new PropertyValueFactory<>("score"));
        playerName.setCellValueFactory(new PropertyValueFactory<>("playerName"));

        //Open session factory
        sessionFactory = HibernateUtil.getSessionFactory();

        loadScoresFromDatabase();

        table.setItems(list);

        // configure back button
        backButton.setOnAction(this::handleBack);
    }

    // Läd die Scores aus der Datenbank
    private void loadScoresFromDatabase() {
        // Retrieve scores from database using Hibernate
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Score> scores = session.createQuery("FROM Score", Score.class).getResultList();
        session.getTransaction().commit();
        session.close();

        // Sort scores and assign ranks
        List<Score> sortedScores = scores.stream()
                .sorted((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()))
                .collect(Collectors.toList());

        IntStream.range(0, sortedScores.size()).forEach(i -> {
            Score score = sortedScores.get(i);
            score.setRank(i + 1);
        });

        list.setAll(sortedScores);
    }

    // Handle back button click
    @FXML
    private void handleBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/setgame/setgame/fxml/StartMenu.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
