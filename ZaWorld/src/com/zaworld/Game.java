package com.zaworld;

import com.zaworld.ui.MainWindow;
import com.zaworld.world.Map;

public class Game {
    private MainWindow mainWindow;
    private Map map;
    static private Game _instance;
    public static Game getInstance(){
        if(_instance == null) _instance = new Game();
        return _instance;
    }
    public Game() {
        map = new Map(20, 20);
        mainWindow = new MainWindow();
    }
    public void runGame(){
        mainWindow.spawn();
        while(true){
            mainWindow.getGamePanel().render(map);
        }
    }
}
