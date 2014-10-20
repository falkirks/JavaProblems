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
            sendMesage("It is your turn.");
            System.out.print("Row:");
            int r = scanner.nextInt();
            System.out.print("Column:");
            int c = scanner.nextInt();
            if(claimTile(r, c)){
                sendMesage("The tile is yours.");
            }
            else{
                sendMesage("That tile isn't claimable.");
                doMove();
            }
        }
        catch(InputMismatchException e){
            doMove();
        }
    }

    void showState(int state) {
       switch (state){
           case WON_STATE:
               sendMesage("Congrats! You have won the game.");
               break;
           case TIE_STATE:
               sendMesage("You have tied.");
               break;
           case LOST_STATE:
               sendMesage("You have lost. Better luck next time");
               break;
           default:
               sendMesage("Unimplemented state.");
               break;
       }
    }
}
