package com.generation_alpha.characters;

import com.generation_alpha.locations.Location;

public class NPC implements Character {
    private String name;
    private String quote;
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
}