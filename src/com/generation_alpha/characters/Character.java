package com.generation_alpha.characters;

import com.generation_alpha.locations.Location;

import java.util.List;

public interface Character {
    void setName(String a);
    String getName();
    void setQuote(List<String> b);
    public List<String> says();
    void setImage(String c);
    public String getImage();
    void setLocation(Location d);
    public Location getLocation();
}
