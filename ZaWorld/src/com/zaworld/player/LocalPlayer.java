package com.zaworld.player;

import com.zaworld.Game;
import com.zaworld.world.Map;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LocalPlayer extends Player implements KeyListener{
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_UP:
                Game.getInstance().getMap().moveEntity(this, Map.FORWARD);
                break;
            case KeyEvent.VK_DOWN:
                Game.getInstance().getMap().moveEntity(this, Map.BACKWARD);
                break;
            case KeyEvent.VK_RIGHT:
                Game.getInstance().getMap().moveEntity(this, Map.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                Game.getInstance().getMap().moveEntity(this, Map.LEFT);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
