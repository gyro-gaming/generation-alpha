package com.generation_alpha.items;

import com.generation_alpha.locations.Location;

public interface Item {
    void setName(String b);
    String getName();
    void setDescription(String c);
    String getDescription();
    void setImage(String d);
    String getImage();
    void setLocation(Location e);
    Location getLocation();
}
