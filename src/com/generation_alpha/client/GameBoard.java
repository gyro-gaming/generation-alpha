package com.generation_alpha.client;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.generation_alpha.characters.Fighter;
import com.generation_alpha.characters.Gyro;
import com.generation_alpha.locations.GamePlay;
import com.generation_alpha.locations.Structure;
import com.generation_alpha.locations.Territory;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.List;

public class GameBoard implements Serializable {
    private Territory territory;
    private Gyro gyro;
    private TextParser textParser;
    private GamePlay gamePlay;
    private Structure currentLocation;

    public GameBoard(String name) {
        this.gamePlay = new GamePlay();
        this.territory = gamePlay.getTerritory("Home");
        this.gyro = gamePlay.getGyro(name);
        this.textParser = new TextParser();
    }

    public GameBoard(String name, GamePlay gamePlay, Gyro gyro, TextParser textParser) {
        this.gamePlay = gamePlay;
        this.territory = gamePlay.getTerritory(name);
        this.gyro = gyro;
        this.textParser = textParser;
    }

    public GameBoard(Boolean isSavedGame, String name, GamePlay gamePlay, Gyro gyro, TextParser textParser) {
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
    public GamePlay getGamePlay() {
        return gamePlay;
    }

    public Territory getTerritory() {
        return territory;
    }

    public Gyro getGyro() {
        return gyro;
    }

    public TextParser getTextParser() {
        return textParser;
    }
}
