package com.falkirks;

import java.util.*;

public class PlayerStore {
    final char[] playerChars = {'X', 'O', 'A', 'B', 'C', 'D'};
    List<BasePlayer> players = new ArrayList<BasePlayer>();
    int currentPlayer = 0;
    public void addPlayer(BasePlayer p){
        players.add(p);
    }
    public void doNextMove(){
        players.get(currentPlayer).doMove();
        currentPlayer++;
        if(currentPlayer == players.size()){
            currentPlayer = 0;
        }
    }
    public void startGame(){
        Collections.shuffle(players);
        for(int i = 0; i < players.size() && i < playerChars.length; i++){
            players.get(i).setPlayerChar(playerChars[i]);
        }
    }
    public int getPlayerID(BasePlayer p){
        return players.indexOf(p)+1;
    }

    public BasePlayer getPlayer(int id){
        return players.get(id-1);
    }

    public List<BasePlayer> getPlayers() {
        return players;
    }
}
