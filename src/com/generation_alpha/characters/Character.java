package com.generation_alpha.characters;

import com.generation_alpha.locations.Location;

public interface Character {
    void setName(String a);
    String getName();
    void setQuote(String b);
    public String says();
    void setImage(String c);
    public String getImage();
    void setLocation(Location d);
    public Location getLocation();
}
