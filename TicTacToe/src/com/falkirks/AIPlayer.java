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
        sendMessage("AI is taking turn.");
        int[][] movePlot = calculateBoard(getBoard());
        sendMessage("Doing double fork check...", true);
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
        sendMessage("Doing wins...", true);
        for (int i = 0; i < movePlot.length; i++) {
            if(movePlot[i][0] + movePlot[i][1] + movePlot[i][2] == 20){
                if(movePlot[i][0] == 0) claimTile(lines[i][0][0], lines[i][0][1]);
                else if(movePlot[i][1] == 0) claimTile(lines[i][1][0], lines[i][1][1]);
                else claimTile(lines[i][2][0], lines[i][2][1]);
                return;
            }
        }
        sendMessage("Doing loses...", true);
        //LOSES
        for (int i = 0; i < movePlot.length; i++) { //Calculate possible losses
            if(movePlot[i][0] + movePlot[i][1] + movePlot[i][2] == 2){ //We have found a possible loss
                if(movePlot[i][0] == 0) claimTile(lines[i][0][0], lines[i][0][1]);
                else if(movePlot[i][1] == 0) claimTile(lines[i][1][0], lines[i][1][1]);
                else claimTile(lines[i][2][0], lines[i][2][1]);
                return;
            }
        }

        sendMessage("Doing forks...", true);
        //FORK
        for(int r = 0; r < getBoard().getTiles().length; r++){
            for(int c = 0; c < getBoard().getTiles()[r].length; c++){
                if(getBoard().isEmpty(r, c)) {
                    //TODO maybe clone the Board object
                    BasePlayer[][] testBoard = getBoard().getTiles();
                    sendMessage("Setting " + r + ":" + c, true);
                    testBoard[r][c] = this;
                    int[][] testMovePlot = calculateBoard(testBoard);
                    int winCount = 0;
                    for (int j = 0; j < testMovePlot.length; j++) {
                        if(testMovePlot[j][0] + testMovePlot[j][1] + testMovePlot[j][2] == 20){
                            sendMessage("Found a possible fork.", true);
                            winCount++;
                        }
                    }
                    if(winCount >= 2){
                        sendMessage("Claiming...", true);
                        claimTile(r, c);
                        return;
                    }
                }
            }
        }
        sendMessage("Doing fork blocks...", true);
        //FIND FORKS
        //TODO Add option 1 strategy
        for(int r = 0; r < getBoard().getTiles().length; r++){
            for(int c = 0; c < getBoard().getTiles()[r].length; c++){
                if(getBoard().isEmpty(r, c)) {
                    //TODO maybe clone the Board object
                    BasePlayer[][] testBoard = getBoard().getTiles();
                    sendMessage("Setting " + r + ":" + c, true);
                    testBoard[r][c] = new HumanPlayer(getBoard()); //TODO generate better
                    int[][] testMovePlot = calculateBoard(testBoard);
                    int winCount = 0;
                    for (int j = 0; j < testMovePlot.length; j++) {
                        if(testMovePlot[j][0] + testMovePlot[j][1] + testMovePlot[j][2] == 2){
                            winCount++;
                        }
                    }
                    if(winCount >= 2){
                        sendMessage("Claiming...", true);
                        claimTile(r, c);
                        return;
                    }
                }
            }
        }
        sendMessage("Claiming center...", true);
        if(claimTile(1, 1)){
            return;
        }
        sendMessage("Opposite corners...", true);
        if(getBoard().isEmpty(0, 0) && getBoard().isEnemy(2, 2, this)){
            claimTile(0, 0);
            return;
        }
        if(getBoard().isEmpty(2, 2) && getBoard().isEnemy(0, 0, this)){
            claimTile(2, 2);
            return;
        }
        if(getBoard().isEmpty(0, 2) && getBoard().isEnemy(2, 0, this)){
            claimTile(0, 2);
            return;
        }
        if(getBoard().isEmpty(2, 0) && getBoard().isEnemy(0, 2, this)){
            claimTile(2, 0);
            return;
        }
        //Any corner and any side
        //TODO this should be rewritten
        sendMessage("Doing corners...", true);
        if(claimTile(0, 0));
        else if(claimTile(0, 2));
        else if(claimTile(2, 0));
        else if(claimTile(2, 2));
        else {
            sendMessage("Finding any tile...", true);
            for(int r = 0; r < getBoard().getTiles().length; r++) {
                for (int c = 0; c < getBoard().getTiles()[r].length; c++) {
                    if (claimTile(r, c)) {
                        return;
                    }
                }
            }
        }
    }

    void showState(int state) {
        if(state == LOST_STATE){
            sendMessage("There is a flaw in my AI. Please review game and correct.", true);
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
