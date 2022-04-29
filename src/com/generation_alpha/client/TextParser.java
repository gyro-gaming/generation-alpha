package com.generation_alpha.client;

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
    public static void main(String[] args) throws IOException {
        // create a Map for different level location
        // For home(starting point)
        HashMap<String, String> home = new HashMap<String, String>();
        home.put("north", "Gym");
        home.put("west", "Shaman");
        home.put("south", "Bully Fight");
        home.put("east", "Cafeteria");

//      For Hunter X level
        JSONParser parser = new JSONParser();
        try {
            // HunterX - TownCenter
            Object obj1 = parser.parse(new FileReader("JsonObjects/hunterx.json"));
            JSONObject jsonObject1 = (JSONObject) obj1;
            Object tc1 = jsonObject1.get("Town Center");
            JSONObject townCenter = (JSONObject) tc1;

            // Talon
            Object obj2 = parser.parse(new FileReader("JsonObjects/talon.json"));
            JSONObject jsonObject2 = (JSONObject) obj2;
            Object tc2 = jsonObject2.get("Stairway to Heaven");
            JSONObject talon = (JSONObject) tc2;

            // Troll
            Object obj3 = parser.parse(new FileReader("JsonObjects/troll.json"));
            JSONObject jsonObject3 = (JSONObject) obj3;
            Object tc3 = jsonObject3.get("Bridge of Death");
            JSONObject troll = (JSONObject) tc3;

            // Master Yamamoto
            Object obj4 = parser.parse(new FileReader("JsonObjects/masteryamamoto.json"));
            JSONObject jsonObject4 = (JSONObject) obj4;
            Object tc4 = jsonObject4.get("The Church of Flames");
            JSONObject yamamoto = (JSONObject) tc4;
            boolean x;
            while (true) {
                String inputLine = "";   // will hold the full input line
                String word1;
                String word2;

                System.out.print("> ");     // print prompt

                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(System.in));
                try {
                    inputLine = reader.readLine();
                } catch (java.io.IOException exc) {
                    System.out.println("There was an error during reading: "
                            + exc.getMessage());
                }

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
                if (word1.equals("go") || word1.equals("move")) {
                    switch (word2){
                        case "north":
                            System.out.println("Home: " + townCenter.get("north"));
                            System.out.println("Town Center: " + townCenter.get("north"));
                            System.out.println("Talon: " + talon.get("north"));
                            System.out.println("Troll: " + troll.get("north"));
                            System.out.println("Master Yamamoto: " + yamamoto.get("north"));
                            break;
                        case "east":
                            System.out.println("Home: " + townCenter.get("east"));
                            System.out.println("Town Center: " + townCenter.get("east"));
                            System.out.println("Talon: " + talon.get("east"));
                            System.out.println("Troll: " + troll.get("east"));
                            System.out.println("Master Yamamoto: " + yamamoto.get("east"));
                            break;
                        case "west":
                            System.out.println("Home: " + townCenter.get("west"));
                            System.out.println("Town Center: " + townCenter.get("west"));
                            System.out.println("Talon: " + talon.get("west"));
                            System.out.println("Troll: " + troll.get("west"));
                            System.out.println("Master Yamamoto: " + yamamoto.get("west"));
                            break;
                        case "south":
                            System.out.println("Home: " + townCenter.get("south"));
                            System.out.println("Town Center: " + townCenter.get("south"));
                            System.out.println("Talon: " + talon.get("south"));
                            System.out.println("Troll: " + troll.get("south"));
                            System.out.println("Master Yamamoto: " + yamamoto.get("south"));
                            break;
                        default:
                            System.out.println("Only can go north,south,east,west");
                            break;
                    }
                }
                else if (word1.equals("quit") || word2.equals("quit")) {
                    break;
                }
                else {
                    System.out.println("no such command.......yet");
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


        // For hunterX location if bully is defeated
//        HashMap<String, String> hunterX = new HashMap<String, String>();
//        hunterX.put("north", "Hunter X Fight");
//        hunterX.put("west", "Saloon");
//        hunterX.put("south", "Grave site");
//        hunterX.put("east", "The Rapid River");

        // For Talon location if hunterX is defeated

//        HashMap<String, String> Talon = new HashMap<String, String>();
//        Talon.put("north", "The Willow Tree Trail");
//        Talon.put("west", "Museum");
//        Talon.put("east", "The Merchant");
//        Talon.put("south", "Talon Fight");
//
//        // For Troll location when Talon is defeated
//        HashMap<String, String> Troll = new HashMap<String, String>();
//        Troll.put("west", "Bridge Officer");
//        Troll.put("east", "Troll Fight");
//
//        // For Master Yamamoto when Troll is defeated
//        HashMap<String, String> master = new HashMap<String, String>();
//        master.put("east", "Master Yamamoto Fight");
//        master.put("west", "The Gate of Eternal Fate");
//        master.put("north", "The Cliff of Demise");
//        master.put("south", "The Screaming Pasture");


    }
}

