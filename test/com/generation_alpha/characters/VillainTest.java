package com.generation_alpha.characters;

import com.generation_alpha.items.Item;
import com.generation_alpha.items.PowerItem;
import com.generation_alpha.locations.GamePlay;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class VillainTest {
    Villain villain;
    GamePlay gamePlay;

    @Before
    public void init() {
        gamePlay = new GamePlay();
        Character character = gamePlay.getCharacters("Bully");
        villain = (Villain) character;
    }

    @Test
    public void usePowerBuildPowerItemAndRemoveFromPowersListInValid() {
        Item power = gamePlay.getItems("power2");
        List<PowerItem> powers = villain.getPowers();
        villain.usePower((PowerItem) power);
        assertNotEquals(villain.getPowers().size(), 1);
    }

    @Test
    public void usePowerBuildPowerItemAndRemoveFromPowersListValid() {
        Item power = gamePlay.getItems("power2");
        List<PowerItem> powers = villain.getPowers();
        for (int i = 0; i < powers.size(); i++) {
            if (powers.get(i).getName().equals(power.getName())) {
                power = powers.get(i);
            }
        }
        villain.usePower((PowerItem) power);
        assertEquals(villain.getPowers().size(), 1);
    }
}