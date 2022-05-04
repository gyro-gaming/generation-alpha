package com.generation_alpha.characters;

import com.generation_alpha.items.PowerItem;

import java.util.List;

public abstract class Fighter implements Character {
    public abstract void setStrength(int a);
    public abstract int getStrength();
    public abstract void setHealth(int b);
    public abstract int getHealth();
    public abstract void setPowers(List<PowerItem> c);
    public abstract void usePower(PowerItem d);
    public abstract List<PowerItem> getPowers();
}
