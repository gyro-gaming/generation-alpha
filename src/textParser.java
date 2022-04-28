import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class textParser {
    public static void main(String[] args) {
        // create a Map for different level location
        // For home(starting point)
        HashMap<String, String> home = new HashMap<String, String>();
        home.put("north", "Gym");
        home.put("west", "Shaman");
        home.put("south", "Bully Fight");
        home.put("east", "Cafeteria");

        // For hunterX location if bully is defeated
        HashMap<String, String> hunterX = new HashMap<String, String>();
        hunterX.put("north", "Hunter X Fight");
        hunterX.put("west", "Saloon");
        hunterX.put("south", "Grave site");
        hunterX.put("east", "The Rapid River");

        // For Talon location if hunterX is defeated
        HashMap<String, String> Talon = new HashMap<String, String>();
        Talon.put("north", "The Willow Tree Trail");
        Talon.put("west", "Museum");
        Talon.put("east", "The Merchant");
        Talon.put("south", "Talon Fight");

        // For Troll location when Talon is defeated
        HashMap<String, String> Troll = new HashMap<String, String>();
        Troll.put("west", "Bridge Officer");
        Troll.put("east", "Troll Fight");

        // For Master Yamamoto when Troll is defeated
        HashMap<String, String> master = new HashMap<String, String>();
        master.put("east", "Master Yamamoto Fight");
        master.put("west", "The Gate of Eternal Fate");
        master.put("north", "The Cliff of Demise");
        master.put("south", "The Screaming Pasture");

        System.out.println("Welcome to Generation Alpha");
        System.out.println("===========================");
        System.out.println("Commands: go/move[direction], look/examine/peek[direction], quit/q");
        
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

            if (tokenizer.hasMoreTokens()) {
                word1 = tokenizer.nextToken(); // get first word
            }
            else {
                word1 = "";
            }
            if (tokenizer.hasMoreTokens()) {
                word2 = tokenizer.nextToken();      // get second word
            }
            else {
                word2 = "";
            }

            // Conditions
            if (word1.equals("go") || word1.equals("move")) {
                switch (word2){
                    case "north":
                        System.out.println(home.get("north") + ": Hey I'm your fitness trainer, Susie. Thanks for attending. Your strength has increased!!");
                        break;
                    case "east":
                        System.out.println(home.get("east") + ": Hey I'm the Shaman, Yoshi. Thanks for attending. Your super power blast increased");
                        break;
                    case "west":
                        System.out.println(home.get("west") + ": Hey I'm Mr. Baker. You're in the cafeteria. Thanks for attending. Your health increased");
                        break;
                    case "south":
                        System.out.println(home.get("south") + ": Get ready to fight the Bully");
                        break;
                    default:
                        System.out.println("Only can go(move) north,south,east,west");
                        break;
                }
            }
            else if (word1.equals("look") || word1.equals("examine") || word1.equals("peek")){
                switch (word2){
                    case "north":
                        System.out.println(home.get("north") + " is located in that direction");
                        break;
                    case "east":
                        System.out.println(home.get("east") + " is located in that direction");
                        break;
                    case "west":
                        System.out.println(home.get("west") + " is located in that direction");
                        break;
                    case "south":
                        System.out.println(home.get("south") + " is located in that direction");
                        break;
                    default:
                        System.out.println("You can only see(look,examine) north,south,east,west");
                        break;
                }
            }
            else if (word1.equals("quit") || word1.equals("q") || word2.equals("quit") || word2.equals("q")) {
                break;
            }
            else {
                System.out.println("no such command.......yet");
            }
        }
    }
}
