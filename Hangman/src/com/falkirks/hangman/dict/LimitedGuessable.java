package com.falkirks.hangman.dict;


import com.falkirks.hangman.dict.dodger.DodgingWord;

import java.util.ArrayList;

public class LimitedGuessable implements GuessableWord {
    private GuessableWord guessableWord;
    private int max;
    private int currentGuessId;

    private ArrayList<Character> previousGuesses;

    public LimitedGuessable(GuessableWord guessableWord, int max) {
        this.guessableWord = guessableWord;
        this.max = max;
        currentGuessId = 0;
        previousGuesses = new ArrayList<Character>();
    }

    public ArrayList<Character> getPreviousGuesses() {
        return previousGuesses;
    }

    @Override
    public boolean removeLetter(char letter) {
        if(isGuessable()) {
            if(!previousGuesses.contains(letter)) {
                previousGuesses.add(letter);
                currentGuessId++;
                return guessableWord.removeLetter(letter);
            }
        }
        return false;

    }
    public boolean isGuessable(){
        return currentGuessId < this.max;
    }

    public int getCurrentGuessId() {
        return currentGuessId;
    }

    public int getMax() {
        return max;
    }

    @Override
    public char[] getGuessData() {
        return guessableWord.getGuessData();
    }

    @Override
    public boolean isGuessed() {
        return guessableWord.isGuessed();
    }
    public void shutdown(){
        if(guessableWord instanceof DodgingWord) ((DodgingWord) guessableWord).shutdown();
    }
}
