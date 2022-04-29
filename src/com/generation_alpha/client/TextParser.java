package com.generation_alpha.client;

import com.generation_alpha.locations.Direction;
import com.generation_alpha.locations.Territory;

import java.io.*;
import java.util.StringTokenizer;

public class TextParser {
    // create a Map for different level location
    String inputLine = "";   // will hold the full input line
    String word1;
    String word2;
    public String[] promptInput() throws IOException {

        boolean x;
        while (true) {
            String inputLine = "";   // will hold the full input line

            System.out.print("> ");     // print prompt

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            try {
                inputLine = reader.readLine();
                StringTokenizer tokenizer = new StringTokenizer(inputLine.toLowerCase());
                if (tokenizer.hasMoreTokens())
                    word1 = tokenizer.nextToken();      // get first word
                else
                    word1 = "";
                if (tokenizer.hasMoreTokens())
                    word2 = tokenizer.nextToken();      // get second word
                else
                    word2 = "";
                String[] input = {word1, word2};
                if (word1.equals("go") || word1.equals("move") || word1.equals("run") || word1.equals("jump")){
                    forGo(input[0] + " " + input[1]);
                }
                else if (word1.equals("get") || word1.equals("pickup") || word1.equals("grab") || word1.equals("take")){
                    forGet(input[0] + " " + input[1]);
                }
                else if (word1.equals("quit") || word1.equals("q")){
                    return input;
                }
                else{
                    System.out.println("Must enter go/get direction/item");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
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

        System.out.println(direction);
        return direction;
    }
    // --------------------------------------------------------------------

    public String forGet(String inputLine){
        String item = "";
        GamePlay gp = new GamePlay();
        Territory t = gp.getTerritory("Home");
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
            case "strength":
                item = "Strength";
                break;
            case "health":
                item = "Health";
                break;
            case "power":
                item = "Power";
                break;
            default:
                System.out.println("No such item yet");
                break;
        }
        System.out.println(item);
        return item;
    }

    // --------------------------------------------------------------------

    }











