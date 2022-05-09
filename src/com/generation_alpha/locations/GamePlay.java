package com.generation_alpha.locations;

import com.generation_alpha.characters.Character;
import com.generation_alpha.characters.Gyro;
import com.generation_alpha.characters.NPC;
import com.generation_alpha.characters.Villain;
import com.generation_alpha.client.Display;
import com.generation_alpha.client.GameBoard;
import com.generation_alpha.client.JsonParser;
import com.generation_alpha.client.TextParser;
import com.generation_alpha.items.HealthBoost;
import com.generation_alpha.items.Item;
import com.generation_alpha.items.PowerItem;
import com.generation_alpha.items.StrengthBoost;
import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePlay {
    private Territory territory;
    private List<Map<String, Object>> locationList;

    /**
     * method creates a Territory to include all Structures, Characters, and Items
     *
     * @param name
     * @return Territory
     */
    public Territory getTerritory(String name) {
        Map<String, Object> map = JsonParser.parseJson("resources/JsonObjects/locations.json");
        List<Map<String, Object>> territories = (List) map.get("territories");
        territory = new Territory();
        for (Map<String, Object> territoryMap : territories) {
            if (territoryMap.get("name").equals(name)) {
                territory.setName(territoryMap.get("name").toString());
                territory.setDescription(territoryMap.get("description").toString());
                territory.setImage(territoryMap.get("image").toString());
                territory.setMap((Map<String, Object>) territoryMap.get("directions"));
                locationList = (List) territoryMap.get("locations");
                territory.setLocations(locationList);
                territory.setStart(getStart(territoryMap.get("start").toString()));
            }
        }
        return territory;
    }

    /**
     * overload method to create a Territory from saved game (json file)
     *
     * @param isSavedGame
     * @return Territory
     */
    public Territory getTerritory(boolean isSavedGame) {
        Map<String, Object> map = JsonParser.parseJson("savedGames/savedGame.json");
        Map<String, Object> territoryMap = (Map<String, Object>) map.get("territory");
        territory = getTerritory(territoryMap.get("name").toString());
        territory.setName(territoryMap.get("name").toString());
        territory.setDescription(territoryMap.get("description").toString());
        territory.setImage(territoryMap.get("image").toString());
        try {
            territory.setMap((Map<String, Object>) territoryMap.get("map"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        locationList = (List) territoryMap.get("locations");
        territory.setLocations(locationList);
        territory.setStart(getStart(territoryMap.get("start").toString()));

        return territory;
    }

    /**
     * getTerritory() helper method to build Structure that the Gyro Character begins in
     *
     * @param name
     * @return Structure
     */
    private Structure getStart(String name) {
        Building building = new Building();
        for (Map<String, Object> locationMap : locationList) {
            if (locationMap.get("name").equals(name)) {
                building.setName(locationMap.get("name").toString());
                building.setDescription(locationMap.get("description").toString());
                building.setImage(locationMap.get("image").toString());
                building.setCharacter(getCharacters(locationMap.get("character").toString()));
                building.setItem(getItems(locationMap.get("item").toString()));
            }
        }
        return building;
    }

    /**
     * getTerritory() helper method to build individual Characters encountered by the Gyro
     * character
     *
     * @param name
     * @return
     */
    public Character getCharacters(String name) {
        Map<String, Object> map = JsonParser.parseJson("resources/JsonObjects/characters.json");
        List<Map<String, Object>> characters = (List) map.get("characters");

        for (Map<String, Object> characterMap : characters) {
            if (characterMap.get("name").equals(name) && characterMap.get("type").equals("NPC")) {
                NPC npc = new NPC(name);
                npc.setQuote((List<String>) characterMap.get("quote"));
                npc.setImage(characterMap.get("image").toString());
                Map<String, Object> locationMap = (Map<String, Object>) characterMap.get("location");
                npc.setLocation(new Building(locationMap.get("structure").toString()));
                return npc;
            } else if (characterMap.get("name").equals(name) && characterMap.get("type").equals("Villain")) {
                Villain villain = new Villain(name);
                villain.setQuote((List<String>) characterMap.get("quote"));
                villain.setImage(characterMap.get("image").toString());
                villain.setStrength(Integer.parseInt(characterMap.get("strength").toString()));
                villain.setHealth(Integer.parseInt(characterMap.get("health").toString()));
                Map<String, Object> locationMap = (Map<String, Object>) characterMap.get("location");
                villain.setLocation(new Dojo(locationMap.get("structure").toString()));
                List<String> powers = (List<String>) characterMap.get("powers");
                villain.setPowers(getPowers(powers));
                return villain;
            }
        }
        return null;
    }

    /**
     * getTerritory() helper method to build the PowerItems for Villain Characters
     *
     * @param powers
     * @return List<PowerItem>
     */
    public List<PowerItem> getPowers(List<String> powers) {
        List<PowerItem> powerItems = new ArrayList<>();
        Map<String, Object> map = JsonParser.parseJson("resources/JsonObjects/items.json");
        List<Map<String, Object>> items = (List) map.get("items");

        // TODO: look at streams to make this better
        for (Map<String, Object> itemMap : items) {
            for (String power : powers) {
                if (itemMap.get("name").equals(power) && itemMap.get("type").equals("Power")) {
                    PowerItem powerItem = new PowerItem(power);
                    powerItem.setDescription(itemMap.get("description").toString());
                    powerItem.setImage(itemMap.get("image").toString());
                    powerItem.setLocation(new Dojo(itemMap.get("location").toString()));
                    powerItem.setCombatMultiplier(Integer.parseInt(itemMap.get("modifier").toString()));
                    powerItems.add(powerItem);
                }
            }
        }
        return powerItems;
    }

    /**
     * getTerritory() helper method to build Items that the Gyro Character will encounter
     * in different Structures
     *
     * @param name
     * @return Item
     */
    public Item getItems(String name) {
        Map<String, Object> map = JsonParser.parseJson("resources/JsonObjects/items.json");
        List<Map<String, Object>> items = (List) map.get("items");

        for (Map<String, Object> itemMap : items) {
            if (itemMap.get("name").equals(name) && itemMap.get("type").equals("Strength")) {
                StrengthBoost strengthBoost = new StrengthBoost(name);
                strengthBoost.setDescription(itemMap.get("description").toString());
                strengthBoost.setImage(itemMap.get("image").toString());
                Map<String, Object> locMap = (Map<String, Object>) itemMap.get("location");
                strengthBoost.setLocation(new Building(locMap.get("structure").toString()));
                strengthBoost.setStrengthBoost(Integer.parseInt(itemMap.get("modifier").toString()));
                return strengthBoost;
            } else if (itemMap.get("name").equals(name) && itemMap.get("type").equals("Health")) {
                HealthBoost healthBoost = new HealthBoost(name);
                healthBoost.setDescription(itemMap.get("description").toString());
                healthBoost.setImage(itemMap.get("image").toString());
                Map<String, Object> locMap = (Map<String, Object>) itemMap.get("location");
                healthBoost.setLocation(new Building(locMap.get("structure").toString()));
                healthBoost.setHealthBoost(Integer.parseInt(itemMap.get("modifier").toString()));
                return healthBoost;
            } else if (itemMap.get("name").equals(name) && itemMap.get("type").equals("Power")) {
                PowerItem powerItem = new PowerItem(name);
                powerItem.setDescription(itemMap.get("description").toString());
                powerItem.setImage(itemMap.get("image").toString());
                Map<String, Object> locMap = (Map<String, Object>) itemMap.get("location");
                powerItem.setLocation(new Dojo(locMap.get("structure").toString()));
                powerItem.setCombatMultiplier(Integer.parseInt(itemMap.get("modifier").toString()));
                return powerItem;
            }
        }
        return null;
    }

    /**
     * helper method to convert the Jackson json converted Map into a usable Map
     * of Direction key and String value
     *
     * @param objectMap
     * @return Map<Direction, String>
     */
    public static Map<Direction, String> convertMap(Map<String, Object> objectMap) {
        Map<Direction, String> newMap = new HashMap<>();
        Direction[] directions = Direction.values();
        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            for (Direction direction : directions) {
                if (entry.getKey().toUpperCase().equals(direction.toString())) {

                    newMap.put(direction, entry.getValue().toString());
                }
            }
        }
        return newMap;
    }

    /**
     * helper method to move Gyro Character from one Structure to another
     *
     * @param gyro
     * @param name
     * @param locations
     * @return int
     */
    public int moveGyro(Gyro gyro, String name, List<Structure> locations) {
        int index = 0;
        for (Structure location : locations) {
            if (location.getName().equals(name)) {
                gyro.setLocation(location);
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * helper method for Gyro Character to look at a Structure and 'see' its image
     * and description
     *
     * @param name
     * @param locations
     * @return String
     */
    public String getLocation(String name, List<Structure> locations) {
        for (Structure location : locations) {
            if (location.getName().equals(name)) {
                return location.getImage() + " & " + location.getDescription();
            }
        }
        territory = getTerritory(name);
        return territory.getImage() + " & " + territory.getDescription();
    }

    /**
     * helper method to remove a 'picked up' Item from a Structure
     *
     * @param map
     * @param location
     * @return Structure
     */
    public Structure removeItemFromStructure(Map<String, Object> map, Structure location) {
        if (location instanceof Building) {
            Building structure = new Building(location.getName());
            structure.setDescription(location.getDescription());
            structure.setImage(location.getImage());
            Map<String, Object> newMap = new HashMap<>();
            for (Map.Entry<Direction, String> entry : location.getMap().entrySet()) {
                newMap.put(entry.getKey().toString(), (Object) entry.getValue());
            }
            structure.setMap(newMap);
            structure.setCharacter(location.getCharacter());
            return structure;
        } else if (location instanceof Dojo) {
            Dojo structure = new Dojo(location.getName());
            structure.setDescription(location.getDescription());
            structure.setImage(location.getImage());
            Map<String, Object> newMap = new HashMap<>();
            for (Map.Entry<Direction, String> entry : location.getMap().entrySet()) {
                newMap.put(entry.getKey().toString(), (Object) entry.getValue());
            }
            structure.setMap(newMap);
            structure.setCharacter(location.getCharacter());
            return structure;
        }

        return new Building();
    }

    /**
     * helper method to remove a 'killed' Villain Character from a Structure
     *
     * @param map
     * @param location
     * @return Structure
     */
    public Structure removeKilledVillainFromStructure(Map<String, Object> map, Structure location) {
        Dojo structure = new Dojo(location.getName());
        structure.setDescription(location.getDescription());
        structure.setImage(location.getImage());
        Map<String, Object> newMap = new HashMap<>();
        for (Map.Entry<Direction, String> entry : location.getMap().entrySet()) {
            newMap.put(entry.getKey().toString(), (Object) entry.getValue());
        }
        structure.setMap(newMap);
        structure.setItem(location.getItem());
        return structure;
    }

    /**
     * helper method to set Direction Enum based on String input
     *
     * @param input
     * @return Direction
     */
    public Direction getDirection(String input) {
        Direction direction = null;
        switch (input) {
            case "north":
                direction = Direction.NORTH;
                break;
            case "east":
                direction = Direction.EAST;
                break;
            case "west":
                direction = Direction.WEST;
                break;
            case "south":
                direction = Direction.SOUTH;
                break;
            default:
                // TODO: make a method to print errors on screen
                // "Error: You can only look north, south, east, and west.";
                break;
        }
        return direction;
    }
}
