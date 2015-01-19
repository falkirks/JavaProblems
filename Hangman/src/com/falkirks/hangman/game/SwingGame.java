package com.falkirks.hangman.game;


import com.falkirks.hangman.dict.FilesystemLengthStore;
import com.falkirks.hangman.dict.LengthStore;
import com.falkirks.hangman.dict.LimitedGuessable;
import com.falkirks.hangman.gui.MainWindow;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class SwingGame extends StandardGame implements KeyListener{
    private LengthStore lengthStore;
    private LimitedGuessable currentWord;
    private MainWindow mainWindow;
    private ArrayList<Character> guessQueue;

    @Override
    public void init() {
        guessQueue = new ArrayList<Character>();
        mainWindow = new MainWindow();
        lengthStore = new FilesystemLengthStore();
        lengthStore.printStats();

        currentWord = new LimitedGuessable(lengthStore.nextDodgingWord(), 50);

        mainWindow.getContentPane().addKeyListener(this);
        mainWindow.addKeyListener(this);
        mainWindow.spawn();

    }

    @Override
    public synchronized boolean doTick() {
        for(Character character : guessQueue){
            currentWord.removeLetter(character);
        }
        guessQueue.clear();

        mainWindow.getHangmanPane().setGuessesMade(currentWord.getCurrentGuessId()); // Allows player to see their loss
        mainWindow.getWordPane().setGuessData(currentWord.getGuessData());
        mainWindow.getGuessedPane().setPreviousGuesses(currentWord.getPreviousGuesses());

        if(!currentWord.isGuessable()){
            JOptionPane.showMessageDialog(mainWindow, "Sorry, you lose. Better luck next time.");
            currentWord = new LimitedGuessable(lengthStore.nextDodgingWord(), 6);
        }
        else if(currentWord.isGuessed()){
            JOptionPane.showMessageDialog(mainWindow, "Yay! You win.");
            currentWord = new LimitedGuessable(lengthStore.nextDodgingWord(), 6);
        }


        return true;
    }

    @Override
    public void stop() {
        currentWord.shutdown();
    }

    @Override
    public synchronized void keyTyped(KeyEvent e) {
        if(Character.isLetter(e.getKeyChar())) {
            guessQueue.add(Character.toLowerCase(e.getKeyChar()));
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
