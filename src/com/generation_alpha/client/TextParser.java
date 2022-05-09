package com.generation_alpha.client;

import com.generation_alpha.battle.Battle;
import com.generation_alpha.battle.FightMovement;
import com.generation_alpha.characters.Gyro;
import com.generation_alpha.characters.Villain;
import com.generation_alpha.items.Item;
import com.generation_alpha.items.PowerItem;
import com.generation_alpha.puzzle.Puzzle;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TextParser {

    private static TextParser instance = new TextParser();
    private static Scanner input = new Scanner(System.in);
    private Puzzle puzzle = Puzzle.getInstance();
    private List<Puzzle> puzzleList;
    // create a Map for different level location
    private String inputLine = "";   // will hold the full input line
    private String word1;           // will hold the first word entered
    private String word2;           // will hold the second word entered

    private TextParser() {}

    public static TextParser getInstance() {
        return instance;
    }

    // Method to prompt for input

    /**
     * method to prompt for user input
     * @param gameBoard
     * @return String
     * @throws IOException
     */
    public String promptInput(GameBoard gameBoard, Clip clip) throws IOException {
        // Start the game by giving prompt and using while loop
        while (true) {
            PrintToScreen.getCurrentLocation(gameBoard);

            System.out.println("Which action would you like to do " + gameBoard.getGyro().getName() + "?");
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

                textParse(gameBoard, word1, word2, clip);

            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * method to determine if user wants to play a new or saved game
     * @return boolean
     */
    public static boolean newGame() {
        System.out.println("Would you like to start a new game [y/n]?");
        String option = input.nextLine();
        if (option.equalsIgnoreCase("y")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * method to capture the user input for naming their Gyro Character
     * @return String
     */
    public static String getName() {
        System.out.println("What's the name of your Gyro?");
        return input.nextLine();
    }

    /**
     * helper method parse to determine user input
     * @param gameBoard
     * @param word1
     * @param word2
     */
    private static void textParse(GameBoard gameBoard, String word1, String word2, Clip clip) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        switch (word1) {
            case "look":
            case "examine":
            case "peel":
                preLook(gameBoard, word2);
                break;
            case "go":
            case "move":
            case "run":
            case "jump":
                preGo(gameBoard, word2);
                break;
            case "asks":
                preAsk(gameBoard, word2);
                break;
            case "fight":
            case "battle":
            case "rumble":
                preFight(gameBoard, word2);
                break;
            case "get":
            case "pickup":
            case "grab":
            case "take":
                preGet(gameBoard, word2);
                break;
            case "inspect":
            case "check":
                preInspect(gameBoard, word2);
                break;
            case "use":
            case "utilize":
                preUse(gameBoard, word2);
                break;
            case "stop":
                Audio.stopAudio(clip);
            case "play":
                Audio.playAudio(clip);
            case "save":
            case "s":
                GameBoard.forSave(gameBoard);
                break;
            case "quit":
            case "q":
                preQuit(gameBoard, word2);
                break;
            default:
                System.out.println("Must enter a verb and a noun, save, or quit");
                break;
        }
    }

    /**
     * method to run forLook()
     * @param gameBoard
     * @param word2
     */
    private static void preLook(GameBoard gameBoard, String word2) {
            try {
                String desc = gameBoard.getGyro().forLook(gameBoard, word2);
                String[] arr = desc.split(" & ");
                System.out.println(arr[0] + "\n\n" + arr[1] + "\n\n");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There is nothing to see in this direction");
            }
    }

    /**
     * method to run forGo()
     * @param gameBoard
     * @param word2
     */
    private static void preGo(GameBoard gameBoard, String word2) {
        gameBoard.getGyro().forGo(gameBoard, word2);
        PrintToScreen.getCurrentLocation(gameBoard);
    }

    /**
     * method to run forGet()
     * @param gameBoard
     * @param word2
     */
    private static void preGet(GameBoard gameBoard, String word2) {
        String result = gameBoard.getGyro().forGet(gameBoard, word2);
        System.out.println(result);
    }

    /**
     * method to run forAsk() or if random number the getPuzzleQuestion()
     * @param gameBoard
     * @param word2
     */
    private static void preAsk(GameBoard gameBoard, String word2) {
        int random = getRandom();
        TextParser textParser = new TextParser();
        if (random == 7) {
            textParser.getPuzzleQuestion();
        } else {
            String quote = gameBoard.getGyro().forAsk(gameBoard, word2);
            System.out.println(quote);
        }
    }

    /**
     * method to run Fight()
     * @param gameBoard
     * @param word2
     */
    private static void preFight(GameBoard gameBoard, String word2) {
        try {
            String result = fightParser(gameBoard);
            System.out.println(result);
        } catch (ClassCastException e) {
            System.out.println("You may not fight an NPC.");
        }
    }

    /**
     * method run forInspect()
     * @param gameBoard
     * @param word2
     */
    private static void preInspect(GameBoard gameBoard, String word2) {
        if (gameBoard.getGyro().getItems().size() > 0) {
            String description = gameBoard.getGyro().forInspect(gameBoard);
            System.out.println(description);
        } else {
            System.out.println("No items in your bag!!");
        }
    }

    /**
     * method run forUse()
     * @param gameBoard
     * @param word2
     */
    private static void preUse(GameBoard gameBoard, String word2) {
        if (gameBoard.getGyro().getItems().size() > 0) {
            String result = gameBoard.getGyro().forUse(gameBoard, word2);
            System.out.println(result);
        } else {
            System.out.println("No items in your bag!!");
        }
    }

    /**
     * method run forQuit()
     * @param gameBoard
     * @param word2
     */
    private static void preQuit(GameBoard gameBoard, String word2) {
        GameBoard.forSave(gameBoard);
        GameBoard.forQuit();
    }

    /**
     * method to collect user input during Battle phase of game
     * @param gameBoard
     * @return String
     */
    private static String fightParser(GameBoard gameBoard) {
        Gyro gyro = gameBoard.getGyro();
        Villain villain = (Villain) gameBoard.getGyro().getLocation().getCharacter();
        PrintToScreen.getHealth(gameBoard);
        StringBuilder sb = new StringBuilder();
        Battle battle;

        System.out.println("Are you sure [y/n]? ");
        String option = input.nextLine();

        if (option.equalsIgnoreCase("y")) {
            if (gyro.getPowers().size() > 0) {
                checkPowers(gameBoard);
                System.out.println("\nWould you like to use a power [y/n]? ");
                option = input.nextLine();
                if (option.equalsIgnoreCase("y")) {
                    PowerItem power = useAPower(gameBoard);
                    battle = new Battle(gameBoard, gyro, villain, true, power);
                } else {
                    battle = new Battle(gameBoard, gyro, villain);
                }
            } else {
                battle = new Battle(gameBoard, gyro, villain);
            }
            FightMovement move = moveFighter();
            String result = battle.moveGyro(move);
            System.out.println(result);
        } else {
            return sb.toString();
        }
        return sb.toString();
    }

    /**
     * helper method to check if Fighter has PowerItems remaining
     * @param gameBoard
     */
    private static void checkPowers(GameBoard gameBoard) {
        Gyro gyro = gameBoard.getGyro();
        System.out.println("Your current powers: ");
        for (PowerItem power : gyro.getPowers()) {
            System.out.print(power.getName() + " ");
        }
    }

    /**
     * helper method to use a PowerItem
     * @param gameBoard
     * @return PowerItem
     */
    private static PowerItem useAPower(GameBoard gameBoard) {
        System.out.println("Which power would you like to use? ");
        String option = input.nextLine();
        Item power = new PowerItem();
        try {
            power = gameBoard.getGamePlay().getItems(option);
            return (PowerItem) power;
        } catch (NullPointerException e) {
            System.out.println("Sorry you do not have that power");
        }
        return (PowerItem) power;
    }

    /**
     * helper method to move Gyro Character
     * @return FightMovement
     */
    private static FightMovement moveFighter() {
        System.out.println("Which direction to move to [up/right]");
        String option = input.nextLine();
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
        return move;
    }

    /**
     * method to return a random int between 0 and 7
     * @return int
     */
    private static int getRandom() {
        return (int) (Math.random() * 8);
    }

    /**
     * method to get random int based on puzzleList size
     * @param num
     * @return int
     */
    private static int getRandomPuzzle(int num) {
        return (int) (Math.random() * num);
    }

    /**
     * method to get and display the Puzzle question and get user input
     */
    private void getPuzzleQuestion() {
        puzzleList = puzzle.getPuzzleList();
        int index = getRandomPuzzle(puzzleList.size());
        puzzleList.get(index).askQuestion();
        String option = input.nextLine();
        puzzleList.get(index).checkAnswer(option);
        puzzleList.remove(index);
    }
}