package com.generation_alpha.client;

import com.generation_alpha.characters.Character;
import com.generation_alpha.characters.NPC;
import com.generation_alpha.characters.Villain;
import com.generation_alpha.items.Item;
import com.generation_alpha.locations.Dojo;
import com.generation_alpha.locations.Structure;
import com.generation_alpha.locations.Territory;

import java.util.List;
import java.util.Map;

class GamePlay {
    private Territory territory;
    private Structure structure;
    private Dojo dojo;
    private Villain villain;
    private NPC npc;
    private Item item;

    public void getTerritory(String name) {
        Map<String, Object> map = JsonParser.parseJson("JsonObjects/locations.json");

        List<Map<String, Object>> territories = (List) map.get("territories");
        String start = "";
        territory = new Territory();

        for (Map<String, Object> territoryMap : territories) {
            if (territoryMap.get("name").equals(name)) {
                territory.setName(territoryMap.get("name").toString());
                territory.setDescription(territoryMap.get("description").toString());
                territory.setImage(territoryMap.get("image").toString());
                structure = new Structure(territoryMap.get("start").toString());
                territory.setStart(structure);
                territory.setLocations((List) territoryMap.get("locations"));
            }
        }
        System.out.println(territory.getLocations());
    }

    public void getStructure(String name) {
        List<Map<String, Object>> locations = territory.getLocations();
        String start = structure.getName();

        for (Map<String, Object> location : locations) {
            if (location.get("name").equals(start) && location.get("type").equals("structure")) {
                structure.setDescription(location.get("description").toString());
                structure.setImage(location.get("image").toString());
                structure.setMap((Map<String, Object>) location.get("directions"));
                structure.setCharacter(new NPC(location.get("character").toString()));

                // need to add items

            } else if (location.get("name").equals(start) && location.get("type").equals("dojo")) {
                dojo = new Dojo(start);
                dojo.setDescription(location.get("description").toString());
                dojo.setImage(location.get("image").toString());
                dojo.setMap((Map<String, Object>) location.get("directions"));
                dojo.setCharacter(new Villain(location.get("character").toString()));

                // need to add powers

            }
        }
    }

    public void getCharacters(String name) {
        Map<String, Object> map = JsonParser.parseJson("JsonObjects/locations.json");

        List<Map<String, Object>> characters = (List) map.get("characters");

        for (Map<String, Object> characterMap : characters) {
            if (characterMap.get("name").equals(name) && characterMap.get("type").equals("NPC")) {
                npc = new NPC();
                npc.setName(characterMap.get("name").toString());
                npc.setQuote(characterMap.get("quote").toString());
                npc.setImage(characterMap.get("description").toString());

            } else if (characterMap.get("name").equals(name) && characterMap.get("type").equals("Villain")) {
                villain = new Villain();
                villain.setName(characterMap.get("name").toString());
                villain.setQuote(characterMap.get("quote").toString());
                villain.setImage(characterMap.get("description").toString());
            }
        }
        System.out.println(territory.getLocations());
    }
}
