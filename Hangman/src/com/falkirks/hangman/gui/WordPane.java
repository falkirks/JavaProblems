package com.falkirks.hangman.gui;

import javax.swing.*;
import java.awt.*;

public class WordPane extends JPanel {
    private int wordLength;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", 0, 40));
        g.drawString(getWordBlanks(), 30, 50);

    }
    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }
    private String getWordBlanks(){
        String string = "";
        for(int i = 1; i <= wordLength; i++){
            string += "_ ";
        }
        return string;
    }
}
