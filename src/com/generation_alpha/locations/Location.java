package com.generation_alpha.locations;

import java.util.Map;

interface Location {
    public String getName();
    public String getDescription();
    public Map<Direction, String> getMap();
}