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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String says() {
        return quote;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public Location getLocation() {
        return location;
    }
}