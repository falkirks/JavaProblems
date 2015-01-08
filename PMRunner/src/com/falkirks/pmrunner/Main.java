package com.falkirks.pmrunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        try {
            String line;
            Process myProcess = new ProcessBuilder("/Users/noahheyl/PocketMine/server/mine/start.sh").start();
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(myProcess.getInputStream()));
            while ((line = input.readLine()) != null) {
                if(line.contains("]0;")){
                    //TODO implement process title
                }
                else {
                    System.out.println(line);
                }
            }
            input.close();
            myProcess.destroy();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
