package com.generation_alpha.characters;

import com.generation_alpha.items.PowerItem;
import com.generation_alpha.items.Item;
import com.generation_alpha.locations.Structure;

import java.util.List;

public class Gyro extends Fighter {
    private String name;
    private List<String> quote;
    private String image;
    private Structure location;
    private int strength;
    private int health;
    private List<PowerItem> powers;
    private List<Item> items;

    public Gyro() {}

    public Gyro(String name) {
        setName(name);
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
    public void setQuote(List<String> quote) {
        this.quote = quote;
    }

    @Override
    public List<String> says() {
        return quote;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String getImage() {
        return image;
    }


    public void setLocation(Structure location) {
        this.location = location;
    }

    @Override
    public Structure getLocation() {
        return location;
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setPowers(List<PowerItem> powerItems) {
        this.powers = powerItems;
    }

    public void addPower(PowerItem powerItem) {
        powers.add(powerItem);
    }

    @Override
    public void usePower(PowerItem powerItem) {
        powers.remove(powerItem);
    }

    @Override
    public List<PowerItem> getPowers() {
        return powers;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void useItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }
}