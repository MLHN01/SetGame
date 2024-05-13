package com.setgame.setgame;

import org.hibernate.Session;
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

        //FXML-Datei laden
        Parent root = FXMLLoader.load(getClass().getResource("/com/setgame/setgame/ui/GameBoard.fxml"));

        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Set Game Board");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}


