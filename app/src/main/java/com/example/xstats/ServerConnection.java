package com.example.xstats;

import java.net.Socket;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.io.PrintWriter;
import java.io.InputStream;

import kotlin.UByteArray;

class RemoteServerConnection {
    Boolean isStarted = false;
    String serverAddress ="";
    Integer serverPort = 0;
    private Socket socket;
    private OutputStream outStream;
    private InputStream inStream;

    public void setServerInfo(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connect() throws IOException {
        socket = new Socket(serverAddress, serverPort);
        outStream = socket.getOutputStream();
        inStream = socket.getInputStream();
        isStarted = true;
    }

    public void sendMessage(String message) throws IOException {
        outStream.write(message.getBytes());
        outStream.flush();
    }

    public String receiveMessage() throws IOException { //данные берем отсюда
        byte[] buffer = new byte[1024];
        int length = inStream.read(buffer);
        return new String(buffer, 0, length, StandardCharsets.UTF_8);
    }

    public void disconnect() throws IOException {
        socket.close();
        isStarted = false;
    }

    public boolean isStarted() {
        return isStarted;
    }


}