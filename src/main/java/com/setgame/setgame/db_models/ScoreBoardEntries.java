package com.setgame.setgame.db_models;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ScoreBoardEntries {
    private int Rank;
    private int Score;
    private String Date;

    private String Name;


    public ScoreBoardEntries (int Rank,int Score, String Date, String Name)
    {
        this.Rank = Rank;
        this.Score = Score;
        this.Date = Date;
        this.Name = Name;
    }

    public int getRank(){
        return Rank;
    }
    public int getScore(){
        return Score;
    }

    public String getDate(){
        return Date;
    }
    public String getName(){
        return Name;
    }

}
