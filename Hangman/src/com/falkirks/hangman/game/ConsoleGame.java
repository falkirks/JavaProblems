package com.falkirks.hangman.game;


import com.falkirks.hangman.dict.FilesystemLengthStore;
import com.falkirks.hangman.dict.LengthStore;
import com.falkirks.hangman.dict.LimitedGuessable;
import com.falkirks.hangman.dict.dodger.DodgingWord;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleGame extends StandardGame{
    LengthStore lengthStore;
    LimitedGuessable currentWord;
    BufferedReader br;
    @Override
    public void init() {
        lengthStore = new FilesystemLengthStore();
        lengthStore.printStats();

        currentWord = new LimitedGuessable(lengthStore.nextDodgingWord(), 20);

        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public boolean doTick() {
        try {
            String input = br.readLine();
            if (input.length() > 0) {
                currentWord.removeLetter(input.charAt(0));
                printGuessData(currentWord.getGuessData());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        if(currentWord.isGuessed()){
            System.out.println("You WIN!");
            currentWord = new LimitedGuessable(lengthStore.nextDodgingWord(), 20);
        }
        else if(!currentWord.isGuessable()){
            System.out.println("You ran out of turns.");
            currentWord = new LimitedGuessable(lengthStore.nextDodgingWord(), 20);
        }
        return true;
    }

    @Override
    public void stop() {
        currentWord.shutdown();
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
