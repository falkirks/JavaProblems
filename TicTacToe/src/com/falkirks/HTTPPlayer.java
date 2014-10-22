package com.falkirks;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class HTTPPlayer extends BasePlayer{
    private String sendBuffer;
    private ServerSocket serverSocket;
    public HTTPPlayer(Board board) {
        super(board);
        try {
            serverSocket = new ServerSocket(8080);
            System.out.println("Waiting for HTTP client to connect...");

            Socket clientSocket = serverSocket.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            System.out.println(in.readLine().split("/")[1].split(" ")[0]);
            String file = readFile("/index.html");
            out.write("HTTP/1.0 200 OK\r\n");
            out.write("Content-Type: text/html\r\n");
            out.write("Content-Length: " + file.length() + "\r\n");
            out.write("\r\n");
            out.write(file);

            out.close();
            in.close();
            clientSocket.close();
        }
        catch (IOException e) {
            System.out.println("HTTP client could ");
        }
    }

    void doMove() {
        sendMessage("It's your turn.");
        System.out.println("Waiting for HTTP player...");
    }
    void showState(int state) {

    }
    public void showBoard(){
        BasePlayer[][] tiles = getBoard().getTiles();
        sendBuffer += "\n{{BOARD";
        for (int r = 0; r < tiles.length; r++){
            for (int c = 0; c < tiles[r].length; c++){
                if(tiles[r][c] == null){
                    sendBuffer += " ";
                }
                else{
                    sendBuffer += tiles[r][c].getPlayerChar();
                }
            }
        }
        sendBuffer += "}}";
    }
    @Override
    protected void sendMessage(String msg){
        sendBuffer += "\n [" + getPlayerChar() + "] " + msg;
    }

    /*
        From Stackoverflow
     */
    static public String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(HTTPPlayer.class.getResourceAsStream(file)));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while( ( line = reader.readLine() ) != null ) {
            stringBuilder.append( line );
            stringBuilder.append( ls );
        }
        return stringBuilder.toString();
    }
}
