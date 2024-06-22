package com.setgame.setgame;

import com.setgame.setgame.db_models.Score;
import com.setgame.setgame.networking.GameClient;
import com.setgame.setgame.networking.GameServer;
import com.setgame.setgame.util.HibernateUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    // Programmeinstieg für JavaFX
    @Override
    public void start(Stage primaryStage) throws Exception {

        HibernateUtil.getSessionFactory();
        //FXML-Datei laden
        Parent root = FXMLLoader.load(getClass().getResource("/com/setgame/setgame/fxml/StartMenu.fxml"));

        Scene scene = new Scene(root);
        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Set Game Board");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    void openGameServer(String[] args) {
        // Start the server in a new thread
        new Thread(() -> {
            try {
                GameServer.main(args);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Wait a moment to ensure the server is up
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Start the client
        try {
            GameClient client = new GameClient();
            client.sendMessage("Hello Server!");
            String response = client.processMessage();
            System.out.println("Response from server: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


