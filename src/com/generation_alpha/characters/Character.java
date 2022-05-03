package com.generation_alpha.characters;

import com.generation_alpha.locations.Location;
import com.generation_alpha.locations.Structure;

import java.util.List;

public interface Character {
    void setName(String a);
    String getName();
    void setQuote(List<String> b);
    public List<String> says();
    void setImage(String c);
    public String getImage();
    void setLocation(Structure d);
    public Location getLocation();
}
