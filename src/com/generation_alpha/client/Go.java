package com.generation_alpha.client;

import com.generation_alpha.characters.Gyro;
import com.generation_alpha.locations.Direction;
import com.generation_alpha.locations.GamePlay;
import com.generation_alpha.locations.Location;
import com.generation_alpha.locations.Structure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Go {
    public static Structure forGo(GameBoard gameBoard, String input) {
        Gyro gyro = gameBoard.getGyro();
        Location start = gyro.getLocation();
        GamePlay gamePlay = gameBoard.getGamePlay();
        List<Structure> locations = gameBoard.getTerritory().getLocations();
        for (Structure location : locations) {
            if (location.getName().equals(start.getName())) {
                start = location;
            }
        }
        Map<Direction, String> map = start.getMap();
        Direction direction = gamePlay.getDirection(input);
        if (map.containsKey(direction)) {
            String locName = map.get(direction);
            int index = gamePlay.moveGyro(gyro, locName, locations);
            return locations.get(index);
        } else {
            return null;
        }
    }
}
