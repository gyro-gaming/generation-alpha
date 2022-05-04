package com.generation_alpha.battle;

import com.generation_alpha.characters.Gyro;
import com.generation_alpha.characters.Villain;
import com.generation_alpha.items.PowerItem;

class Battle {
    private Gyro gyro;
    private Villain villain;
    private boolean usePower;
    private PowerItem power;

    public Battle(Gyro gyro, Villain villain, boolean usePower, PowerItem power) {
        setGyro(gyro);
        setVillain(villain);
        setUsePower(usePower);
        setPower(power);
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

    public Villain getVillain() {
        return villain;
    }

    public void setUsePower(boolean usePower) {
        this.usePower = usePower;
    }

    public boolean getUsePower() {
        return usePower;
    }

    public void setPower(PowerItem power) {
        this.power = power;
    }

    public PowerItem getPower() {
        return power;
    }

    public FightMovement moveVillain() {
        int move = randomMove();
        if (move == 0) {
            return FightMovement.LEFT;
        } else {
            return FightMovement.DOWN;
        }
    }

    public void moveGyro(FightMovement moveGyro) {
        FightMovement moveVillain = moveVillain();
        if (moveVillain.equals(FightMovement.LEFT) && moveGyro.equals(FightMovement.UP)
                || moveVillain.equals(FightMovement.DOWN) && moveGyro.equals(FightMovement.RIGHT)) {
            fight();
        } else {
            powerFight();
        }
    }

    private String fight() {
        int gyroStrength = gyro.getStrength();
        int villainStrength = villain.getStrength();
        int priorGyroHealth = gyro.getHealth();
        int priorVillainHealth = villain.getHealth();

        villain.setHealth(villain.getHealth() - ((gyroStrength * randomResult()) / villainStrength));
        gyro.setHealth(gyro.getHealth() - ((villainStrength * randomResult()) / gyroStrength));

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
        } else {
            sb.append("You fought " + villain.getName() + "\n");
            sb.append("You sustained " + (priorGyroHealth - afterGyroHealth)  + " damage.\n");
            sb.append("Your current health level is: " + afterGyroHealth);
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
                villainPower = villain.getPowers().get(villain.getPowers().size());
                int powerMultiplier = villainPower.getCombatMultiplier();
                sb.append(villain.getName() + " used " + villainPower.getName() + " that takes "
                        + powerMultiplier +  " power.\n");
                gyro.setHealth(gyro.getHealth() - powerMultiplier);
                int afterGyroHealth = gyro.getHealth();
                if (afterGyroHealth <= 0) {
                    return "Game Over\n\n";
                } else {
                    sb.append("You sustained " + (priorGyroHealth - afterGyroHealth)  + " damage.\n");
                    sb.append("Your current health level is: " + afterGyroHealth);
                }
                villain.usePower(villainPower);
            }
        }
        if (usePower) {
            int powerMultiplier = power.getCombatMultiplier();
            villain.setHealth(villain.getHealth() - powerMultiplier);
            int afterVillainHealth = villain.getHealth();
            if (afterVillainHealth <= 0) {
                sb.append("You won!\n");
                sb.append("You defeated " + villain.getName() + "\n");
            } else {
                sb.append(villain.getName() + " sustained " + (priorVillainHealth - afterVillainHealth)
                        + " damage.\n");
                sb.append(villain.getName() + "'s current health level is: " + afterVillainHealth+ "\n");
            }
            gyro.usePower(power);
        }

        return sb.toString();
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
