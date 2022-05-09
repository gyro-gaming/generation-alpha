package com.generation_alpha;

import com.generation_alpha.client.Display;
import com.generation_alpha.client.GameBoard;
import com.generation_alpha.client.TextParser;

import javax.sound.sampled.*;
import java.io.IOException;

class Main {
    public static void main(String[] args) {
       

        Display.getInstance();

        GameBoard gameBoard = GameBoard.getInstance();
        boolean isSavedGame = TextParser.newGame();
        if (isSavedGame) {
            gameBoard.init(isSavedGame);
        } else {
            gameBoard.init(TextParser.getName());
        }
        System.out.println(gameBoard.getGyro().getName());
        try {
            System.out.println(gameBoard.getTextParser().promptInput(gameBoard));
        } catch (IOException e) {
            System.out.println("There was an error creating the game board. Error: " + e);
        }
    }
}
