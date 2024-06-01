package com.setgame.setgame.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SetGameServer {
    private String gameCode;
    private ServerSocket serverSocket;
    private final ExecutorService pool = Executors.newFixedThreadPool(10); // Verwenden Sie einen Thread-Pool für mehrere Clients

    public SetGameServer() {
        // Default constructor
    }

    public SetGameServer(String gameCode) {
        this.gameCode = gameCode;
    }

    public void start() {
        pool.execute(() -> {
            try {
                serverSocket = new ServerSocket(12345);
                System.out.println("Server started with game code: " + gameCode);
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Client connected: " + clientSocket.getInetAddress());
                    // Handle the client connection
                    handleClient(clientSocket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void handleClient(Socket clientSocket) {
        // Hier die Logik für die Client-Verarbeitung hinzufügen
        pool.execute(() -> {
            // Fügen Sie hier den Code für die Verarbeitung von Client-Anfragen hinzu
        });
    }

    public static void main(String[] args) {
        new SetGameServer().start();
    }
}
