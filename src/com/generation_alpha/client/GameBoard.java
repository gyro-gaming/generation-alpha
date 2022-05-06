package com.generation_alpha.client;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.generation_alpha.characters.Gyro;
import com.generation_alpha.locations.GamePlay;
import com.generation_alpha.locations.Territory;

import java.io.File;
import java.io.Serializable;

public class GameBoard implements Serializable {
    private Territory territory;
    private Gyro gyro;
    private TextParser textParser;
    private GamePlay gamePlay;


    private static GameBoard instance = new GameBoard();

    private GameBoard(){}

    public static GameBoard getInstance() {
        return instance;
    }

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

    public void init (Boolean isSavedGame, String name, GamePlay gamePlay, Gyro gyro, TextParser textParser) {
        this.gamePlay = gamePlay;
        this.territory = gamePlay.getTerritory(name);
        this.gyro = gyro; // calls the GamePlay() method to parse saved gyro
        this.textParser = textParser;
    }

    public static void forSave(GameBoard gameBoard) {
        try {

            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper();
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            // convert map to JSON file
            writer.writeValue(new File("savedGames/userLocation.json"), gameBoard);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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
}
