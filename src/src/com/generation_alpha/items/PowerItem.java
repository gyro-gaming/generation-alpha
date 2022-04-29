package com.generation_alpha.items;

import com.generation_alpha.locations.Location;

public class PowerItem implements Item {
    private String name;
    private String description;
    private String image;
    private Location location;
    private int combatMultiplier;

    public PowerItem() {}

    public PowerItem(String name) {
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
    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    public void setCombatMultiplier(int combatMultiplier) {
        this.combatMultiplier = combatMultiplier;
    }

    public int getCombatMultiplier() {
        return combatMultiplier;
    }
}
