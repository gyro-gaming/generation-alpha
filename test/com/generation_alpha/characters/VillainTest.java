package com.generation_alpha.characters;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VillainTest {
    Fighter villain;

    @Before
    public void init() {
        villain = new Villain();
    }

    @Test
    public void setName() {
        villain.setName("Clyde");
        assertEquals(villain.getName(), "Clyde");
    }

    @Test
    public void getName() {
    }

    @Test
    public void setQuote() {
    }

    @Test
    public void says() {
    }

    @Test
    public void setImage() {
    }

    @Test
    public void getImage() {
    }

    @Test
    public void setLocation() {
    }

    @Test
    public void getLocation() {
    }

    @Test
    public void setStrength() {
    }

    @Test
    public void getStrength() {
    }

    @Test
    public void setHealth() {
    }

    @Test
    public void getHealth() {
    }

    @Test
    public void setPowers() {
    }

    @Test
    public void getPowers() {
    }
}