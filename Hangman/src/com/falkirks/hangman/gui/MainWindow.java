package com.falkirks.hangman.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame{
    private HangmanPane hangmanPane;
    private WordPane wordPane;
    private GuessedPane guessedPane;

    public MainWindow(){
        hangmanPane = new HangmanPane();
        hangmanPane.setBounds(0, 0, 200, 400);
        hangmanPane.setBackground(Color.RED);

        wordPane = new WordPane();
        wordPane.setBounds(200, 300, 400, 100);
        wordPane.setBackground(Color.CYAN);
        wordPane.setWordLength(5);

        guessedPane = new GuessedPane();
        guessedPane.setBounds(200, 0, 400, 300);
        guessedPane.setBackground(Color.DARK_GRAY);

        Timer timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.start();;
        setTitle("Hangman");
        setSize(600, 400);
        //setIconImage((new ImageIcon(getClass().getClassLoader().getResource("icon.png"))).getImage());
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        getContentPane().add(new LoadingPane());
    }
    public void spawn(){
        getContentPane().removeAll();
        getContentPane().add(hangmanPane);
        getContentPane().add(wordPane);
        getContentPane().add(guessedPane);
        setVisible(true);
    }

    public HangmanPane getHangmanPane() {
        return hangmanPane;
    }

    public WordPane getWordPane() {
        return wordPane;
    }

    public GuessedPane getGuessedPane() {
        return guessedPane;
    }
}
