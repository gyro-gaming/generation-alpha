package com.generation_alpha.locations;

import com.generation_alpha.characters.Character;
import com.generation_alpha.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Building extends Structure {
    private String name;
    private String description;
    private String image;
    private Map<Direction, String> map;
    private Character npc;
    private Item item;

    public Building() {}

    public Building(String name) {
        setName(name);
    }

    public Building(String name, String description, String image, Map<String, Object> objectMap,
                    Character npc, Item item) {
        setName(name);
        setDescription(description);
        setImage(image);
        setMap(objectMap);
        setCharacter(npc);
        setItem(item);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setMap(Map<String, Object> objectMap) {
        this.map = convertMap(objectMap);
    }

    @Override
    public Map<Direction, String> getMap() {
        return map;
    }

    @Override
    public void setCharacter(Character npc) {
        this.npc = npc;
    }

    @Override
    public Character getCharacter() {
        return npc;
    }

    @Override
    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public Item getItem() {
        return item;
    }

    private Map<Direction, String> convertMap(Map<String, Object> objectMap) {
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
}
