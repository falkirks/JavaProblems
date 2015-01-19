package com.falkirks.hangman.game;

abstract public class StandardGame implements GameInterface{
    public void start(){
        init();
        while (doTick());
        stop();
    }
}
