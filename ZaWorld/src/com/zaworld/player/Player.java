package com.zaworld.player;

import com.zaworld.entity.Entity;

import java.awt.*;

public class Player extends Entity{
    int x;
    int y;
    public Player() {
        x = 0;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    @Override
    public void render(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.RED);
        g2d.fillRect(x, y, 25, 25);
    }

}
