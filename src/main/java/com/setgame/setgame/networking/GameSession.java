package com.setgame.setgame.networking;

public class GameSession {
    private ClientHandler host;
    private ClientHandler client;
    private int hostScore;
    private int clientScore;
    private int gameTimeInSeconds;

    public GameSession(ClientHandler host) {
        this.host = host;
    }

    public void setClient(ClientHandler client) {
        this.client = client;
    }

    public boolean isFull() {
        return client != null;
    }

    public void setGameTime(int seconds) {
        this.gameTimeInSeconds = seconds;
    }

    public int getGameTime() {
        return gameTimeInSeconds;
    }

    public void incrementHostScore() {
        hostScore++;
    }

    public void incrementClientScore() {
        clientScore++;
    }

    public String getWinner() {
        if (hostScore > clientScore) {
            return "Host wins!";
        } else if (clientScore > hostScore) {
            return "Client wins!";
        } else {
            return "It's a tie!";
        }
    }

    public ClientHandler getHost() {
        return host;
    }

    public ClientHandler getClient() {
        return client;
    }
}
