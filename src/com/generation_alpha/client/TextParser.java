package com.generation_alpha.client;

import com.generation_alpha.battle.Battle;
import com.generation_alpha.battle.FightMovement;
import com.generation_alpha.characters.Gyro;
import com.generation_alpha.characters.Villain;
import com.generation_alpha.items.Item;
import com.generation_alpha.items.PowerItem;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TextParser {
    private static Scanner input = new Scanner(System.in);
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
                    try {
                        System.out.println("Would you like to ask " + gameBoard.getGyro().getLocation().getCharacter().getName() + " a question?");
                    } catch (NullPointerException e) {}
                    try {
                        System.out.println("There is also a " + gameBoard.getGyro().getLocation().getItem().getName() + " in the room.\n");
                    } catch (NullPointerException e) {}
                } else if (word1.equals("get") || word1.equals("pickup") || word1.equals("grab") || word1.equals("take")) {
                    String result = Get.forGet(gameBoard, word2);
                    System.out.println(result);

                } else if (word1.equals("look") || word1.equals("examine") || word1.equals("peel")) {
                    try{
                        String desc = Look.forLook(gameBoard, word2);
                        String[] arr = desc.split(" & ");
                        System.out.println(arr[0] + "\n\n" + arr[1] + "\n\n");
                    }catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("There is nothing to see in this direction");
                    }

                } else if (word1.equals("asks")) {
                    String quote = Ask.forAsk(gameBoard, word2);
                    System.out.println(quote);

                } else if(word1.equals("fight")){
                    try {
                        String result = fightParser(gameBoard);
                        System.out.println(result);
                    } catch (ClassCastException e) {
                        System.out.println("You may not fight an NPC.");
                    }
                } else if (word1.equals("inspect")) {
                    if (gameBoard.getGyro().getItems().size() > 0) {
                        String description = Inspect.forInspect(gameBoard);
                        System.out.println(description);
                    } else {
                        System.out.println("No items in your bag!!");
                    }
                } else if (word1.equals("use")) {
                    if (gameBoard.getGyro().getItems().size() > 0) {
                        String result = Use.forUse(gameBoard, word2);
                        System.out.println(result);
                    } else {
                        System.out.println("No items in your bag!!");
                    }

                } else if (word1.equals("quit") || word1.equals("q")) {
                    return word2;

                } else {
                    System.out.println("Must enter go/get direction/item or quit");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String fightParser(GameBoard gameBoard) {
        // Give option to fight
        StringBuilder sb = new StringBuilder();
        Battle battle;
        Villain villain = (Villain)gameBoard.getGyro().getLocation().getCharacter();
        int villainHealth = villain.getHealth();
        int villainStrength = villain.getStrength();
        Gyro gyro = gameBoard.getGyro();
        int gyroHealth = gyro.getHealth();
        int gyroStrength = gyro.getStrength();
        System.out.println("Your health: " + gyroHealth);
        System.out.println("Your Strength: " + gyroStrength);
        System.out.println(gameBoard.getGyro().getLocation().getCharacter().getName()+ "'s health: " + villainHealth);
        System.out.println(gameBoard.getGyro().getLocation().getCharacter().getName()+ "'s Strength: " + villainStrength);
        System.out.println("Are you sure [y/n]? ");
        String option = input.nextLine();

        if (option.equalsIgnoreCase("y")) {
            if (gyro.getPowers().size() > 0) {
                System.out.println("Your current powers: ");
                System.out.println(gyro.getPowers().toString());
                System.out.println("Would you like to use a power [y/n]? ");
                option = input.nextLine();
                if (option.equalsIgnoreCase("y")) {
                    System.out.println("Which power would you like to use? ");
                    option = input.nextLine();
                    Item power = new PowerItem();
                    try {
                        power = gameBoard.getGamePlay().getItems(option);
                    } catch (NullPointerException e) {
                        System.out.println("Sorry you do not have that power");
                    }
                    battle = new Battle(gyro, villain, true, (PowerItem) power);
                } else {
                    battle = new Battle(gyro, villain);
                }
            } else {
                battle = new Battle(gyro, villain);
            }
            System.out.println("Which direction to move to [up/right]");
            option = input.nextLine();
            FightMovement move = FightMovement.UP;
            option = option.toLowerCase();
            switch (option) {
                case "up":
                    move = FightMovement.UP;
                    break;
                case "right":
                    move = FightMovement.RIGHT;
                    break;
                default:
                    System.out.println("Not a valid direction.");
                    break;
            }
            String result = battle.moveGyro(move);
            System.out.println(result);
        } else {
            return sb.toString();
        }
        return sb.toString();
    }
}











