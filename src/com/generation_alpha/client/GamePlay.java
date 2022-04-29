package com.generation_alpha.client;

import com.generation_alpha.characters.Character;
import com.generation_alpha.characters.NPC;
import com.generation_alpha.characters.Villain;
import com.generation_alpha.items.HealthBoost;
import com.generation_alpha.items.Item;
import com.generation_alpha.items.PowerItem;
import com.generation_alpha.items.StrengthBoost;
import com.generation_alpha.locations.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GamePlay {
    private Territory territory;
    private List<Map<String, Object>> locationList;

    public Territory getTerritory(String name) {
        Map<String, Object> map = JsonParser.parseJson("JsonObjects/locations.json");
        List<Map<String, Object>> territories = (List) map.get("territories");
        String start = "";
        territory = new Territory();

        for (Map<String, Object> territoryMap : territories) {
            if (territoryMap.get("name").equals(name)) {
                territory.setName(territoryMap.get("name").toString());
                territory.setDescription(territoryMap.get("description").toString());
                territory.setImage(territoryMap.get("image").toString());
                locationList = (List) territoryMap.get("locations");
                territory.setLocations(locationList);
                territory.setStart(getStart(territoryMap.get("start").toString()));
            }
        }
        return territory;
    }

    public List<Structure> convertToLocationList(List<Map<String, Object>> locations) {
        List<Structure>locationList = new ArrayList<>();

        for (Map<String, Object> locationMap : locations) {
            if (locationMap.get("type").equals("Building")) {
                Building building = new Building(
                        locationMap.get("name").toString(),
                        locationMap.get("description").toString(),
                        locationMap.get("image").toString(),
                        (Map<String, Object>) locationMap.get("directions"),
                        getCharacters(locationMap.get("character").toString()),
                        getItems(locationMap.get("item").toString())
                );
                locationList.add(building);
            } else if (locationMap.get("type").equals("Dojo")) {
                Dojo dojo = new Dojo(
                        locationMap.get("name").toString(),
                        locationMap.get("description").toString(),
                        locationMap.get("image").toString(),
                        (Map<String, Object>) locationMap.get("directions"),
                        getCharacters(locationMap.get("character").toString())
                );
                locationList.add(dojo);
            }
        }
        return locationList;
    }

    private Character getCharacters(String name) {
        Map<String, Object> map = JsonParser.parseJson("JsonObjects/characters.json");
        List<Map<String, Object>> characters = (List) map.get("characters");

        for (Map<String, Object> characterMap : characters) {
            if (characterMap.get("name").equals(name) && characterMap.get("type").equals("NPC")) {
                NPC npc = new NPC(name);
                npc.setQuote(characterMap.get("quote").toString());
                npc.setImage(characterMap.get("image").toString());
                Map<String, Object> locationMap = (Map<String, Object>) characterMap.get("location");
                npc.setLocation(new Building(locationMap.get("structure").toString()));
                return npc;
            } else if (characterMap.get("name").equals(name) && characterMap.get("type").equals("Villain")) {
                Villain villain = new Villain(name);
                villain.setQuote(characterMap.get("quote").toString());
                villain.setImage(characterMap.get("image").toString());
                Map<String, Object> locationMap = (Map<String, Object>) characterMap.get("location");
                villain.setLocation(new Dojo(locationMap.get("structure").toString()));
                List<String> powers = (List<String>) characterMap.get("powers");
                villain.setPowers(getPowers(powers));
                return villain;
            }
        }
        return null;
    }

    private List<PowerItem> getPowers(List<String> powers) {
        List<PowerItem> powerItems = new ArrayList<>();
        Map<String, Object> map = JsonParser.parseJson("JsonObjects/items.json");
        List<Map<String, Object>> items = (List) map.get("items");

        // look at streams to make this better
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

    private Item getItems(String name) {
        Map<String, Object> map = JsonParser.parseJson("JsonObjects/items.json");
        List<Map<String, Object>> items = (List) map.get("items");

        for (Map<String, Object> itemMap : items) {
            if (itemMap.get("name").equals(name) && itemMap.get("type").equals("Strength")) {
                StrengthBoost strengthBoost = new StrengthBoost(name);
                strengthBoost.setDescription(itemMap.get("description").toString());
                strengthBoost.setImage(itemMap.get("image").toString());
                strengthBoost.setLocation(new Building(itemMap.get("location").toString()));
                strengthBoost.setStrengthBoost(Integer.parseInt(itemMap.get("modifier").toString()));
                return strengthBoost;
            } else if (itemMap.get("name").equals(name) && itemMap.get("type").equals("Health")) {
                HealthBoost healthBoost = new HealthBoost(name);
                healthBoost.setDescription(itemMap.get("description").toString());
                healthBoost.setImage(itemMap.get("image").toString());
                healthBoost.setLocation(new Building(itemMap.get("location").toString()));
                healthBoost.setHealthBoost(Integer.parseInt(itemMap.get("modifier").toString()));
                return healthBoost;
            } else if (itemMap.get("name").equals(name) && itemMap.get("type").equals("Power")) {
                PowerItem powerItem = new PowerItem(name);
                powerItem.setDescription(itemMap.get("description").toString());
                powerItem.setImage(itemMap.get("image").toString());
                powerItem.setLocation(new Dojo(itemMap.get("location").toString()));
                powerItem.setCombatMultiplier(Integer.parseInt(itemMap.get("modifier").toString()));
                return powerItem;
            }
        }
        return null;
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
}
