package com.falkirks;

import java.io.IOException;
import java.net.*;

public class Sender implements Runnable{
    public static final int START_PORT = 11111;
    private String destination;
    private int port;
    private int senderId;
    public Sender(String destination, int port, int senderId) {
        this.destination = destination;
        this.port = port;
        this.senderId = senderId;
    }

    public void run() {
        try {
            DatagramSocket serverSocket = new DatagramSocket(START_PORT + senderId);

            while(true){
                String sendString = "polo";
                byte[] sendData = sendString.getBytes("UTF-8");
                DatagramPacket sendPacket = new DatagramPacket(sendData, 0, sendData.length, InetAddress.getByName(destination), port);
                serverSocket.send(sendPacket);
                System.out.println("Packet");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}