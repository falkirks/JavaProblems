package com.zaworld.entity;


import java.awt.*;

public class BlankTile extends Entity {
    @Override
    public void render(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.white);
        g2d.drawRect(x, y, 25, 25);
    }
}
