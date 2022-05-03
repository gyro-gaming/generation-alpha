package com.generation_alpha;

import com.generation_alpha.client.GameBoard;

import java.io.IOException;

class Main {
    public static void main(String[] args) {
        Display display = new Display();
        display.hello();
        display.instructions();
        display.proceed();
        GameBoard gameBoard = new GameBoard("Home");
        try {
            System.out.println(gameBoard.getTextParser().promptInput(gameBoard));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}