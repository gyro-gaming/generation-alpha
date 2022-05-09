package com.generation_alpha.locations;

import org.junit.Before;
import org.junit.Test;

public class GamePlayTest {
    GamePlay gamePlay;
    Territory territory;

    @Before
    public void init() {
        gamePlay = new GamePlay();
    }

    @Test
    public void getTerritory() {
        territory = gamePlay.getTerritory("Home");
        System.out.println(territory.getLocations().get(0).getName());
    }

    @Test
    public void getTerritoryOverload() {
        territory = gamePlay.getTerritory(true);
        //System.out.println(territory.getLocations().get(0).getMap());
    }

    @Test
    public void getStart() {
    }

    @Test
    public void getCharacters() {
    }

    @Test
    public void getPowers() {
    }

    @Test
    public void getItems() {
    }

    @Test
    public void convertMap() {
    }

    @Test
    public void moveGyro() {
    }

    @Test
    public void getLocation() {
    }

    @Test
    public void removeItemFromStructure() {
    }

    @Test
    public void removeKilledVillainFromStructure() {

    }

    @Test
    public void getDirection() {

    }
}