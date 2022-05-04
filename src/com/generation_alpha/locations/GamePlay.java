package com.generation_alpha.locations;

import com.generation_alpha.characters.Character;
import com.generation_alpha.characters.Gyro;
import com.generation_alpha.characters.NPC;
import com.generation_alpha.characters.Villain;
import com.generation_alpha.items.HealthBoost;
import com.generation_alpha.items.Item;
import com.generation_alpha.items.PowerItem;
import com.generation_alpha.items.StrengthBoost;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePlay {
    private Territory territory;
    private List<Map<String, Object>> locationList;


    public Gyro getGyro(String name) {
        Gyro gyro = new Gyro(name);
        gyro.setStrength(10);
        gyro.setHealth(100);
        gyro.setLocation(territory.getStart());
        gyro.setItems(new ArrayList<>());
        gyro.setPowers(new ArrayList<>());
        return gyro;
    }

    /**
     * method creates a Territory object
     * @param name
     * @return
     */
    public Territory getTerritory(String name) {
        Map<String, Object> map = JsonParser.parseJson("src/JsonObjects/locations.json");
        List<Map<String, Object>> territories = (List) map.get("territories");
        String start = "";
        territory = new Territory();
        for (Map<String, Object> territoryMap : territories) {
            if (territoryMap.get("name").equals(name)) {
                territory.setName(territoryMap.get("name").toString());
                territory.setDescription(territoryMap.get("description").toString());
                territory.setImage(territoryMap.get("image").toString());
                territory.setMap((Map<String, Object>)territoryMap.get("directions"));
                locationList = (List) territoryMap.get("locations");
                territory.setLocations(locationList);
                territory.setStart(getStart(territoryMap.get("start").toString()));
            }
        }
        return territory;
    }

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

    public Character getCharacters(String name) {
        Map<String, Object> map = JsonParser.parseJson("src/JsonObjects/characters.json");
        List<Map<String, Object>> characters = (List) map.get("characters");

        for (Map<String, Object> characterMap : characters) {
            if (characterMap.get("name").equals(name) && characterMap.get("type").equals("NPC")) {
                NPC npc = new NPC(name);
                npc.setQuote((List<String>)characterMap.get("quote"));
                npc.setImage(characterMap.get("image").toString());
                Map<String, Object> locationMap = (Map<String, Object>) characterMap.get("location");
                npc.setLocation(new Building(locationMap.get("structure").toString()));
                return npc;
            } else if (characterMap.get("name").equals(name) && characterMap.get("type").equals("Villain")) {
                Villain villain = new Villain(name);
                villain.setQuote((List<String>)characterMap.get("quote"));
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

    public List<PowerItem> getPowers(List<String> powers) {
        List<PowerItem> powerItems = new ArrayList<>();
        Map<String, Object> map = JsonParser.parseJson("src/JsonObjects/items.json");
        List<Map<String, Object>> items = (List) map.get("items");

        // TODO: look at streams to make this better
        for (Map<String, Object> itemMap : items) {
            for (String power : powers) {
                if (itemMap.get("name").equals(power) && itemMap.get("type").equals("PowerItem")) {
                    PowerItem powerItem = new PowerItem(power);
                    powerItem.setDescription(itemMap.get("description").toString());
                    powerItem.setImage(itemMap.get("image").toString());
                    powerItem.setLocation(new Dojo(itemMap.get("location").toString()));
                    powerItems.add(powerItem);
                }
            }
        }
        return powerItems;
    }

    public Item getItems(String name) {
        Map<String, Object> map = JsonParser.parseJson("src/JsonObjects/items.json");
        List<Map<String, Object>> items = (List) map.get("items");

        for (Map<String, Object> itemMap : items) {
            if (itemMap.get("name").equals(name) && itemMap.get("type").equals("Strength")) {
                StrengthBoost strengthBoost = new StrengthBoost(name);
                strengthBoost.setDescription(itemMap.get("description").toString());
                strengthBoost.setImage(itemMap.get("image").toString());
                Map<String, Object> locMap = (Map<String, Object>)itemMap.get("location");
                strengthBoost.setLocation(new Building(locMap.get("structure").toString()));
                strengthBoost.setStrengthBoost(Integer.parseInt(itemMap.get("modifier").toString()));
                return strengthBoost;
            } else if (itemMap.get("name").equals(name) && itemMap.get("type").equals("Health")) {
                HealthBoost healthBoost = new HealthBoost(name);
                healthBoost.setDescription(itemMap.get("description").toString());
                healthBoost.setImage(itemMap.get("image").toString());
                Map<String, Object> locMap = (Map<String, Object>)itemMap.get("location");
                healthBoost.setLocation(new Building(locMap.get("structure").toString()));
                healthBoost.setHealthBoost(Integer.parseInt(itemMap.get("modifier").toString()));
                return healthBoost;
            } else if (itemMap.get("name").equals(name) && itemMap.get("type").equals("Power")) {
                PowerItem powerItem = new PowerItem(name);
                powerItem.setDescription(itemMap.get("description").toString());
                powerItem.setImage(itemMap.get("image").toString());
                Map<String, Object> locMap = (Map<String, Object>)itemMap.get("location");
                powerItem.setLocation(new Dojo(locMap.get("structure").toString()));
                powerItem.setCombatMultiplier(Integer.parseInt(itemMap.get("modifier").toString()));
                return powerItem;
            }
        }
        return null;
    }

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

    public String getLocation(String name, List<Structure> locations) {
        for (Structure location : locations) {
            if (location.getName().equals(name)) {
                return location.getImage() + " & " + location.getDescription();
            }
        }
        territory = getTerritory(name);
            return territory.getImage() + " & " + territory.getDescription();
    }

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
