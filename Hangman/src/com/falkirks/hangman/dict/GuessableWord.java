package com.falkirks.hangman.dict;


public interface GuessableWord {
    public void removeLetter(char letter);
    public char[] getGuessData();
    public boolean isGuessed();
}
