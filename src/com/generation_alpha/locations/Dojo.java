package com.generation_alpha.locations;

import com.generation_alpha.characters.Villain;

import java.util.HashMap;
import java.util.Map;

public class Dojo extends Location {
    private String name;
    private String description;
    private String image;
    private Map<Direction, String> map;
    private Villain villain;

    public Dojo() {}

    public Dojo(String name) {
        setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setMap(Map<String, Object> objectMap) {
        this.map = convertMap(objectMap);
    }

    public Map<Direction, String> getMap() {
        return map;
    }

    public void setCharacter(Villain villain) {
        this.villain = villain;
    }

    public Villain getCharacter() {
        return villain;
    }

    private Map<Direction, String> convertMap(Map<String, Object> objectMap) {
        Map<Direction, String> newMap = new HashMap<>();
        Direction[] directions = Direction.values();
        for (Map.Entry<String, Object> entry : objectMap.entrySet()) {
            for (Direction direction : directions) {
                if (entry.getKey().toUpperCase().equals(direction)) {
                    newMap.put(direction, entry.getValue().toString());
                }
            }
        }
        return newMap;
    }
}