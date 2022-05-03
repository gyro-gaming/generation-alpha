package com.generation_alpha.client;
import com.generation_alpha.items.Item;
import com.generation_alpha.locations.*;

import java.util.List;
import java.util.Map;

class Look {
    public static String forLook(String inputLine, String location) {
        Map<String, Object> map = JsonParser.parseJson("JsonObjects/characters.json");
        List<Map<String, Object>> characters = (List) map.get("characters");
        if (location.equals("Home")) {
            switch (inputLine) {
                case "north":
                    GamePlay north = new GamePlay();
                    Territory n = north.getTerritory("Home");
                    System.out.println("Map: " + n.getLocations().get(0).getMap());
                    System.out.println("Location: " + n.getLocations().get(0).getMap().get(Direction.NORTH));
                    System.out.println("Character name: " + n.getLocations().get(0).getCharacter().getName());
                    System.out.println("Item: " + n.getLocations().get(0).getItem().getName());
                    break;
                case "south":
                    GamePlay south = new GamePlay();
                    Territory s = south.getTerritory("Home");
                    System.out.println(s.getLocations().get(0).getMap());
                    System.out.println(s.getLocations().get(0).getMap().get(Direction.SOUTH));
                    System.out.println(characters.get(4).get("character"));
                    System.out.println("Item: " + characters.get(4).get("powers"));
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
                    System.out.println(characters.get(2).get("name"));
                    System.out.println(e.getLocations().get(3).getItem().getName());
                    break;
                default:
                    System.out.println("You can only look north, south, east, west");
                    break;
            }
        } else if (location.equals("Hunter X")) {
            switch (inputLine) {
                case "south":
                    GamePlay south = new GamePlay();
                    Territory s = south.getTerritory("Hunter X");
                    System.out.println("Map: " + s.getMap());
                    System.out.println("Location: " + s.getLocations().get(1).getName());
                    System.out.println("Character: " + s.getLocations().get(1).getCharacter().getName());
                    System.out.println("Items: TBD");
                    break;
                case "east":
                    GamePlay east = new GamePlay();
                    Territory e = east.getTerritory("Hunter X");
                    System.out.println("Map: " + e.getMap());
                    System.out.println("Location: " + e.getLocations().get(3).getName());
                    System.out.println("Character: " + e.getLocations().get(3).getCharacter().getName());
                    System.out.println("Items: " + characters.get(7).get("item"));
                    break;
                case "north":
                    GamePlay north = new GamePlay();
                    Territory n = north.getTerritory("Hunter X");
                    System.out.println("Map: " + n.getMap());
                    System.out.println("Location: " + n.getLocations().get(1).getName());
                    System.out.println("Character: " + n.getLocations().get(1).getCharacter().getName());
                    System.out.println("Items: ");
                    break;
                case "west":
                    GamePlay west = new GamePlay();
                    Territory w = west.getTerritory("Hunter X");
                    System.out.println("Map: " + w.getMap());
                    System.out.println("Location: " + w.getLocations().get(2).getName());
                    System.out.println("Character: " + w.getLocations().get(2).getCharacter().getName());
                    System.out.println("Items: " + characters.get(6).get("item"));
                    break;
                default:
                    break;
            }
        } else if (location.equals("Talon")) {
            System.out.println(characters.get(9).get("name"));
            switch (inputLine) {
                case "north":
                    GamePlay north = new GamePlay();
                    Territory n = north.getTerritory("Talon");
                    System.out.println("Map: " + n.getMap());
                    System.out.println("Location: " + n.getMap().get(Direction.NORTH));
                    System.out.println("Character Name: " + n.getStart().getItem());
                    System.out.println("Item: " + n.getStart().getItem());
                    break;
                case "south":
                    GamePlay south = new GamePlay();
                    Territory s = south.getTerritory("Talon");
                    System.out.println("Map: " + s.getMap());
                    System.out.println("Location: " + s.getMap().get(Direction.SOUTH));
                    System.out.println("Character Name: " + s.getStart().getItem());
                    System.out.println("Item: " + s.getStart().getItem());
                    break;
                case "west":
                    GamePlay west = new GamePlay();
                    Territory w = west.getTerritory("Talon");
                    System.out.println("Map: " + w.getMap());
                    System.out.println("Location: " + w.getMap().get(Direction.WEST));
                    System.out.println("Character Name: " + characters.get(9).get("name"));
                    System.out.println("Item: " + w.getStart().getItem());
                    break;
                case "east":
                    GamePlay east = new GamePlay();
                    Territory e = east.getTerritory("Talon");
                    System.out.println("Map: " + e.getMap());
                    System.out.println("Location: " + e.getMap().get(Direction.EAST));
                    System.out.println("Character Name: " + characters.get(10).get("name"));
                    System.out.println("Item: " + characters.get(10).get("item"));
                    break;
                default:
                    System.out.println("You can only look north, south, east, west");
                    break;
            }
        } else if (location.equals("Troll")) {
            switch (inputLine) {
                case "south":
                    GamePlay south = new GamePlay();
                    Territory s = south.getTerritory("Troll");
                    System.out.println("Map: " + s.getMap());
                    System.out.println("Location: ");
                    System.out.println("Character: ");
                    System.out.println("Items: TBD");
                    break;
                case "east":
                    GamePlay east = new GamePlay();
                    Territory e = east.getTerritory("Troll");
                    System.out.println("Map: " + e.getMap());
                    System.out.println("Location: " + e.getMap().get(Direction.EAST));
                    System.out.println("Character: " + characters.get(13).get("type"));
                    System.out.println("Items: " + characters.get(13).get("item"));
                    break;
                case "north":
                    GamePlay north = new GamePlay();
                    Territory n = north.getTerritory("Troll");
                    System.out.println("Map: " + n.getMap());
                    System.out.println("Location: ");
                    System.out.println("Character: ");
                    System.out.println("Items: ");
                    break;
                case "west":
                    GamePlay west = new GamePlay();
                    Territory w = west.getTerritory("Troll");
                    System.out.println("Map: " + w.getMap());
                    System.out.println("Location: " + w.getMap().get(Direction.WEST));
                    System.out.println("Character: " + characters.get(12).get("name"));
                    System.out.println("Items: " + characters.get(12).get("item"));
                    break;
                default:
                    break;
            }
        }else if (location.equals("Master Yamamoto")){
            switch (inputLine) {
                case "north":
                    GamePlay north = new GamePlay();
                    Territory n = north.getTerritory("Master Yamamoto");
                    System.out.println("Map: " + n.getMap());
                    System.out.println("Location: " + n.getMap().get(Direction.NORTH));
                    System.out.println("Character Name: TBD" );
                    System.out.println("Item: ");
                    break;
                case "south":
                    GamePlay south = new GamePlay();
                    Territory s = south.getTerritory("Master Yamamoto");
                    System.out.println("Map: " + s.getMap());
                    System.out.println("Location: " + s.getMap().get(Direction.SOUTH));
                    System.out.println("Character Name: ");
                    System.out.println("Item: ");
                    break;
                case "west":
                    GamePlay west = new GamePlay();
                    Territory w = west.getTerritory("Master Yamamoto");
                    System.out.println("Map: " + w.getMap());
                    System.out.println("Location: " + w.getMap().get(Direction.WEST));
                    System.out.println("Character Name: " + characters.get(15).get("name"));
                    System.out.println("Item: ");
                    break;
                case "east":
                    GamePlay east = new GamePlay();
                    Territory e = east.getTerritory("Master Yamamoto");
                    System.out.println("Map: " + e.getMap());
                    System.out.println("Location: " + e.getMap().get(Direction.EAST));
                    System.out.println("Character Name: " +characters.get(14).get("name"));
                    System.out.println("Item: "+ characters.get(14).get("item"));
                    break;
                default:
                    System.out.println("You can only look north, south, east, west");
                    break;
            }
        }
        return inputLine;
    }
}