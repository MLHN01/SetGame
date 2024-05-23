package com.setgame.setgame;

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

        //var session = HibernateUtil.getSessionFactory().openSession();

        //FXML-Datei laden
        //Parent root = FXMLLoader.load(getClass().getResource("/com/setgame/setgame/fxml/GameBoard.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/com/setgame/setgame/fxml/ScoreBoard.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/com/setgame/setgame/fxml/SetGame.fxml"));


        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Set Game Board");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}


