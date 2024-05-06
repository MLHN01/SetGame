package com.setgame.setgame;

import org.hibernate.Session;

import com.setgame.setgame.*;
import com.setgame.setgame.db_models.Score;
import com.setgame.setgame.util.HibernateUtil;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // Programmeinstieg für JavaFX
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Hibernate Session öffnen
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Score score = new Score();
        score.setPlayerName("Alice");
        score.setScore(100);

        session.persist(score);
        session.getTransaction().commit();
        session.close();

        //Test Database 
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Score scoreFromDb = session.get(Score.class, 1L);
        System.out.println("Score from DB: " + scoreFromDb.getScore());

        session.getTransaction().commit();
        session.close();
        


        // FXML-Datei laden
        //Parent root = FXMLLoader.load(getClass().getResource("/com/setgame/setgame/ui/GameBoard.fxml"));
//
        //Scene scene = new Scene(root);
        //
        //primaryStage.setScene(scene);
        //primaryStage.setTitle("Set Game Board");
        //primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


