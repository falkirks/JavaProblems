package com.falkirks.hangman.dict;


public interface GuessableWord {
    /**
     * Guesses a char in the word, should return false if char is not in word and true if it is.
     * This method doesn't need to manage repeated guesses.
     * @param letter Character to remove
     * @return boolean
     */
    public boolean removeLetter(char letter);
    public char[] getGuessData();
    public boolean isGuessed();
}
