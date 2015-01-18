package com.falkirks.hangman;

import com.falkirks.hangman.dict.FilesystemLengthStore;
import com.falkirks.hangman.dict.RemoteLengthStore;
import com.falkirks.hangman.dict.dodger.DodgingWord;
import com.falkirks.hangman.gui.MainWindow;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        /*try {
            FilesystemLengthStore lengthStore = new FilesystemLengthStore();
            lengthStore.printStats();
            DodgingWord dodgingWord = lengthStore.nextDodgingWord();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(dodgingWord.getWordsLeft());
            while (!dodgingWord.isGuessed()) {
                String input = br.readLine();
                if(input.length() > 0) {
                    dodgingWord.removeLetter(input.charAt(0));
                    System.out.println(dodgingWord.getWordsLeft());
                    printGuessData(dodgingWord.getGuessData());
                }
            }
            dodgingWord.shutdown();
        }
        catch (Exception e){
            e.printStackTrace();
        }*/
        MainWindow mainWindow = new MainWindow();
        try {
            Thread.sleep(5000);
        }
        catch(InterruptedException e){

        }
        mainWindow.spawn();
    }
    public static void printGuessData(char[] guessData){
        for(int i = 0; i < guessData.length; i++){
            if(guessData[i] == '\u0000'){
                System.out.print(" _ ");
            }
            else{
                System.out.print(guessData[i]);
            }
        }
        System.out.println();
    }
}
