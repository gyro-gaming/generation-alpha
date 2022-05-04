package com.generation_alpha.locations;

import com.generation_alpha.characters.Character;
import com.generation_alpha.items.Item;

import java.util.Map;

class Dojo extends Structure {
    private String name;
    private String description;
    private String image;
    private Map<Direction, String> map;
    private Character villain;
    private Item power;

    public Dojo() {}

    public Dojo(String name) {
        setName(name);
    }

    public Dojo(String name, String description, String image, Map<String, Object> objectMap,
                    Character villain) {
        setName(name);
        setDescription(description);
        setImage(image);
        setMap(objectMap);
        setCharacter(villain);
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
        this.map = GamePlay.convertMap(objectMap);
    }

    @Override
    public Map<Direction, String> getMap() {
        return map;
    }

    @Override
    public void setCharacter(Character villain) {
        this.villain = villain;
    }

    @Override
    public Character getCharacter() {
        return villain;
    }

    @Override
    public void setItem(Item power) {
        this.power = power;
    }

    @Override
    public Item getItem() {
        return power;
    }
}