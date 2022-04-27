package com.generation_alpha.characters;

import com.generation_alpha.locations.Location;
import com.generation_alpha.powers.Power;
import com.generation_alpha.items.Item;

import java.util.List;

class Gyro extends Fighter {
    private String name;
    private String quote;
    private String image;
    private Location location;
    private int strength;
    private int health;
    private List<Power> powers;
    private List<Item> items;

    public Gyro() {

    }
    public String getName() {
        return name;
    }

    public String says() {
        return quote;
    }

    public String getImage() {
        return image;
    }

    public Location getLocation() {
        return location;
    }

    public void setStrength(int Strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }

    public void addPowers(Power power) {
        powers.add(power);
    }

    public void usePowers(Power power) {
        powers.remove(power);
    }

    public List<Power> getPowers() {
        return powers;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItems(Item item) {
        items.add(item);
    }

    public void useItems(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }
}
