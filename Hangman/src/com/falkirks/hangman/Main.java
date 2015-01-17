package com.falkirks.hangman;

import com.falkirks.hangman.dict.FilesystemLengthStore;
import com.falkirks.hangman.dict.RemoteLengthStore;
import com.falkirks.hangman.dict.dodger.DodgingWord;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        try {
            RemoteLengthStore lengthStore = new RemoteLengthStore();
            lengthStore.printStats();
            DodgingWord dodgingWord = lengthStore.nextDodgingWord();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(dodgingWord.getWordsLeft());
            while (!dodgingWord.hasWon()) {
                dodgingWord.removeLetter(br.readLine().charAt(0));
                System.out.println(dodgingWord.getWordsLeft());
            }
            dodgingWord.shutdown();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
