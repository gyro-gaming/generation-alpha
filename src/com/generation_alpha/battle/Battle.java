package com.generation_alpha.battle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generation_alpha.characters.Gyro;
import com.generation_alpha.characters.Villain;
import com.generation_alpha.client.Display;
import com.generation_alpha.client.GameBoard;
import com.generation_alpha.items.PowerItem;
import com.generation_alpha.locations.Structure;

import java.util.List;
import java.util.Map;

public class Battle {
    private GameBoard gameBoard;
    private Gyro gyro;
    private Villain villain;
    private boolean usePower;
    private PowerItem power;

    /**
     * constructor
     * @param gameBoard
     * @param gyro
     * @param villain
     */
    public Battle(GameBoard gameBoard, Gyro gyro, Villain villain) {
        setGameBoard(gameBoard);
        setGyro(gyro);
        setVillain(villain);
        setUsePower(false);
    }

    /**
     * overload constructor
     * @param gameBoard
     * @param gyro
     * @param villain
     * @param usePower
     * @param power
     */
    public Battle(GameBoard gameBoard, Gyro gyro, Villain villain, boolean usePower, PowerItem power) {
        setGameBoard(gameBoard);
        setGyro(gyro);
        setVillain(villain);
        setUsePower(usePower);
        setPower(power);
    }

    /**
     * setter
     * @param board
     */
    public void setGameBoard(GameBoard board) {
        this.gameBoard = board;
    }

    /**
     * getter
     * @return GameBoard
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * setter
     * @param gyro
     */
    public void setGyro(Gyro gyro) {
        this.gyro = gyro;
    }

    /**
     * getter
     * @return Gyro
     */
    public Gyro getGyro() {
        return gyro;
    }

    /**
     * setter
     * @param villain
     */
    public void setVillain(Villain villain) {
        this.villain = villain;
    }

    /**
     * setter
     * @param usePower
     */
    public void setUsePower(boolean usePower) {
        this.usePower = usePower;
    }

    /**
     * setter
     * @param power
     */
    public void setPower(PowerItem power) {
        this.power = power;
    }

    /**
     * method to randomly move the Villain Character DOWN or LEFT
     * @return FightMovement
     */
    public FightMovement moveVillain() {
        int move = randomMove();
        if (move == 0) {
            return FightMovement.LEFT;
        } else {
            return FightMovement.DOWN;
        }
    }

    /**
     * method to randomly move Gyro Character UP or RIGHT
     * @param moveGyro
     * @return String
     */
    public String moveGyro(FightMovement moveGyro) {
        FightMovement moveVillain = moveVillain();
        StringBuilder sb = new StringBuilder();
        if (moveVillain.equals(FightMovement.LEFT) && moveGyro.equals(FightMovement.UP)
                || moveVillain.equals(FightMovement.DOWN) && moveGyro.equals(FightMovement.RIGHT)) {
            String result = fight();
            sb.append(result);
        } else {
            String result = powerFight();
            sb.append(result);
        }
        return sb.toString();
    }

    /**
     * method to randomly decide the outcome of a fight between the
     * Villain Character and the Gyro Character
     * @return String
     */
    private String fight() {
        int gyroStrength = gyro.getStrength();
        int villainStrength = villain.getStrength();
        int priorGyroHealth = gyro.getHealth();
        int priorVillainHealth = villain.getHealth();

        villain.setHealth(villain.getHealth() - ((gyroStrength * randomResult()) + villainStrength));
        gyro.setHealth(gyro.getHealth() - ((villainStrength * randomResult()) + gyroStrength));

        int afterGyroHealth = gyro.getHealth();
        int afterVillainHealth = villain.getHealth();

        StringBuilder sb = new StringBuilder();
        sb.append("Face-to-Face Combat\n");
        if (afterGyroHealth <= 0) {
            Display.gameEnd();
            GameBoard.forQuit();
        } else if (afterVillainHealth <= 0) {
            Display.showVictory();
            sb.append("You defeated " + villain.getName() + "\n");
            sb.append("You sustained " + (priorGyroHealth - afterGyroHealth)  + " damage.\n");
            sb.append("Your current health level is: " + afterGyroHealth + "\n");
            if (villain.getPowers().size() > 0) {
                for (int i = 0; i < villain.getPowers().size(); i++) {
                    gyro.addPower(villain.getPowers().remove(i));
                }
            }
            removeVillain();
            nextLevel(gameBoard);
        } else {
            sb.append("You fought " + villain.getName() + "\n");
            sb.append("You sustained " + (priorGyroHealth - afterGyroHealth)  + " damage.\n");
            sb.append("Your current health level is: " + afterGyroHealth + "\n");
            sb.append(villain.getName() + " sustained " + (priorVillainHealth - afterVillainHealth)
                    + " damage.\n");
            sb.append(villain.getName() + "'s current health level is: " + afterVillainHealth+ "\n");
        }
        return sb.toString();
    }

    /**
     * method to determine if the Villain Character randomly uses a random
     * PowerItem to attack the Gyro Character and if the Gyro Character attacks
     * the Villain Character with a PowerItem
     * @return String
     */
    private String powerFight() {
        StringBuilder sb = new StringBuilder();
        PowerItem villainPower;
        int priorGyroHealth = gyro.getHealth();
        int priorVillainHealth = villain.getHealth();

        sb.append("Powers Combat\n");
        if (villain.getPowers().size() > 0) {
            if (randomMove() == 0) {
                villainPower = villain.getPowers().get(villain.getPowers().size() - 1);
                int powerMultiplier = villainPower.getCombatMultiplier();
                sb.append(villain.getName() + " used " + villainPower.getName() + " that takes "
                        + powerMultiplier +  " power.\n");
                gyro.setHealth(gyro.getHealth() - powerMultiplier);
                int afterGyroHealth = gyro.getHealth();
                if (afterGyroHealth <= 0) {
                    Display.gameEnd();
                    GameBoard.forQuit();
                } else {
                    sb.append("You sustained " + (priorGyroHealth - afterGyroHealth)  + " damage.\n");
                    sb.append("Your current health level is: " + afterGyroHealth + "\n");
                }
                List<PowerItem> powers = villain.getPowers();
                for (int i = 0; i < powers.size(); i++) {
                    if (powers.get(i).getName().equals(villainPower.getName())) {
                        villain.usePower(powers.get(i));
                    }
                }
            }
        } else {
            sb.append(villain.getName() + " did not use a power.\n");
        }
        if (usePower) {
            int powerMultiplier = power.getCombatMultiplier();
            villain.setHealth(villain.getHealth() - powerMultiplier);
            int afterVillainHealth = villain.getHealth();
            sb.append(gyro.getName() + " used " + power.getName() + " that takes "
                    + powerMultiplier +  " power.\n");
            if (afterVillainHealth <= 0) {
                Display.showVictory();
                sb.append("You defeated " + villain.getName() + "\n");
                if (villain.getPowers().size() > 0) {
                    for (int i = 0; i < villain.getPowers().size(); i++) {
                        gyro.addPower(villain.getPowers().remove(i));
                    }
                }
                removeVillain();
                nextLevel(gameBoard);
            } else {
                sb.append(villain.getName() + " sustained " + (priorVillainHealth - afterVillainHealth)
                        + " damage.\n");
                sb.append(villain.getName() + "'s current health level is: " + afterVillainHealth+ "\n");
            }
            List<PowerItem> powers = gyro.getPowers();
            for (int i = 0; i < powers.size(); i++) {
                if (powers.get(i).getName().equals(power.getName())) {
                    gyro.usePower(powers.get(i));
                }
            }
        } else {
            sb.append("You did not use a power.\n");
        }
        return sb.toString();
    }

    /**
     * method to remove the Villain Character from the Location if the
     * Villain Character dies
     */
    private void removeVillain() {
        ObjectMapper mapper = new ObjectMapper();
        Gyro gyro = getGameBoard().getGyro();
        Structure location = gyro.getLocation();

        Map<String, Object> map = mapper.convertValue(location, Map.class);
        map.remove("character");
        Structure newStructure = getGameBoard().getGamePlay().removeKilledVillainFromStructure(map, location);
        gyro.setLocation(newStructure);

        List<Structure> locations = getGameBoard().getTerritory().getLocations();
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getName().equals(newStructure.getName())) {
                locations.remove(i);
                locations.add(newStructure);
            }
        }
    }

    /**
     * get random int between 0 and 4
     * @return int
     */
    private int randomResult() {
        return (int)(Math.random() * 5);
    }

    /**
     * get random int between 0 or 1
     * @return int
     */
    private int randomMove() {
        return (int)Math.round(Math.random());
    }

    public void nextLevel(GameBoard gameBoard) {
        String[] levels = {"Master Yamamoto", "Troll", "Talon", "Hunter X", "Home"};
        String name = gameBoard.getTerritory().getName();
        try {
            for (int i = 0; i < levels.length; i++) {
                if (name.equalsIgnoreCase(levels[i])) {
                    name = levels[i - 1];
                    break;
                }
            }
        } catch (NullPointerException e) {
            Display.showVictory();
            Display.gameEnd();
            GameBoard.forQuit();
        }
        gameBoard.setTerritory(gameBoard.getGamePlay().getTerritory(name));
    }
}
