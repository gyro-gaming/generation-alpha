package com.generation_alpha.characters;

import com.generation_alpha.locations.Location;

class NPC implements Character {
    private String name;
    private String quote;
    private String image;
    private Location location;

    public NPC() {

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
}