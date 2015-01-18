package com.falkirks.hangman.gui;

import javax.swing.*;
import java.awt.*;

public class LoadingPane extends JPanel{
    int tick;
    int phase;
    public LoadingPane() {
        tick = 0;
        phase = 1;
        setBounds(0, 0, 600, 400);
    }

    @Override
    protected void paintComponent(Graphics g) {
        tick++;
        super.paintComponent(g);
        if(tick % 40 == 0){
            phase++;
            if(phase > 3) phase = 1;
        }
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", 0, 30));
        g.drawString("Loading" + getPhaseText(), 50, 50);
        g.drawImage(new ImageIcon(getClass().getClassLoader().getResource("icon.png")).getImage(), 225, 125, 150, 150, this);
    }
    private String getPhaseText(){
        String string = "";
        for(int i = 1; i <= phase; i++){
            string += ".";
        }
        return string;
    }
}
