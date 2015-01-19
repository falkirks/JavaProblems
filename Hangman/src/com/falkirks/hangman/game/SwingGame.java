package com.falkirks.hangman.game;


import com.falkirks.hangman.dict.FilesystemLengthStore;
import com.falkirks.hangman.dict.LengthStore;
import com.falkirks.hangman.dict.LimitedGuessable;
import com.falkirks.hangman.gui.MainWindow;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SwingGame extends StandardGame implements KeyListener{
    LengthStore lengthStore;
    LimitedGuessable currentWord;
    MainWindow mainWindow;

    @Override
    public void init() {
        mainWindow = new MainWindow();
        lengthStore = new FilesystemLengthStore();
        lengthStore.printStats();

        currentWord = new LimitedGuessable(lengthStore.nextDodgingWord(), 20);

        mainWindow.getContentPane().addKeyListener(this);
        mainWindow.addKeyListener(this);
        mainWindow.spawn();

    }

    @Override
    public boolean doTick() {
        mainWindow.getWordPane().setGuessData(currentWord.getGuessData());
        if(!currentWord.isGuessable()){
            JOptionPane.showMessageDialog(mainWindow, "Sorry, you lose. Better luck next time.");
            currentWord = new LimitedGuessable(lengthStore.nextDodgingWord(), 20);
        }
        else if(currentWord.isGuessed()){
            JOptionPane.showMessageDialog(mainWindow, "Yay! You win.");
            currentWord = new LimitedGuessable(lengthStore.nextDodgingWord(), 20);
        }
        return true;
    }

    @Override
    public void stop() {
        currentWord.shutdown();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        currentWord.removeLetter(e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
