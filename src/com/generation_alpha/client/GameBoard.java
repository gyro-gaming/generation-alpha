package com.generation_alpha.client;

import com.generation_alpha.characters.Fighter;
import com.generation_alpha.characters.Gyro;
import com.generation_alpha.locations.GamePlay;
import com.generation_alpha.locations.Structure;
import com.generation_alpha.locations.Territory;

import java.util.List;

public class GameBoard {
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
