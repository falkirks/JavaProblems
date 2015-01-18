package com.falkirks.hangman.dict;

import java.util.ArrayList;
import java.util.Iterator;

public class WordStore {
    private ArrayList<String> words;
    public WordStore(ArrayList<String> words){
        this.words = words;
    }
    public WordStore(){
        this(new ArrayList<String>());
    }
    public void addWord(String word){
        if(!words.contains(word)){
            words.add(word);
        }
    }
    public void removeWord(String word){
        words.remove(word);
    }
    public boolean containsWord(String word){
        return words.contains(word);
    }
    public int count(){
        return words.size();
    }
    public ArrayList<String> getWords(){
        ArrayList<String> newList = new ArrayList<String>();
        for(String word : words)
            newList.add(word);
        return newList;
    }
    public Iterator<String> getIterator(){
        return words.iterator();
    }
    /*
        This method
     */
    public String getWord(){
        if(words.size() > 0)
            return words.get(0);
        else return null;
    }
    public void addAll(ArrayList<String> data){
        words.addAll(data);
    }
}
