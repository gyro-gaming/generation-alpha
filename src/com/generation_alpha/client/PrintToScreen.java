package com.generation_alpha.client;

import com.generation_alpha.characters.Gyro;
import com.generation_alpha.characters.Villain;

class PrintToScreen {
    public static void getCurrentLocation(GameBoard gameBoard) {
        System.out.println("You are now in " + gameBoard.getGyro().getLocation().getName());
        try {
            if (gameBoard.getGyro().getLocation().getCharacter().getName().equals("null")) {
                System.out.println("Would you like to ask " + gameBoard.getGyro().getLocation().getCharacter().getName() + " a question?");
            }
        } catch (NullPointerException e) {
        }
        try {
            if (gameBoard.getGyro().getLocation().getItem().getName().equals("null")) {
                System.out.println("There is also a " + gameBoard.getGyro().getLocation().getItem().getName() + " in the room.\n");
            }
        } catch (NullPointerException e) {
        }
    }

    public static void getHealth(GameBoard gameBoard) {
        Villain villain = (Villain) gameBoard.getGyro().getLocation().getCharacter();
        int villainHealth = villain.getHealth();
        int villainStrength = villain.getStrength();
        Gyro gyro = gameBoard.getGyro();
        int gyroHealth = gyro.getHealth();
        int gyroStrength = gyro.getStrength();
        System.out.println("Your health: " + gyroHealth);
        System.out.println("Your Strength: " + gyroStrength);
        System.out.println(gameBoard.getGyro().getLocation().getCharacter().getName() + "'s health: " + villainHealth);
        System.out.println(gameBoard.getGyro().getLocation().getCharacter().getName() + "'s Strength: " + villainStrength);
    }
}