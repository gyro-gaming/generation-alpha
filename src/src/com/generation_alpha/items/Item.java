package com.generation_alpha.items;

import com.generation_alpha.locations.Location;

public interface Item {
    void setName(String a);
    String getName();
    void setDescription(String b);
    String getDescription();
    void setImage(String c);
    String getImage();
    void setLocation(Location d);
    Location getLocation();
}
