package com.falkirks.hangman.dict;


import com.falkirks.hangman.dict.dodger.DodgingWord;
import com.falkirks.hangman.exception.DictLoadingException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

abstract public class LengthStore {
    protected HashMap<Integer, WordStore> dictionaryData;
    public LengthStore(){
        dictionaryData = new HashMap<Integer, WordStore>();
        if(!load()) throw new DictLoadingException();
    }
    public DodgingWord nextDodgingWord(){
        return new DodgingWord(get(4));
    }
    public abstract boolean load();
    public boolean reload(){
        clearData();
        return load();
    }
    protected void clearData(){
        dictionaryData.clear();
    }
    protected void importData(ArrayList<String> strings){
        String[] newArr = new String[strings.size()];
        newArr = strings.toArray(newArr);
        importData(newArr);
    }
    protected void importData(String[] strings){
        System.out.println("Loading words...");
        ArrayList<Future<HashMap<Integer, ArrayList<String>>>> futureList = new ArrayList<Future<HashMap<Integer, ArrayList<String>>>>();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        String[][] chunkedWords = chunkArray(strings, 5000);
        for(String[] chunk : chunkedWords){
            futureList.add(executor.submit(new DictChunkTask(chunk)));
        }

        executor.shutdown();
        while (!executor.isTerminated());

        HashMap<Integer, ArrayList<String>> combinedData = new HashMap<Integer, ArrayList<String>>();
        for(Future<HashMap<Integer, ArrayList<String>>> task : futureList){
            try {
                HashMap<Integer, ArrayList<String>> data = task.get();
                for (Map.Entry<Integer, ArrayList<String>> entry : data.entrySet()) {
                    if(!combinedData.containsKey(entry.getKey())) combinedData.put(entry.getKey(), new ArrayList<String>());

                    combinedData.get(entry.getKey()).addAll(entry.getValue());
                }
            }
            catch(ExecutionException e){
                e.printStackTrace();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }


        for (Map.Entry<Integer, ArrayList<String>> entry : combinedData.entrySet()) {
            if(!dictionaryData.containsKey(entry.getKey())) dictionaryData.put(entry.getKey(), new WordStore(entry.getValue()));
            else dictionaryData.get(entry.getKey()).addAll(entry.getValue());
        }
    }
    protected void importData(String string, String split){
        importData(string.split(split));
    }
    protected void addWord(String word){
        word = word.toLowerCase();
        if(!dictionaryData.containsKey(word.length())) dictionaryData.put(word.length(), new WordStore());
        dictionaryData.get(word.length()).addWord(word);
    }
    protected void removeWord(String word) {
        word = word.toLowerCase();
        if(!dictionaryData.containsKey(word.length())) dictionaryData.put(word.length(), new WordStore());
        dictionaryData.get(word.length()).removeWord(word);
    }
    public boolean has(int length){
        return dictionaryData.containsKey(length);
    }
    public WordStore get(int length){
        if(has(length))
            return new WordStore(dictionaryData.get(length).getWords());
        return null;
    }
    public void printStats(){
        for (Map.Entry<Integer, WordStore> entry : dictionaryData.entrySet()) {
            System.out.println("Length = " + entry.getKey() + ", Words = " + entry.getValue().count());
        }
    }
    /*
        https://gist.github.com/lesleh/7724554
     */
    private static String[][] chunkArray(String[] array, int chunkSize) {
        int numOfChunks = (int)Math.ceil((double)array.length / chunkSize);
        String[][] output = new String[numOfChunks][];

        for(int i = 0; i < numOfChunks; ++i) {
            int start = i * chunkSize;
            int length = Math.min(array.length - start, chunkSize);

            String[] temp = new String[length];
            System.arraycopy(array, start, temp, 0, length);
            output[i] = temp;
        }

        return output;
    }
}
