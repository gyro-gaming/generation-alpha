package com.generation_alpha.client;

import com.apps.util.Console;

import javax.sound.sampled.AudioSystem;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Display {
    private static Scanner scanner = new Scanner(System.in);
    private static String path = "";
    private static Display instance = new Display();

    private Display() {
    }

    public static Display getInstance() {
        instance.hello();
        instance.instructions();
        return instance;
    }

    // getters and setters
    public static void setPath(String path) {
        Display.path = path;
    }

    public static String getPath() {
        return path;
    }
    // end getters and setters

    /**
     * helper method to retrieve Files
     * @param path
     */
    private static void getPathReturn(String path) {
        if (Files.exists(Path.of(path))) {
            try {
                List<String> lines = Files.readAllLines(Path.of(path));
                for (String line : lines) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("File path not found! Error: " + e);
            }
        }
        proceed();
        Console.clear();
    }

    /**
     * method to show game title on splash page
     */
    private static void hello() {
//      AudioPlayer.playSound("resources/sounds/gameplay.wav");
        setPath("resources/asciiBanners/welcomeBanner.txt");
        getPathReturn(getPath());
        story();
    }

    /**
     * method to require user to hit [Enter] to continue to next page
     */
    private static void proceed() {
        System.out.println("Press [Enter] to continue.");
        String input = scanner.nextLine();
    }

    /**
     * method to show Gyro background story
     */
    private static void story() {
        setPath("resources/text_files/story.txt");
        getPathReturn(getPath());
    }

    /**
     * method to show game play instructions
     */
    private static void instructions() {
        setPath("resources/text_files/instructions.txt");
        getPathReturn(getPath());
    }

    /**
     * method to show game over banner
     */
    public static void gameEnd() {
        setPath("resources/asciiBanners/endBanner.txt");
        getPathReturn(getPath());
    }

    /**
     * method to show you win banner
     */
    public static void showVictory() {
        setPath("resources/asciiBanners/victoryBanner.txt");
        getPathReturn(path);
    }
}
