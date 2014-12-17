package com.zaworld.world;


import com.zaworld.entity.BlankTile;
import com.zaworld.entity.Entity;

public class Map {
    Entity[][] map;
    public Map(int rows, int cols) {
        map = new Entity[rows][cols];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                map[i][j] = new BlankTile();
            }
        }
    }
    public Map(Entity[][] entities){
        map = entities;
    }
    public Map getRect(int row0, int row1, int col0, int col1){
        Map ret = new Map(row1-row0, col1-col0);
        for (int i = row0; i <= row1; i++){
            for(int j = col0; j <= col1; j++){
                ret.setEntity(i, j, getEntity(i, j));
            }
        }
        return ret;
    }
    public boolean setEntity(int row, int col, Entity e){
        map[row][col] = e;
        return true;
    }
    public Entity getEntity(int row, int col){
        return map[row][col];
    }
    public Entity[][] getEntities(){
        return map;
    }
}
