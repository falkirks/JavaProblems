package com.falkirks.hangman.game;


import com.falkirks.hangman.Main;
import com.falkirks.hangman.dict.FilesystemLengthStore;
import com.falkirks.hangman.dict.LengthStore;
import com.falkirks.hangman.dict.LimitedGuessable;
import com.falkirks.hangman.dict.RemoteLengthStore;
import com.falkirks.hangman.gui.MainWindow;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class SwingGame extends StandardGame implements KeyListener{
    protected LengthStore lengthStore;
    protected LimitedGuessable currentWord;
    private MainWindow mainWindow;
    private ArrayList<Character> guessQueue;

    private PrintStream oldPrintStream;

    @Override
    public void init() {
        guessQueue = new ArrayList<Character>();
        mainWindow = new MainWindow();
        lengthStore = new FilesystemLengthStore();
        lengthStore.printStats();

        currentWord = new LimitedGuessable(lengthStore.nextDodgingWord(), 50); //TODO limit top 6 on production

        mainWindow.getContentPane().addKeyListener(this);
        mainWindow.addKeyListener(this);
        mainWindow.spawn();
        oldPrintStream = System.out;
        System.out.println("### Output is now redirected ###");
        System.setOut(new PrintStream(new SwingOutputStream()));
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
            System.out.println("Sorry, you lose. Better luck next time.");
            currentWord = new LimitedGuessable(lengthStore.nextDodgingWord(), 6);
        }
        else if(currentWord.isGuessed()){
            System.out.println("Yay! You win.");
            currentWord = new LimitedGuessable(lengthStore.nextDodgingWord(), 6);
        }


        return true;
    }

    @Override
    public void stop() {
        System.setOut(oldPrintStream);
        System.out.println("### Output redirect removed ###");
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
    private class SwingOutputStream extends OutputStream{
        private String output = "";
        @Override
        public void write(int b) throws IOException {
            char charToWrite = (char) b;
            if(b == '\n'){
                JOptionPane.showMessageDialog(mainWindow, output);
                output = "";
            }
            else{
                output += charToWrite;
            }
        }
    }
}

