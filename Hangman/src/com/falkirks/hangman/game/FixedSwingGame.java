package com.falkirks.hangman.game;


import com.falkirks.hangman.dict.LimitedGuessable;

public class FixedSwingGame extends SwingGame {
    /*
        NOTE:
            This needs optimized so a DodgingWord isn't constructed.
     */
    @Override
    public void init() {
        super.init();
        currentWord.setGuessableWord(lengthStore.nextFixedWord());
    }
}
