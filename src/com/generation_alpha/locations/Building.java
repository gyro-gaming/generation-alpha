package com.generation_alpha.locations;

import com.generation_alpha.characters.Character;
import com.generation_alpha.items.Item;

import java.util.Map;

class Building extends Structure {
    private String type;
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

    public Building(String type, String name, String description, String image, Map<String, Object> objectMap,
                    Character npc, Item item) {
        setType(type);
        setName(name);
        setDescription(description);
        setImage(image);
        try {
            setMap(objectMap);
        } catch (Exception e) {
            System.out.println("No directions available for " + getName());
        }
        setCharacter(npc);
        setItem(item);
    }

    // getters and setters
    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
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
    // end getters and setters
}
