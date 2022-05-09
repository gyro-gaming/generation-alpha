package com.generation_alpha.locations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Location {

    void setType(String a);
    String getType();
    void setName(String b);
    String getName();
    void setDescription(String c);
    String getDescription();
    void setImage(String d);
    String getImage();
    void setMap(Map<String, Object> e);
    Map<Direction, String> getMap();
}