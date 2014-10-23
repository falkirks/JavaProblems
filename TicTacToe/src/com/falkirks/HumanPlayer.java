package com.falkirks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HumanPlayer extends BasePlayer{
    private BufferedReader scanner = new BufferedReader(new InputStreamReader(System.in));
    public HumanPlayer(Board board) {
        super(board);
    }

    void doMove(){
        try {
            sendMessage("It is your turn.");
            System.out.print("Row:");
            String testRead = scanner.readLine();
            if(testRead == null){
                Main.stopGame();
                sendMessage("\nProcess termination detected.");
                return;
            }
            int r = Integer.parseInt(testRead);
            System.out.print("Column:");
            int c = Integer.parseInt(scanner.readLine());
            if(claimTile(r, c)){
                sendMessage("The tile is yours.");
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
    }

}
