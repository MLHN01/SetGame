package com.setgame.setgame.ui.controller;

import com.setgame.setgame.db_models.ScoreBoardEntries;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ScoreBoardController implements Initializable {
        @FXML
        private TableView<ScoreBoardEntries> table;
        @FXML
        private TableColumn<ScoreBoardEntries, String> Date;

        @FXML
        private TableColumn<ScoreBoardEntries, String> Name;

        @FXML
        private TableColumn<ScoreBoardEntries,Integer> Rank;

        @FXML
        private TableColumn<ScoreBoardEntries,Integer> Score;

        @FXML
        private Button backButton;

        ObservableList<ScoreBoardEntries> List = FXCollections.observableArrayList(
                new ScoreBoardEntries(1,28,"3.3.2024","Karl"),
                new ScoreBoardEntries(2,12,"6.3.2024","Karsten"),
                new ScoreBoardEntries(3,11,"6.3.2024","Kerstin"),
                new ScoreBoardEntries(4,4,"15.3.2024","Karla")
        );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Rank.setCellValueFactory(new PropertyValueFactory<ScoreBoardEntries, Integer>("Rank"));
        Score.setCellValueFactory(new PropertyValueFactory<ScoreBoardEntries, Integer>("Score"));
        Date.setCellValueFactory(new PropertyValueFactory<ScoreBoardEntries, String>("Date"));
        Name.setCellValueFactory(new PropertyValueFactory<ScoreBoardEntries, String>("Name"));

        table.setItems(List);

        // configure back button
        backButton.setOnAction(this::handleBack);

        }


    //start
    @FXML
    private void handleBack(ActionEvent event) {
        try {
            // Laden der Start-Szene
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
