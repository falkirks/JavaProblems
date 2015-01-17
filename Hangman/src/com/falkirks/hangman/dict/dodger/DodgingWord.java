package com.falkirks.hangman.dict.dodger;

import com.falkirks.hangman.dict.WordStore;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DodgingWord {
    private WordStore currentStore;
    private int iterationId;
    private ExecutorService executor;
    private HashMap<Character, Future<WordStore>> futureStores;
    public DodgingWord(WordStore wordStore) {
        this.executor = Executors.newFixedThreadPool(10);
        this.currentStore = wordStore;
        this.iterationId = 0;
        this.futureStores = new HashMap<Character, Future<WordStore>>();
        doNextIteration();
    }
    private void doNextIteration(){
        for(int i = 'a'; i <= 'z'; i++){
            futureStores.put((char) i, executor.submit(new LetterDiffTask((char) i, currentStore)));
        }
    }
    public void removeLetter(char letter){
        try {
            currentStore = futureStores.get(letter).get();
            iterationId++;
            futureStores.clear();
            doNextIteration();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        catch(ExecutionException e){
            e.printStackTrace();
        }
    }

    public void shutdown(){
        executor.shutdown();
    }

    public int getIterationId() {
        return iterationId;
    }
    public boolean hasWon(){
        return getWordsLeft() < 2;
    }
    public int getWordsLeft(){
        return currentStore.count();
    }
}
