package com.zaworld.ui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    private JFrame frame;
    private GamePanel gamePanel;
    public MainWindow() {
        gamePanel = new GamePanel();
    }
    public void spawn(){
        frame = new JFrame("ZaWorld");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setContentPane(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
