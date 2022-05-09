package com.generation_alpha;

import com.generation_alpha.client.Display;
import com.generation_alpha.client.GameBoard;
import com.generation_alpha.client.TextParser;

import javax.sound.sampled.*;
import java.io.IOException;

class Main {
    public static void main(String[] args) {
        // Clip clip = AudioSystem.getClip();
        // File f = new File("resources/sounds/classic.wav");
        // AudioInputStream as = AudioSystem.getAudioInputStream(f);
        // clip.open(as);
        // clip.start();
        // clip.loop(-1);

//        List<Puzzle> puzzleList = LoadPuzzleFromJson.loadPuzzleFromJson();
//        System.out.println(puzzleList);

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