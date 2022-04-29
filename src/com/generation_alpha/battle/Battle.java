package com.generation_alpha.battle;

import com.generation_alpha.characters.Fighter;
import com.generation_alpha.characters.Gyro;
import com.generation_alpha.characters.Villain;

class Battle {
    private Gyro gyro;
    private Villain villain;
    private Fighter[][] fightMap = new Fighter[2][2];


    public void setGyro(Gyro gyro) {
        fightMap[0][1] = gyro;
        this.gyro = gyro;
    }

    public void setVillain(Villain villain) {
        fightMap[1][0] = villain;
        this.villain = villain;
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
        }

    }


    private int randomMove() {
        return (int)Math.random() + 1;
    }
}
