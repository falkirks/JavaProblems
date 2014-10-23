package com.falkirks;


import java.io.PrintWriter;

public class Board {
    final char wallChar = '|';
    final char floorChar = '_';
    final int[][] magicSquare = {{2, 7, 6}, {9, 5, 1}, {4, 3, 8}};
    private BasePlayer[][] tiles = new BasePlayer[3][3];
    private PlayerStore playerStore;

    public Board(PlayerStore playerStore) {
        this.playerStore = playerStore;
    }
    public void reset(){
        tiles = new BasePlayer[3][3];
    }
    public boolean claimTile(int r, int c, BasePlayer player){
        try {
            if (tiles[r][c] == null) {
                tiles[r][c] = player;
                return true;
            } else {
                return false;
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
    }
    public BasePlayer whoOwns(int r, int c){
        return tiles[r][c];
    }
    public BasePlayer[][] getTiles() {
        BasePlayer[][] result = new BasePlayer[tiles.length][tiles[0].length];
        for (int r = 0; r < tiles.length; r++) {
            result[r] = tiles[r].clone();
        }
        return result;
    }
    public void showBoard(){
        System.out.print(getTextBoard());
    }
    public String getTextBoard(){
        String out = "";
        for (int r = 0; r < tiles.length; r++){
            out += getFloor(tiles[r].length*3, "");
            for (int c = 0; c < tiles[r].length; c++){
                out += Character.toString(wallChar) + (tiles[r][c] == null ? " " : tiles[r][c].getPlayerChar()) + Character.toString(wallChar);
            }
            out += "\n";
        }
        return out;
    }
    private String getFloor(int length, String out){
        if(length > 0){
            out += floorChar;
            length--;
            return getFloor(length, out);
        }
        out += "\n";
        return out;
    }
    public boolean isFilled(){
        for (int r = 0; r < tiles.length; r++){
            for (int c = 0; c < tiles[r].length; c++){
                if(tiles[r][c] == null){
                    return false;
                }
            }
        }
        return true;
    }
    public BasePlayer isWon(){
        BasePlayer winner;
        int sum = 0;

        //Rows
        for (int r = 0; r < tiles.length; r++){
            for(int c = 0; c < tiles[r].length; c++){
                sum += playerStore.getPlayerID(tiles[r][c]) * magicSquare[r][c]; //TODO optimize performance
            }
            //System.out.println(sum);
            winner = getWinningPlayer(sum);
            if(winner != null){
                return winner;
            }
            sum = 0;
        }

        //Columns
        //TODO dont assume the square or at least do some try catch
        for (int c = 0; c < tiles.length; c++){
            for(int r = 0; r < tiles.length; r++){
                sum += playerStore.getPlayerID(tiles[r][c]) * magicSquare[r][c]; //TODO optimize performance
            }
            //System.out.println(sum);
            winner = getWinningPlayer(sum);
            if(winner != null){
                return winner;
            }
            sum = 0;
        }

        //Diagonals
        //TODO make them prettier
        sum += playerStore.getPlayerID(tiles[0][0]) * magicSquare[0][0];
        sum += playerStore.getPlayerID(tiles[1][1]) * magicSquare[1][1];
        sum += playerStore.getPlayerID(tiles[2][2]) * magicSquare[2][2];
        winner = getWinningPlayer(sum);
        if(winner != null){
            return winner;
        }
        sum = 0;

        sum += playerStore.getPlayerID(tiles[0][2]) * magicSquare[0][2];
        sum += playerStore.getPlayerID(tiles[1][1]) * magicSquare[1][1];
        sum += playerStore.getPlayerID(tiles[2][0]) * magicSquare[2][0];
        winner = getWinningPlayer(sum);
        if(winner != null){
            return winner;
        }

        return null;
    }
    private int getTileID(int r, int c){
        if(tiles[r][c] == null){
            return -1;
        }
        return playerStore.getPlayerID(tiles[r][c]);
    }
    private BasePlayer getWinningPlayer(int sum){
        if(sum % 15 == 0 && sum != 0){
            return playerStore.getPlayer(sum/15);
        }
        return null;
    }
    public boolean isEmpty(int r, int c){
        return tiles[r][c] == null;
    }
    public boolean isEnemy(int r, int c, BasePlayer player){
        return (tiles[r][c] != player && !isEmpty(r, c));
    }
    public boolean isBoardClear(){
        for (BasePlayer[] row : tiles){
            for (BasePlayer player : row){
                if(player != null){
                    return false;
                }
            }
        }
        return true;
    }
}
