package com.generation_alpha;

import com.apps.util.Console;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

class Display {
    Scanner scanner = new Scanner(System.in);
    String path = "";

    public Display() {
        super();
    }
    public void hello() {
        setPath("resources/welcomeBanner.txt");
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

        setPath("text_files/story.txt");
        getPathReturn(getPath());
    }

    public void instructions() {

        setPath("text_files/instructions.txt");
        getPathReturn(getPath());
    }


    public void gameEnd() {
        setPath("resources/endBanner.txt");
        getPathReturn(getPath());
    }

    public void showVictory() {
        setPath("resources/victoryBanner.txt");
        getPathReturn(path);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
