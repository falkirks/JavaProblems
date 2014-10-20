package com.falkirks;

abstract public class BasePlayer {
    final static int WON_STATE = 0;
    final static int LOST_STATE = 1;
    final static int TIE_STATE = 2;
    final static int START_STATE = 3;

    private char playerChar;
    private Board board;

    protected BasePlayer(Board board) {
        this.board = board;
    }

    public char getPlayerChar() {
        return playerChar;
    }

    public void setPlayerChar(char playerChar) {
        this.playerChar = playerChar;
    }

    public Board getBoard() {
        return board;
    }

    abstract void doMove();
    abstract void showState(int state);
    protected boolean claimTile(int r, int c){
        return board.claimTile(r, c, this);
    }
    //TODO fix typo and refactor
    protected void sendMessage(String msg){
        sendMessage(msg, false);
    }
    protected void sendMessage(String msg, Boolean isDebug){
        if(!isDebug || Main.isDebug) {
            System.out.println("[" + getPlayerChar() + "] " + msg);
        }
    }
}
