package com.falkirks.hangman.dict;


import com.falkirks.hangman.dict.dodger.DodgingWord;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
/*
    Loads dictionary from an external source. The default location
    was provided for use with the project and tends to be harder
    than the built in words file as it includes acronyms which
    don't have vowels.
 */
public class RemoteLengthStore extends LengthStore{
    public final static String REMOTE_URL = "http://www.cs.duke.edu/~ola/ap/linuxwords";
    @Override
    public boolean load() {
        try {
            ArrayList<String> list = new ArrayList<String>();
            BufferedReader in = new BufferedReader(new InputStreamReader((new URL(REMOTE_URL)).openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                list.add(inputLine);
            }
            importData(list);
            return true;
        }
        catch(MalformedURLException e){
            return false;
        }
        catch(IOException e){
            return false;
        }
    }
}
