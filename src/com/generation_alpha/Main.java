package com.generation_alpha;

import com.generation_alpha.client.Display;
import com.generation_alpha.client.GameBoard;
import com.generation_alpha.locations.GamePlay;

import java.io.IOException;
import java.util.Scanner;

class Main {


    public static void main(String[] args) {
        Display display = new Display();
        Scanner scanner = new Scanner(System.in);
        display.hello();
        display.instructions();
        System.out.println("What's the name of your Gyro?");
        String input = scanner.nextLine();
        GameBoard gameBoard = new GameBoard(input);
        try {
            System.out.println(gameBoard.getTextParser().promptInput(gameBoard));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}