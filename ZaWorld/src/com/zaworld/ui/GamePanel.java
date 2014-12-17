package com.zaworld.ui;

import com.zaworld.entity.Entity;
import com.zaworld.world.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private Entity[][] entities;
    public GamePanel() {
        setBackground(Color.BLACK);
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 500);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(entities != null) {
            Graphics2D g2d = (Graphics2D) g.create();
            for (int i = 0; i < entities.length; i++) {
                for (int j = 0; j < entities[i].length; j++) {
                    entities[i][j].render(g2d, i*25, j*25);
                }
            }
            g2d.dispose();
        }
    }
    public void render(Map map){
        entities = map.getEntities();
    }
}
