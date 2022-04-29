package com.generation_alpha.characters;

import com.generation_alpha.locations.Location;

import java.util.List;

public class NPC implements Character {
    private String name;
    private List<String> quote;
    private String image;
    private Location location;

    public NPC() {}

    public NPC(String name) {
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

    @Override
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return location;
    }
}