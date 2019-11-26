package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;


public class MainActivity extends AppCompatActivity {

    // array to hold all 9 grid buttons
    private Button[] buttonArray;
    // class with static methods to handle game logic (represent 3x3 board with 2d array of CHARS, etc.)
    private GameLogic gamelogic;
    // reset game button
    private Button resetGame;
    // text box to view which player turn it is
    private TextView viewTurn;
    // boolean to keep track of player turn (true = X, false = O)
    private boolean turn;
    // player names
    private String Xname;
    private String Oname;

    // constructor
    public MainActivity() {
        // initialize the gamelogic
        gamelogic = new GameLogic();
        // initialize array to hold all 9 grid buttons
        buttonArray = new Button[9];
        Xname = "Player X";
        Oname = "Player O";

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // find reset game button and TextView using findViewById
        resetGame = findViewById(R.id.resetButton);
        viewTurn = findViewById(R.id.player);
        // add functionality so TextView display's whose turn it is
        viewTurn.setText("Turn: " + Xname);
        // set player turn to X
        turn = true;

        if (savedInstanceState != null) {
            gamelogic.game = (char[][]) savedInstanceState.getSerializable("GAMELOGIC");
            turn = savedInstanceState.getBoolean("TURN");
        }

        // create an array to hold all the R.ids of the buttons, to use in the for loop later
        int[] rIdArray = {R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};
        // add the buttons (R.id) to the array using for loop
        for (int x = 0; x < 9; x++){
            final int i = x % 3;
            final int j = x / 3;
            buttonArray[x] = findViewById(rIdArray[x]);
            buttonArray[x].setText(Character.toString(gamelogic.game[i][j]));
            if (gamelogic.game[i][j] != ' ') {
                buttonArray[x].setEnabled(false);
            }
        }

/*
        for (int x = 0; x < 9; x++) {
            final int i = x % 3;
            final int j = x / 3;

            buttonArray[x].setText(gamelogic.game[i][j]);
        }
*/


        // add an On ClickListener to all 9 grid buttons using for loop
        for (int x = 0; x < 9; x++){
            // create a final variable
            final int b = x;
            // the button index will correlate to the following indexes when translating from 1D to 2D array
            final int i = x % 3;
            final int j = x / 3;

            // add what happens when the button is clicked on
            buttonArray[b].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    // disable button so it can't be changed
                    buttonArray[b].setEnabled(false);

                    // if player X turn (turn = true = X), set both button and char in logic board to X
                    if (turn) {
                        buttonArray[b].setText("X");
                        gamelogic.game[i][j] = 'X';
                    // if player O turn (turn = false = O), set both button and char in logic board to O
                    }else {
                        buttonArray[b].setText("O");
                        gamelogic.game[i][j] = 'O';
                    }

                    // check if there are any wins in the logic board
                    if (gamelogic.checkWin()){
                        //disable all buttons when player win
                        for (int x = 0; x < 9; x++) {
                            buttonArray[x].setEnabled(false);}

                            // if it is currently player X's turn, then set text to Player X wins, exit function
                        if (turn) {
                            viewTurn.setText(Xname + " WINS!");
                            return;
                        // if it is currently player O's turn, then set text to Player O wins, exit function
                        } else {
                            viewTurn.setText(Oname + " WINS!");
                            return;
                        }
                    }

                    // otherwise, if the turn is player X, change to Player O, and vice versa.
                    // turn = true = X, turn = false = O
                    if (turn) {
                        turn = false;
                        viewTurn.setText("Turn: " + Oname);
                    } else {
                        turn = true;
                        viewTurn.setText("Turn: " + Xname);
                    }
                }
            });
        }

        // add functionality so rest game button clears all 9 grid button's text
        resetGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // clear the text on all the buttons
                for (int x = 0; x < 9; x++) {
                    buttonArray[x].setEnabled(true);
                    buttonArray[x].setText(" ");
                }
                // clear the game logic board
                gamelogic.resetGame();

                // reset the player to player X (turn = true = X)
                turn = true;
                viewTurn.setText("Turn: " + Xname);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.help:
                return true;
            case R.id.settings:
                Intent intent = new Intent(this, Settings.class);
                intent.putExtra("XNAME", Xname);
                intent.putExtra("ONAME", Oname);
                this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("GAMELOGIC", gamelogic.game);
        outState.putBoolean("TURN", turn);
    }

    /*public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        gamelogic.game = (char[][]) savedInstanceState.getSerializable("GAMELOGIC");
        turn = savedInstanceState.getBoolean("TURN");
    }*/
}

