package com.setgame.setgame.ui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.TableViewSkin;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
        @FXML
        private TableView<ScoreBoarEntries> table;
        @FXML
        private TableColumn<ScoreBoarEntries, String> Date;

        @FXML
        private TableColumn<ScoreBoarEntries, String> Name;

        @FXML
        private TableColumn<ScoreBoarEntries,Integer> Rank;

        @FXML
        private TableColumn<ScoreBoarEntries,Integer> Score;

        ObservableList<ScoreBoarEntries> List = FXCollections.observableArrayList(
                new ScoreBoarEntries(1,28,"3.3.2024","Karl"),
                new ScoreBoarEntries(2,12,"6.3.2024","Karsten"),
                new ScoreBoarEntries(3,11,"6.3.2024","Kerstin"),
                new ScoreBoarEntries(4,4,"15.3.2024","Karla")
        );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Rank.setCellValueFactory(new PropertyValueFactory<ScoreBoarEntries, Integer>("Rank"));
        Score.setCellValueFactory(new PropertyValueFactory<ScoreBoarEntries, Integer>("Score"));
        Date.setCellValueFactory(new PropertyValueFactory<ScoreBoarEntries, String>("Date"));
        Name.setCellValueFactory(new PropertyValueFactory<ScoreBoarEntries, String>("Name"));

        table.setItems(List);

        }
}
