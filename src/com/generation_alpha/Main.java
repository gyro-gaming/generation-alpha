package com.generation_alpha;

import com.generation_alpha.characters.Gyro;
import com.generation_alpha.client.TextParser;
import com.generation_alpha.locations.*;

class Main {
    public static void main(String[] args) {
        TextParser textParser = new TextParser();
        GamePlay gamePlay = new GamePlay();
        Gyro gyro = new Gyro();
        Territory territory = gamePlay.getTerritory("Home");
        int index = gamePlay.moveGyro(gyro, "Gym", territory.getLocations());
        System.out.println(gyro.getLocation().getName());
        System.out.println(territory.getLocations().get(index).getCharacter().getName());
        System.out.println(territory.getLocations().get(index).getItem().getName());
        // System.out.println(territory.getLocations());
    }
}
