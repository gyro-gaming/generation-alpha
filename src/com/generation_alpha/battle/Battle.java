package com.generation_alpha.battle;

import com.generation_alpha.characters.Fighter;
import com.generation_alpha.characters.Gyro;
import com.generation_alpha.characters.Villain;

class Battle {
    private Gyro gyro;
    private Villain villain;

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
            // fight
        } else {
            // if powers are used...
        }

    }


    private int randomMove() {
        return (int)Math.random() + 1;
    }
}
