package com.falkirks.hangman.dict;

import com.falkirks.hangman.dict.dodger.DodgingWord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FilesystemLengthStore extends LengthStore {
    public final static String WORD_PATH = "/usr/share/dict/words";

    @Override
    public boolean load() {
        try {
            Scanner s = new Scanner(new File(WORD_PATH));
            ArrayList<String> list = new ArrayList<String>();
            while (s.hasNext()) {
                list.add(s.next());
            }
            s.close();
            importData(list);
            return true;
        }
        catch(FileNotFoundException e){
            return false;
        }
    }
}
