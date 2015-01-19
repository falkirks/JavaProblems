package com.falkirks.hangman;

import com.falkirks.hangman.dict.FilesystemLengthStore;
import com.falkirks.hangman.dict.LimitedGuessable;
import com.falkirks.hangman.dict.RemoteLengthStore;
import com.falkirks.hangman.dict.dodger.DodgingWord;
import com.falkirks.hangman.game.ConsoleGame;
import com.falkirks.hangman.game.SwingGame;
import com.falkirks.hangman.gui.MainWindow;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        (new SwingGame()).start();
        /*
        MainWindow mainWindow = new MainWindow();
        try {
            Thread.sleep(5000);
        }
        catch(InterruptedException e){

        }
        mainWindow.spawn();*/
    }
    public static void printGuessData(char[] guessData){
        for(int i = 0; i < guessData.length; i++){
            if(guessData[i] == 0){
                System.out.print(" _ ");
            }
            else{
                System.out.print(guessData[i]);
            }
        }
        System.out.println();
    }
}
