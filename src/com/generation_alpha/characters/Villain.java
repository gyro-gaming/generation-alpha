package com.generation_alpha.characters;

import com.generation_alpha.items.PowerItem;
import com.generation_alpha.locations.Location;

import java.util.List;

public class Villain extends Fighter {
    private String name;
    private String quote;
    private String image;
    private Location location;
    private int strength;
    private int health;
    private List<PowerItem> powers;

    public Villain() {}

    public Villain(String name) {
        this.name = name;
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
    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public String says() {
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

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Location getLocation() {
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

    @Override
    public void usePowers(PowerItem powerItem) {
        powers.remove(powerItem);
    }

    @Override
    public List<PowerItem> getPowers() {
        return powers;
    }
}
