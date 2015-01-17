package com.falkirks.hangman.dict.dodger;


import com.falkirks.hangman.dict.WordStore;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class LetterDiffTask implements Callable<WordStore> {
    private char charToRemove;
    private ArrayList<String> words;
    public LetterDiffTask(char charToRemove, WordStore store) {
        this.charToRemove = charToRemove;
        this.words = store.getWords();
    }

    @Override
    public WordStore call() throws Exception {
        ArrayList<String> out = new ArrayList<String>();
        for(String word : words){
            if(!word.contains(charToRemove + "")){
                out.add(word);
            }
        }
        return new WordStore(out);
    }
}
