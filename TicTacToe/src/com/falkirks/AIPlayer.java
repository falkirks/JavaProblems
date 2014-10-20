package com.falkirks;

/*
    This is the perfect player implementation.
    It is a variant of my old Processing PP AI.

    It uses a move plot which contains all possible lines. These are
    then used in conjunction with relationship integers, which can easily
    identify the AI's relationship to the player holding the tile.
 */
public class AIPlayer extends BasePlayer{
    final int[][][] lines = {
            {{0, 0}, {0, 1}, {0, 2}},
            {{1, 0}, {1, 1}, {1, 2}},
            {{2, 0}, {2, 1}, {2, 2}},
            {{0, 0}, {1, 0}, {2, 0}},
            {{0, 1}, {1, 1}, {2, 1}},
            {{0, 2}, {1, 2}, {2, 2}},
            {{0, 0}, {1, 1}, {2, 2}},
            {{0, 2}, {1, 1}, {2, 0}}
    };
    boolean firstMove = true;
    public AIPlayer(Board board) {
        super(board);
    }

    void doMove() {
        int[][] movePlot = calculateBoard(getBoard());
        if(firstMove && !getBoard().isBoardClear()){ //Prevent a double fork
            firstMove = false;
            if(getBoard().isEnemy(0, 0, this)){
                claimTile(2, 2);
                return;
            }
            else if(getBoard().isEnemy(2, 2, this)){
                claimTile(0, 0);
                return;
            }
            else if(getBoard().isEnemy(0, 2, this)){
                claimTile(2, 0);
                return;
            }
            else if(getBoard().isEnemy(2, 0, this)){
                claimTile(0, 2);
                return;
            }
        }
        //WINS
        for (int i = 0; i < movePlot.length; i++) {
            if(movePlot[i][0] + movePlot[i][1] + movePlot[i][2] == 20){
                if(movePlot[i][0] == 0) claimTile(lines[i][0][0], lines[i][0][1]);
                else if(data[i][1] == 0) claimTile(lines[i][1][0], lines[i][1][1]);
                else claimTile(lines[i][2][0], lines[i][2][1]);
                return;
            }
        }
        //LOSES
        for (int i = 0; i < movePlot.length; i++) { //Calculate possible losses
            if(movePlot[i][0] + movePlot[i][1] + movePlot[i][2] == 2){ //We have found a possible loss
                if(movePlot[i][0] == 0) claimTile(lines[i][0][0], lines[i][0][1]);
                else if(data[i][1] == 0) claimTile(lines[i][1][0], lines[i][1][1]);
                else claimTile(lines[i][2][0], lines[i][2][1]);
                return;
            }
        }


        //FORK
        for(int r = 0; r < getBoard().getTiles().length; r++){
            for(int c = 0; c < getBoard().getTiles()[r].length; c++){
                if(getBoard().isEmpty(r, c)) {
                    //TODO maybe clone the Board object
                    BasePlayer[][] testBoard = getBoard().getTiles();
                    testBoard[r][c] = this;
                    int[][] testMovePlot = calculateBoard(testBoard);
                    int winCount = 0;
                    for (int j = 0; j < testMovePlot.length; j++) {
                        if(testMovePlot[j][0] + testMovePlot[j][1] + testMovePlot[j][2] == 20){
                            winCount++;
                        }
                    }
                    if(c >= 2){
                        claimTile(r, c);
                        return;
                    }
                }
            }
        }
        //FIND FORKS
        //TODO: Add opt\
        // ion 1 strategy
        for(int i = 0; i < board.length; i++){
            if(test[i] == 0){
                test = board.clone();
                int c = 0;
                test[i] = 1;
                data = calculateBoard(test);
                for (int j = 0; j < data.length; j++) {
                    if(data[j][0] + data[j][1] + data[j][2] == 2) c++;
                }
                if(c > 1){
                    board[i] = 10;
                    toMove = false;
                    return;
                }
            }
        }
        //Mark center
        if(board[4] == 0){
            board[4] = 10;
            toMove = false;
            return;
        }
        //Opposite corners
        if(board[0] == 0 && board[8] == 1){
            board[0] = 10;
            toMove = false;
            return;
        }
        if(board[8] == 0 && board[0] == 1){
            board[8] = 10;
            toMove = false;
            return;
        }
        if(board[2] == 0 && board[6] == 1){
            board[2] = 10;
            toMove = false;
            return;
        }
        if(board[6] == 0 && board[2] == 1){
            board[6] = 10;
            toMove = false;
            return;
        }
        //Any corner and any side
        if(board[0] == 0) board[0] = 10;
        else if(board[2] == 0) board[2] = 10;
        else if(board[6] == 0) board[6] = 10;
        else if(board[8] == 0) board[8] = 10;
        else {
            for(int k = 0; k < board.length; k++){
                if(board[k] == 0){
                    board[k] = 10;
                    toMove = false;
                    return;
                }
            }
        }
    }

    void showState(int state) {
        if(state == LOST_STATE){
            sendMesage("There is a flaw in my AI. Please review game and correct.");
        }
    }
    public int[][] calculateBoard(Board board){
        return calculateBoard(board.getTiles());
    }
    public int[][] calculateBoard(BasePlayer[][] players){
        int[][] movePlot =  new int[8][3];
        int i = 0;
        for (int[][] line: lines) {
            movePlot[i][0] = tileToRelationInteger(players[line[0][0]][line[0][1]]);
            movePlot[i][1] = tileToRelationInteger(players[line[1][0]][line[1][1]]);
            movePlot[i][2] = tileToRelationInteger(players[line[2][0]][line[2][1]]);
            i++;
        }
        return movePlot;
    }
    public int tileToRelationInteger(BasePlayer p){
        if(p == null){
            return 0;
        }
        else if(p == this){
            return 10;
        }
        return 1;
    }
}
