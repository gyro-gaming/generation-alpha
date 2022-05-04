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

    private void fight() {
        int gyroStrength = gyro.getStrength();
        int villainStrength = villain.getStrength();

        villain.setHealth(villain.getHealth() - ((gyroStrength * randomResult()) / villainStrength));
        gyro.setHealth(gyro.getHealth() - ((villainStrength * randomResult()) / gyroStrength));
    }

    private void powerFight() {

    }

    private int randomResult() {
        return (int)(Math.random() * 5);
    }

    private int randomMove() {
        return (int)Math.round(Math.random());
    }
}
