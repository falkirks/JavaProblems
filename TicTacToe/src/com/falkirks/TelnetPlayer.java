package com.falkirks;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TelnetPlayer extends BasePlayer {
    private BufferedReader in;
    private PrintWriter out;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    public TelnetPlayer(Board board, int port) {
        super(board);
        System.out.println("Waiting for telnet connection.");
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            System.out.println("Client Connected!");
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            sendMessage("Welcome to TicTacToe server.");
            sendMessage("# At the current time telnet clients");
            sendMessage("# will only be able to see messages which");
            sendMessage("# directly involve them.");
            sendBoard();
        }
        catch(IOException e) {
            System.err.println("IO Exception.");
        }
    }
    @Override
    void doMove() {
        sendBoard();
        System.out.println("Waiting for telnet client...");
        try {
            sendMessage("It is your turn.");
            sendMessage("Row:");
            String testRead = in.readLine();
            if(testRead == null){
                Main.stopGame();
                sendMessage("Lost connection with client. Closing...");
                return;
            }
            int r = Integer.parseInt(testRead);
            sendMessage("Column:");
            int c = Integer.parseInt(in.readLine());
            if(claimTile(r, c)){
                sendMessage("The tile is yours.");
                sendBoard();
            }
            else{
                sendMessage("That tile isn't claimable.");
                doMove();
            }
        }
        catch(NumberFormatException e) {
            doMove();
        }
        catch (IOException e){
            sendMessage("IO Exception", true);
            doMove();
        }
    }
    @Override
    void showState(int state) {
        switch (state){
            case WON_STATE:
                sendMessage("Congrats! You have won the game.");
                break;
            case TIE_STATE:
                sendMessage("You have tied.");
                break;
            case LOST_STATE:
                sendMessage("You have lost. Better luck next time");
                break;
            default:
                sendMessage("Unimplemented state.");
                break;
        }
        sendBoard();
        closeSockets();
    }
    void sendBoard(){
        out.println(getBoard().getTextBoard());
    }
    protected void closeSockets(){
        try {
            out.close();
            clientSocket.close();
            serverSocket.close();
            in.close();
        }
        catch (IOException e){
            System.err.println("IO error when closing sockets.");
        }
    }
    @Override
    protected void sendMessage(String msg, Boolean isDebug) {
        if(!isDebug || Main.isDebug) {
            out.println("[" + getPlayerChar() + "] " + msg);
        }
    }
    @Override
    protected void sendMessage(String msg) {
        sendMessage(msg, false);
    }
}
