package com.generation_alpha.client;

import com.generation_alpha.characters.Character;
import com.generation_alpha.characters.NPC;
import com.generation_alpha.characters.Villain;
import com.generation_alpha.items.HealthBoost;
import com.generation_alpha.items.Item;
import com.generation_alpha.items.PowerItem;
import com.generation_alpha.items.StrengthBoost;
import com.generation_alpha.locations.*;

import java.util.List;
import java.util.Map;

class GamePlay {
    private Territory territory;
    private Structure building;
    private Structure dojo;
    private Character villain;
    private Character npc;
    private Item strengthBoost;
    private Item healthBoost;
    private Item powerItem;

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
                territory.setLocations((List) territoryMap.get("locations"));
                territory.setStart(getStructure(territoryMap.get("start").toString()));
            }
        }
        return territory;
    }

    public Structure getStructure(String name) {
        List<Location> locations = territory.getLocations();

        for (Location location : locations) {
           if (location.getName().equals(name)) {
               return (Structure)location;
           }
        }
        return null;
    }

    public Character getCharacters(String name) {
        Map<String, Object> map = JsonParser.parseJson("JsonObjects/characters.json");

        List<Map<String, Object>> characters = (List) map.get("characters");

        for (Map<String, Object> characterMap : characters) {
            if (characterMap.get("name").equals(name) && characterMap.get("type").equals("NPC")) {
                npc = new NPC(name);
                npc.setQuote(characterMap.get("quote").toString());
                npc.setImage(characterMap.get("image").toString());
                return npc;
            } else if (characterMap.get("name").equals(name) && characterMap.get("type").equals("Villain")) {
                villain = new Villain(name);
                villain.setQuote(characterMap.get("quote").toString());
                villain.setImage(characterMap.get("image").toString());
                return villain;
            }
        }
        return null;
    }

    public Item getItems(String name) {
        Map<String, Object> map = JsonParser.parseJson("JsonObjects/items.json");

        List<Map<String, Object>> items = (List) map.get("items");

        for (Map<String, Object> itemMap : items) {
            if (itemMap.get("name").equals(name) && itemMap.get("type").equals("Strength")) {
                strengthBoost = new StrengthBoost(name);
                strengthBoost.setDescription(itemMap.get("description").toString());
                strengthBoost.setImage(itemMap.get("image").toString());
                strengthBoost.setLocation(new Building(itemMap.get("location").toString()));
                return strengthBoost;
            } else if (itemMap.get("name").equals(name) && itemMap.get("type").equals("Health")) {
                healthBoost = new HealthBoost(name);
                healthBoost.setDescription(itemMap.get("description").toString());
                healthBoost.setImage(itemMap.get("image").toString());
                healthBoost.setLocation(new Building(itemMap.get("location").toString()));
                return healthBoost;
            } else if (itemMap.get("name").equals(name) && itemMap.get("type").equals("PowerItem")) {
                powerItem = new PowerItem(name);
                powerItem.setDescription(itemMap.get("description").toString());
                powerItem.setImage(itemMap.get("image").toString());
                powerItem.setLocation(new Dojo(itemMap.get("location").toString()));
                return powerItem;
            }
        }
        return null;
    }
}
