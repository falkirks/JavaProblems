package com.falkirks;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends BasePlayer{
    private Scanner scanner = new Scanner(System.in);
    public HumanPlayer(Board board) {
        super(board);
    }

    void doMove() {
        try {
            sendMessage("It is your turn.");
            System.out.print("Row:");
            int r = scanner.nextInt();
            System.out.print("Column:");
            int c = scanner.nextInt();
            if(claimTile(r, c)){
                sendMessage("The tile is yours.");
            }
            else{
                sendMessage("That tile isn't claimable.");
                doMove();
            }
        }
        catch(InputMismatchException e){
            scanner.next(); //TODO A BufferedReader would remove the need for this
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
