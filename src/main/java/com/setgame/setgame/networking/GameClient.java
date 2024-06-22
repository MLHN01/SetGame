package com.setgame.setgame.networking;

import java.io.*;
import java.net.*;

public class GameClient {

    private Socket socket;

    public GameClient() throws IOException {
        this.socket = new Socket("localhost", 8000);
        System.out.println("Connected to server");
    }

    public void sendMessage(String message) throws IOException {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(message);
    }

    public String processMessage() throws IOException {
        DataInputStream in = new DataInputStream(socket.getInputStream());
        return in.readUTF();
    }

    public static void main(String[] args) throws IOException {
        GameClient client = new GameClient();
        client.sendMessage("Hello Server!");
        String response = client.processMessage();
        System.out.println("Response from server: " + response);
    }
}