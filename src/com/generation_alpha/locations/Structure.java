package com.generation_alpha.locations;

import com.generation_alpha.characters.Character;
import com.generation_alpha.items.Item;

import java.util.Map;

public abstract class Structure extends Territory {
    public abstract void setCharacter(Character b);
    public abstract Character getCharacter();
    public abstract void setItem(Item c);
    public abstract Item getItem();
}
