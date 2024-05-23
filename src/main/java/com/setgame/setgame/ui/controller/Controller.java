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
        private TableView<ScoreBoardEntries> table;
        @FXML
        private TableColumn<ScoreBoardEntries, String> Date;

        @FXML
        private TableColumn<ScoreBoardEntries, String> Name;

        @FXML
        private TableColumn<ScoreBoardEntries,Integer> Rank;

        @FXML
        private TableColumn<ScoreBoardEntries,Integer> Score;

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

        }
}
