package com.generation_alpha.locations;

import com.generation_alpha.characters.Character;
import com.generation_alpha.items.Item;

import java.util.Map;

public abstract class Structure implements Location {
    public abstract void setMap(Map<String, Object> a);
    public abstract Map<Direction, String> getMap();
    public abstract void setCharacter(Character b);
    public abstract Character getCharacter();
    public abstract void setItem(Item c);
    public abstract Item getItem();
}
