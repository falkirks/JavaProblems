TicTacToe 
=========
This is a simple modular TicTacToe game for the command line. It includes an AI player, Human player (console), and a Telnet Player.

### Board calculations
The Board is responsible for calculating a win, or tie. This is done in a fashion which allows for accurate calculations regardless of the amount of players in the game. So it can calculate winners in a 3 player Tic-Tac-Toe game.

### The AI Player
The AI Player implements a straightforward AI which unlike the board can't function with more than two players. It uses a system which converts all tiles in the board into relationship integers (10 for belong to AI, 1 for an enemy, and 0 for empty) and then organizes them into an array of all the possible lines on the board. This array is used for checking wins and losses. To find forks the AI attempts to set every 0 tiles to 10 in a cloned board and then calculates if there are possible 2 wins. 
