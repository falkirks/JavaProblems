package com.falkirks;

public class Main {
    static public PlayerStore playerStore;
    static public Board board;
    static public final Boolean isDebug = true;
    public static void main(String[] args) {
        playerStore = new PlayerStore();

        board = new Board(playerStore);
        board.showBoard();

        HumanPlayer humanPlayer = new HumanPlayer(board);
        playerStore.addPlayer(humanPlayer);

        //HumanPlayer humanPlayer1 = new HumanPlayer(board);
        //playerStore.addPlayer(humanPlayer1);

        AIPlayer aiPlayer = new AIPlayer(board);
        playerStore.addPlayer(aiPlayer);

        //AIPlayer aiPlayer1 = new AIPlayer(board);
        //playerStore.addPlayer(aiPlayer1);

        playerStore.startGame();

        gameTick();
    }
    public static void gameTick(){
        playerStore.doNextMove();
        board.showBoard();

        BasePlayer winner = board.isWon();
        if(winner != null){
            winner.showState(BasePlayer.WON_STATE);
            for(BasePlayer player : playerStore.getPlayers()){
                if(player != winner) {
                    player.showState(BasePlayer.LOST_STATE);
                }
            }
        }
        else if(board.isFilled()){
            for(BasePlayer player : playerStore.getPlayers()){
                player.showState(BasePlayer.TIE_STATE);
            }
        }
        else {
            gameTick();
        }
    }
}
