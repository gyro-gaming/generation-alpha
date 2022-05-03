package com.generation_alpha.client;

import com.generation_alpha.characters.Gyro;
import com.generation_alpha.items.HealthBoost;
import com.generation_alpha.items.Item;
import com.generation_alpha.items.PowerItem;
import com.generation_alpha.items.StrengthBoost;
import com.generation_alpha.locations.GamePlay;
import com.generation_alpha.locations.Location;
import com.generation_alpha.locations.Structure;

class Get {
    public static String forGet(GameBoard gameBoard, String name) {
        GamePlay gamePlay = gameBoard.getGamePlay();
        Gyro gyro = gameBoard.getGyro();
        Structure location = gyro.getLocation();

        if (name.equals("null")) {
            return "There is no item at this location.";
        }
        Item item = gamePlay.getItems(name);
        System.out.println(item);
        if (item instanceof StrengthBoost || item instanceof HealthBoost) {
            gyro.addItem(item);
            location.setItem(new StrengthBoost("null"));
        } else if (item instanceof PowerItem) {
            gyro.addPower((PowerItem)item);
            location.setItem(new PowerItem("null"));
        }
        return String.format("%s was added to %s's bag.", item.getName(), gyro.getName());
    }
}
