package com.generation_alpha.characters;

import com.generation_alpha.locations.Location;

public interface Character {
    public String getName();
    public String says();
    public String getImage();
    public Location getLocation();
}
