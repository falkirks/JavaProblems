package com.zaworld;

import com.zaworld.player.LocalPlayer;
import com.zaworld.player.Player;
import com.zaworld.ui.MainWindow;
import com.zaworld.world.Map;

public class Game {
    private MainWindow mainWindow;
    private Map map;
    private LocalPlayer player;
    static private Game _instance;
    public static Game getInstance(){
        if(_instance == null) _instance = new Game();
        return _instance;
    }
    public Game() {
        map = new Map(20, 20);
        mainWindow = new MainWindow();
        player = new LocalPlayer();
    }
    public void runGame(){
        mainWindow.spawn();
        mainWindow.getGamePanel().addKeyListener(player);
        mainWindow.getFrame().addKeyListener(player);

        map.setEntity(0, 0, player);
        while(true){
            mainWindow.getGamePanel().render(map);
        }
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public Map getMap() {
        return map;
    }
}
