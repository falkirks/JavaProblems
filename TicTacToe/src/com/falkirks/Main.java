package com.falkirks;

public class Main {
    static public PlayerStore playerStore;
    static public Board board;
    static public final Boolean isDebug = true;
    static public Boolean running = true;
    public static void main(String[] args) {
        playerStore = new PlayerStore();

        board = new Board(playerStore);
        board.showBoard();

        //HumanPlayer humanPlayer = new HumanPlayer(board);
        //playerStore.addPlayer(humanPlayer);

        //HumanPlayer humanPlayer1 = new HumanPlayer(board);
        //playerStore.addPlayer(humanPlayer1);

        AIPlayer aiPlayer = new AIPlayer(board);
        playerStore.addPlayer(aiPlayer);

        TelnetPlayer telnetPlayer = new TelnetPlayer(board, 10101);
        playerStore.addPlayer(telnetPlayer);

        //AIPlayer aiPlayer1 = new AIPlayer(board);
        //playerStore.addPlayer(aiPlayer1);

        playerStore.startGame();
        startGame();
    }
    public static void startGame(){
        gameLoop();
    }
    public static void gameLoop(){
        while(Main.running) {
            playerStore.doNextMove();
            board.showBoard();

            BasePlayer winner = board.isWon();
            if (winner != null) {
                winner.showState(BasePlayer.WON_STATE);
                for (BasePlayer player : playerStore.getPlayers()) {
                    if (player != winner) {
                        player.showState(BasePlayer.LOST_STATE);
                    }
                }
                stopGame();
            } else if (board.isFilled()) {
                for (BasePlayer player : playerStore.getPlayers()) {
                    player.showState(BasePlayer.TIE_STATE);
                }
                stopGame();
            }
        }
    }
    public static void stopGame(){
        Main.running = false;
    }
}
