package com.generation_alpha;

import com.generation_alpha.client.Display;
import com.generation_alpha.client.GameBoard;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Main {


    public static void main(String[] args) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        Clip clip = AudioSystem.getClip();
        File f = new File("resources/sounds/classic.wav");
        AudioInputStream as = AudioSystem.getAudioInputStream(f);
        clip.open(as);
        clip.start();
        clip.loop(-1);
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