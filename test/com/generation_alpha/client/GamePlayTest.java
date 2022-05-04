package com.generation_alpha.client;

import com.generation_alpha.locations.GamePlay;
import com.generation_alpha.locations.Territory;
import org.junit.Before;
import org.junit.Test;

public class GamePlayTest {
    GamePlay gamePlay;

    @Before
    public void init() {
        gamePlay = new GamePlay();
    }

    @Test
    public void getTerritory() {
        Territory territory = gamePlay.getTerritory("Home");
        System.out.println(territory.getLocations());
    }

    @Test
    public void getStructure() {
    }

    @Test
    public void getCharacters() {
    }

    @Test
    public void getItems() {
    }
}