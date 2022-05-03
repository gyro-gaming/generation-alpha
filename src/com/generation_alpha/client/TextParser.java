package com.generation_alpha.client;

import java.io.*;
import java.util.StringTokenizer;

public class TextParser {
    // create a Map for different level location
    String inputLine = "";   // will hold the full input line
    String word1;           // will hold the first word entered
    String word2;           // will hold the second word entered

    // Method to prompt for input
    public String promptInput(GameBoard gameBoard) throws IOException {
        // Start the game by giving prompt and using while loop
        while (true) {
            String inputLine = "";   // will hold the full input line
            System.out.println("Which action will you like to do?");
            System.out.print("> ");     // print prompt

            // Takes in input and saves it as inputLine
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));
            try {
                inputLine = reader.readLine();
                // Use StringTokenizer to break up the input and makes it lowercase
                StringTokenizer tokenizer = new StringTokenizer(inputLine.toLowerCase());

                // breaks up word 1 / verbs
                if (tokenizer.hasMoreTokens())
                    word1 = tokenizer.nextToken();      // get first word
                else
                    word1 = "";

                // breaks up word / noun
                if (tokenizer.hasMoreTokens())
                    word2 = tokenizer.nextToken();      // get second word
                else
                    word2 = "";

                //  Conditions - forGo, forGet,forLook, quit
                if (word1.equals("go") || word1.equals("move") || word1.equals("run") || word1.equals("jump")) {
                    Go.forGo(gameBoard, word2);
                    System.out.println("You are now in " + gameBoard.getGyro().getLocation().getName());
                    System.out.println("Would you like to ask " + gameBoard.getGyro().getLocation().getCharacter().getName() + " a question?");
                    System.out.println("There is also a " + gameBoard.getGyro().getLocation().getItem().getName() + " in the room.");
                } else if (word1.equals("get") || word1.equals("pickup") || word1.equals("grab") || word1.equals("take")) {
                    String result = Get.forGet(gameBoard, word2);
                    System.out.println(result);
                } else if (word1.equals("look") || word1.equals("examine") || word1.equals("peel")) {
                    String desc = Look.forLook(gameBoard, word2);
                    System.out.println(desc);

                }
                else if (word1.equals("asks")) {
                    String quote = Ask.forAsk(gameBoard, word2);
                    System.out.println(quote);
                }
                else if (word1.equals("inspect")) {
                    if (gameBoard.getGyro().getItems().size() > 0) {
                        String description = Inspect.forInspect(gameBoard);
                        System.out.println(description);
                    }else{
                        System.out.println("No items in your bag!!");
                    }

                }
                else if (word1.equals("quit") || word1.equals("q")){
                    return word2;
                }
                else{
                    System.out.println("Must enter go/get direction/item or quit");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    }











