package com.generation_alpha.characters;

import com.generation_alpha.client.GameBoard;
import com.generation_alpha.client.JsonParser;
import com.generation_alpha.items.Item;
import com.generation_alpha.items.PowerItem;
import com.generation_alpha.locations.GamePlay;
import com.generation_alpha.locations.Structure;
import com.generation_alpha.locations.Territory;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GyroTest {
    Gyro gyro;
    GameBoard gameBoard;
    GamePlay gamePlay;
    List<Structure> locations;

    @Before
    public void init() {
        gyro = Gyro.getInstance();
        gameBoard = GameBoard.getInstance();
        gamePlay = new GamePlay();
        locations = gameBoard.getTerritory().getLocations();

    }

    @Test
    public void addPower() {
        Item power = gamePlay.getItems("power3");
        gyro.addPower((PowerItem) power);
        assertEquals(gyro.getPowers().size(), 1);
    }

    @Test
    public void usePower() {
        Item power = gamePlay.getItems("power3");
        gyro.addPower((PowerItem) power);
        gyro.usePower((PowerItem) power);
        assertEquals(gyro.getPowers().size(), 0);
    }

    @Test
    public void addItem() {
        Item item = gamePlay.getItems("strength1");
        gyro.addItem(item);
        assertEquals(gyro.getItems().size(), 1);
    }

    @Test
    public void useItem() {
        Item item = gamePlay.getItems("strength1");
        gyro.addItem(item);
        gyro.useItem(item);
        assertEquals(gyro.getItems().size(), 0);
    }

    @Test
    public void forGo() {
        Structure newStructure = Gyro.forGo(gameBoard, "north");

        Structure gym = gamePlay.getLocation("gy")
    }

    @Test
    public void forLook() {

    }

    @Test
    public void forGet() {

    }

    @Test
    public void forInspect() {

    }

    @Test
    public void forAsk() {

    }

    @Test
    public void forUse() {

    }
}