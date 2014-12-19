package com.zaworld.world;


import com.zaworld.entity.BlankTile;
import com.zaworld.entity.Entity;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Map {
    public final static int FORWARD = 0;
    public final static int BACKWARD = 1;
    public final static int LEFT = 2;
    public final static int RIGHT = 3;

    ArrayList<ArrayList<Entity>> map;
    public Map(int rows, int cols) {
        map = new ArrayList<ArrayList<Entity>>();
        for(int i = 0; i < rows; i++){
            map.add(new ArrayList<Entity>());
            for(int j = 0; j < cols; j++){
                map.get(i).add(new BlankTile());
            }
        }
    }
    public Map(ArrayList<ArrayList<Entity>> entities){
        map = entities;
    }
    public Map getRect(int row0, int row1, int col0, int col1){
        Map ret = new Map(row1-row0, col1-col0);
        for (int i = row0; i <= row1; i++){
            for(int j = col0; j <= col1; j++){
                Entity e = getEntity(i, j);
                ret.setEntity(i, j, (e != null ? e : new BlankTile()));
            }
        }
        return ret;
    }
    public void setDimensions(Dimension dimension){
        int width = (int) Math.ceil(dimension.getWidth()/25);
        int height = (int) Math.ceil(dimension.getHeight()/25);
        for(int i = 0; i < height; i++){
            if(i >= map.size()) map.add(new ArrayList<Entity>());
        }
        for(int i = 0; i < map.size(); i++){
            for(int j = 0; j < width; j++){
                if(j >= map.get(i).size()) map.get(i).add(new BlankTile());
            }
        }
        //System.out.println(map.size() + " " + map.get(0).size());
    }
    public boolean setEntity(int row, int col, Entity e){
        try {
            map.get(row).set(col, e);
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }
    public boolean moveEntity(Entity e, int dir){
        int col = -1;
        int row = 0;
        for(; row < map.size(); row++){
            col = map.get(row).indexOf(e);
            if(col != -1) break;
        }
        if(col == -1) return false; //TODO Change to Exception
        switch(dir){
            case FORWARD:
                if(!setEntity(row-1, col, e)) return false;
                break;
            case BACKWARD:
                if(!setEntity(row+1, col, e)) return false;
                break;
            case LEFT:
                if(!setEntity(row, col-1, e)) return false;
                break;
            case RIGHT:
                if(!setEntity(row, col+1, e)) return false;
                break;
            default:
                return false; //TODO Change to Exception
        }
        setEntity(row,col, new BlankTile());
        return true;
    }
    public Entity getEntity(int row, int col){
        try {
            return map.get(row).get(col);
        }
        catch(Exception e){
            return null;
        }
    }
    public ArrayList<ArrayList<Entity>> getEntities(){
        return map;
    }
}
