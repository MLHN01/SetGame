package com.setgame.setgame.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SetGameClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private boolean connected = false;

    public SetGameClient() {
        connectToServer();
    }

    private void connectToServer() {
        int attempts = 0;
        int maxAttempts = 5;
        while (attempts < maxAttempts && !connected) {
            try {
                socket = new Socket("localhost", 12345);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                connected = true;
            } catch (IOException e) {
                attempts++;
                System.out.println("Verbindungsversuch " + attempts + " fehlgeschlagen. Erneut versuchen...");
                try {
                    Thread.sleep(2000); // 2 Sekunden warten, bevor erneut versucht wird
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }

        if (!connected) {
            System.out.println("Maximale Verbindungsversuche erreicht. Verbindung zum Server fehlgeschlagen.");
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        } else {
            System.out.println("Verbindung zum Server ist nicht verfügbar.");
        }
    }

    public String receiveMessage() throws IOException {
        if (in != null) {
            return in.readLine();
        }
        return null;
    }

    public void startGame() {
        sendMessage("startGame");
    }

    public void timeUp() {
        sendMessage("timeUp");
    }
}
