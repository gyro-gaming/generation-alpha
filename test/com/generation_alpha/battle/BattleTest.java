package com.generation_alpha.battle;

import com.generation_alpha.characters.Gyro;
import com.generation_alpha.characters.Villain;
import com.generation_alpha.client.GameBoard;
import com.generation_alpha.items.Item;
import com.generation_alpha.items.PowerItem;
import com.generation_alpha.locations.Location;
import com.generation_alpha.locations.Structure;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BattleTest {
    GameBoard gameBoard;
    Gyro gyro;
    Villain villain;
    Battle battle;

    @Before
    public void init() {
        gameBoard = GameBoard.getInstance();
        gameBoard.init("Jamal");
        gyro = gameBoard.getGyro();
        List<Structure> locations = gameBoard.getTerritory().getLocations();
        for (Structure location : locations) {
            if (location.getName().equals("Sand Lot")) {
                gyro.setLocation(location);
            }
        }
        villain = (Villain)gameBoard.getGamePlay().getCharacters(gameBoard.getGyro().getLocation().getCharacter().getName());

    }

    @Test
    public void testMoveVillainAssertsThatIsFightMovementLeftOrDown() {
        battle = new Battle(gameBoard, gyro, villain);
        assertThat(battle.moveVillain(), anyOf(is(FightMovement.DOWN), is(FightMovement.LEFT)));
    }

    @Test
    public void testMoveVillainAssertsThatIsNotFightMovementRightOrUp() {
        battle = new Battle(gameBoard, gyro, villain);
        assertThat(battle.moveVillain(), not(anyOf(is(FightMovement.UP), is(FightMovement.RIGHT))));
    }

    @Test
    public void testMoveGyroAssertsThatReturnString() {
        Item power3 = gameBoard.getGamePlay().getItems("power3");
        gyro.addPower((PowerItem) power3);

        battle = new Battle(gameBoard, gyro, villain, true, (PowerItem) power3);
        assertThat(battle.moveGyro(FightMovement.UP), notNullValue());
    }

    @Test
    public void nextLevel() {
        battle = new Battle(gameBoard, gyro, villain);
        battle.nextLevel(gameBoard);
        System.out.println(gameBoard.getTerritory().getName());
    }
}