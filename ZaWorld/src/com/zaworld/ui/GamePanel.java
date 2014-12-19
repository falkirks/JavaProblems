package com.zaworld.ui;

import com.zaworld.Game;
import com.zaworld.entity.Entity;
import com.zaworld.world.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GamePanel extends JPanel {
    private ArrayList<ArrayList<Entity>> entities;
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
            for (int i = 0; i < entities.size(); i++) {
                for (int j = 0; j < entities.get(i).size(); j++) {
                    entities.get(i).get(j).render(g2d, j * 25, i * 25);
                }
            }
            g2d.dispose();
        }
    }
    public void render(Map map){
        map.setDimensions(Game.getInstance().getMainWindow().getFrame().getSize());
        entities = map.getEntities();
    }
}
