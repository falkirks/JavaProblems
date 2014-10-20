package com.falkirks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Char:");
            String charToPrint = reader.readLine();
            System.out.print("Width:");
            Integer width = Integer.parseInt(reader.readLine());
            System.out.print("Height:");
            Integer height = Integer.parseInt(reader.readLine());
            Box box = new Box(charToPrint, width, height);
            box.doLinePrint();
        }
        catch (IOException e){
            System.out.println("IO issue.");
        }
        catch (Exception e){
            System.out.println("Please enter proper stuff!");
            main(new String[0]);
        }
    }
}
