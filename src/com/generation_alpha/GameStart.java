package com.generation_alpha;

import com.generation_alpha.characters.Gyro;

public class GameStart {

    public void execute() {
        Display display = new Display();
        display.hello();
        Gyro gyro = new Gyro();
        gyro.setName("");
        display.showVictory();
        display.proceed();
        display.gameEnd();
    }
}
