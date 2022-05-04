package com.generation_alpha.locations;

import java.util.Map;

public interface Location {
    void setName(String a);
    String getName();
    void setDescription(String b);
    String getDescription();
    void setImage(String c);
    String getImage();
    void setMap(Map<String, Object> d);
    Map<Direction, String> getMap();
}