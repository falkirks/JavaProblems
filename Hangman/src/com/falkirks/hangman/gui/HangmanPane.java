package com.falkirks.hangman.gui;

import javax.swing.*;
import java.awt.*;

public class HangmanPane extends JPanel {
    private int guessesMade = 0;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        ((Graphics2D) g).setStroke(new BasicStroke(10));
        if(guessesMade > 6) guessesMade = 6;
        switch(guessesMade){
            case 6:
                g.drawLine(100, 230, 125, 280);
            case 5:
                g.drawLine(100, 230, 75, 280);
            case 4:
                g.drawLine(100, 150, 150, 140);
            case 3:
                g.drawLine(100, 150, 50, 140);
            case 2:
                g.drawLine(100, 110, 100, 230);
            case 1:
                g.drawArc(75, 60, 50, 50, 0, 360);
            default:
                g.drawLine(20, 40, 20, 400);
                g.drawLine(20, 40, 100, 40);
                g.drawLine(100, 40, 100, 60);
        }

    }

    public void setGuessesMade(int guessesMade) {
        this.guessesMade = guessesMade;
    }
}
