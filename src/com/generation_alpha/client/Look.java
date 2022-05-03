package com.generation_alpha.client;

import com.generation_alpha.characters.Character;
import com.generation_alpha.items.Item;
import com.generation_alpha.locations.*;
import jdk.swing.interop.SwingInterOpUtils;

import java.util.List;
import java.util.Map;

class Look {

    public static String forLook(GameBoard gameBoard, String input) {
        Location start = gameBoard.getGyro().getLocation();
        System.out.println(start.getName());
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
            return gamePlay.getLocation(locName, locations);
        } else {
            return "There is nothing to see in that direction.";
        }
    }
}