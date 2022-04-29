package com.generation_alpha.client;

import com.generation_alpha.locations.Direction;
import com.generation_alpha.locations.Territory;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class TextParser {
    // create a Map for different level location
    String inputLine = "";   // will hold the full input line
    String word1;
    String word2;

    public String promptInput() {
        JSONParser parser = new JSONParser();
        boolean x;
        while (true) {
            String inputLine = "";   // will hold the full input line

            System.out.print("> ");     // print prompt

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            try {
                inputLine = reader.readLine();
            } catch (java.io.IOException exc) {
                System.out.println("There was an error during reading: " + exc.getMessage());
            }
            return inputLine;
        }
    }


    // -------------------------------------------------------------------

    public Direction forGo(String inputLine) {
        Direction direction = null;
        StringTokenizer tokenizer = new StringTokenizer(inputLine.toLowerCase());
        if (tokenizer.hasMoreTokens())
            word1 = tokenizer.nextToken();      // get first word
        else
            word1 = "";
        if (tokenizer.hasMoreTokens())
            word2 = tokenizer.nextToken();      // get second word
        else
            word2 = "";

        // Conditions
            switch (word2) {
                case "north":
                    direction = Direction.NORTH;
                    break;
                case "east":
                    direction = Direction.EAST;
                    break;
                case "west":
                    direction = Direction.WEST;
                    break;
                case "south":
                    direction = Direction.SOUTH;
                    break;
                default:
                    System.out.println("Only can go north,south,east,west");
                    break;
            }
        return direction;
    }

    // --------------------------------------------------------------------
}









