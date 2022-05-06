package com.generation_alpha.client;

import com.apps.util.Console;

import javax.sound.sampled.AudioSystem;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Display {
    Scanner scanner = new Scanner(System.in);
    String path = "";

    public Display() {
        super();
    }
    public void hello() {
//        AudioPlayer.playSound("resources/sounds/gameplay.wav");
        setPath("resources/asciiBanners/welcomeBanner.txt");
        getPathReturn(getPath());
        story();
    }
    public void getPathReturn(String path){
        if(Files.exists(Path.of(path))){
            try{
                List<String> lines = Files.readAllLines(Path.of(path));
                for (String line : lines){
                    System.out.println(line);
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        proceed();
        Console.clear();
    }

    public void proceed() {
        System.out.println("Press [Enter] to continue.");
        String input = scanner.nextLine();
    }

    public void story() {

        setPath("resources/text_files/story.txt");
        getPathReturn(getPath());
    }

    public void instructions() {

        setPath("resources/text_files/instructions.txt");
        getPathReturn(getPath());
    }


    public void gameEnd() {
        setPath("resources/asciiBanners/endBanner.txt");
        getPathReturn(getPath());
    }

    public void showVictory() {
        setPath("resources/asciiBanners/victoryBanner.txt");
        getPathReturn(path);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
