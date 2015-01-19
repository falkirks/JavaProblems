package com.falkirks.hangman.dict.dodger;

import com.falkirks.hangman.dict.GuessableWord;
import com.falkirks.hangman.dict.WordStore;
import com.falkirks.hangman.dict.FixedWord;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/*
    This class wraps around FixedWord and allows
    the hangman to cheat.

    NOTE: shutdown() must be called to allow disposal of the instance.
 */
public class DodgingWord implements GuessableWord{
    private WordStore currentStore;
    private int iterationId;
    private ExecutorService executor;
    private HashMap<Character, Future<WordStore>> futureStores;
    private FixedWord fixedWord;
    private boolean isDodging;

    @Override
    public boolean isGuessed() {
        return !isDodging && fixedWord != null && fixedWord.isGuessed();
    }

    public DodgingWord(WordStore wordStore) {
        this.executor = Executors.newFixedThreadPool(10);
        this.currentStore = wordStore;
        this.iterationId = 0;
        this.futureStores = new HashMap<Character, Future<WordStore>>();
        this.isDodging = true;
        doNextIteration();
    }
    private void doNextIteration(){
        for(int i = 'a'; i <= 'z'; i++){
            futureStores.put((char) i, executor.submit(new LetterDiffTask((char) i, currentStore)));
        }
    }
    public boolean removeLetter(char letter){
        if(isDodging) {
            try {
                if (futureStores.get(letter).get().count() == 0) {
                    System.out.println("Locked to " + currentStore.getWord());
                    fixedWord = new FixedWord(currentStore.getWord());
                    fixedWord.removeLetter(letter);
                    isDodging = false;
                }
                else {
                    currentStore = futureStores.get(letter).get();
                }
                iterationId++;
                futureStores.clear();
                doNextIteration();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        else{
            if(fixedWord != null){
                return fixedWord.removeLetter(letter);
            }
        }
        return false;
    }
    public char[] getGuessData(){
        if(isDodging) return new char[currentStore.getWord().length()];
        return (fixedWord != null ? fixedWord.getGuessData() : null);
    }
    public void shutdown(){
        executor.shutdown();
    }

    public int getIterationId() {
        return iterationId;
    }
    public int getWordsLeft(){
        return currentStore.count();
    }
}
