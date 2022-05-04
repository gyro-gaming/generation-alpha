package com.generation_alpha;

import com.generation_alpha.characters.Gyro;
import com.generation_alpha.client.GameBoard;

import java.io.IOException;
import java.util.Scanner;

class Main {


    public static void main(String[] args) {
        Display display = new Display();
        Scanner scanner = new Scanner(System.in);
        display.hello();
        display.instructions();
        Gyro gyro = new Gyro();
        System.out.println("What's the name of your Gyro?");
        String input = scanner.nextLine();
        gyro.setName(input);
        GameBoard gameBoard = new GameBoard("Home");
        try {
            System.out.println(gameBoard.getTextParser().promptInput(gameBoard));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}