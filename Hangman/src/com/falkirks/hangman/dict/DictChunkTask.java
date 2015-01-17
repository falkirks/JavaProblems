package com.falkirks.hangman.dict;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class DictChunkTask implements Callable<HashMap<Integer, ArrayList<String>>>{
    private String[] data;

    public DictChunkTask(String[] data) {
        this.data = data;
    }
    @Override
    public HashMap<Integer, ArrayList<String>> call() throws Exception {
        HashMap<Integer, ArrayList<String>> out = new HashMap<Integer, ArrayList<String>>();
        for(int i = 0; i < data.length; i++){
            if(!out.containsKey(data[i].length())) out.put(data[i].length(), new ArrayList<String>());

            out.get(data[i].length()).add(data[i].toLowerCase());
        }
        return out;
    }
}
