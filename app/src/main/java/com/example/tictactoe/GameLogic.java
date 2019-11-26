package com.example.tictactoe;

import java.io.Serializable;

public class GameLogic {

    // array that holds the game board values
    static public char[][] game;

    // constructor, start with an empty board
    public GameLogic() {
        game = new char [][] {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    }

    // checks if there are any wins in the rows, columns, or diagonals
    public boolean checkWin() {
        return(rowWin() || colWin() || diagWin());
    }

    // checks each row if there is a win & not null
    private boolean rowWin() {
        for (int i = 0; i < 3; i++){
            if (game[i][0] == game[i][1] && game[i][1] == game[i][2] && game[i][0] != ' ')
                return true;
        }
        return false;
    }

    // checks each column for a win & not null
    private boolean colWin() {
        for (int i = 0; i < 3; i++) {
            if (game[0][i] == game[1][i] && game[1][i] == game[2][i] && game[0][i] != ' ')
                return true;
        }
        return false;
    }

    // checks both diagonals for a win, & not null
    private boolean diagWin() {
        if (((game[0][0] == game[1][1] && game[1][1] == game[2][2]) || (game[0][2] == game[1][1] && game[1][1] == game[2][0])) && game[1][1] != ' ')
                return true;
        return false;
    }

    // resets the game logic board and sets them all to null
    public static void resetGame() {
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                game[i][j] = ' ';
            }
        }
    }

}
