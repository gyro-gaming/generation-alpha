package com.generation_alpha.client;

import com.generation_alpha.locations.Direction;
import com.generation_alpha.locations.Territory;

class Look {
    public static String forLook(String inputLine, String location) {
        if (location.equals("Home")) {
            switch (inputLine) {
                case "north":
                    GamePlay north = new GamePlay();
                    Territory n = north.getTerritory("Home");
                    System.out.println("Map: " + n.getLocations().get(0).getMap());
                    System.out.println("Location: " + n.getLocations().get(0).getMap().get(Direction.NORTH));
                    System.out.println("Character Name: " + n.getLocations().get(1).getCharacter().getName());
                    System.out.println("Item Name: " + n.getLocations().get(1).getItem().getName());
                    break;
                case "south":
                    GamePlay south = new GamePlay();
                    Territory s = south.getTerritory("Home");
                    System.out.println(s.getLocations().get(0).getMap());
                    System.out.println(s.getLocations().get(0).getMap().get(Direction.SOUTH));
                    System.out.println(s.getLocations().get(4).getCharacter().getName());
                    System.out.println("Power item needs to be added to locations.json");
                    break;
                case "west":
                    GamePlay west = new GamePlay();
                    Territory w = west.getTerritory("Home");
                    System.out.println(w.getLocations().get(0).getMap());
                    System.out.println(w.getLocations().get(0).getMap().get(Direction.WEST));
                    System.out.println(w.getLocations().get(2).getCharacter().getName());
                    System.out.println(w.getLocations().get(2).getItem().getName());
                    break;
                case "east":
                    GamePlay east = new GamePlay();
                    Territory e = east.getTerritory("Home");
                    System.out.println(e.getLocations().get(0).getMap());
                    System.out.println(e.getLocations().get(0).getMap().get(Direction.EAST));
                    System.out.println(e.getLocations().get(3).getCharacter().getName());
                    System.out.println(e.getLocations().get(3).getItem().getName());
                    break;
                default:
                    System.out.println("You can only look north, south, east, west");
                    break;
            }
        }

        return inputLine;
    }
}
