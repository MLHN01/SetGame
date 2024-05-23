package com.setgame.setgame.ui.controller;

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
