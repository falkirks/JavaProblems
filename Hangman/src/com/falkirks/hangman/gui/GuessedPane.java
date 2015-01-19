package com.falkirks.hangman.gui;


import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class GuessedPane extends JPanel{
    private ArrayList<Character> previousGuesses = new ArrayList<Character>();
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", 0, 40));
        drawString(g, getParsedPreviousGuesses(), 50, 0);

    }
    public String getParsedPreviousGuesses(){
        String string = "";
        for(int i = 0; i < previousGuesses.size(); i++){
            if(i % 6 == 0 && i != 0) string += "\n";
            string += previousGuesses.get(i) + "  ";
        }
        return string;
    }

    public void setPreviousGuesses(ArrayList<Character> previousGuesses) {
        this.previousGuesses = previousGuesses;
    }
    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }
}
