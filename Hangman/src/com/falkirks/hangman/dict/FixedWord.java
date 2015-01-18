package com.falkirks.hangman.dict;

public class FixedWord implements GuessableWord{
    private String word;
    private char[] guess;

    public FixedWord(String word) {
        this.word = word;
        this.guess = new char[word.length()];

    }

    @Override
    public char[] getGuessData() {
        return guess;
    }

    @Override
    public boolean isGuessed() {
        return word.equals(new String(guess));
    }

    public void removeLetter(char letter){
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == letter){
                guess[i] = letter;
            }
        }
    }
    private static String repeat(String string, int times){
        String out = "";
        for(int i = 0; i < times; i++)
            out += string;
        return out;
    }

}
