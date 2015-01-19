package com.falkirks.hangman.gui;

import javax.swing.*;
import java.awt.*;

public class WordPane extends JPanel {
    private char[] guessData = new char[0];
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", 0, 40));
        g.drawString(getParsedGuessData(), 30, 50);

    }
    public String getParsedGuessData(){
        String string = "";
        for(int i = 0; i < guessData.length; i++){
            if(guessData[i] == 0){
                string += "_ ";
            }
            else{
                string += guessData[i] + " ";
            }
        }
        return string;
    }

    public void setGuessData(char[] guessData) {
        this.guessData = guessData;
    }
}
