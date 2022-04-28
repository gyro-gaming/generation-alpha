package com.generation_alpha.characters;

import com.generation_alpha.powers.Power;

import java.util.List;

abstract class Fighter implements Character {
    private int strength;
    private int health;
    private List<Power> powers;

    public void setStrength(int Strength) {
        this.strength = strength;
    }

    public int getStrength() {
        return strength;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }

    public List<Power> getPowers() {
        return powers;
    }
}
