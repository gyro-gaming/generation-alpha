package com.generation_alpha.battle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generation_alpha.characters.Gyro;
import com.generation_alpha.characters.Villain;
import com.generation_alpha.client.GameBoard;
import com.generation_alpha.items.PowerItem;
import com.generation_alpha.locations.Structure;

import java.util.List;
import java.util.Map;

public class Battle {
    private GameBoard board;
    private Gyro gyro;
    private Villain villain;
    private boolean usePower;
    private PowerItem power;

    public Battle(GameBoard gameBoard, Gyro gyro, Villain villain) {
        setGameBoard(gameBoard);
        setGyro(gyro);
        setVillain(villain);
        setUsePower(false);
    }

    public Battle(GameBoard gameBoard, Gyro gyro, Villain villain, boolean usePower, PowerItem power) {
        setGameBoard(gameBoard);
        setGyro(gyro);
        setVillain(villain);
        setUsePower(usePower);
        setPower(power);
    }

    public void setGameBoard(GameBoard board) {
        this.board = board;
    }

    public GameBoard getGameBoard() {
        return board;
    }

    public void setGyro(Gyro gyro) {
        this.gyro = gyro;
    }

    public Gyro getGyro() {
        return gyro;
    }

    public void setVillain(Villain villain) {
        this.villain = villain;
    }

    public void setUsePower(boolean usePower) {
        this.usePower = usePower;
    }

    public void setPower(PowerItem power) {
        this.power = power;
    }

    public FightMovement moveVillain() {
        int move = randomMove();
        if (move == 0) {
            return FightMovement.LEFT;
        } else {
            return FightMovement.DOWN;
        }
    }

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
            return "Game Over\n\n";
        } else if (afterVillainHealth <= 0) {
            sb.append("You won!\n");
            sb.append("You defeated " + villain.getName() + "\n");
            sb.append("You sustained " + (priorGyroHealth - afterGyroHealth)  + " damage.\n");
            sb.append("Your current health level is: " + afterGyroHealth + "\n");
            if (villain.getPowers().size() > 0) {
                for (int i = 0; i < villain.getPowers().size(); i++) {
                    gyro.addPower(villain.getPowers().remove(i));
                }
            }
            removeVillain();
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
                    return "Game Over\n\n";
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
                sb.append("You won!\n");
                sb.append("You defeated " + villain.getName() + "\n");
                if (villain.getPowers().size() > 0) {
                    for (int i = 0; i < villain.getPowers().size(); i++) {
                        gyro.addPower(villain.getPowers().remove(i));
                    }
                }
                removeVillain();
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

    private int randomResult() {
        return (int)(Math.random() * 5);
    }

    private int randomResult(int multiplier) {
        return (int)(Math.random() * multiplier);
    }

    private int randomMove() {
        return (int)Math.round(Math.random());
    }
}
