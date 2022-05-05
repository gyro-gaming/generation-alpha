package com.generation_alpha.battle;

import com.generation_alpha.characters.Gyro;
import com.generation_alpha.characters.Villain;
import com.generation_alpha.client.GameBoard;
import com.generation_alpha.items.Item;
import com.generation_alpha.items.PowerItem;
import com.generation_alpha.locations.Location;
import com.generation_alpha.locations.Structure;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BattleTest {
    GameBoard gameBoard;
    Gyro gyro;
    Battle battle;

    @Before
    public void setUp() {
        gameBoard = new GameBoard("Jamal");
        gyro = gameBoard.getGyro();
    }

    @Test
    public void testMoveVillain() {
    }

    @Test
    public void testMoveGyro() {
        Item power1 = gameBoard.getGamePlay().getItems("power1");
        Item power2 = gameBoard.getGamePlay().getItems("power2");
        Item power3 = gameBoard.getGamePlay().getItems("power3");
        gyro.addPower((PowerItem) power3);
        List<Structure> locations = gameBoard.getTerritory().getLocations();
        for (Structure location : locations) {
            if (location.getName().toString().equals("Sand Lot")) {
                gyro.setLocation(location);
            }
        }
        Villain bully = (Villain)gameBoard.getGamePlay().getCharacters(gameBoard.getGyro().getLocation().getCharacter().getName());
        System.out.println(bully.getPowers());
        battle = new Battle(gyro, bully, true, (PowerItem) power3 );
        System.out.println(battle.moveGyro(FightMovement.UP));
        System.out.println(battle.moveGyro(FightMovement.UP));
        System.out.println(battle.moveGyro(FightMovement.UP));
        System.out.println(battle.moveGyro(FightMovement.UP));
        System.out.println(battle.moveGyro(FightMovement.UP));
        System.out.println(battle.moveGyro(FightMovement.UP));
        System.out.println(battle.moveGyro(FightMovement.UP));
        System.out.println(battle.moveGyro(FightMovement.UP));
        System.out.println(battle.moveGyro(FightMovement.UP));
        System.out.println(battle.moveGyro(FightMovement.UP));
        System.out.println(gyro.getPowers());
    }
}