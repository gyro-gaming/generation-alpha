package com.generation_alpha.client;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.generation_alpha.characters.Gyro;
import com.generation_alpha.items.Item;
import com.generation_alpha.locations.*;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameBoard implements Serializable {
    private Territory territory;
    private Gyro gyro;
    private TextParser textParser;
    private GamePlay gamePlay;

    private static GameBoard instance = new GameBoard();

    private GameBoard() {
    }

    public static GameBoard getInstance() {
        return instance;
    }

    // initializer methods since this is a Singleton Pattern
    public void init(String name) {
        setGamePlay(new GamePlay());
        setTerritory(gamePlay.getTerritory("Home"));
        setGyro(Gyro.getInstance());
        getGyro().setName(name);
        getGyro().setLocation(getTerritory().getStart());
        setTextParser(TextParser.getInstance());
    }

    public void init(String territory, GamePlay gamePlay, Gyro gyro) {
        setGamePlay(gamePlay);
        setTerritory(gamePlay.getTerritory(territory));
        setGyro(gyro);
        getGyro().setLocation(getTerritory().getStart());
        setTextParser(textParser);
    }

    public void init(Boolean isSavedGame) {
        if (isSavedGame) {
            this.gamePlay = new GamePlay();
            this.territory = gamePlay.getTerritory(true);
            this.gyro = getSavedGyro();
            this.textParser = TextParser.getInstance();
        }
    }
    // end initializer methods

    /**
     * method to save game to json file
     * @param gameBoard
     */
    public static void forSave(GameBoard gameBoard) {
        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            // convert map to JSON file
            writer.writeValue(new File("savedGames/savedGame.json"), gameBoard);

        } catch (Exception e) {
            System.out.println("\n\nYour game was not saved!\n\n");
        }
    }

    /**
     * helper method to retrieve Gyro Character from json file
     * @return Gyro
     */
    private Gyro getSavedGyro() {
        Map<String, Object> map = JsonParser.parseJson("savedGames/savedGame.json");
        Map<String, Object> gyroMap = (Map<String, Object>) map.get("gyro");
        gyro = Gyro.getInstance();
        gyro.setName(gyroMap.get("name").toString());
        gyro.setQuote(new ArrayList<>());
        try {
            gyro.setImage(gyroMap.get("image").toString());
        } catch (NullPointerException e) {
            System.out.println("WARNING: Your gyro does not have an image");
        } finally {
            List<Structure> locations = territory.getLocations();
            Map<String, Object> locMap = (Map<String, Object>) gyroMap.get("location");
            for (int i = 0; i < locations.size(); i++) {
                if (locations.get(i).getName().equalsIgnoreCase(locMap.get("name").toString())) {
                    gyro.setLocation(locations.get(i));
                }
            }
            gyro.setStrength(Integer.parseInt(gyroMap.get("strength").toString()));
            gyro.setHealth(Integer.parseInt(gyroMap.get("health").toString()));
            List<String> itemList = (List<String>) gyroMap.get("items");
            List<Item> items = new ArrayList<>();
            for (String item : itemList) {
                items.add(gamePlay.getItems(item));
            }
            gyro.setItems(items);
            gyro.setPowers(gamePlay.getPowers((List<String>) gyroMap.get("powers")));
        }
        return gyro;
    }

    /**
     * method to quit game
     */
    public static void forQuit() {
        System.exit(0);
    }

    // getters and setters
    public void setGamePlay(GamePlay gamePlay) {
        this.gamePlay = gamePlay;
    }

    public GamePlay getGamePlay() {
        return gamePlay;
    }

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    public Territory getTerritory() {
        return territory;
    }

    public void setGyro(Gyro gyro) {
        this.gyro = gyro;
    }

    public Gyro getGyro() {
        return gyro;
    }

    public void setTextParser(TextParser textParser) {
        this.textParser = textParser;
    }

    public TextParser getTextParser() {
        return textParser;
    }
    // end getters and setters
}
