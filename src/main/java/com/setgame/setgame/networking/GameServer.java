package com.setgame.setgame.networking;

import java.io.*;
import java.net.*;

public class GameServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("Server started");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            // Handle client connection in a new thread
            new Thread(() -> {
                try {
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                    String message = in.readUTF();
                    System.out.println("Received: " + message);

                    // Send response back to client
                    out.writeUTF("Message received: " + message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}